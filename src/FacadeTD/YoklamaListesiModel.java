package FacadeTD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Model.ExcelConnection;
import Model.DBConnection;
import View.SinavOlustur;

public class YoklamaListesiModel {
	
	private LinkedList<Long> ogrenciNumarasi = new LinkedList<>();
	private LinkedList<String> ogrenciAdveSoyadi = new LinkedList<>();
	private LinkedList<String> salonNo = new LinkedList<>();
	private LinkedList<String> binaAdi = new LinkedList<>();
	private LinkedList<String> gozetmen = new LinkedList<>();
	private LinkedList<Integer> kapasite = new LinkedList<>();
	private String dersAdi, sinavAdi;
	private int sinavID;
	
	
	public YoklamaListesiModel() {
		baglantiExcel();
		baglantiSQL();
	}

	private void baglantiSQL() {
		
		try {
			
			sinavAdi = SinavOlustur.getTextFieldSinavAdi().getText().toString();
			
			
			 Connection con = DBConnection.dbConnector(); 	
			 Statement State = con.createStatement();
			 ResultSet rs  = State.executeQuery("SELECT * FROM Sinav WHERE SinavAdi = '"+ SinavOlustur.getTextFieldSinavAdi().getText().toString() + "'");
				
			 while(rs.next()) {
				 
				 sinavID = rs.getInt(1);
		    	 dersAdi = rs.getString(8);

					}

							
			 ResultSet rs2 = State.executeQuery("SELECT * FROM Derslik WHERE SinavID = "+ sinavID);
			
			 while(rs2.next()) {
				 
				 salonNo.add(rs2.getString(2)) ;
				 binaAdi.add(rs2.getString(3));
				 kapasite.add(rs2.getInt(4));
	
				 
					}
			 
			 ResultSet rs3 = State.executeQuery("SELECT * FROM Gozetmen");
			 
			 while(rs3.next()) {
				 
				 gozetmen.add(rs3.getString(2));

					}
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void baglantiExcel() {
		ExcelConnection ogrenciBilgileri = ExcelConnection.getObj();
		ogrenciBilgileri.okuma();
		
		setOgrenciAdveSoyadi(ogrenciAdveSoyadi=ogrenciBilgileri.getOgrAdveSoyad());
		setOgrenciNumarasi(ogrenciNumarasi=ogrenciBilgileri.getOgrNo());
		
	}
	
	//Getler ve Setler
	public String getDersAdi() {
		return dersAdi;
	}
	
	public String getSinavAdi() {
		return sinavAdi;
	}

	public LinkedList<Long> getOgrenciNumarasi() {
		return ogrenciNumarasi;
	}

	public LinkedList<String> getOgrenciAdveSoyadi() {
		return ogrenciAdveSoyadi;
	}

	public LinkedList<String> getSalonNo() {
		return salonNo;
	}

	public LinkedList<String> getBinaAdi() {
		return binaAdi;
	}

	public LinkedList<String> getGozetmen() {
		return gozetmen;
	}

	public LinkedList<Integer> getKapasite() {
		return kapasite;
	}

	public void setOgrenciNumarasi(LinkedList<Long> ogrenciNumarasi) {
		this.ogrenciNumarasi = ogrenciNumarasi;
	}

	public void setOgrenciAdveSoyadi(LinkedList<String> ogrenciAdveSoyadi) {
		this.ogrenciAdveSoyadi = ogrenciAdveSoyadi;
	}

}
