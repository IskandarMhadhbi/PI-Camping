package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.camping.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RateRepository extends CrudRepository<Rate, Long> {
}