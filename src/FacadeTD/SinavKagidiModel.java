package FacadeTD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Model.DBConnection;
import View.SinavOlustur;

public class SinavKagidiModel {
 
	private int ID , OgretimUyesiID;
	String ogretimUyesi;
	String sinavAdi ;
	String dersAdi ;
	String toplamPuan ;
	
	LinkedList<Integer> tipID  =new LinkedList<Integer>();
	LinkedList<String> soru = new LinkedList<String>();
	LinkedList<String> puan = new LinkedList<String>();
	LinkedList<String> sikA = new LinkedList<String>();
	LinkedList<String> sikB = new LinkedList<String>();
	LinkedList<String> sikC = new LinkedList<String>();
	LinkedList<String> sikD = new LinkedList<String>();
	LinkedList<String> cevap = new LinkedList<String>();
	
	
public SinavKagidiModel() {
	
   	baglantiSinav();				
	baglantiSoruBank();
	
	}

public SinavKagidiModel(String gozetmenAdi){
	
	
}

private void baglantiSinav() {
	
	try {
		
		 Connection con = DBConnection.dbConnector(); 	
		 Statement State = con.createStatement();
		 ResultSet rs = State.executeQuery("SELECT * FROM Sinav WHERE SinavAdi = '"+ SinavOlustur.getTextFieldSinavAdi().getText().toString() + "'");
			
		 while(rs.next()) {
			 
			 ID = rs.getInt(1);
			 sinavAdi = rs.getString(2);
			 toplamPuan = rs.getString(7);
			 dersAdi = rs.getString(8);
			 
				}
		 
		 Statement State2 = con.createStatement();
		 ResultSet rs2 = State2.executeQuery("SELECT * FROM Ders WHERE Adi = '"+ dersAdi + "'");

		 while(rs2.next()) {
			 OgretimUyesiID = rs2.getInt(3);
		 }
		 
		 Statement State3 = con.createStatement();
		 ResultSet rs3 = State3.executeQuery("SELECT * FROM OgretimUyesi WHERE ID = "+ OgretimUyesiID );
		 
		 while(rs3.next()) {
			 ogretimUyesi = rs3.getString(2);
		 }
		 
		 
		 State.close();
		 State2.close();
		 State3.close();
		 con.close();
		 
					
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
		
private void baglantiSoruBank() {
	
	try {
		
		 Connection con = DBConnection.dbConnector(); 	
		 Statement State = con.createStatement();
		 ResultSet rs = State.executeQuery("SELECT * FROM SoruBank WHERE SinavID = " + getID());
		
		 while(rs.next()) {
			 
			 tipID.add(rs.getInt(3));
			 soru.add(rs.getString(5));
			 puan.add(rs.getString(6));
			 sikA.add(rs.getString(7));
			 sikB.add(rs.getString(8));
			 sikC.add(rs.getString(9));
			 sikD.add(rs.getString(10));
			 cevap.add(rs.getString(11));
			 
				}
		 
		State.close();
		con.close();
								
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public int getID() {
	return ID;
}
public String getOgretimUyesi() {
	return ogretimUyesi;
}
public String getSinavAdi() {
	return sinavAdi;
}

public String getDersAdi() {
	return dersAdi;
}
public String getToplamPuan() {
	return toplamPuan;
}
public LinkedList<Integer> getTipID() {
	return tipID;
}

public LinkedList<String> getSoru() {
	return soru;
}

public LinkedList<String> getPuan() {
	return puan;
}

public LinkedList<String> getSikA() {
	return sikA;
}

public LinkedList<String> getSikB() {
	return sikB;
}

public LinkedList<String> getSikC() {
	return sikC;
}

public LinkedList<String> getSikD() {
	return sikD;
}

public LinkedList<String> getCevap() {
	return cevap;
}
	
	
}
