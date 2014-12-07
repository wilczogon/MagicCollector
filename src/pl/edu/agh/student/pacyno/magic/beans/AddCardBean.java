package pl.edu.agh.student.pacyno.magic.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import pl.edu.agh.student.pacyno.magic.cards.Card;
import pl.edu.agh.student.pacyno.magic.db.DatabaseHelper;

public class AddCardBean {
	private String pattern = null;
	private List<SelectItem> matchingCards = null;
	private int chosenCardId;
	private int amountAll = 0;
	private int amountPublic = 0;
	
	public int getAmountAll(){
		return amountAll;
	}
	
	public int getAmountPublic(){
		return amountPublic;
	}
	
	public void setAmountAll(int amount){
		amountAll = amount;
	}
	
	public void setAmountPublic(int amount){
		amountPublic = amount;
	}
	
	public void setChosenCardId(String id){
		chosenCardId = Integer.parseInt(id);
	}
	
	public String getChosenCardId(){
		return new Integer(chosenCardId).toString();
	}
	
	public String getPattern(){
		return pattern;
	}
	
	public void setPattern(String pattern){
		this.pattern = pattern;
	}
	
	public List<SelectItem> getMatchingCards(){
		return matchingCards;
	}
	
	public String search(){
		findAllMatching();
		return "";
	}
	
	void findAllMatching(){
		List<Card> tmpCards = DatabaseHelper.findMatchingCards(pattern);
		
		matchingCards = new ArrayList<SelectItem>();
		
		if(tmpCards != null){
			for(Card card: tmpCards){
				matchingCards.add(new SelectItem(card.getId(), card.getName()));
			}
		}
	}
	
	public boolean isAddedPattern(){
		if(pattern == null || pattern.equals(""))
			return false;
		return true;
	}
	
	public boolean isMatching(){
		if(matchingCards == null || matchingCards.isEmpty())
			return false;
		return true;
	}
	
	public String add(){
		if(amountAll >= amountPublic && amountAll > 0 && amountPublic >= 0){
			DatabaseHelper.addCardData(chosenCardId, "-", amountAll, amountPublic);
			return "MyCards";
		} else
			return "";
	}
}
