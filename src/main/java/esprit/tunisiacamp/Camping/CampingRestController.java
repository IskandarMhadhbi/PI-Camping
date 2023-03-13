package esprit.tunisiacamp.Camping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CampingRestController {
    @Autowired
    CampingIService campingIService;
    @CrossOrigin("http://localhost:4200")
    @GetMapping("/AllCamp")
    public List<Camping> listCamping(){
        List<Camping> camp = campingIService.AllCamp();
        return  camp;
    }

    @PostMapping("/addCamping")
    public  void  addCamping(@RequestBody Camping c){
        campingIService.addCamping(c);
        //return null;
    }
}
