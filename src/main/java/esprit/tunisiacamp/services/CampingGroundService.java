package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.camping.Advantage;
import esprit.tunisiacamp.entities.camping.CampingGround;
import esprit.tunisiacamp.repositories.AdvantagesRepositories;
import esprit.tunisiacamp.repositories.CampingGroundRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampingGroundService implements ICampingGroundService {
    @Autowired
    private CampingGroundRepositories campingGroundRepository;
    @Autowired
    private AdvantagesRepositories advantagesRepositories;

    @Override
    public CampingGround ajouterCamping(CampingGround campingGrounds) {
        return campingGroundRepository.save(campingGrounds);
    }

    @Override
    public List<CampingGround> getAllCampingGrounds() {
        return (List<CampingGround>) campingGroundRepository.findAll();
    }

    @Override
    public Optional<CampingGround> getCampingGroundById(long id) {
        return campingGroundRepository.findById(id);
    }

    @Override
    public void deleteCampingGroundById(long id) {
        campingGroundRepository.deleteById(id);
    }

    @Override
    public String affecterCampingAuAdvantages(Long IdCamping, Long IdAdvantages) {
        CampingGround cp = campingGroundRepository.findById(IdCamping).get();
        Advantage ad = advantagesRepositories.findById(IdAdvantages).get();
        cp.getAdvantages().add(ad);
        campingGroundRepository.save(cp);
        return null;
    }
}
