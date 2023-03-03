package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.forum.ChatRoom;
import esprit.tunisiacamp.entities.forum.Message;
import esprit.tunisiacamp.repositories.MessageRepository;
import esprit.tunisiacamp.repositories.RoomRespository;
import esprit.tunisiacamp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ServiceMessage implements IserviceMessage{
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    RoomRespository roomRespository;
    @Autowired
    UserRepository userRepository;

    @Override

    public void ajouterMessage(Message m, long idUser, long chatroom) {
        User user = userRepository.findById(idUser).get();
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
}
