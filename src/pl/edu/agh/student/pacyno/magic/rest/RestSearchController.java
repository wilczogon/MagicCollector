package pl.edu.agh.student.pacyno.magic.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.edu.agh.student.pacyno.magic.cards.*;
import pl.edu.agh.student.pacyno.magic.db.DatabaseHelper;

@Path("/user")
public class RestSearchController {
	@GET
	@Path("{userId}/public/")
	@Produces(MediaType.TEXT_HTML)
	public String getPublicCards(@PathParam("userId") String userId){
		List<PublicCardData> cards = DatabaseHelper.getPublicCardData(Integer.parseInt(userId));
		
		if(cards == null)
			return "<html><head></head><body>No cards found.</body></html>";
		
		String beginning = "<html><head></head><body>"+
				"<table border='1'><tr><th>Card name</th><th>Amount</th><th>User nickname</th></tr>";
		String ending = "</table></body></html>";
		String center = "";
		
		for(PublicCardData card: cards){
			center = center + "<tr>";
			
			center = center + "<td>";
			center = center + card.getCard().getName();
			center = center + "</td>";
			
			center = center + "<td>";
			center = center + card.getAmount();
			center = center + "</td>";
			
			center = center + "<td>";
			center = center + card.getUser().getNickname();
			center = center + "</td>";
			
			center = center + "</tr>";
		}
		
		return beginning + center + ending;
	}
	
	@GET
	@Path("{userId}/public/set/{setId}/")
	@Produces(MediaType.TEXT_HTML)
	public String getPublicCardsWithSet(@PathParam("userId") String userId, @PathParam("setId") String setId){
		List<PublicCardData> cards = DatabaseHelper.getPublicCardDataForSet(Integer.parseInt(userId), setId);
		
		if(cards == null)
			return "<html><head></head><body>No cards found.</body></html>";
		
		String beginning = "<html><head></head><body>"+
				"<table border='1'><tr><th>Card name</th><th>Amount</th><th>User nickname</th></tr>";
		String ending = "</table></body></html>";
		String center = "";
		
		for(PublicCardData card: cards){
			center = center + "<tr>";
			
			center = center + "<td>";
			center = center + card.getCard().getName();
			center = center + "</td>";
			
			center = center + "<td>";
			center = center + card.getAmount();
			center = center + "</td>";
			
			center = center + "<td>";
			center = center + card.getUser().getNickname();
			center = center + "</td>";
			
			center = center + "</tr>";
		}
		
		return beginning + center + ending;
	}
}
