package pl.edu.agh.student.pacyno.magic.db;

import pl.edu.agh.student.pacyno.magic.beans.LoginBean;
import pl.edu.agh.student.pacyno.magic.cards.*;
import pl.edu.agh.student.pacyno.magic.users.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DatabaseHelper {
	private static String dbURL = "jdbc:derby://localhost:1527/magicdb";
	private static Connection conn = null;
	
	static void init(){
		try{
			//Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			//conn = DriverManager.getConnection(dbURL);
			
			Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/MySQLDS");
            conn = ds.getConnection();
		/*} catch(SQLException e){
			e.printStackTrace();
		} catch(ClassNotFoundException e){
			e.printStackTrace();*/
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static List<CardData> getCardData(String id){
		if(conn == null)
			init();
		
		List<CardData> result = null;
		String sql = 
				"select * from card "+
				"join card_data on card.id = card_data.card_id "+
				"where card_data.user_id = ?";
		
		try{
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet set = statement.executeQuery();
			
			if(set.next()){
				result = new ArrayList<CardData>();
				
				do{
					int cardId = set.getInt(1);
					String cardName = set.getString(2);
					String additionalInfo = set.getString(7);
					int amount = set.getInt(8);
					int amountPublic = set.getInt(9);
					
					result.add(new CardData(cardId, cardName, additionalInfo, amount, amountPublic));
				} while(set.next());
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String getCardName(int id){
		if(conn == null)
			init();
		
		String cardName = null;
		String sql = "select name from Card where id = ?";
		
		try{
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			if(results.next())
				cardName = results.getString(1);
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return cardName;
	}
	
	public static List<Card> findMatchingCards(String pattern){
		if(conn == null)
			init();
		
		List<Card> result = null;
		
		String sql = "select * from card "+
				"where card.name like ? and not card.id in ("+
				"select card.id from card "+
				"join card_data on card_data.card_id = card.id "+
				"where card.name like ? and card_data.user_id = ?)";
		
		try{
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + pattern + "%");
			statement.setString(2, "%" + pattern + "%");
			statement.setString(3, LoginBean.getId());
			ResultSet set = statement.executeQuery();
			
			if(set.next()){
				result = new ArrayList<Card>();
				do{
					int cardId = set.getInt(1);
					String cardName = set.getString(2);
					result.add(new Card(cardId, cardName));
				} while(set.next());
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void setAmount(int index, int amount){
		setCardData(index, "amount", amount);
	}
	
	public static void setAmountPublic(int index, int amount){
		setCardData(index, "amount_public", amount);
	}
	
	public static void setCardData(int index, String fieldName, int value){
		if(conn == null)
			init();
		
		String sql = "update card_data set " + fieldName + " = ? "+
				"where card_id = ? and user_id = ?";
		
		try{
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, value);
			statement.setInt(2, index);
			statement.setString(3, LoginBean.getId());
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static int getAmount(int index){
		return getCardData(index, "amount");
	}
	
	public static int getAmountPublic(int index){
		return getCardData(index, "amount_public");
	}
	
	public static int getCardData(int index, String fieldName){
		if(conn == null)
			init();
		
		String sql = "select " + fieldName + " from card_data "+
				"where card_id = ? and user_id = ?";
		
		try{
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, index);
			statement.setString(2, LoginBean.getId());
			ResultSet set = statement.executeQuery();
			
			if(set.next())
				return set.getInt(1);
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public static void removeCardData(int index){
		if(conn == null)
			init();
		
		String sql = "delete from card_data "+
				"where card_id = ? and user_id = ?";
		
		try{
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, index);
			statement.setString(2, LoginBean.getId());
			statement.executeUpdate();
			
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void addCardData(int cardId, String additionalInfo, int amount, int amountPublic){
		if(conn == null)
			init();
		
		String sql = "insert into card_data values (?, ?, FALSE, ?, ?, ?)";
		try{
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, LoginBean.getId());
			statement.setInt(2, cardId);
			statement.setString(3, additionalInfo);
			statement.setInt(4, amount);
			statement.setInt(5,  amountPublic);
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static List<PublicCardData> getPublicCardData(int userId){
		return getPublicCardDataForSet(userId, "%");
	}
	
	public static List<PublicCardData> getPublicCardDataForSet(int userId, String setId){
		if(conn == null)
			init();
		
		List<PublicCardData> result = null;
		String sql = "select user_id, name, set_id, card_id, "+
				"foil, additional_info, amount, amount_public "+
				"from card_data "+
				"join card on card_data.card_id = card.id "+
				"join user_data on card_data.user_id = user_data.user_id "+
				"where card_data.user_id = ? and card_data.amount_public > 0 and card.set_id like ?";
		
		try{
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, userId);
			statement.setString(2, setId);
			ResultSet set = statement.executeQuery();
			
			if(set.next()){
				result = new ArrayList<PublicCardData>();
				do{
					Card card = new Card(set.getInt(4), set.getString(2));
					User user = new User(set.getString(1));
					int amount = set.getInt(8);
					
					result.add(new PublicCardData(card, user, amount));
					
				} while(set.next());
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static List<PublicCardData> getPublicCardDataByPattern(String pattern){
		if(conn == null)
			init();
		
		List<PublicCardData> result = null;
		
		String sql = "select user_id, name, set_id, card_id, "+
				"foil, additional_info, amount, amount_public "+
				"from card_data "+
				"join card on card_data.card_id = card.id "+
				"where card.name like ? and card_data.amount_public > 0";
		
		try{
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "%" + pattern + "%");
			ResultSet set = statement.executeQuery();
			
			if(set.next()){
				result = new ArrayList<PublicCardData>();
				do{
					Card card = new Card(set.getInt(4), set.getString(2));
					User user = new User(set.getString(1));
					int amount = set.getInt(8);
					
					result.add(new PublicCardData(card, user, amount));
					
				} while(set.next());
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	//draft solution
	public static String getUserPassword(){
		if(conn == null)
			init();
		
		String sql = "select password from users where username = ? and enabled";
		
		try{
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, LoginBean.getId());
		ResultSet set = statement.executeQuery();
		
		if(set.next())
			return set.getString(1);
		
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		
		
		return null;
	}
}
