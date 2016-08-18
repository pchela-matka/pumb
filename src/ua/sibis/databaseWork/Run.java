package ua.sibis.databaseWork;

import java.sql.*;
//import java.util.Date;

public class Run {
	public static void main(String[] args) {
		WorkWithJSON my = new WorkWithJSON();
		CameraData my2 = my.getJSON();
		try {
			String url = "jdbc:sqlserver://192.168.70.230:1433;databaseName=pumb";
			Connection conn = DriverManager.getConnection(url, "pumb",
					"P@ssw0rd");
			Statement st = conn.createStatement();
			//Date now = new Date();
			String sqlText = "INSERT INTO cameradata2 (serial, name, timestamp,"
					+ " region1name, region1people, region2name, region2people, region3name, region3people)"
					+ "VALUES ('"
					+ my2.getSerial()
					+ "','"
					+ my2.getName()
					+ "','"
					+ my2.getTimestamp()
					+ "','"
					+ my2.getRegion1name()
					+ "',"
					+ my2.getRegion1people()
					+ ",'"
					+ my2.getRegion2name()
					+ "',"
					+ my2.getRegion2people()
					+ ",'"
					+ my2.getRegion3name()
					+ "',"
					+ my2.getRegion3people() + ")";
			System.out.printf(sqlText);
			st.executeUpdate(sqlText);

			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		System.out.println("test -" + my2.getSerial());
	}
}
