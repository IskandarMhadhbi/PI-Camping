package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.Role;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.enums.Provider;
import esprit.tunisiacamp.entities.enums.State;
import esprit.tunisiacamp.repositories.RoleRepository;
import esprit.tunisiacamp.repositories.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Set;

@Service
public class UserService implements UserIService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    JavaMailSender mailSender;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User FindById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long id, State state) {
        userRepository.deleteUser(id,state);
    }

    @Override
    public void affecterUserToRole(long idUser, long idRole) {
        Role role = roleRepository.findById(idRole).get();
        User user = userRepository.findById(idUser).get();
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public void register(User user, String siteURL) throws UnsupportedEncodingException, MessagingException {

        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setEnable(false);

        userRepository.save(user);

        sendVerificationEmail(user, siteURL);
    }

    @Override
    public void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "benabdallah.jalel@esprit.tn";
        String senderName = "Camping";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Camping.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getUsername());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @Override
    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);

        if (user == null || user.isEnable()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnable(true);
            userRepository.save(user);

            return true;
        }
    }

    public void processOAuthPostLogin(String email) {
        User existUser = userRepository.getUserByUsername(email);

        if (existUser == null) {
            User newUser = new User();
            newUser.setEmail(email);
            //newUser.setUsername(name);
            newUser.setProdiver(Provider.GOOGLE);
            newUser.setEnable(true);

            userRepository.save(newUser);
        }

    }
}
