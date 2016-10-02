package cn.imzack.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;



public class ParseSqlResultToJSON {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/DTC";
	static final String USER = "root";
	static final String PASS = "";
	
	
	JsonObject object = new JsonObject();
	JsonArray dtcarrays = new JsonArray();
	
	public void getSqlData(){
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String querySql = "select * from catch_data_transfer_collection";
			ResultSet rs = stmt.executeQuery(querySql);
			
			
			List li = new ArrayList<JsonElement>();
			while (rs.next()){
				JsonObject dtcone = new JsonObject();
				String customer_id = rs.getString("customer_id");
				
				dtcone.addProperty("customer_id", customer_id);
				System.out.println(customer_id);
				String phone_coll = rs.getString("phone_coll");
				dtcone.addProperty("phone_coll", phone_coll);
				String severity_coll_H = rs.getString("severity_coll_H");
				dtcone.addProperty("severity_coll_H", severity_coll_H);
				String severity_coll_M = rs.getString("severity_coll_M");
				dtcone.addProperty("severity_coll_M", severity_coll_M);
				String severity_coll_L = rs.getString("severity_coll_L");
				dtcone.addProperty("severity_coll_L", severity_coll_L);
				String network_coll = rs.getString("network_coll");
				dtcone.addProperty("network_coll", network_coll);
				String severity_net_H = rs.getString("severity_net_H");
				dtcone.addProperty("severity_net_H", severity_net_H);
				String severity_net_M = rs.getString("severity_net_M");
				dtcone.addProperty("severity_net_M", severity_net_M);
				String severity_net_L = rs.getString("severity_net_L");
				dtcone.addProperty("severity_net_L", severity_net_L);
				System.out.println(severity_net_L);
				dtcarrays.add(dtcone);
				
				
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
			object.add("dct",dtcarrays );
			String jsonStr = object.toString();   // 将json对象转化成json字符串
		    System.out.println(jsonStr);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	// a smaple for create json 
	public void createJSON() throws IOException{

	    JsonObject object = new JsonObject();  // 创建一个json对象
	    object.addProperty("cat", "it");       // 为json对象添加属性   

	    JsonArray languages = new JsonArray(); // 创建json数组
	    JsonObject language = new JsonObject();
	    language.addProperty("id", 1);
	    language.addProperty("ide", "Eclipse");
	    language.addProperty("name", "java");
	    languages.add(language);               // 将json对象添加到数组  

	    language = new JsonObject();
	    language.addProperty("id", 2);
	    language.addProperty("ide", "XCode");
	    language.addProperty("name", "Swift");
	    languages.add(language);

	    language = new JsonObject();
	    language.addProperty("id", 3);
	    language.addProperty("ide", "Visual Studio");
	    language.addProperty("name", "C#");
	    languages.add(language);

	    object.add("languages", languages);   // 将数组添加到json对象   
	    object.addProperty("pop", true);

	    String jsonStr = object.toString();   // 将json对象转化成json字符串
	    System.out.println(jsonStr);
	}
	
	public static void main(String[] args) throws IOException {
		ParseSqlResultToJSON cm = new ParseSqlResultToJSON();
//		cm.createJSON();
		cm.getSqlData();
	}

}
