package service;

import java.util.Iterator;
import java.util.Map;

import javax.ejb.Stateful;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v2.model.Language;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;


@Stateful
@Path("ServiceToneAnalysis")
public class ServiceToneAnalysis {
	
	@POST
	@GET
	@Consumes("text/plain")
	@Produces("application/json")
	public String toneAnalysis(String text){
		
		ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);
		service.setUsernameAndPassword("26a63819-6f8d-4d53-8465-1b49bd14dddf", "RvxlXTrEtdqm");
		
		ToneAnalysis tone = service.getTone(translateText(text), null).execute();
		
		JSONArray jsonArray = new JSONArray(tone.getDocumentTone().getTones());
		
		JSONObject jsonObject = new JSONObject(jsonArray.getJSONObject(0).toString());
		
		JSONArray emotionalTones = new JSONArray(jsonObject.get("tones").toString());
				
		return emotionalTones.toString();
	}
	
	public String translateText(String text){
		
		LanguageTranslator serviceTranslate = new LanguageTranslator();
		serviceTranslate.setUsernameAndPassword("c7d75272-9937-46c7-a559-1b230272adc8", "VDfY2OyD2Ypf");
		
		TranslationResult translationResult = serviceTranslate.translate(text, Language.PORTUGUESE, Language.ENGLISH).execute();
				
		JSONObject jsonObject = new JSONObject(translationResult.toString());
		
		JSONObject translation = new JSONObject(jsonObject.getJSONArray("translations").get(0).toString());
		
		String translated = translation.getString("translation");
				
		return translated;
		
	}

}
