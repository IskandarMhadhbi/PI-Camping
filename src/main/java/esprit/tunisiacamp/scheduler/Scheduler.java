package esprit.tunisiacamp.scheduler;

import esprit.tunisiacamp.entities.Autority;
import esprit.tunisiacamp.entities.Role;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.repositories.AutorityRepository;
import esprit.tunisiacamp.repositories.RoleRepository;
import esprit.tunisiacamp.repositories.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
public class Scheduler {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    AutorityRepository autorityRepository;
    @Autowired
    RoleRepository roleRepository;
    //@Scheduled(cron = "* * * * * *")
    public void affecterUseraAutority(){
        List<User> users = (List<User>) userRepository.findAll();
        List<Autority> autorities = (List<Autority>) autorityRepository.findAll();
        for(User u : users){
            Autority au = autorityRepository.getAutority(u);
                    if(au==null){
                        Autority a = new Autority();
                        a.setUserAuth(u);
                        a.setName(u.getRole1().toString());
                        autorityRepository.save(a);
                    }

        }
    }
    //@Scheduled(cron = "* * * * * * ")
    public void Notification(){
        List<User> users = (List<User>) userRepository.findAll();
        for(User u : users){
            if(u.isEnable() && u.getLastCnx().before(new Date())){
                //String random = RandomString.make(6);
                //u.setLastCnxCode(random);

            }
        }
    }
    //@Scheduled(cron = "* * * * * *")
    public void affecterUserARole(){
        List<User> users = (List<User>) userRepository.findAll();
        for (User u : users){
            System.out.println("gsfgdf");
            Role ro = roleRepository.getRole(u.getRole1());
            System.out.println("gsfgdfcghcvvjbjbjf");
            if (ro!=null){
                u.setRole(ro);
                userRepository.save(u);
            }
        }
    }



}
