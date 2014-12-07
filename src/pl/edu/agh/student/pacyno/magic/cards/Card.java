package pl.edu.agh.student.pacyno.magic.cards;

public class Card {
	private int id = -1;
	private String name = null;
	private String stringId = null;
	
	public int getId(){
		return id;
	}
	
	public String toString(){
		return stringId;
	}
	
	public String getName(){
		return name;
	}
	
	public Card(int id, String name){
		this.id = id;
		this.stringId = new Integer(id).toString();
		this.name = name;
	}
}
