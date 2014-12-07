package pl.edu.agh.student.pacyno.magic.cards;

public class CardData {
	private Card card = null;
	private String additionalInfo;
	private int amount;
	private int amountPublic;
	
	public Card getCard(){
		return card;
	}
	
	public String getAdditionalInfo(){
		return additionalInfo;
	}
	
	public void setAmount(int amount){
		this.amount = amount;
	}
	
	public void setAmountPublic(int amount){
		this.amountPublic = amount;
	}
	
	public int getAmount(){
		return amount;
	}
	
	public int getAmountPublic(){
		return amountPublic;
	}
	
	public CardData(int cardId, String cardName, String additionalInfo, int amount, int amountPublic){
		card = new Card(cardId, cardName);
		this.additionalInfo = additionalInfo;
		this.amount = amount;
		this.amountPublic = amountPublic;
	}
}
