package pl.edu.agh.student.pacyno.magic.beans;

import pl.edu.agh.student.pacyno.magic.db.DatabaseHelper;

public class LoginBean {
	private static String nickname = null;
	private String password = null;
	
	public static String getId(){
		return nickname;
	}
	
	public String getNickname(){
		return nickname;
	}
	
	public void setNickname(String nickname){
		LoginBean.nickname = nickname;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return null;
	}
	
	public String login(){//TODO
		if(!password.equals(DatabaseHelper.getUserPassword())){
			password = null;
			nickname = null;
			return "";
		}
		password = null;
		return "MyCards";
	}
}
