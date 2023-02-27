package esprit.tunisiacamp.restController;

import esprit.tunisiacamp.entities.forum.Post;
import esprit.tunisiacamp.services.Iservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController

public class GestionDesPostes {
    @Autowired
    Iservices iservices ;
    @PutMapping("AjouterPost")
    String ajouterpost(@RequestBody Post p, @RequestParam long idUser, @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date creation){
        String[] interdit = {"non", "oui"};

        for (String word : interdit) {
            if (p.getContentPost().contains(word)) {
            return "Votre poste nest pas approuve car le mot '" + word + "' est interdit. \n"
                    + "\"veuillez reessayer\"";
        }
        }
        iservices.ajouterpost(p,idUser) ;

        return "votre poste est ajouter avec succes" ;

    }

    @GetMapping("AfficherPostes")
    public List<Post> getpost (){
        return iservices.getposts();
    }
    @DeleteMapping("SupprimerPost")
    void deletepost(@RequestParam long idPost){
        iservices.supprimerpost(idPost);
    }
    @PostMapping("ModifierPost")
    void modifposte(@RequestBody Post post){
        iservices.modifpost(post);
    }
}
