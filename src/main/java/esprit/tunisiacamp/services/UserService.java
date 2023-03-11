package esprit.tunisiacamp.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import esprit.tunisiacamp.entities.Role;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.enums.Provider;
import esprit.tunisiacamp.entities.enums.State;
import esprit.tunisiacamp.entities.enums.role;
import esprit.tunisiacamp.repositories.AutorityRepository;
import esprit.tunisiacamp.repositories.RoleRepository;
import esprit.tunisiacamp.repositories.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class UserService implements UserIService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    AutorityRepository autorityRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User FindById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id, State state) {
        userRepository.deleteUser(id,state);
    }

    @Override
    public void affecterUserToRole(Integer idUser, long idRole) {
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
        String pwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(pwd);
        user.setProdiver(Provider.Local);

        userRepository.save(user);
        //User u = userRepository.findByEmail(user.getEmail()).get();
        //Autority a = new Autority();
        //a.setUserAuth(u);
        //a.setName(u.getRole().toString());
        //autorityRepository.save(a);
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
            //String randomCode = RandomString.make(10);
            String pwd = passwordEncoder.encode("camping");
            newUser.setPassword(pwd);
            newUser.setProdiver(Provider.GOOGLE);
            newUser.setEnable(true);
            newUser.setRole1(role.CAMPER);

            userRepository.save(newUser);
/*
            String toAddress = newUser.getEmail();
            String fromAddress = "benabdallah.jalel@esprit.tn";
            String senderName = "Camping";
            String subject = "Your Password ";
            String content = "Dear [[name]],<br>"
                    + "your password code is :<br>" +randomCode
                    + "Thank you,<br>"
                    + "Camping.";

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);

            content = content.replace("[[name]]", newUser.getUsername());
            //String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

            //content = content.replace("[[URL]]");

            //helper.setText(content, true);

            mailSender.send(message);*/
        }

    }
    private final String ACCOUNT_SID = "AC40a2bf2c3b42a8ca159c39d88298e173";
    private final String AUTH_TOKEN = "5cd0d08a1668034921f512ba9d316c75";
    // private final String FROM_NUMBER = "+14302492629";
    @Override
    public void sendSms(String code) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21692108297"),
                new com.twilio.type.PhoneNumber("+14302492629"),
                "Reminder: Your code is "+code).create();

        System.out.println(message.getSid());
    }

    @Override
    public void resetPassword(String email) {
        User user = userRepository.getUserByUsername(email);
        String randomCode = RandomString.make(6);
        user.setVerifiepwd(randomCode);
        userRepository.save(user);
        sendSms(randomCode);


    }

    @Override
    public String verifiePwd(String code, String pwd) {
        User user = userRepository.getUserByVerifiepwd(code);
        if(user!=null){
            user.setPassword(pwd);
            user.setVerifiepwd(null);
            userRepository.save(user);
            return "valide";
        }
        return "Verifie your code";
    }
}
