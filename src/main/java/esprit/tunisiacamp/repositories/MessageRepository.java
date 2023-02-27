package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.forum.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MessageRepository extends CrudRepository<Message, Long> {
}
