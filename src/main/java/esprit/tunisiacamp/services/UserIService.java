package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.enums.State;

public interface UserIService {
    void addUser(User user);
    User FindById(long id);
    void updateUser(User user);
    void deleteUser(long id, State state);
}
