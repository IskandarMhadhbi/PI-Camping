package esprit.tunisiacamp.Camping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampingService implements CampingIService{

    @Autowired
    CampingRepository campingRepository;
    @Override
    public List<Camping> AllCamp() {
        return (List<Camping>) campingRepository.findAll();
    }

    @Override
    public void addCamping(Camping c) {
        campingRepository.save(c);
    }
}
