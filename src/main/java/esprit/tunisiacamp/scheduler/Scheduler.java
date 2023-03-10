package esprit.tunisiacamp.scheduler;

import esprit.tunisiacamp.entities.Autority;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.repositories.AutorityRepository;
import esprit.tunisiacamp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
@Controller
public class Scheduler {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AutorityRepository autorityRepository;
    //@Scheduled(cron = "* * * * * *")
    public void affecterUseraAutority(){
        List<User> users = (List<User>) userRepository.findAll();
        List<Autority> autorities = (List<Autority>) autorityRepository.findAll();
        for(User u : users){
            Autority au = autorityRepository.getAutority(u);
                    if(au==null){
                        Autority a = new Autority();
                        a.setUserAuth(u);
                        a.setName(u.getRole().toString());
                        autorityRepository.save(a);
                    }

        }
    }
}
