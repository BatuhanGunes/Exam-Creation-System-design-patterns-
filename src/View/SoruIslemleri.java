package View;


import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import Model.DBConnection;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class SoruIslemleri extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private static SoruIslemleri obj=null;
	private static JTable table;
	private JTextField textFieldSoruID;

	
	public static SoruIslemleri getObj(){
        if(obj==null){
            obj=new SoruIslemleri();
        }return obj;
    }
	
	private SoruIslemleri() {
		
		setTitle("Soru Islemleri");
		setClosable(true);
		setBounds(0, 20, 1008, 700);
		getContentPane().setLayout(null);
		
		JPanel panelSoruCevap = new JPanel();
		panelSoruCevap.setBounds(270, 397, 700, 262);
		getContentPane().add(panelSoruCevap);
		panelSoruCevap.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 680, 240);
		panelSoruCevap.add(tabbedPane);
		
		JPanel panelSoru = new JPanel();
		panelSoru.setToolTipText("");
		tabbedPane.addTab("Soru", null, panelSoru, null);
		panelSoru.setLayout(null);
		
		JLabel lblSoruListele = new JLabel("Soru:");
		lblSoruListele.setBounds(22, 11, 59, 22);
		panelSoru.add(lblSoruListele);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(22, 44, 643, 157);
		panelSoru.add(scrollPane_1);
		
		JTextArea TextAreaSoru = new JTextArea();
		scrollPane_1.setViewportView(TextAreaSoru);
		TextAreaSoru.setFont(new Font("Monospaced", Font.PLAIN, 15));
		TextAreaSoru.setLineWrap(true);
		
		JTabbedPane tabbedPaneCevap = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Cevaplar", null, tabbedPaneCevap, null);
		
		JPanel panelCoktanSecmeli = new JPanel();
		panelCoktanSecmeli.setLayout(null);
		
		JRadioButton radioButtonA = new JRadioButton("A:)");
		radioButtonA.setBounds(6, 11, 59, 23);
		panelCoktanSecmeli.add(radioButtonA);
		
		JTextArea textAreaACevap = new JTextArea();
		textAreaACevap.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textAreaACevap.setText("A \u015E\u0131kk\u0131n\u0131 Giriniz");
		textAreaACevap.setBounds(71, 11, 589, 23);
		panelCoktanSecmeli.add(textAreaACevap);
		
		JRadioButton radioButtonB = new JRadioButton("B:)");
		radioButtonB.setBounds(6, 57, 59, 23);
		panelCoktanSecmeli.add(radioButtonB);
		
		JTextArea textAreaBCevap = new JTextArea();
		textAreaBCevap.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textAreaBCevap.setText("B \u015E\u0131kk\u0131n\u0131 Giriniz");
		textAreaBCevap.setBounds(71, 57, 589, 23);
		panelCoktanSecmeli.add(textAreaBCevap);
		
		JRadioButton radioButtonC = new JRadioButton("C:)");
		radioButtonC.setBounds(6, 102, 59, 23);
		panelCoktanSecmeli.add(radioButtonC);
		
		JTextArea textAreaCCevap = new JTextArea();
		textAreaCCevap.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textAreaCCevap.setText("C \u015E\u0131kk\u0131n\u0131 Giriniz");
		textAreaCCevap.setBounds(71, 102, 589, 23);
		panelCoktanSecmeli.add(textAreaCCevap);
		
		JRadioButton radioButtonD = new JRadioButton("D:)");
		radioButtonD.setBounds(6, 150, 59, 23);
		panelCoktanSecmeli.add(radioButtonD);
		
		ButtonGroup bgListele = new ButtonGroup(); // grup elemanlarindan sadece biri secilebilsin
		bgListele.add(radioButtonA);
		bgListele.add(radioButtonB);
		bgListele.add(radioButtonC);
		bgListele.add(radioButtonD);
		
		JTextArea textAreaDCevap = new JTextArea();
		textAreaDCevap.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textAreaDCevap.setText("D \u015E\u0131kk\u0131n\u0131 Giriniz");
		textAreaDCevap.setBounds(71, 150, 589, 23);
		panelCoktanSecmeli.add(textAreaDCevap);
		
		JPanel panelDogruYanlis = new JPanel();
		panelDogruYanlis.setLayout(null);
		
		JRadioButton radioButtonDogru = new JRadioButton("Do\u011Fru");
		radioButtonDogru.setBounds(18, 34, 109, 23);
		panelDogruYanlis.add(radioButtonDogru);
		
		JRadioButton radioButtonYanlis = new JRadioButton("Yanl\u0131\u015F");
		radioButtonYanlis.setBounds(18, 78, 109, 23);
		panelDogruYanlis.add(radioButtonYanlis);
		
		ButtonGroup bgDYListele = new ButtonGroup(); // grup elemanlarindan sadece biri secilebilsin
		bgDYListele.add(radioButtonDogru);
		bgDYListele.add(radioButtonYanlis);
		
		JPanel panelKlasik = new JPanel();
		panelKlasik.setLayout(null);
		
		JTextArea textAreaKlasikCevap = new JTextArea();
		textAreaKlasikCevap.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textAreaKlasikCevap.setText("Klasik Sorunun Cevab\u0131n\u0131 Giriniz.");
		textAreaKlasikCevap.setBounds(25, 33, 624, 140);
		panelKlasik.add(textAreaKlasikCevap);
		
		JLabel lblCevap = new JLabel("Cevap:");
		lblCevap.setBounds(25, 11, 59, 22);
		panelKlasik.add(lblCevap);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 434, 206, 2);
		getContentPane().add(separator);
		
		JLabel label = new JLabel("    Puan:");
		label.setBounds(10, 375, 74, 36);
		getContentPane().add(label);
		
		JComboBox comboBox_Puan = new JComboBox();
		comboBox_Puan.setBounds(96, 383, 123, 20);
		getContentPane().add(comboBox_Puan);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(13, 347, 206, 2);
		getContentPane().add(separator_1);
		
		JComboBox comboBox_Zorluk_Adi = new JComboBox();
		comboBox_Zorluk_Adi.setBounds(93, 286, 123, 20);
		getContentPane().add(comboBox_Zorluk_Adi);
		comboBox_Zorluk_Adi.setSelectedItem(null); 
		
		JLabel label_1 = new JLabel("    Zorluk:");
		label_1.setBounds(10, 278, 74, 36);
		getContentPane().add(label_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(13, 253, 206, 2);
		getContentPane().add(separator_2);
		
		JComboBox comboBox_Tip_Adi = new JComboBox();
		comboBox_Tip_Adi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(tabbedPaneCevap.getTabCount() != 0)
				tabbedPaneCevap.removeTabAt(0);
				if(comboBox_Tip_Adi.getSelectedIndex() != -1) {
				if(comboBox_Tip_Adi.getSelectedItem().toString().trim().equals("Çoktan Seçmeli")) {
				if(tabbedPaneCevap.getTabCount() != 0)
				tabbedPaneCevap.removeTabAt(0);
				tabbedPaneCevap.addTab("\u00C7oktan Se\u00E7meli", null, panelCoktanSecmeli, null);
				textAreaACevap.setText("A þýkkýný giriniz.");
				textAreaBCevap.setText("B þýkkýný giriniz.");
				textAreaCCevap.setText("C þýkkýný giriniz.");
				textAreaDCevap.setText("D þýkkýný giriniz.");
				bgListele.clearSelection();
				}
				
				if(comboBox_Tip_Adi.getSelectedItem().toString().trim().equals("Doðru Yanlýþ")) {
					if(tabbedPaneCevap.getTabCount() != 0)
					tabbedPaneCevap.removeTabAt(0);
					tabbedPaneCevap.addTab("Do\u011Fru Yanl\u0131\u015F", null, panelDogruYanlis, null);
					bgDYListele.clearSelection();
				}
				
				if(comboBox_Tip_Adi.getSelectedItem().toString().trim().equals("Klasik")) {
					if(tabbedPaneCevap.getTabCount() != 0)
					tabbedPaneCevap.removeTabAt(0);
					tabbedPaneCevap.addTab("Klasik", null, panelKlasik, null);
					textAreaKlasikCevap.setText("Klasik Sorunun Cevabýný Giriniz.");
				}
				}
			}
		});

		comboBox_Tip_Adi.setBounds(94, 199, 123, 20);
		getContentPane().add(comboBox_Tip_Adi);
		
		JLabel label_2 = new JLabel("    Tip:");
		label_2.setBounds(10, 191, 74, 36);
		getContentPane().add(label_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(11, 164, 206, 2);
		getContentPane().add(separator_3);
		
		JComboBox comboBox_Kategori_Adi = new JComboBox();
		comboBox_Kategori_Adi.setBounds(94, 110, 123, 20);
		getContentPane().add(comboBox_Kategori_Adi);
		
		JLabel label_3 = new JLabel("    Kategori:");
		label_3.setBounds(11, 102, 74, 36);
		getContentPane().add(label_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(11, 70, 206, 2);
		getContentPane().add(separator_4);
		
		
		JComboBox comboBox_Ders_Adi = new JComboBox();
		comboBox_Ders_Adi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox_Kategori_Adi.removeAllItems();
				
				//Kategori Adý ComboBox Doldurulmasý
				if(comboBox_Ders_Adi.getSelectedItem() != null) {
				try {
					Connection con = DBConnection.dbConnector();
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT ID FROM Ders WHERE Adi = '" + comboBox_Ders_Adi.getSelectedItem().toString() + "'");
			    	int DersID = 0;
			    	while(rs.next()) {
				    	DersID = rs.getInt(1);
			    	}
			    	ResultSet rs2 = st.executeQuery("SELECT Adi FROM Kategori WHERE DersID = " + DersID);
			    	while (rs2.next()) {  
						comboBox_Kategori_Adi.addItem(rs2.getString("Adi"));
			    	}
					st.close();
					con.close();
					} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}  
				}
				    comboBox_Kategori_Adi.setSelectedItem(null);
			}
		});
		comboBox_Ders_Adi.setBounds(95, 19, 123, 20);
		getContentPane().add(comboBox_Ders_Adi);
		
		JLabel label_4 = new JLabel("    Ders:");
		label_4.setBounds(11, 11, 74, 36);
		getContentPane().add(label_4);
		
		
		//Ekle Butonu
		JButton btnSoruEkle = new JButton("Soru Ekle");
		btnSoruEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
				    Connection con = DBConnection.dbConnector(); 						
				    Statement state = con.createStatement();
					 if((comboBox_Ders_Adi.getSelectedItem() == null || comboBox_Kategori_Adi.getSelectedItem() == null
					    		|| comboBox_Tip_Adi.getSelectedItem() == null || comboBox_Zorluk_Adi.getSelectedItem() == null
					    		|| comboBox_Puan.getSelectedItem() == null || TextAreaSoru.getText().isEmpty()) 
					    		) {
					    	JOptionPane.showMessageDialog(null, "Combobox bilgilerini eksiksiz giriniz.");
					    	
					 }
					 else if((!(radioButtonA.isSelected() || radioButtonB.isSelected() || radioButtonC.isSelected() || radioButtonD.isSelected())
				    			|| textAreaACevap.getText().isEmpty() || textAreaBCevap.getText().isEmpty() || textAreaCCevap.getText().isEmpty()
				    			|| textAreaDCevap.getText().isEmpty()) && comboBox_Tip_Adi.getSelectedItem().equals("Çoktan Seçmeli")) {

				    	JOptionPane.showMessageDialog(null, "Çoktan seçmeli Cevap bilgilerini giriniz.");
				    	
					    
				    }
					 else if(!(radioButtonDogru.isSelected() || radioButtonYanlis.isSelected()) && comboBox_Tip_Adi.getSelectedItem().equals("Doðru Yanlýþ")) {

					    	JOptionPane.showMessageDialog(null, "DY Cevap bilgilerini giriniz.");
					    	
				    }
					 else	if((textAreaKlasikCevap.getText().trim().isEmpty()) && comboBox_Tip_Adi.getSelectedItem().equals("Klasik")) {

						    	JOptionPane.showMessageDialog(null, "Klasik Cevap bilgilerini giriniz.");
						    	
						
						    }
				    
					 else {
						 int KategoriID = 0;
					    	int SoruTipiID = 0;
					    	int ZorlukID = 0;
						    if(comboBox_Kategori_Adi.getSelectedItem() != null) {
						    	ResultSet rs = state.executeQuery("SELECT ID FROM Kategori WHERE Adi = '" + comboBox_Kategori_Adi.getSelectedItem().toString() + "'");
						    	while(rs.next()) {
							    	KategoriID = rs.getInt(1);
						    	}
						    }
						    if(comboBox_Tip_Adi.getSelectedItem() != null) {
						    	ResultSet rs = state.executeQuery("SELECT ID FROM SoruTipi WHERE Adi = '" + comboBox_Tip_Adi.getSelectedItem().toString() + "'");
						    	while(rs.next()) {
							    	SoruTipiID = rs.getInt(1);
						    	}
						    }
						    if(comboBox_Zorluk_Adi.getSelectedItem() != null) {
						    	ResultSet rs = state.executeQuery("SELECT ID FROM Zorluk WHERE Adi = '" + comboBox_Zorluk_Adi.getSelectedItem().toString() + "'");
						    	while(rs.next()) {
							    	ZorlukID = rs.getInt(1);
						    	}
						    }
					    	String query = null;
					    	if(comboBox_Tip_Adi.getSelectedItem().toString().equals("Çoktan Seçmeli")) {
					    		String cevap = null;
					    		if(radioButtonA.isSelected())
					    			cevap = "A";
					    		if(radioButtonB.isSelected())
					    			cevap = "B";
					    		if(radioButtonC.isSelected())
					    			cevap = "C";
					    		if(radioButtonD.isSelected())
					    			cevap = "D";
					    		query = "INSERT INTO SoruBank (KategoriID, SoruTipiID, ZorlukID, Soru, Puan, SikA, SikB, SikC, SikD, Cevap) "
					    				+ "VALUES (" + KategoriID + "," + SoruTipiID + "," + ZorlukID + ", '" + TextAreaSoru.getText() + "', "
					    				+ comboBox_Puan.getSelectedItem() + ", '" + textAreaACevap.getText() + "', '"
					    				+ textAreaBCevap.getText() + "', '" + textAreaCCevap.getText() + "', '"
					    				+ textAreaDCevap.getText() + "', '" + cevap + "')";
					    	}
					    	if(comboBox_Tip_Adi.getSelectedItem().toString().equals("Doðru Yanlýþ")) {
					    		String cevap = null;
					    		if(radioButtonDogru.isSelected())
					    			cevap = "D";
					    		if(radioButtonYanlis.isSelected())
					    			cevap = "Y";
					    		query = "INSERT INTO SoruBank (KategoriID, SoruTipiID, ZorlukID, Soru, Puan, Cevap) "
					    				+ "VALUES (" + KategoriID + "," + SoruTipiID + "," + ZorlukID + ", '" + TextAreaSoru.getText() + "', "
					    				+ comboBox_Puan.getSelectedItem() + ", '" + cevap + "')";
					    	}
					    	if(comboBox_Tip_Adi.getSelectedItem().toString().equals("Klasik")) {
					    		String cevap = textAreaKlasikCevap.getText();
					    		query = "INSERT INTO SoruBank (KategoriID, SoruTipiID, ZorlukID, Soru, Puan, Cevap) "
					    				+ "VALUES (" + KategoriID + "," + SoruTipiID + "," + ZorlukID + ", '" + TextAreaSoru.getText() + "', "
					    				+ comboBox_Puan.getSelectedItem() + ", '" + cevap + "')";
					    	}
					    	state.executeUpdate(query);
					    	JOptionPane.showMessageDialog(null, "Ekleme iþlemi baþarýyla tamamlandý.");
					    	comboBox_Ders_Adi.setSelectedIndex(-1);
					    	comboBox_Kategori_Adi.setSelectedIndex(-1);
					    	comboBox_Puan.setSelectedIndex(-1);
					    	comboBox_Tip_Adi.setSelectedIndex(-1);
					    	comboBox_Zorluk_Adi.setSelectedIndex(-1);
					    	textFieldSoruID.setText(null);
					    	TextAreaSoru.setText(null);
							if(tabbedPaneCevap.getTabCount() != 0)
								tabbedPaneCevap.removeTabAt(0);
					 }
			    	
				    
				    	
				    
				    	
					state.close();
					con.close();
				    } catch (SQLException ee) {
						ee.printStackTrace();
					}
					TabloYazdir("");

					
			}
		});
		btnSoruEkle.setBounds(29, 466, 196, 57);
		getContentPane().add(btnSoruEkle);
		

		//Güncelle Butonu
		JButton btnSoruDüzenle = new JButton("Soru G\u00FCncelle");
		btnSoruDüzenle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    Connection con = DBConnection.dbConnector(); 						
				    Statement state = con.createStatement();
				    if((comboBox_Ders_Adi.getSelectedItem() == null || comboBox_Kategori_Adi.getSelectedItem() == null
				    		|| comboBox_Tip_Adi.getSelectedItem() == null || comboBox_Zorluk_Adi.getSelectedItem() == null
				    		|| comboBox_Puan.getSelectedItem() == null || TextAreaSoru.getText().isEmpty()) 
				    		) {
				    	JOptionPane.showMessageDialog(null, "Combobox bilgilerini eksiksiz giriniz.");
				    	
				 }
				 else if((!(radioButtonA.isSelected() || radioButtonB.isSelected() || radioButtonC.isSelected() || radioButtonD.isSelected())
			    			|| textAreaACevap.getText().isEmpty() || textAreaBCevap.getText().isEmpty() || textAreaCCevap.getText().isEmpty()
			    			|| textAreaDCevap.getText().isEmpty()) && comboBox_Tip_Adi.getSelectedItem().equals("Çoktan Seçmeli")) {

			    	JOptionPane.showMessageDialog(null, "Çoktan seçmeli Cevap bilgilerini giriniz.");
			    	
				    
			    }
				 else if(!(radioButtonDogru.isSelected() || radioButtonYanlis.isSelected()) && comboBox_Tip_Adi.getSelectedItem().equals("Doðru Yanlýþ")) {

				    	JOptionPane.showMessageDialog(null, "DY Cevap bilgilerini giriniz.");
				    	
			    }
				 else	if((textAreaKlasikCevap.getText().trim().isEmpty()) && comboBox_Tip_Adi.getSelectedItem().equals("Klasik")) {

					    	JOptionPane.showMessageDialog(null, "Klasik Cevap bilgilerini giriniz.");
					    	
					
					    }
			    
				 else {
				    	int KategoriID = 0;
				    	int SoruTipiID = 0;
				    	int ZorlukID = 0;
					    if(comboBox_Kategori_Adi.getSelectedItem() != null) {
					    	ResultSet rs = state.executeQuery("SELECT ID FROM Kategori WHERE Adi = '" + comboBox_Kategori_Adi.getSelectedItem().toString() + "'");
					    	while(rs.next()) {
						    	KategoriID = rs.getInt(1);
					    	}
					    }
					    if(comboBox_Tip_Adi.getSelectedItem() != null) {
					    	ResultSet rs = state.executeQuery("SELECT ID FROM SoruTipi WHERE Adi = '" + comboBox_Tip_Adi.getSelectedItem().toString() + "'");
					    	while(rs.next()) {
						    	SoruTipiID = rs.getInt(1);
					    	}
					    }
					    if(comboBox_Zorluk_Adi.getSelectedItem() != null) {
					    	ResultSet rs = state.executeQuery("SELECT ID FROM Zorluk WHERE Adi = '" + comboBox_Zorluk_Adi.getSelectedItem().toString() + "'");
					    	while(rs.next()) {
						    	ZorlukID = rs.getInt(1);
					    	}
					    }
				    	String query = null;
				    	if(comboBox_Tip_Adi.getSelectedItem().toString().equals("Çoktan Seçmeli")) {
				    		String cevap = null;
				    		if(radioButtonA.isSelected())
				    			cevap = "A";
				    		if(radioButtonB.isSelected())
				    			cevap = "B";
				    		if(radioButtonC.isSelected())
				    			cevap = "C";
				    		if(radioButtonD.isSelected())
				    			cevap = "D";
				    		query = "UPDATE SoruBank SET KategoriID = " + KategoriID + ", SoruTipiID = " + SoruTipiID + ", ZorlukID = " + ZorlukID
				    				+ ", Soru = '" + TextAreaSoru.getText() + "', Puan = " + comboBox_Puan.getSelectedItem() + ", SikA = '"
				    				+ textAreaACevap.getText() + "', SikB = '" + textAreaBCevap.getText() + "', SikC = '"
				    				+ textAreaCCevap.getText() + "', SikD = '" + textAreaDCevap.getText() + "', Cevap = '" + cevap + "' WHERE ID = "
				    				+ textFieldSoruID.getText();
				    	}
				    	if(comboBox_Tip_Adi.getSelectedItem().toString().equals("Doðru Yanlýþ")) {
				    		String cevap = null;
				    		if(radioButtonDogru.isSelected())
				    			cevap = "D";
				    		if(radioButtonYanlis.isSelected())
				    			cevap = "Y";
				    		query = "UPDATE SoruBank SET KategoriID = " + KategoriID + ", SoruTipiID = " + SoruTipiID + ", ZorlukID = " + ZorlukID
				    				+ ", Soru = '" + TextAreaSoru.getText() + "', Puan = " + comboBox_Puan.getSelectedItem() + ", SikA = "
				    				+ "null" + ", SikB = " + "null" + ", SikC = "
				    				+ "null" + ", SikD = " + "null" + ", Cevap = '" + cevap + "' WHERE ID = "
				    				+ textFieldSoruID.getText();
				    	}
				    	if(comboBox_Tip_Adi.getSelectedItem().toString().equals("Klasik")) {
				    		String cevap = textAreaKlasikCevap.getText();
				    		query = "UPDATE SoruBank SET KategoriID = " + KategoriID + ", SoruTipiID = " + SoruTipiID + ", ZorlukID = " + ZorlukID
				    				+ ", Soru = '" + TextAreaSoru.getText() + "', Puan = " + comboBox_Puan.getSelectedItem() + ", SikA = "
				    				+ "null" + ", SikB = " + "null" + ", SikC = "
				    				+ "null" + ", SikD = " + "null" + ", Cevap = '" + cevap + "' WHERE ID = "
				    				+ textFieldSoruID.getText();
				    	}
				    	state.executeUpdate(query);
				    	JOptionPane.showMessageDialog(null, "Güncelleme iþlemi baþarýyla tamamlandý.");
				    	comboBox_Ders_Adi.setSelectedIndex(-1);
				    	comboBox_Kategori_Adi.setSelectedIndex(-1);
				    	comboBox_Puan.setSelectedIndex(-1);
				    	comboBox_Tip_Adi.setSelectedIndex(-1);
				    	comboBox_Zorluk_Adi.setSelectedIndex(-1);
				    	TextAreaSoru.setText(null);
				    	textFieldSoruID.setText(null);
						if(tabbedPaneCevap.getTabCount() != 0)
							tabbedPaneCevap.removeTabAt(0);
				    }
					state.close();
					con.close();
					} catch (SQLException ee) {
						ee.printStackTrace();
					}
					TabloYazdir("");
			}
		});
		btnSoruDüzenle.setBounds(29, 534, 196, 57);
		getContentPane().add(btnSoruDüzenle);
		
		//Sil Butonu
		JButton btnSoruSil = new JButton("Soru Sil");
		btnSoruSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
				    Connection con = DBConnection.dbConnector(); 						
				    Statement state = con.createStatement();
				    if(!textFieldSoruID.getText().isEmpty()) {
				    	state.executeUpdate("DELETE FROM SoruBank WHERE ID = " + textFieldSoruID.getText());
						JOptionPane.showMessageDialog(null, "Silme iþlemi baþarýyla tamamlandý.");
				    	comboBox_Ders_Adi.setSelectedIndex(-1);
				    	comboBox_Kategori_Adi.setSelectedIndex(-1);
				    	comboBox_Puan.setSelectedIndex(-1);
				    	comboBox_Tip_Adi.setSelectedIndex(-1);
				    	comboBox_Zorluk_Adi.setSelectedIndex(-1);
				    	TextAreaSoru.setText(null);
				    	textFieldSoruID.setText(null);
						if(tabbedPaneCevap.getTabCount() != 0)
							tabbedPaneCevap.removeTabAt(0);
				    } else
				    	JOptionPane.showMessageDialog(null, "Ýlk önce tablodan bir veri seçiniz.");
					state.close();
					} catch (SQLException ee) {
						ee.printStackTrace();
					} 
					TabloYazdir("");
			    	
			
			}
		});
		btnSoruSil.setBounds(29, 602, 196, 57);
		getContentPane().add(btnSoruSil);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(270, 59, 700, 327);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int index = table.getSelectedRow();
				
				textFieldSoruID.setText(model.getValueAt(index,0).toString());
				comboBox_Tip_Adi.setSelectedItem(model.getValueAt(index,1).toString());
				TextAreaSoru.setText(model.getValueAt(index,2).toString());
				comboBox_Ders_Adi.setSelectedItem(model.getValueAt(index,8).toString());
				comboBox_Kategori_Adi.setSelectedItem(model.getValueAt(index,9).toString());
				comboBox_Zorluk_Adi.setSelectedItem(model.getValueAt(index,10).toString());
				comboBox_Puan.setSelectedItem(model.getValueAt(index,11).toString());


				textAreaKlasikCevap.setText(model.getValueAt(index,7).toString());
				
				if(model.getValueAt(index,1).toString().trim().equals("Çoktan Seçmeli")) {
					textAreaACevap.setText(model.getValueAt(index,3).toString());
					textAreaBCevap.setText(model.getValueAt(index,4).toString());
					textAreaCCevap.setText(model.getValueAt(index,5).toString());
					textAreaDCevap.setText(model.getValueAt(index,6).toString());
					if(model.getValueAt(index, 7).toString().trim().equals("A"))
						radioButtonA.setSelected(true);
					if(model.getValueAt(index, 7).toString().trim().equals("B"))
						radioButtonB.setSelected(true);
					if(model.getValueAt(index, 7).toString().trim().equals("C"))
						radioButtonC.setSelected(true);
					if(model.getValueAt(index, 7).toString().trim().equals("D"))
						radioButtonD.setSelected(true);

				}
				
				if(model.getValueAt(index,1).toString().trim().equals("Doðru Yanlýþ")) {
					if(model.getValueAt(index, 7).toString().trim().equals("D"))
						radioButtonDogru.setSelected(true);
					if(model.getValueAt(index, 7).toString().trim().equals("Y"))
						radioButtonYanlis.setSelected(true);

				}
				
				if(model.getValueAt(index,1).toString().trim().equals("Klasik")) {
					textAreaKlasikCevap.setText(model.getValueAt(index, 7).toString());

				}
			}
		});
		scrollPane.setViewportView(table);
		
		
		
		JLabel lblNewLabel = new JLabel("Ders");
		lblNewLabel.setBounds(393, 11, 105, 14);
		getContentPane().add(lblNewLabel);
		
		JComboBox cmbAraKategori = new JComboBox();
		cmbAraKategori.setBounds(543, 27, 131, 20);
		getContentPane().add(cmbAraKategori);
		
		JLabel lblKategori = new JLabel("Kategori");
		lblKategori.setBounds(544, 11, 130, 14);
		getContentPane().add(lblKategori);
		
		JComboBox cmbAraZorluk = new JComboBox();
		cmbAraZorluk.setBounds(698, 27, 113, 20);
		getContentPane().add(cmbAraZorluk);
		
		JLabel lblZorluk = new JLabel("Zorluk");
		lblZorluk.setBounds(698, 11, 113, 14);
		getContentPane().add(lblZorluk);
		
		JLabel lblPuan = new JLabel("Puan");
		lblPuan.setBounds(821, 11, 123, 14);
		getContentPane().add(lblPuan);
		
		JComboBox cmbAraPuan = new JComboBox();
		cmbAraPuan.setBounds(821, 27, 46, 20);
		getContentPane().add(cmbAraPuan);
		
		JLabel lblSrala = new JLabel("SIRALA:");
		lblSrala.setBounds(349, 27, 46, 20);
		getContentPane().add(lblSrala);
		
		
		
 
		
		//Tip Adý ComboBox Doldurulmasý
		try {
			Connection con = DBConnection.dbConnector();
			Statement st = con.createStatement();
			ResultSet r = st.executeQuery("SELECT * FROM SoruTipi");
			while (r.next()) {  
					comboBox_Tip_Adi.addItem(r.getString("Adi"));
			}
			st.close();
			con.close();
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			} comboBox_Tip_Adi.setSelectedItem(null);
		
		//Zorluk Adý ComboBox Doldurulmasý
		try {
			Connection con = DBConnection.dbConnector();
			Statement st = con.createStatement();
			ResultSet r = st.executeQuery("SELECT * FROM Zorluk");
			while (r.next()) {  
					comboBox_Zorluk_Adi.addItem(r.getString("Adi"));
					cmbAraZorluk.addItem(r.getString("Adi"));
			} comboBox_Zorluk_Adi.setSelectedItem(null);
			cmbAraZorluk.setSelectedItem(null);
			st.close();
			con.close();
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}

		
		//PuanComboBox Doldurulmasý
			comboBox_Puan.addItem("5");
			comboBox_Puan.addItem("10");
			comboBox_Puan.addItem("20");
			comboBox_Puan.addItem("25");
			comboBox_Puan.setSelectedItem(null);
			
			cmbAraPuan.addItem("5");
			cmbAraPuan.addItem("10");
			cmbAraPuan.addItem("20");
			cmbAraPuan.addItem("25");
			cmbAraPuan.setSelectedItem(null);
			
			JLabel lblSoruId = new JLabel("Soru ID");
			lblSoruId.setBounds(240, 27, 63, 20);
			getContentPane().add(lblSoruId);
			
			textFieldSoruID = new JTextField();
			textFieldSoruID.setEditable(false);
			textFieldSoruID.setBounds(280, 27, 63, 20);
			getContentPane().add(textFieldSoruID);
			textFieldSoruID.setColumns(10);
			
			
			
			JButton btnSifirla = new JButton("S\u0131f\u0131rla");
			btnSifirla.setBounds(881, 24, 89, 23);
			getContentPane().add(btnSifirla);
			
			JComboBox cmbAraDers = new JComboBox();
			cmbAraDers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cmbAraKategori.removeAllItems();
					if(cmbAraDers.getSelectedItem() != null) {
						cmbAraPuan.setSelectedItem(null);
						cmbAraZorluk.setSelectedItem(null);
					
					//Kategori Adý ComboBox Doldurulmasý
					if(cmbAraDers.getSelectedItem() != null) {
					try {
						Connection con = DBConnection.dbConnector();
						Statement st = con.createStatement();
						ResultSet rsx = st.executeQuery("SELECT ID FROM Ders WHERE Adi = '" + cmbAraDers.getSelectedItem().toString() + "'");
				    	int DersID = 0;
				    	while(rsx.next()) {
					    	DersID = rsx.getInt(1);
				    	}
				    	ResultSet rs2 = st.executeQuery("SELECT Adi FROM Kategori WHERE DersID = " + DersID);
				    	while (rs2.next()) {  
				    		cmbAraKategori.addItem(rs2.getString("Adi"));
				    	}
						st.close();
						con.close();
						} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
						}  
					}
					cmbAraKategori.setSelectedItem(null);
					String query = "WHERE Ders.Adi = '" + cmbAraDers.getSelectedItem() + "'";
					TabloYazdir(query);
					}
				}
			});
			cmbAraDers.setBounds(393, 27, 121, 20);
			getContentPane().add(cmbAraDers);
			
			//Ders Adý ComboBox Doldurulmasý
			try {
				Connection con = DBConnection.dbConnector();
				Statement st = con.createStatement();
				ResultSet r = st.executeQuery("SELECT * FROM Ders");
				while (r.next()) {  
						comboBox_Ders_Adi.addItem(r.getString("Adi"));
						cmbAraDers.addItem(r.getString("Adi"));
				}
				st.close();
				con.close();
				} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				} comboBox_Ders_Adi.setSelectedItem(null); 	    
				cmbAraDers.setSelectedItem(null); 	  
			
				btnSifirla.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cmbAraDers.setSelectedItem(null);
						cmbAraKategori.setSelectedItem(null);
						cmbAraPuan.setSelectedItem(null);
						cmbAraZorluk.setSelectedItem(null);
						TabloYazdir("");
					}
				});
				
				cmbAraKategori.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(cmbAraKategori.getSelectedItem() != null) {
						try {
							int kid = 0;
						    Connection con = DBConnection.dbConnector(); 						//Database ile baðlantýmýzý oluþturduk.
							Statement st = con.createStatement();
							ResultSet r = st.executeQuery("SELECT ID FROM Kategori WHERE Adi = '" + cmbAraKategori.getSelectedItem().toString() + "'");
							while (r.next()) {  
								kid = r.getInt("ID");
							}
							st.close();
						    String query = "WHERE SoruBank.KategoriID = " + kid;
						    TabloYazdir(query);
					}catch(Exception e1) {e1.printStackTrace();}
						}
					}
				});
				
				cmbAraZorluk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(cmbAraZorluk.getSelectedItem() != null) {
						cmbAraDers.setSelectedItem(null);
						cmbAraKategori.setSelectedItem(null);
						cmbAraPuan.setSelectedItem(null);
						
						String query = "WHERE Zorluk.Adi = '" + cmbAraZorluk.getSelectedItem() + "'";
						TabloYazdir(query);
						}
					}
				});
				
				cmbAraPuan.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(cmbAraPuan.getSelectedItem() != null) {
						cmbAraDers.setSelectedItem(null);
						cmbAraKategori.setSelectedItem(null);
						cmbAraZorluk.setSelectedItem(null);
						
						String query = "WHERE SoruBank.Puan = " + cmbAraPuan.getSelectedItem();
						TabloYazdir(query);
						}
					}
				});
				
		TabloYazdir("");
		
	}
	
		// Her buton iþleminden sonra tablo güncellenmek amacý ile tabloyu tekrar tekrar yazdýrma iþlemlemlerinin terkrarýnýn önlenmesi için
		//Tablo yazdýr methodunun oluþturduk ve bu methodu yukarýda çaðýrdýk.
		public static void TabloYazdir(String query) {
			
			try {
				
			    Connection con = DBConnection.dbConnector(); 						//Database ile baðlantýmýzý oluþturduk.
			    Statement state = con.createStatement();
				ResultSet rs = state.executeQuery("SELECT        SoruBank.ID, SoruTipi.Adi AS \"Soru Tipi\", SoruBank.Soru, SoruBank.SikA as \"A Þýkký\", SoruBank.SikB as \"B Þýkký\", SoruBank.SikC as \"C Þýkký\", SoruBank.SikD as \"D Þýkký\", SoruBank.Cevap, Ders.Adi AS \"Ders\", Kategori.Adi AS Kategori, Zorluk.Adi as \"Zorluk\", SoruBank.Puan\r\n" + 
						"FROM            Ders INNER JOIN\r\n" + 
						"                         Kategori ON Ders.ID = Kategori.DersID INNER JOIN\r\n" + 
						"                         SoruBank ON Kategori.ID = SoruBank.KategoriID INNER JOIN\r\n" + 
						"                         SoruTipi ON SoruBank.SoruTipiID = SoruTipi.ID INNER JOIN\r\n" + 
						"                         Zorluk ON SoruBank.ZorlukID = Zorluk.ID " + query);		//istenilen query 'nin yazýmýnýn geçcekleþtirileceði alan
				
				ResultSetMetaData rsmd = rs.getMetaData();							//Alýnan sonucu metadata olarak rsmd deðiþkenine aktarýyoruz.
				int columns = rsmd.getColumnCount();								//Sütun sayýsýný alýyoruz.
				DefaultTableModel dtm = new DefaultTableModel();					//alýnan verileri hangi format doðrultusunda tabloya yazdýrýlýcaðýný söylüyoruz.
				Vector columns_name = new Vector();
				Vector data_rows = new Vector();
				
				for (int i=1; i<=columns; i++) {									//sütün sayýsý kadar döndürerek sütün isimlerini alýp ekliyoruz.
					columns_name.addElement(rsmd.getColumnName(i));
				}
				
				dtm.setColumnIdentifiers(columns_name);								  
				
				while(rs.next()) {
					data_rows = new Vector();
					for(int j=1;j<=columns;j++) {									//deðerlerin sayýsý kadar döndürüp deðerleri tabloya ekliyoruz.
						data_rows.addElement(rs.getString(j));
					}
					dtm.addRow(data_rows);											
					
				}
				table.setModel(dtm);												//tablomuzun modelini deðiþtiriyoruz.
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
		}
}
