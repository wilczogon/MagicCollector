package pl.edu.agh.student.pacyno.magic.beans;

import java.util.List;

import pl.edu.agh.student.pacyno.magic.cards.PublicCardData;
import pl.edu.agh.student.pacyno.magic.db.DatabaseHelper;

public class SearchBean {
	private String pattern;
	private List<PublicCardData> cardData;
	
	public boolean isResultAvailable(){
		if(cardData == null || cardData.isEmpty())
			return false;
		return true;
	}
	
	public boolean isPatternAdded(){
		if(pattern == null || pattern.equals(""))
			return false;
		return true;
	}
	
	public List<PublicCardData> getCardData(){
		return cardData;
	}
	
	public void setPattern(String pattern){
		this.pattern = pattern;
	}
	
	public String getPattern(){
		return pattern;
	}
	
	public String search(){
		cardData = DatabaseHelper.getPublicCardDataByPattern(pattern);
		return "";
	}
}
