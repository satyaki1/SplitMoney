package com.splitmoney.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.splitmoney.beans.Expenditure;
import com.splitmoney.beans.Group;
import com.splitmoney.beans.User;
import com.splitmoney.dao.DaoImpl;


public class SplitMoneyService {

	private Connection connection = null;
	private DaoImpl daoImpl = new DaoImpl();
	private Statement stmt = null;
	private ResultSet rs = null;
	
	public User createUser(User user){
		
	    // create the mysql insert preparedstatement
	    try {
	    	connection = daoImpl.getConnection();
			String query = " insert into user (user_id, fname, lname)"
				        + " values (?, ?, ?)";
			String userId = "user-"+user.getfName()+"-"+user.getlName();
	    	PreparedStatement preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString (1, userId);
			preparedStmt.setString (2, user.getfName());
		    preparedStmt.setString (3, user.getlName());

		    // execute the preparedstatement
		    preparedStmt.execute();
		    
		    user.setUserId(userId);
		    return user;
		} catch( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	 }
	
	public Group createGroup(Group group){
		// create the mysql insert preparedstatement
	    try {
	    	connection = daoImpl.getConnection();
			String query = " INSERT INTO GROUP_TABLE (GROUP_ID, GNAME)"
				        + " VALUES (?, ?)";
			String groupId = "group-" + group.getGroupName();
	    	PreparedStatement preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString (1, groupId);
			preparedStmt.setString (2, group.getGroupName());

		    // execute the preparedstatement
		    preparedStmt.execute();
		     connection.close();
		     group.setGroupId(groupId);
		    return group; //successfully created
		} catch( Exception e) {
			e.printStackTrace();
			return null; //Not Created 
		}
	}
	
	public Expenditure createExpenditure(Expenditure expenditure){
		// create the mysql insert preparedstatement
	    try {
	    	connection = daoImpl.getConnection();
			String query = " INSERT INTO EXPENDITURE (EXPENDITURE_ID, GROUP_ID, USER_ID, SPENDINGNAME, AMOUNT)"
				        + " VALUES (?, ?, ?, ?, ?)";
			String expId = "exp-" + expenditure.getSpendingName().split(" ")[0] + expenditure.getUserId();
	    	PreparedStatement preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString (1, expId);
			preparedStmt.setString (2, expenditure.getGroupId());
			preparedStmt.setString (3, expenditure.getUserId());
			preparedStmt.setString (4, expenditure.getSpendingName());
			preparedStmt.setFloat(5, expenditure.getSpendingAmount());

		    // execute the preparedstatement
		    preparedStmt.execute();
		     connection.close();
		     expenditure.setExpenditureId(expId);
		   
		     return expenditure; //successfully created
		     
		} catch( Exception e) {
			e.printStackTrace();
			return null; //Not Created 
		}
	}
	
	public List<Group> getAllGroups(){
		List<Group> groups = new ArrayList<Group>();
		try {
			connection = daoImpl.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM group_table");
			while (rs.next()) {
				String id = rs.getString("group_id");
				String gName = rs.getString("gname");
				groups.add(new Group(id, gName));
			}
			connection.close();
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return groups;
	}
	
	public Group readAGroup(String groupId){
		
		System.out.println("READ A GROUP");
		Group group = null;
		Set<User> users = new HashSet<>();
		try{connection = daoImpl.getConnection();
		String query = "select user.user_id, user.fname, user.lname, group_users.group_id from user inner join group_users on group_users.user_id=user.user_id where group_users.group_id like ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, groupId);
		
		rs = preparedStatement.executeQuery();//stmt.executeQuery(query);
		while (rs.next()) {
			String userId = rs.getString("user_id");
			String fName = rs.getString("fname");
			String lName = rs.getString("lname");
			users.add(new User(userId, fName, lName));
		}
		
		query = "select gname from group_table where group_Id like ?";
		
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, groupId);
		
		rs = preparedStatement.executeQuery();
		while (rs.next()) {
			String gname = rs.getString("gname");
			group = new Group(groupId, gname, users);
		}
		connection.close();
		rs.close();
		preparedStatement.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return group;
}

	public int addToGroup(String groupId, String userId) {
		// create the mysql insert preparedstatement
	    try {
	    	connection = daoImpl.getConnection();
			String query = " INSERT INTO GROUP_USERS (GROUP_ID, USER_ID)"
				        + " VALUES (?, ?)";
			PreparedStatement preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString (1, groupId);
			preparedStmt.setString (2, userId);

		    // execute the preparedstatement
		    preparedStmt.execute();
		     connection.close();
		    return 1; //successfully created
		} catch( Exception e) {
			e.printStackTrace();
			return 0; //Not Created 
		}
		
	}
	
	public User readUserFromAGroup(String groupId, String userId){
		Set<User> users = this.readAGroup(groupId).getUsers();
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if(user.getUserId().equals(userId))
				return user;
		}
		return null;
		
	}

	public List<Expenditure> getAllExpenditures(String groupId) {
		List<Expenditure> expenditures = new ArrayList<>();
		
		try {
			connection = daoImpl.getConnection();
			String query = "SELECT * FROM expenditure where group_id like ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, groupId);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString("expenditure_id");
				String gId = rs.getString("group_id");
				String uId = rs.getString("user_id");
				String sName = rs.getString("spendingName");
				int amount = rs.getInt("amount");
				expenditures.add(new Expenditure(id, gId, uId, sName, amount));
			}
			connection.close();
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return expenditures;
	}

	public List<Expenditure> getAllExpendituresUser(String groupId,
			String userId) {
		List<Expenditure> expenditures = new ArrayList<>();
		
		try {
			connection = daoImpl.getConnection();
			String query = "SELECT * FROM expenditure where group_id like ? and user_id like ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, groupId);
			ps.setString(2, userId);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString("expenditure_id");
				String gId = rs.getString("group_id");
				String uId = rs.getString("user_id");
				String sName = rs.getString("spendingName");
				int amount = rs.getInt("amount");
				expenditures.add(new Expenditure(id, gId, uId, sName, amount));
			}
			connection.close();
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return expenditures;
	}
	
	
	/*public List<User> getAllGroups(){
		List<User> users = new ArrayList<User>();
		try {
			connection = daoImpl.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM user");
			while (rs.next()) {
				String id = rs.getString("user_id");
				String firstName = rs.getString("fname");
				String lastName = rs.getString("lname");
				users.add(new User(id, firstName, lastName));
			}
			connection.close();
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}*/
	
	
}
