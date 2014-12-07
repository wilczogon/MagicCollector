package pl.edu.agh.student.pacyno.magic.beans;

public class LoginBean {
	private String nickname = null;
	private static int id;
	
	public static int getId(){
		return id;
	}
	
	public String getNickname(){
		return nickname;
	}
	
	public void setNickname(String nickname){
		this.nickname = nickname;
		id = Integer.parseInt(nickname); //TODO
	}
	
	public String login(){//TODO
		//validate
		return "MyCards";
	}
}
