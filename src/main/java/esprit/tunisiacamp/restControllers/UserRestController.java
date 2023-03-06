package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.enums.State;
import esprit.tunisiacamp.services.UserIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

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
    @PutMapping("/affecterUserToRole")
    public void affecterUserToRole(long idUser , long idRole){
        userIService.affecterUserToRole(idUser,idRole);
    }

    @PostMapping("/process_register")
    public String processRegister(@RequestBody User user, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {
        userIService.register(user, getSiteURL(request));
        return "register_success";
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (userIService.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
/*
    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login?logout";
    }
*/
        }
