package pl.edu.agh.student.pacyno.magic.users;

public class User {
	private int id;
	private String nickname;
	
	public int getId(){
		return id;
	}
	
	public String getNickname(){
		return nickname;
	}
	
	public User(int id, String nickname){
		this.id = id;
		this.nickname = nickname;
	}
}
