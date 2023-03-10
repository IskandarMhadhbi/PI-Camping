package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.Claim;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends CrudRepository<Claim,Long> {
}
