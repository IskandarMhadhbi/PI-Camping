package esprit.tunisiacamp.restController;

import esprit.tunisiacamp.entities.forum.Rating;
import esprit.tunisiacamp.services.Iservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GestionDesLikes {

    @Autowired
    Iservices iservices ;

    @PutMapping("LikerPost")
    void  likerpost (@RequestBody Rating l, @RequestParam long idPost, @RequestParam long idUser){
        iservices.likerPost(l,idPost,idUser);
    }
    @GetMapping("AfficheLikes")
    public List<Rating> getlikess (){
        return iservices.getLikes();
    }

    @DeleteMapping("SupprimerLike")
    void supprimerlike(@RequestParam long idlike ){
        iservices.supprimerlike(idlike);
    }

    @PostMapping("ModifierRating")
    void modifrate(@RequestBody Rating rating){
        iservices.modifrate(rating);
    }
}
