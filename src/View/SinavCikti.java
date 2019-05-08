package View;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import FacadeTD.Facade;
import FacadeTD.SinavKagidi;
import FacadeTD.SinavYeriIlanListesi;
import FacadeTD.YoklamaListesi;
import Model.DBConnection;
import javax.swing.JScrollPane;

public class SinavCikti extends JInternalFrame {

	private static SinavCikti obj=null;
	private SinavOlustur SinavOlustur;
	private JTextField textFieldSoruID;
	private JTextField textFieldPuani;
	int sID = 0;
	List<Integer> soruIDs = new ArrayList<>();
	int currentSoru = 0;
	int OlusturulanPuan=0;
	int ilk = 1;
	int son = 0;
	private static JTextField textFieldDosyaAdi;
	
	
	public static SinavCikti getObj(){
        if(obj==null || obj!=null){ 
            obj=new SinavCikti();
        }return obj;
    }
	
	private SinavCikti() {
		setTitle("Sinav Cikti");
		setClosable(true);
		setBounds(0, 0, 997, 700);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 336, 502);
		getContentPane().add(tabbedPane);
		
		JPanel panelGenelBilgiler = new JPanel();
		tabbedPane.addTab("Genel Bilgiler", null, panelGenelBilgiler, null);
		panelGenelBilgiler.setLayout(null);
		
		JLabel lblDers = new JLabel("Ders                        :");
		lblDers.setBounds(25, 40, 212, 16);
		panelGenelBilgiler.add(lblDers);
		
		JLabel lblDersAdi = new JLabel("Ders");
		lblDersAdi.setBounds(143, 41, 161, 14);
		panelGenelBilgiler.add(lblDersAdi);
		
		JLabel lblKategori = new JLabel("Kategori                 : ");
		lblKategori.setBounds(25, 78, 175, 16);
		panelGenelBilgiler.add(lblKategori);
		
		JLabel lblKategoriAdi = new JLabel("Kategori");
		lblKategoriAdi.setBounds(143, 79, 161, 14);
		panelGenelBilgiler.add(lblKategoriAdi);
		
		JLabel sad = new JLabel("\u0130stenilen Puan       : ");
		sad.setBounds(25, 323, 186, 16);
		panelGenelBilgiler.add(sad);
		
		JLabel labeliPuan = new JLabel("TPuan");
		labeliPuan.setBounds(143, 324, 161, 14);
		panelGenelBilgiler.add(labeliPuan);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(366, 11, 605, 612);
		getContentPane().add(tabbedPane_1);
		
		JPanel panelSorular = new JPanel();
		tabbedPane_1.addTab("Sorular", null, panelSorular, null);
		panelSorular.setLayout(null);
		
		JTabbedPane tabbedPaneCevap = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneCevap.setBounds(10, 223, 580, 290);
		panelSorular.add(tabbedPaneCevap);
		
		JPanel panelCoktanSecmeli = new JPanel();
		tabbedPaneCevap.addTab("Çoktan Seçmeli", null, panelCoktanSecmeli, null);
		panelCoktanSecmeli.setLayout(null);
		
		JRadioButton radioButtonA = new JRadioButton("A:)");
		radioButtonA.setEnabled(false);
		radioButtonA.setBounds(6, 11, 59, 38);
		panelCoktanSecmeli.add(radioButtonA);
		
		JRadioButton radioButtonB = new JRadioButton("B:)");
		radioButtonB.setEnabled(false);
		radioButtonB.setBounds(6, 74, 59, 38);
		panelCoktanSecmeli.add(radioButtonB);
		
		JRadioButton radioButtonC = new JRadioButton("C:)");
		radioButtonC.setEnabled(false);
		radioButtonC.setBounds(6, 139, 59, 38);
		panelCoktanSecmeli.add(radioButtonC);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(71, 137, 465, 54);
		panelCoktanSecmeli.add(scrollPane_2);
		
		JTextArea textAreaCCevap = new JTextArea();
		scrollPane_2.setViewportView(textAreaCCevap);
		textAreaCCevap.setEditable(false);
		textAreaCCevap.setFont(new Font("Monospaced", Font.PLAIN, 11));
		
		JRadioButton radioButtonD = new JRadioButton("D:)");
		radioButtonD.setEnabled(false);
		radioButtonD.setBounds(6, 200, 59, 46);
		panelCoktanSecmeli.add(radioButtonD);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(71, 200, 465, 51);
		panelCoktanSecmeli.add(scrollPane_3);
		
		JTextArea textAreaDCevap = new JTextArea();
		scrollPane_3.setViewportView(textAreaDCevap);
		textAreaDCevap.setEditable(false);
		textAreaDCevap.setFont(new Font("Monospaced", Font.PLAIN, 11));
		
		ButtonGroup bgListele = new ButtonGroup(); // grup elemanlarindan sadece biri secilebilsin
		bgListele.add(radioButtonA);
		bgListele.add(radioButtonB);
		bgListele.add(radioButtonC);
		bgListele.add(radioButtonD);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(71, 74, 465, 51);
		panelCoktanSecmeli.add(scrollPane_1);
		
		JTextArea textAreaBCevap = new JTextArea();
		scrollPane_1.setViewportView(textAreaBCevap);
		textAreaBCevap.setEditable(false);
		textAreaBCevap.setFont(new Font("Monospaced", Font.PLAIN, 11));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(71, 11, 465, 48);
		panelCoktanSecmeli.add(scrollPane);
		
		JTextArea textAreaACevap = new JTextArea();
		textAreaACevap.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textAreaACevap.setEditable(false);
		scrollPane.setViewportView(textAreaACevap);
		
		
		JPanel panelDogruYanlis = new JPanel();
		tabbedPaneCevap.addTab("Doğru Yanlış", null, panelDogruYanlis, null);
		panelDogruYanlis.setLayout(null);
		
		JRadioButton radioButtonDogru = new JRadioButton("Doğru");
		radioButtonDogru.setEnabled(false);
		radioButtonDogru.setBounds(18, 34, 109, 23);
		panelDogruYanlis.add(radioButtonDogru);
		
		JRadioButton radioButtonYanlis = new JRadioButton("Yanlış");
		radioButtonYanlis.setEnabled(false);
		radioButtonYanlis.setBounds(18, 78, 109, 23);
		panelDogruYanlis.add(radioButtonYanlis);
		
		ButtonGroup bgDYListele = new ButtonGroup(); // grup elemanlarindan sadece biri secilebilsin
		
		bgDYListele.add(radioButtonDogru);
		bgDYListele.add(radioButtonYanlis);
		
		JPanel panelKlasik = new JPanel();
		tabbedPaneCevap.addTab("Klasik", null, panelKlasik, null);
		panelKlasik.setLayout(null);
		
		JTextArea textAreaKlasikCevap = new JTextArea();
		textAreaKlasikCevap.setEditable(false);
		textAreaKlasikCevap.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textAreaKlasikCevap.setBounds(10, 52, 336, 140);
		panelKlasik.add(textAreaKlasikCevap);
		
		JLabel labelCevap = new JLabel("Cevap:");
		labelCevap.setBounds(10, 31, 230, 22);
		panelKlasik.add(labelCevap);
		
		
		
		JButton buttonGeri = new JButton("<< Geri");
		buttonGeri.setBounds(29, 550, 121, 23);
		panelSorular.add(buttonGeri);
		
		JButton btnSoruEkle = new JButton("Yeni Soru Ekle");
		btnSoruEkle.setBounds(246, 550, 131, 23);
		panelSorular.add(btnSoruEkle);
		
		JLabel ffff = new JLabel("S\u0131nav Ad\u0131               :");
		ffff.setBounds(25, 116, 212, 16);
		panelGenelBilgiler.add(ffff);
		
		JLabel lblSinavadi = new JLabel("SinavAdi");
		lblSinavadi.setBounds(143, 117, 314, 14);
		panelGenelBilgiler.add(lblSinavadi);
		lblSinavadi.setText(SinavOlustur.getTextFieldSinavAdi().getText());
		JButton btnIleri = new JButton("\u0130leri >>");
		
		btnIleri.setBounds(459, 548, 131, 27);
		panelSorular.add(btnIleri);
		
		JLabel lblPuan = new JLabel("Puan\u0131:");
		lblPuan.setBounds(10, 198, 60, 14);
		panelSorular.add(lblPuan);
		
		textFieldPuani = new JTextField();
		textFieldPuani.setBounds(58, 195, 51, 20);
		panelSorular.add(textFieldPuani);
		textFieldPuani.setColumns(10);
		
		JButton btnPuanGuncelle = new JButton("Puan\u0131 G\u00FCncelle");
		btnPuanGuncelle.setBounds(119, 194, 171, 23);
		panelSorular.add(btnPuanGuncelle);
		
		JLabel lblSoruId = new JLabel("Soru ID: ");
		lblSoruId.setBounds(0, 3, 64, 14);
		panelSorular.add(lblSoruId);
		
		JLabel lblIlk = new JLabel("1");
		lblIlk.setBounds(610, 15, 46, 14);
		getContentPane().add(lblIlk);
		
		JLabel lblSon = new JLabel("Son");
		lblSon.setBounds(624, 15, 46, 14);
		getContentPane().add(lblSon);
		
		textFieldSoruID = new JTextField();
		textFieldSoruID.setBounds(44, 0, 54, 20);
		panelSorular.add(textFieldSoruID);
		textFieldSoruID.setEditable(false);
		textFieldSoruID.setColumns(10);
		
		JButton btnSoruyuSil = new JButton("Soruyu \u00C7\u0131kar");
		btnSoruyuSil.setBounds(429, 515, 161, 23);
		panelSorular.add(btnSoruyuSil);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 28, 580, 159);
		panelSorular.add(scrollPane_4);
		
		JTextArea textAreasoru = new JTextArea();
		textAreasoru.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textAreasoru.setEditable(false);
		scrollPane_4.setViewportView(textAreasoru);
		
		JLabel lblNewLabel = new JLabel("Olu\u015Fturulan Puan : ");
		lblNewLabel.setBounds(25, 298, 130, 14);
		panelGenelBilgiler.add(lblNewLabel);
		
		JLabel lbloPuan = new JLabel("PuanSorular");
		lbloPuan.setBounds(143, 298, 94, 14);
		panelGenelBilgiler.add(lbloPuan);
		
		JButton btnCiktiAl = new JButton("\u00C7\u0131kt\u0131 Al");
		btnCiktiAl.setBounds(10, 585, 336, 38);
		getContentPane().add(btnCiktiAl);
		btnCiktiAl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Integer.parseInt(lbloPuan.getText()) < Integer.parseInt(labeliPuan.getText())){
					JOptionPane.showMessageDialog(null, "Olusturulan sorularin puani istenilen puandan daha az.");
				}
				else if(Integer.parseInt(lbloPuan.getText()) > Integer.parseInt(labeliPuan.getText())){
					JOptionPane.showMessageDialog(null, "Olusturulan sorularin puani istenilen puandan daha fazla.");
				}
				else {

					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								Facade.objDondur().allShowReport();

							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
		
		try {
			Connection con = DBConnection.dbConnector();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Sinav WHERE SinavAdi = '" + lblSinavadi.getText() + "'");
			
			Statement st2 = con.createStatement();
			ResultSet rs2 =  st2.executeQuery("SELECT ID FROM Sinav WHERE SinavAdi = '" + lblSinavadi.getText() + "'");
			
			while(rs2.next()) {
				sID = rs2.getInt(1);
			}
			
			while (rs.next()) {
				lblDersAdi.setText(rs.getString("DersAdi").toString()); 
				lblKategoriAdi.setText(rs.getString("KategoriAdi").toString());
				labeliPuan.setText(rs.getString("Puan").toString()); 

			}

			st.close();
			st2.close();
			con.close();
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}  
		
		


		int CoktanSec = 0, DY = 0, Klasik = 0;
		try {
			
			
		    Connection con = DBConnection.dbConnector(); 						
		    Statement st = con.createStatement();
		    ResultSet r = st.executeQuery("SELECT CoktanSecSayi, DYSayi, KlasikSayi FROM Sinav WHERE ID = " + sID);
				
		    while(r.next()) {
		    	CoktanSec = r.getInt(1);
		    	DY = r.getInt(2);
		    	Klasik = r.getInt(3);
		    }
		    st.close();
		    
		    Statement statement = con.createStatement();
		    

		    int KolayOlusturulan=0, OrtaOlusturulan=0, ZorOlusturulan=0;
		  //CoktanSecmeli sýnývýn kolay - orta - zor sorularýný sql de sýnav ID'sini update eden sorgular
			for(int i=0; i < CoktanSec; i++){
				if(KolayOlusturulan < SinavOlustur.k){
					statement.executeUpdate(
					"WITH cte AS (SELECT TOP 1 * FROM SoruBank WHERE SoruBank.KategoriID = "
						+ "(SELECT ID FROM Kategori WHERE Adi ='" + lblKategoriAdi.getText() + "') AND "
						+ "SoruBank.SoruTipiID = (SELECT ID From SoruTipi WHERE Adi = 'Çoktan Seçmeli') AND "
						+ "SoruBank.ZorlukID = (SELECT ID From Zorluk WHERE Adi = 'Kolay') AND COALESCE(SinavID, '') != " + sID + ")"
						+ "UPDATE cte SET SinavID = " + sID);
					KolayOlusturulan++;
				
				}else if(OrtaOlusturulan < SinavOlustur.o){
					statement.executeUpdate(
					"WITH cte AS (SELECT TOP 1 * FROM SoruBank WHERE SoruBank.KategoriID = "
						+ "(SELECT ID FROM Kategori WHERE Adi ='" + lblKategoriAdi.getText() + "') AND "
						+ "SoruBank.SoruTipiID = (SELECT ID From SoruTipi WHERE Adi = 'Çoktan Seçmeli') AND "
						+ "SoruBank.ZorlukID = (SELECT ID From Zorluk WHERE Adi = 'Orta') AND COALESCE(SinavID, '') != " + sID + ")"
						+ "UPDATE cte SET SinavID = " + sID);
					OrtaOlusturulan++;
				}else if(ZorOlusturulan < SinavOlustur.z){
					statement.executeUpdate(
					"WITH cte AS (SELECT TOP 1 * FROM SoruBank WHERE SoruBank.KategoriID = "
						+ "(SELECT ID FROM Kategori WHERE Adi ='" + lblKategoriAdi.getText() + "') AND "
						+ "SoruBank.SoruTipiID = (SELECT ID From SoruTipi WHERE Adi = 'Çoktan Seçmeli') AND "
						+ "SoruBank.ZorlukID = (SELECT ID From Zorluk WHERE Adi = 'Zor') AND COALESCE(SinavID, '') != " + sID + ")"
						+ "UPDATE cte SET SinavID = " + sID);
					ZorOlusturulan++;
				}
			}
			
			
			
			//Doðru Yanlýþ sýnývýn kolay - orta - zor sorularýný sql de sýnav ID'sini update eden sorgular
				for(int i=0; i < DY; i++){
					if(KolayOlusturulan < SinavOlustur.k){
						statement.executeUpdate(
						"WITH cte AS (SELECT TOP 1 * FROM SoruBank WHERE SoruBank.KategoriID = "
							+ "(SELECT ID FROM Kategori WHERE Adi ='" + lblKategoriAdi.getText() + "') AND "
							+ "SoruBank.SoruTipiID = (SELECT ID From SoruTipi WHERE Adi = 'Doðru Yanlýþ') AND "
							+ "SoruBank.ZorlukID = (SELECT ID From Zorluk WHERE Adi = 'Kolay') AND COALESCE(SinavID, '') != " + sID + ")"
							+ "UPDATE cte SET SinavID = " + sID);
						KolayOlusturulan++;
					
					}else if(OrtaOlusturulan < SinavOlustur.o){
						statement.executeUpdate(
						"WITH cte AS (SELECT TOP 1 * FROM SoruBank WHERE SoruBank.KategoriID = "
							+ "(SELECT ID FROM Kategori WHERE Adi ='" + lblKategoriAdi.getText() + "') AND "
							+ "SoruBank.SoruTipiID = (SELECT ID From SoruTipi WHERE Adi = 'Doðru Yanlýþ') AND "
							+ "SoruBank.ZorlukID = (SELECT ID From Zorluk WHERE Adi = 'Orta') AND COALESCE(SinavID, '') != " + sID + ")"
							+ "UPDATE cte SET SinavID = " + sID);
						OrtaOlusturulan++;
					}else if(ZorOlusturulan < SinavOlustur.z){
						statement.executeUpdate(
						"WITH cte AS (SELECT TOP 1 * FROM SoruBank WHERE SoruBank.KategoriID = "
							+ "(SELECT ID FROM Kategori WHERE Adi ='" + lblKategoriAdi.getText() + "') AND "
							+ "SoruBank.SoruTipiID = (SELECT ID From SoruTipi WHERE Adi = 'Doðru Yanlýþ') AND "
							+ "SoruBank.ZorlukID = (SELECT ID From Zorluk WHERE Adi = 'Zor') AND COALESCE(SinavID, '') != " + sID + ")"
							+ "UPDATE cte SET SinavID = " + sID);
						ZorOlusturulan++;
					}
				}
				
				
				//Klasik sýnývýn kolay - orta - zor sorularýný sql de sýnav ID'sini update eden sorgular
				for(int i=0; i < Klasik; i++){
					if(KolayOlusturulan < SinavOlustur.k){
						statement.executeUpdate(
						"WITH cte AS (SELECT TOP 1 * FROM SoruBank WHERE SoruBank.KategoriID = "
							+ "(SELECT ID FROM Kategori WHERE Adi ='" + lblKategoriAdi.getText() + "') AND "
							+ "SoruBank.SoruTipiID = (SELECT ID From SoruTipi WHERE Adi = 'Klasik') AND "
							+ "SoruBank.ZorlukID = (SELECT ID From Zorluk WHERE Adi = 'Kolay') AND COALESCE(SinavID, '') != " + sID + ")"
							+ "UPDATE cte SET SinavID = " + sID);
						KolayOlusturulan++;
					
					}else if(OrtaOlusturulan < SinavOlustur.o){
						statement.executeUpdate(
						"WITH cte AS (SELECT TOP 1 * FROM SoruBank WHERE SoruBank.KategoriID = "
							+ "(SELECT ID FROM Kategori WHERE Adi ='" + lblKategoriAdi.getText() + "') AND "
							+ "SoruBank.SoruTipiID = (SELECT ID From SoruTipi WHERE Adi = 'Klasik') AND "
							+ "SoruBank.ZorlukID = (SELECT ID From Zorluk WHERE Adi = 'Orta') AND COALESCE(SinavID, '') != " + sID + ")"
							+ "UPDATE cte SET SinavID = " + sID);
						OrtaOlusturulan++;
					}else if(ZorOlusturulan < SinavOlustur.z){
						statement.executeUpdate(
						"WITH cte AS (SELECT TOP 1 * FROM SoruBank WHERE SoruBank.KategoriID = "
							+ "(SELECT ID FROM Kategori WHERE Adi ='" + lblKategoriAdi.getText() + "') AND "
							+ "SoruBank.SoruTipiID = (SELECT ID From SoruTipi WHERE Adi = 'Klasik') AND "
							+ "SoruBank.ZorlukID = (SELECT ID From Zorluk WHERE Adi = 'Zor') AND COALESCE(SinavID, '') != " + sID + ")"
							+ "UPDATE cte SET SinavID = " + sID);
						ZorOlusturulan++;
					}
					
					
					
					
					
					
				}
			
		
		    
		    
			statement.close();
			con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
	

		try {
			Connection con = DBConnection.dbConnector();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM SoruBank WHERE SinavID = '" + sID + "'");
			while (rs.next()) {
				OlusturulanPuan += rs.getInt("Puan");
				soruIDs.add(rs.getInt("ID"));
				son++;
			}
			st.close();
			con.close();
		} catch (Exception ehu) {
			ehu.printStackTrace();
		}
		lbloPuan.setText(String.valueOf(OlusturulanPuan));
		
		JLabel lblDosyaAdGiriniz = new JLabel("Dosya Ad\u0131 Giriniz : ");
		lblDosyaAdGiriniz.setBounds(25, 425, 113, 16);
		panelGenelBilgiler.add(lblDosyaAdGiriniz);
		
		JLabel lblDosyaAdGirilmezse = new JLabel("Dosya ad\u0131 girilmezse dosya ad\u0131 \"s\u0131nav.pdf\" olarak kaydedilecektir.");
		lblDosyaAdGirilmezse.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblDosyaAdGirilmezse.setBounds(25, 443, 294, 16);
		panelGenelBilgiler.add(lblDosyaAdGirilmezse);
		
		textFieldDosyaAdi = new JTextField();
		textFieldDosyaAdi.setText("sinav");
		textFieldDosyaAdi.setBounds(141, 422, 116, 22);
		panelGenelBilgiler.add(textFieldDosyaAdi);
		textFieldDosyaAdi.setColumns(100);
		lblSon.setText(" / " + String.valueOf(son));
		
		JButton btnDosyaYoluSec = new JButton("Dosya Yolu Se\u00E7");
		btnDosyaYoluSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String secilendosya = dosyaSec();
				 if (secilendosya != null) {
				 Facade.objDondur().ciktiYoluAl(secilendosya);
				 } else {
				 
				 }
			}
		});
		
		
		btnDosyaYoluSec.setBounds(10, 526, 336, 38);
		getContentPane().add(btnDosyaYoluSec);
		

		
		if(tabbedPaneCevap.getTabCount() == 3) {
			tabbedPaneCevap.removeTabAt(2);
			tabbedPaneCevap.removeTabAt(1);
			tabbedPaneCevap.removeTabAt(0);
		}
		
		
		int TipID = 0;
		//Ýlk sorunun veritabanýndan çekilmesi
		try {
			Connection con = DBConnection.dbConnector();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT TOP 1 * FROM SoruBank WHERE SinavID = '" + sID + "'");
			while (rs.next()) {
				TipID = rs.getInt("SoruTipiID");
				textFieldSoruID.setText(String.valueOf(rs.getInt(1)));
				textFieldPuani.setText(String.valueOf(rs.getInt("Puan")));
				Statement st2 = con.createStatement();
				ResultSet rs2 = st2.executeQuery("SELECT Adi FROM SoruTipi WHERE ID = '" + TipID + "'");
				

				
			while(rs2.next()) {
				textAreasoru.setText(rs.getString("Soru"));
				
					if(rs2.getString(1).equals("Çoktan Seçmeli")) {
						if(tabbedPaneCevap.getTabCount() != 0)
							tabbedPaneCevap.removeTabAt(0);
					tabbedPaneCevap.addTab("\u00C7oktan Se\u00E7meli", null, panelCoktanSecmeli, null);
					textAreaACevap.setText(rs.getString("SikA"));
					textAreaBCevap.setText(rs.getString("SikB"));
					textAreaCCevap.setText(rs.getString("SikC"));
					textAreaDCevap.setText(rs.getString("SikD"));

							if(rs.getString("Cevap").equals("A")){
								radioButtonA.setSelected(true);
							}else if(rs.getString("Cevap").equals("B")) {
								radioButtonB.setSelected(true);
							}else if(rs.getString("Cevap").equals("C")) {
								radioButtonC.setSelected(true);
							}else if(rs.getString("Cevap").equals("D")) {
								radioButtonD.setSelected(true);
							}
					}
					
					if(rs2.getString(1).equals("Doðru Yanlýþ")) {
						if(tabbedPaneCevap.getTabCount() != 0)
							tabbedPaneCevap.removeTabAt(0);
						tabbedPaneCevap.addTab("Do\u011Fru Yanl\u0131\u015F", null, panelDogruYanlis, null);
						
							if(rs.getString("Cevap").equals("D")){
								radioButtonDogru.setSelected(true);
							}else if (rs.getString("Cevap").equals("Y")) {
								radioButtonYanlis.setSelected(true);
							}
					}
					
					if(rs2.getString(1).equals("Klasik")) {
						if(tabbedPaneCevap.getTabCount() != 0)
							tabbedPaneCevap.removeTabAt(0);
						tabbedPaneCevap.addTab("Klasik", null, panelKlasik, null);
						textAreaKlasikCevap.setText(rs.getString("Cevap"));
					}
				}
			st2.close();
			}
			st.close();

			con.close();
		}catch (SQLException e) {e.printStackTrace();} 
		
		//Ýleri Butonu
		btnIleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int TipID = 0;
				try {

					int s = ilk+1;
					if(s > son) {
						JOptionPane.showMessageDialog(null, "Sýnavdaki son sorudasýnýz.");
					}else {
					Connection con = DBConnection.dbConnector();
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT TOP 1 * FROM SoruBank WHERE SinavID = '" + sID + "' AND "
							+ "ID = " + soruIDs.get(++currentSoru));
					

					while (rs.next()) {
						TipID = rs.getInt("SoruTipiID");
						textFieldSoruID.setText(String.valueOf(rs.getInt(1)));
						textFieldPuani.setText(String.valueOf(rs.getInt("Puan")));
						Statement st2 = con.createStatement();
						ResultSet rs2 = st2.executeQuery("SELECT Adi FROM SoruTipi WHERE ID = '" + TipID + "'");
						lblIlk.setText(String.valueOf(++ilk));
						
					while(rs2.next()) {
						textAreasoru.setText(rs.getString("Soru"));
							if(rs2.getString(1).equals("Çoktan Seçmeli")) {
								if(tabbedPaneCevap.getTabCount() != 0)
									tabbedPaneCevap.removeTabAt(0);
							tabbedPaneCevap.addTab("\u00C7oktan Se\u00E7meli", null, panelCoktanSecmeli, null);
							textAreaACevap.setText(rs.getString("SikA"));
							textAreaBCevap.setText(rs.getString("SikB"));
							textAreaCCevap.setText(rs.getString("SikC"));
							textAreaDCevap.setText(rs.getString("SikD"));

									if(rs.getString("Cevap").equals("A")){
										radioButtonA.setSelected(true);
									}else if(rs.getString("Cevap").equals("B")) {
										radioButtonB.setSelected(true);
									}else if(rs.getString("Cevap").equals("C")) {
										radioButtonC.setSelected(true);
									}else if(rs.getString("Cevap").equals("D")) {
										radioButtonD.setSelected(true);
									}
							}
							
							if(rs2.getString(1).equals("Doðru Yanlýþ")) {
								if(tabbedPaneCevap.getTabCount() != 0)
									tabbedPaneCevap.removeTabAt(0);
								tabbedPaneCevap.addTab("Do\u011Fru Yanl\u0131\u015F", null, panelDogruYanlis, null);
								
									if(rs.getString("Cevap").equals("D")){
										radioButtonDogru.setSelected(true);
									}else if (rs.getString("Cevap").equals("Y")) {
										radioButtonYanlis.setSelected(true);
									}
							}
							
							if(rs2.getString(1).equals("Klasik")) {
								if(tabbedPaneCevap.getTabCount() != 0)
									tabbedPaneCevap.removeTabAt(0);
								tabbedPaneCevap.addTab("Klasik", null, panelKlasik, null);
								textAreaKlasikCevap.setText(rs.getString("Cevap"));
							}
					}
					st2.close();
					}
					st.close();
					con.close();
					}
				}catch (SQLException e) {e.printStackTrace();} 
			}
		});
		
		//Geri Butonu
		buttonGeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int TipID = 0;
				try {
					int i = ilk-1;
					if(i < 1) {
						JOptionPane.showMessageDialog(null, "Sýnavdaki ilk sorudasýnýz.");
					}
					else {
					Connection con = DBConnection.dbConnector();
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT TOP 1 * FROM SoruBank WHERE SinavID = '" + sID + "' AND "
							+ "ID = " + soruIDs.get(--currentSoru));
					
					while (rs.next()) {
						TipID = rs.getInt("SoruTipiID");
						textFieldSoruID.setText(String.valueOf(rs.getInt(1)));
						textFieldPuani.setText(String.valueOf(rs.getInt("Puan")));
						Statement st2 = con.createStatement();
						ResultSet rs2 = st2.executeQuery("SELECT Adi FROM SoruTipi WHERE ID = '" + TipID + "'");
						lblIlk.setText(String.valueOf(--ilk));
						
					while(rs2.next()) {
						textAreasoru.setText(rs.getString("Soru"));
							if(rs2.getString(1).equals("Çoktan Seçmeli")) {
								if(tabbedPaneCevap.getTabCount() != 0)
									tabbedPaneCevap.removeTabAt(0);
							tabbedPaneCevap.addTab("\u00C7oktan Se\u00E7meli", null, panelCoktanSecmeli, null);
							textAreaACevap.setText(rs.getString("SikA"));
							textAreaBCevap.setText(rs.getString("SikB"));
							textAreaCCevap.setText(rs.getString("SikC"));
							textAreaDCevap.setText(rs.getString("SikD"));

									if(rs.getString("Cevap").equals("A")){
										radioButtonA.setSelected(true);
									}else if(rs.getString("Cevap").equals("B")) {
										radioButtonB.setSelected(true);
									}else if(rs.getString("Cevap").equals("C")) {
										radioButtonC.setSelected(true);
									}else if(rs.getString("Cevap").equals("D")) {
										radioButtonD.setSelected(true);
									}
							}
							
							if(rs2.getString(1).equals("Doðru Yanlýþ")) {
								if(tabbedPaneCevap.getTabCount() != 0)
									tabbedPaneCevap.removeTabAt(0);
								tabbedPaneCevap.addTab("Do\u011Fru Yanl\u0131\u015F", null, panelDogruYanlis, null);
								
									if(rs.getString("Cevap").equals("D")){
										radioButtonDogru.setSelected(true);
									}else if (rs.getString("Cevap").equals("Y")) {
										radioButtonYanlis.setSelected(true);
									}
							}
							
							if(rs2.getString(1).equals("Klasik")) {
								if(tabbedPaneCevap.getTabCount() != 0)
									tabbedPaneCevap.removeTabAt(0);
								tabbedPaneCevap.addTab("Klasik", null, panelKlasik, null);
								textAreaKlasikCevap.setText(rs.getString("Cevap"));
							}
					}
					st2.close();
					}
					st.close();
					con.close();
					}
				}catch (SQLException e) {e.printStackTrace();} 
			}
		});
		
		btnSoruEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// PATATES
					Connection con = DBConnection.dbConnector();	
					Statement st6 = con.createStatement();
					ResultSet rss = st6.executeQuery("SELECT TOP 1 * FROM SoruBank WHERE SoruBank.KategoriID = "
							+ "(SELECT ID FROM Kategori WHERE Adi ='" + lblKategoriAdi.getText() + "') AND "
							+ "SoruBank.SoruTipiID = (SELECT ID From SoruTipi WHERE Adi = 'Çoktan Seçmeli') AND "
							+ "SoruBank.ZorlukID = (SELECT ID From Zorluk WHERE Adi = 'Zor') AND COALESCE(SinavID, '') != " + sID);
					if(!rss.next()) {
						JOptionPane.showMessageDialog(null, "Veritabanýnda ekleyecek soru yok.");
					}else {
						soruIDs.add(rss.getInt(1));
						OlusturulanPuan += (rss.getInt("Puan"));
						lbloPuan.setText(String.valueOf(OlusturulanPuan));
					st6.close();
					
					Statement st2 = con.createStatement();
					st2.executeUpdate(
					"WITH cte AS (SELECT TOP 1 * FROM SoruBank WHERE SoruBank.KategoriID = "
						+ "(SELECT ID FROM Kategori WHERE Adi ='" + lblKategoriAdi.getText() + "') AND "
						+ "SoruBank.SoruTipiID = (SELECT ID From SoruTipi WHERE Adi = 'Çoktan Seçmeli') AND "
						+ "SoruBank.ZorlukID = (SELECT ID From Zorluk WHERE Adi = 'Zor') AND COALESCE(SinavID, '') != " + sID + ")"
						+ "UPDATE cte SET SinavID = " + sID);
					st2.close();
				
					currentSoru = son;
					int TipID = 0;
					Statement st3 = con.createStatement();
					ResultSet rs = st3.executeQuery("SELECT TOP 1 * FROM SoruBank WHERE ID = " + soruIDs.get(currentSoru));
					while (rs.next()) {
						TipID = rs.getInt("SoruTipiID");
						textFieldSoruID.setText(String.valueOf(rs.getInt(1)));
						textFieldPuani.setText(String.valueOf(rs.getInt("Puan")));
						son++;
						ilk = son;
						lblIlk.setText(String.valueOf(ilk));
						lblSon.setText(" / " + String.valueOf(son));
						Statement st4 = con.createStatement();
						ResultSet rs2 = st4.executeQuery("SELECT Adi FROM SoruTipi WHERE ID = '" + TipID + "'");
						

						
					while(rs2.next()) {
						textAreasoru.setText(rs.getString("Soru"));
						
							if(rs2.getString(1).equals("Çoktan Seçmeli")) {
								if(tabbedPaneCevap.getTabCount() != 0)
									tabbedPaneCevap.removeTabAt(0);
							tabbedPaneCevap.addTab("\u00C7oktan Se\u00E7meli", null, panelCoktanSecmeli, null);
							textAreaACevap.setText(rs.getString("SikA"));
							textAreaBCevap.setText(rs.getString("SikB"));
							textAreaCCevap.setText(rs.getString("SikC"));
							textAreaDCevap.setText(rs.getString("SikD"));

									if(rs.getString("Cevap").equals("A")){
										radioButtonA.setSelected(true);
									}else if(rs.getString("Cevap").equals("B")) {
										radioButtonB.setSelected(true);
									}else if(rs.getString("Cevap").equals("C")) {
										radioButtonC.setSelected(true);
									}else if(rs.getString("Cevap").equals("D")) {
										radioButtonD.setSelected(true);
									}
							}
							
							if(rs2.getString(1).equals("Doðru Yanlýþ")) {
								if(tabbedPaneCevap.getTabCount() != 0)
									tabbedPaneCevap.removeTabAt(0);
								tabbedPaneCevap.addTab("Do\u011Fru Yanl\u0131\u015F", null, panelDogruYanlis, null);
								
									if(rs.getString("Cevap").equals("D")){
										radioButtonDogru.setSelected(true);
									}else if (rs.getString("Cevap").equals("Y")) {
										radioButtonYanlis.setSelected(true);
									}
							}
							
							if(rs2.getString(1).equals("Klasik")) {
								if(tabbedPaneCevap.getTabCount() != 0)
									tabbedPaneCevap.removeTabAt(0);
								tabbedPaneCevap.addTab("Klasik", null, panelKlasik, null);
								textAreaKlasikCevap.setText(rs.getString("Cevap"));
							}
						}
					st4.close();

					}
					st3.close();
					JOptionPane.showMessageDialog(null, "Sýnavýnýza yeni bir soru eklendi!");
					}
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnPuanGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int OlusturulanPuan = 0;
					Connection con = DBConnection.dbConnector();
					Statement st = con.createStatement();
					st.executeUpdate("UPDATE SoruBank SET Puan = " + textFieldPuani.getText() 
								+ " WHERE ID = " + textFieldSoruID.getText());
					st.close();

					Statement st2 = con.createStatement();
					ResultSet rs = st2.executeQuery("SELECT * FROM SoruBank WHERE SinavID = '" + sID + "'");
					while (rs.next()) {
						OlusturulanPuan += rs.getInt("Puan");
					}
					st2.close();
					con.close();
					lbloPuan.setText(String.valueOf(OlusturulanPuan));
				
					JOptionPane.showMessageDialog(null, "Sorunun puaný güncellendi!");
				} catch (Exception ehu) {
					ehu.printStackTrace();
					JOptionPane.showMessageDialog(null, "Sorunun puaný güncellenemedi.");
				}
			}
		});
		
		btnSoruyuSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				Connection con = DBConnection.dbConnector();
				Statement st6 = con.createStatement();
				ResultSet rss = st6.executeQuery("SELECT * FROM SoruBank WHERE ID = " + textFieldSoruID.getText());
				if(!rss.next()) {
					JOptionPane.showMessageDialog(null, "Soruyu çýkartýrken hata oluþtu.");
				}else {
					soruIDs.add(rss.getInt(1));
					OlusturulanPuan -= (rss.getInt("Puan"));
					lbloPuan.setText(String.valueOf(OlusturulanPuan));
					ilk = 1;
					lblIlk.setText(String.valueOf(ilk));
					lblSon.setText(String.valueOf(" / " + --son));
					
					Statement st2 = con.createStatement();
					st2.executeUpdate("UPDATE SoruBank SET SinavID = null WHERE ID = " + textFieldSoruID.getText());
					st2.close();
					
					soruIDs.remove(currentSoru);
					currentSoru = 0;
					int TipID = 0;
					Statement st3 = con.createStatement();
					ResultSet rs = st3.executeQuery("SELECT TOP 1 * FROM SoruBank WHERE ID = " + soruIDs.get(currentSoru));
					while (rs.next()) {
						TipID = rs.getInt("SoruTipiID");
						textFieldSoruID.setText(String.valueOf(rs.getInt(1)));
						textFieldPuani.setText(String.valueOf(rs.getInt("Puan")));
						Statement st4 = con.createStatement();
						ResultSet rs2 = st4.executeQuery("SELECT Adi FROM SoruTipi WHERE ID = '" + TipID + "'");
						

						
					while(rs2.next()) {
						textAreasoru.setText(rs.getString("Soru"));
						
							if(rs2.getString(1).equals("Çoktan Seçmeli")) {
								if(tabbedPaneCevap.getTabCount() != 0)
									tabbedPaneCevap.removeTabAt(0);
							tabbedPaneCevap.addTab("\u00C7oktan Se\u00E7meli", null, panelCoktanSecmeli, null);
							textAreaACevap.setText(rs.getString("SikA"));
							textAreaBCevap.setText(rs.getString("SikB"));
							textAreaCCevap.setText(rs.getString("SikC"));
							textAreaDCevap.setText(rs.getString("SikD"));

									if(rs.getString("Cevap").equals("A")){
										radioButtonA.setSelected(true);
									}else if(rs.getString("Cevap").equals("B")) {
										radioButtonB.setSelected(true);
									}else if(rs.getString("Cevap").equals("C")) {
										radioButtonC.setSelected(true);
									}else if(rs.getString("Cevap").equals("D")) {
										radioButtonD.setSelected(true);
									}
							}
							
							if(rs2.getString(1).equals("Doðru Yanlýþ")) {
								if(tabbedPaneCevap.getTabCount() != 0)
									tabbedPaneCevap.removeTabAt(0);
								tabbedPaneCevap.addTab("Do\u011Fru Yanl\u0131\u015F", null, panelDogruYanlis, null);
								
									if(rs.getString("Cevap").equals("D")){
										radioButtonDogru.setSelected(true);
									}else if (rs.getString("Cevap").equals("Y")) {
										radioButtonYanlis.setSelected(true);
									}
							}
							
							if(rs2.getString(1).equals("Klasik")) {
								if(tabbedPaneCevap.getTabCount() != 0)
									tabbedPaneCevap.removeTabAt(0);
								tabbedPaneCevap.addTab("Klasik", null, panelKlasik, null);
								textAreaKlasikCevap.setText(rs.getString("Cevap"));
							}
						}
					st4.close();
					
					}
					st3.close();
					JOptionPane.showMessageDialog(null, "Soru baþarýyla sýnavdan çýkarýldý.");
				}
				st6.close();
				
				soruIDs = new ArrayList<>();
				Statement stx = con.createStatement();
				ResultSet rsx = stx.executeQuery("SELECT * FROM SoruBank WHERE SinavID = '" + sID + "'");
				while (rsx.next()) {
					soruIDs.add(rsx.getInt("ID"));
				}
				stx.close();
				
				con.close();
				}catch(Exception e1) {e1.printStackTrace();}
			}
		});
		
	}
	public static String dosyaSec() {
		
		 JFileChooser jFileChooser = new JFileChooser();
		 jFileChooser.setCurrentDirectory(new File("C:\\Users\\(DESSAS)\\Desktop\\"));
		 jFileChooser.setDialogTitle("lutfen dosya seciniz");
		 jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		 
		 int donendeger = jFileChooser.showOpenDialog(null);
		
		 if (donendeger == JFileChooser.APPROVE_OPTION) {
		 return jFileChooser.getSelectedFile().getAbsolutePath();
		 } else {
		 return null;
		}
		
	 }

	public static JTextField getTextFieldSinavAdi() {
		// TODO Auto-generated method stub
		return textFieldDosyaAdi;
	}
}