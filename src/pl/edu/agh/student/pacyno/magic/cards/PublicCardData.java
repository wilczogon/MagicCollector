package pl.edu.agh.student.pacyno.magic.cards;

import pl.edu.agh.student.pacyno.magic.users.User;

public class PublicCardData {
	private Card card;
	private User user;
	private int amount;
	
	public Card getCard(){
		return card;
	}
	
	public User getUser(){
		return user;
	}
	
	public int getAmount(){
		return amount;
	}
	
	public PublicCardData(Card card, User user, int amount){
		this.card = card;
		this.user = user;
		this.amount = amount;
	}
	
}
