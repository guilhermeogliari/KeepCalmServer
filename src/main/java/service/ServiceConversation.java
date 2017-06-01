package service;

import java.util.Map;

import javax.ejb.Stateful;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

@Stateful
@Path("ServiceConversation")
public class ServiceConversation {
	
	ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2017_02_03);
	public static Map<String, Object> context = null;
	
	public ServiceConversation() {
		service.setUsernameAndPassword("8733b87b-c43d-4d1a-b0ad-8bb035f50a91", "C1XroUw4jDJR");
	}
	
	@POST
	@GET
	@Consumes("text/plain")
	@Produces("application/json")
	public String conversation(String message){
						
		MessageRequest newMessage = new MessageRequest.Builder().inputText(message).alternateIntents(true).context(context).build();
		MessageResponse response = service.message("f7f9cdd7-e3f4-410c-b976-bbc895fc70fd", newMessage).execute();
		
		context = response.getContext();
				
		JSONObject jsonObject = new JSONObject(response.toString());
		
		JSONObject output = new JSONObject(jsonObject.get("output").toString());
		
		JSONArray jsonArray = new JSONArray(output.get("text").toString());
		
		return jsonArray.toString();
	}

}
