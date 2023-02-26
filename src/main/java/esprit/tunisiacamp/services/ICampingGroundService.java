package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.camping.CampingGround;

import java.util.List;
import java.util.Optional;

public interface ICampingGroundService {
    CampingGround ajouterCamping(CampingGround campingGrounds);
    List<CampingGround> getAllCampingGrounds();
    Optional<CampingGround> getCampingGroundById(long id);
    void deleteCampingGroundById(long id);
    public String affecterCampingAuAdvantages(Long IdCamping,Long IdAdvantages);
}
