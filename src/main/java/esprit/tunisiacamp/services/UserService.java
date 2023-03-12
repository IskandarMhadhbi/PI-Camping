package esprit.tunisiacamp.services;


import esprit.tunisiacamp.model.Userr;

import java.util.HashSet;
import java.util.List;

public interface UserService {
    List<Userr> getall() ;

    Userr addUser(Userr userr) ;

    HashSet<Userr> getUserByUserName(String username)  ;
}
