package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.enums.State;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface UserIService {
    void addUser(User user);
    User FindById(long id);
    void updateUser(User user);
    void deleteUser(long id, State state);
    void affecterUserToRole(long idUser,long idRole);
    public void register(User user, String siteURL) throws UnsupportedEncodingException, MessagingException;
    void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException;
    public boolean verify(String verificationCode);
    public void processOAuthPostLogin(String username);
}
