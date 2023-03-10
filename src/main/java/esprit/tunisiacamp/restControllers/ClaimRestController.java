package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.Claim;
import esprit.tunisiacamp.repositories.ClaimRepository;
import esprit.tunisiacamp.services.IClaimsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name="Manager of Claim")
public class ClaimRestController {
@Autowired
    IClaimsService iClaimsService;
@Autowired
    ClaimRepository claimRepository;

@PostMapping("/addclaim")
    public void addclaim(@RequestBody Claim c){
    iClaimsService.addclaim(c);
}
    @PostMapping("/addclaimtouser")
    public void addclaimtouser(@RequestBody Claim c,@RequestParam long idUser){

    iClaimsService.addclaimsandaffecterUser(c,idUser);
    }
    @PutMapping("/updateclaim")
    public  void modifyclaim(long idClaim,@RequestBody Claim c1){

    iClaimsService.modifyclaim(idClaim, c1);
    }
    @PutMapping("/modifyetat")
    public void modifyetatclaimsbyadmin(@RequestParam long idUser,long idClaim) {
        iClaimsService.modifyetatclaimsbyadmin(idUser, idClaim);
    }
    @DeleteMapping("/deleteclaimbyId")
    public void supprimerclaimAvecId(@RequestParam long idClaim){

        iClaimsService.deleteclaim(idClaim);
    }
    @GetMapping("/retriveclaimbyuser")
    public List<Claim> retrieveclaimByUser(long idUser){

        return iClaimsService.retrieveclaimByUser(idUser);
    }
    @GetMapping("/sentiment")
    public List<Claim> getClaimsSortedBySentiment() {
        //List<Claim> claim = (List<Claim>) claimRepository.findAll();
        List<Claim> claim=iClaimsService.getAllClaims();
        return iClaimsService.sortedComplaintsBySentiment(claim);
    }
    @GetMapping("/retriveallclaims")
    public List<Claim>  retriveclaims(){
        return iClaimsService.getAllClaims();
    }
}
