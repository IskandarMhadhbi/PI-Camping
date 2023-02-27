package esprit.tunisiacamp.restController;

import esprit.tunisiacamp.entities.forum.Message;
import esprit.tunisiacamp.services.Iservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GestionDesMessges {
    @Autowired
    Iservices iservices ;

    @PutMapping("EnvoyerMessage")
    void ajoutermessage(@RequestBody Message m, @RequestParam long idUser, @RequestParam long chatroom ){
        iservices.ajouterMessage(m, idUser ,chatroom); ;
    }
    @GetMapping("AfficherMessages")
    public List<Message> getmsg (){
        return iservices.getmessages();
    }
    @DeleteMapping("SupprimerMsg")
    void suppmessage (@RequestParam long idmsg){
        iservices.supprimermessage(idmsg);
    }
}
