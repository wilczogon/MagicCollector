package pl.edu.agh.student.pacyno.magic.beans;

import java.util.List;

import pl.edu.agh.student.pacyno.magic.cards.CardData;
import pl.edu.agh.student.pacyno.magic.db.DatabaseHelper;

public class MyCardsBean {
	private List<CardData> cardData;
	private int activeCardId;
	
	public void setActiveCardId(int id){
		activeCardId = id;
	}
	
	public List<CardData> getCardData(){
		cardData = DatabaseHelper.getCardData(LoginBean.getId());
		return cardData;
	}
	
	public boolean isCardDataEmpty(){
		cardData = getCardData();
		if(cardData == null || cardData.isEmpty())
			return true;
		return false;
	}
	
	int getCardDataIndex(int id){
		int index = -1;
		
		for(int i = 0; i<cardData.size(); ++i){
			if(cardData.get(i).getCard().getId() == id){
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	//TODO - rzucanie wyjatkow
	
	public String addCardAmountAll(){
		int index = getCardDataIndex(activeCardId);
		if(activeCardId != -1){
			int tmpAmount = DatabaseHelper.getAmount(activeCardId);
			DatabaseHelper.setAmount(activeCardId, tmpAmount + 1);
			cardData.get(index).setAmount(tmpAmount + 1);
		}
		return "";
	}
	
	public String subtractCardAmountAll(){
		int index = getCardDataIndex(activeCardId);
		if(activeCardId != -1){
			int tmpAmount = DatabaseHelper.getAmount(activeCardId);
			int tmpAmountPublic = DatabaseHelper.getAmountPublic(activeCardId);
			if(tmpAmount >= 1 && tmpAmount > tmpAmountPublic){
				if(tmpAmount - 1 > 0){
					DatabaseHelper.setAmount(activeCardId, tmpAmount-1);
					cardData.get(index).setAmount(tmpAmount - 1);
				} else{
					DatabaseHelper.removeCardData(activeCardId);
					cardData.remove(index);
				}
			}
		}
		return "";
	}
	
	public String addCardAmountPublic(){
		int index = getCardDataIndex(activeCardId);
		if(activeCardId != -1){
			int tmpAmount = DatabaseHelper.getAmount(activeCardId);
			int tmpAmountPublic = DatabaseHelper.getAmountPublic(activeCardId);
			if(tmpAmountPublic+1 <= tmpAmount){
				DatabaseHelper.setAmountPublic(activeCardId, tmpAmountPublic + 1);
				cardData.get(index).setAmountPublic(tmpAmountPublic + 1);
			}
		}
		return "";
	}
	
	public String subtractCardAmountPublic(){
		int index = getCardDataIndex(activeCardId);
		if(activeCardId != -1){
			int tmpAmountPublic = DatabaseHelper.getAmountPublic(activeCardId);
			if(tmpAmountPublic >= 1){
				DatabaseHelper.setAmountPublic(activeCardId, tmpAmountPublic-1);
				cardData.get(index).setAmountPublic(tmpAmountPublic - 1);
			}
		}
		return "";
	}
	
}
