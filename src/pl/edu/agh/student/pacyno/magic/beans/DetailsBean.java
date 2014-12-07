package pl.edu.agh.student.pacyno.magic.beans;

import pl.edu.agh.student.pacyno.magic.db.DatabaseHelper;

public class DetailsBean {
	private int cardId;
	
	public void setCardId(int id){
		cardId = id;
	}
	
	public int getCardId(){
		return cardId;
	}
	
	public String getCardName(){
		return DatabaseHelper.getCardName(cardId);
	}
}
