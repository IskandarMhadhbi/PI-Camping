package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.camping.CampingGround;
import esprit.tunisiacamp.services.ICampingGroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CampingGroundController {
    @Autowired
    private ICampingGroundService campingGroundService;

    @PostMapping("/addCamping")
    public CampingGround ajouterCamping(@org.springframework.web.bind.annotation.RequestBody CampingGround c) {

        return campingGroundService.ajouterCamping(c);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CampingGround> updateCampingGround(@PathVariable long id, @RequestBody CampingGround campingGround) {
        Optional<CampingGround> existingCampingGround = campingGroundService.getCampingGroundById(id);
        if (existingCampingGround.isPresent()) {
            campingGround.setIdCampingGround(id);
            return ResponseEntity.ok(campingGroundService.ajouterCamping(campingGround));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public List<CampingGround> getAllCampingGrounds() {
        return campingGroundService.getAllCampingGrounds();
    }

    @DeleteMapping("/{id}")
    public void  deleteCampingGround(@RequestParam long idCampingGround) {

        campingGroundService.deleteCampingGroundById(idCampingGround);

    }
    @PutMapping("AffecterCampingAdvantages")
    public String affecterCampingAuAdvantages(Long IdCamping, Long IdAdvantages){
        return campingGroundService.affecterCampingAuAdvantages(IdCamping,IdAdvantages);
    }

}
