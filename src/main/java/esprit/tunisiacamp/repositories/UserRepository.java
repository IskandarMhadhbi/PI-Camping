package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.Autority;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.enums.State;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    @Transactional
    @Modifying
    @Query("update User  set enable=false  where idUser=?1")
    void deleteUser(Integer id, State state);
    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    public User findByVerificationCode(String code);

    @Query("SELECT u FROM User u WHERE u.email = :username")
    public User getUserByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u where u.verifiepwd=:code")
    public User getUserByVerifiepwd(@Param("code") String code);

    @Query("SELECT u FROM User u WHERE u.verifiepwd=:cd")
    public User getUserCD(@Param("cd") String code);

    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);



    
}
