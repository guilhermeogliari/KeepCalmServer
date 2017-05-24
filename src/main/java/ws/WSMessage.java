package ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import model.Message;

@Stateful
@ServerEndpoint("/ws")
public class WSMessage {
	
	private static Message instance = null;
	
	private static final Logger log = Logger.getLogger(WSMessage.class.getName());
	private static List<Message> messages  = Collections.synchronizedList(new ArrayList<Message>());
	
	@OnMessage
	public void onMessage(Session session, String jsonMessage) {
		log.log(Level.INFO, "message: "+jsonMessage);
		sendToAll(jsonMessage, session);
	}

	@OnOpen
	public void onOpen(Session session) {
		log.log(Level.INFO, "onOpen");
		Message message = new Message();
		message.setSession(session);
		messages.add(message);
	}
	
	@OnClose
	public void onClose(Session session) {
		log.log(Level.INFO, "onClose");
		messages.remove(findDevice(session));
	}
	
	public static Message getInstance(){
		if(instance == null){
			instance = new Message();
		}
		return instance;
	}
	
	public void sendToAll(String text, Session session){		
		try{
			synchronized (messages) {
				for (Message message : messages) {
					if(message.getSession() != session){
						message.getSession().getBasicRemote().sendText(text);
					}
				}
			}
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public Message findDevice(Session session){
		Message message = null;
		for (Message m : messages){
			String idSession = m.getSession().getId();
			if(idSession.equals(session.getId())){
				message = m;
			}
		}
		return message;
	}

}
