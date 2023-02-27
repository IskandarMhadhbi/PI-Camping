package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.forum.*;
import esprit.tunisiacamp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class Services implements Iservices{

    @Autowired
    CommentRepository commentRepository ;
    @Autowired
    MessageRepository messageRepository ;
    @Autowired
    RoomRespository roomRespository;
    @Autowired
    RtingRepository ratingRepository;
    @Autowired
    UserRepository userRepository ;
    @Autowired
    postRepository postRepository;

    @Override
    public void ajouterpost(Post p , long idUser) {


            User u = userRepository.findById(idUser).get();
            p.setUser(u);

            postRepository.save(p);





    }
    @Override
    public void modifpost(Post post) {
        Post postt = postRepository.findById(post.getIdPost()).get();
        postt.setContentPost(post.getContentPost());
        postRepository.save(postt);
    }
    @Override
    public void supprimerpost(long idPost) {
        postRepository.deleteById(idPost);

    }
    @Override
    public List<Post> getposts() {
        return (List<Post>) postRepository.findAll();
    }

    /////////////////////////////////////////////////////

    @Override

    public void ajouterMessage(Message m, long idUser, long chatroom) {
         User user= userRepository.findById(idUser).get();
         ChatRoom room = roomRespository.findById(chatroom).get();

         m.setUser(user);
         m.setChatRoom(room);

        messageRepository.save(m);
    }
    @Override
    public void supprimermessage(long idmsg) {
        messageRepository.deleteById(idmsg);
    }

    @Override
    public List<Message> getmessages() {

            return (List<Message>) messageRepository.findAll();

    }

    //////////////////////////////////////////////////

    @Override
    public void likerPost(Rating l, long idPost,long idUser) {
        Post post = postRepository.findById(idPost).get();
        User user= userRepository.findById(idUser).get();

        l.setPost_rate(post);
        l.setUser(user);

        ratingRepository.save(l);
    }
    @Override
    public void modifrate(Rating rating) {
        Rating ratingg=ratingRepository.findById(rating.getIdRating()).get();
        ratingg.setValue(rating.getValue());
        ratingRepository.save(ratingg);
    }
    @Override
    public void supprimerlike(long idlike) {
        ratingRepository.deleteById(idlike);
    }
    @Override
    public List<Rating> getLikes() {
        return (List<Rating>) ratingRepository.findAll();
    }

    ////////////////////////////////////////////////

    @Override
    public void ajoutercommentare(Comment m, long idPost, long idUser) {
        Post post = postRepository.findById(idPost).get();
        User user = userRepository.findById(idUser).get();

        m.setPost(post);
        m.setUser(user);

        commentRepository.save(m);
    }
    @Override
    public void modifierCommentaire(Comment commentaire) {
        Comment comment = commentRepository.findById(commentaire.getIdComment()).get();
        comment.setContentComment(commentaire.getContentComment());
        commentRepository.save(comment);
    }
    @Override
    public void supprimercommentaire(long idcomment) {
        commentRepository.deleteById(idcomment);
    }

    @Override
    public List<Comment> getComments() {
        return (List<Comment>) commentRepository.findAll();

    }

    ///////////////////////////////////////////////////////////////////////////////////////
    @Override
    public ChatRoom ajoutroom(ChatRoom room) {
       return roomRespository.save(room);
    }

    @Override
    public void affecteruseraroom(long idUser, long idroom) {
        User user=userRepository.findById(idUser).get();
        ChatRoom room=roomRespository.findById(idroom).get();
      //  user.getChatRooms();
        //room.setUser(user);
        //roomRespository.save(room);
       //.add(room);
    room.getUsers();
    user.addChatroom(room);
    userRepository.save(user);
    }

    @Override
    public void midifroom(ChatRoom room) {
  ChatRoom roomm =roomRespository.findById(room.getIdChatRoom()).get();
  roomm.setNameRoom(room.getNameRoom());
  roomRespository.save(roomm);
    }

    @Override
    public void suprimerroom(long idroom) {
        roomRespository.deleteById(idroom);
    }


/*
    @Override
    public void ajouterroom(ChatRoom r, List<Long> usernames) {
        List<User> users=userRepository.findById(usernames);
       // r.setUsers(users);
        roomRespository.save(r);
    }
*/

    @Override
    public void supprimeruser(long iduser) {
        userRepository.deleteById(iduser);
    }
/*
    @PostMapping("/ajouter-poste")
    public String ajouterPoste(@RequestBody Poste poste) {
        for (String word : forbiddenWords) {
            if (poste.getContenu().contains(word)) {
                return "Le mot '" + word + "' est interdit.";
            }
        }
        // Code d'ajout du poste à la base de données ici
        return "Poste ajouté avec succès.";
    }
*/
}



