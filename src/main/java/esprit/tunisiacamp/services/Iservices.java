package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.forum.*;
import org.springframework.stereotype.Service;

import java.util.List;


public interface Iservices  {

    void ajouterpost(Post p, long idUser);
    void supprimerpost(long idPost);
    public List<Post> getposts();
    public void modifpost(Post post);

    ///////////////////////////////////////////////////////////

    public void ajouterMessage(Message m, long idUser, long chatroom);
    public List<Message> getmessages();
    void supprimermessage(long idmsg);

    ///////////////////////////////////////////////////////////

    public void likerPost(Rating l, long idPost,long idUser);
    public List<Rating> getLikes();
    void supprimerlike( long idlike);
    public void modifrate(Rating rating);

    ///////////////////////////////////////////////////////////

    void ajoutercommentare(Comment m ,long idPost ,long idUser);
    public List<Comment> getComments();
    void supprimercommentaire(long idcomment) ;
    public void modifierCommentaire(Comment commentaire);

    ///////////////////////////////////////////////////////////

    public ChatRoom ajoutroom(ChatRoom room) ;
    void affecteruseraroom(long idUser, long idroom);
    public void midifroom(ChatRoom room);
    void suprimerroom(long idroom);

    ///////////////////////////////////////////////////////////

    void supprimeruser( long iduser);


    //  void ajouterroom(ChatRoom r , List<Long> usernames);

}
