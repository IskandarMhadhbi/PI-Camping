package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.Claim;
<<<<<<< HEAD
import esprit.tunisiacamp.entities.enums.Category;
=======
>>>>>>> 868160218ec9782301b514cc81f4fd28223db5df
import esprit.tunisiacamp.repositories.ClaimRepository;
import esprit.tunisiacamp.services.IClaimsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name="Manager of Claim")
@RequestMapping("/api/claims")
public class ClaimRestController {
@Autowired
    IClaimsService iClaimsService;
//@Autowired
    //ClaimRepository claimRepository;
=======
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name="Manager of Claim")
public class ClaimRestController {
@Autowired
    IClaimsService iClaimsService;
@Autowired
    ClaimRepository claimRepository;
>>>>>>> 868160218ec9782301b514cc81f4fd28223db5df

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
<<<<<<< HEAD
    @PutMapping("/resolveclaims")
    public void processUnresolvedClaims(long adminId) {
    iClaimsService.processUnresolvedClaims(adminId);
    }
    @GetMapping("/claims/count-by-category")
    public List<Object[]> countByCategory() {
        return iClaimsService.countClaimsByCategory();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/category")
    public Map<String, Long> getClaimsByCategory() {
    return iClaimsService.getClaimsByCategory();
    }
   /* @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/NumberBetweenDate")
    public Map<Category, Long> countClaimsByCategoryBetweenDates(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date startDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate) {
    return iClaimsService.countClaimsByCategoryBetweenDates(startDate,endDate);
    }*/
=======
>>>>>>> 868160218ec9782301b514cc81f4fd28223db5df
}
