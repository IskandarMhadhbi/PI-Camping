package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.camping.CampingGround;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampingGroundRepositories extends CrudRepository<CampingGround,Long> {
}
