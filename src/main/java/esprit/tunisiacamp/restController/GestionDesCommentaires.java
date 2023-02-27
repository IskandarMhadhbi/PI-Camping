package esprit.tunisiacamp.restController;

import esprit.tunisiacamp.entities.forum.Comment;
import esprit.tunisiacamp.services.Iservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GestionDesCommentaires {


    @Autowired
    Iservices iservices ;

    @PutMapping("AjouterCommentaire")
    void ajoutcomment (@RequestBody Comment m, @RequestParam long idPost, @RequestParam long idUser){
        iservices.ajoutercommentare(m,idPost,idUser);
    }

    @GetMapping("Affichercommentaires")
     List<Comment> getcomment (){
        return iservices.getComments();
    }

    @DeleteMapping("SupprimerCommenteire")
    void suppcomment (@RequestParam long idcomment){
        iservices.supprimercommentaire(idcomment);
    }

    @PostMapping("ModifierCommentaire")
    void modifcomment(@RequestBody Comment commentaire){
        iservices.modifierCommentaire( commentaire );
    }
}
