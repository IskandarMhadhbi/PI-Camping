package esprit.tunisiacamp.Camping;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampingRepository extends CrudRepository<Camping,Integer> {
}
