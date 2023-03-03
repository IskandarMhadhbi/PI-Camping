package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.forum.Message;

import java.util.List;

public interface IserviceMessage {

    public void ajouterMessage(Message m, long idUser, long chatroom);

    public List<Message> getmessages();

    void supprimermessage(long idmsg);
}
