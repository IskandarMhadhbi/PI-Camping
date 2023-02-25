package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.enums.State;
import esprit.tunisiacamp.services.UserIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRestController {
    @Autowired
    UserIService userIService;
    @PostMapping("/addUser")
    public void addUser(@RequestBody User user){
        userIService.addUser(user);
    }
    @GetMapping("/FindById")
    public User FindById(long id){
        return userIService.FindById(id);
    }
    @PutMapping("/updateUser")
    public void updateUser(@RequestBody User user){
        userIService.updateUser(user);
    }
    @PutMapping("/deleteOrDisableUser")
    public void deleteUser(long id, State state){
        userIService.deleteUser(id,state);
    }
}
