package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.enums.State;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    @Transactional
    @Modifying
    @Query("update User  set state=?2 where idUser=?1")
    void deleteUser(long id, State state);
    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    public User findByVerificationCode(String code);

    @Query("SELECT u FROM User u WHERE u.email = :username")
    public User getUserByUsername(@Param("username") String username);
    
}
