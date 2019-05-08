package View;


import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;



import Model.DBConnection;
import Strategy.KolaySinav;
import Strategy.OrtaSinav;
import Strategy.SoruZorlugu;
import Strategy.ZorSinav;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.event.ItemListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;



public class SinavOlustur extends JInternalFrame {

	private JPanel contentPane;
	static int k, o, z;
	private static SinavOlustur obj=null;
	private static JTextField textFieldIstenilenPuan;
	private static JTextField textFieldSinavAdi;
	private static String Zorluk;
	static JSpinner spinnerCoktan = new JSpinner();
	static JSpinner spinnerDY = new JSpinner();
	static JSpinner spinnerKlasik = new JSpinner();
	private JTextField textFieldOgretimUyesi;
	
	public static SinavOlustur getObj(){
        if(obj==null){
            obj=new SinavOlustur();
        }return obj;
    }
	
	private SinavOlustur() {
		setTitle("Sinav Olustur");
		setClosable(true);
		setBounds(0, 20, 1008, 700);

		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.scrollbar);
		desktopPane.setBounds(0, 0, 992, 670);
		contentPane.add(desktopPane);
		
		JTabbedPane tabbedPaneSinavBilgileri = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneSinavBilgileri.setBounds(37, 42, 409, 468);
		desktopPane.add(tabbedPaneSinavBilgileri);
		
		JPanel panelSinavBilgileri = new JPanel();
		tabbedPaneSinavBilgileri.addTab("S\u0131nav Bilgileri", null, panelSinavBilgileri, null);
		panelSinavBilgileri.setLayout(null);
		
		JLabel lblSinavAdi = new JLabel("S\u0131nav Ad\u0131 Giriniz :");
		lblSinavAdi.setBounds(25, 45, 123, 16);
		panelSinavBilgileri.add(lblSinavAdi);
		
		textFieldSinavAdi = new JTextField();
		textFieldSinavAdi.setBounds(25, 74, 331, 22);
		panelSinavBilgileri.add(textFieldSinavAdi);
		textFieldSinavAdi.setColumns(10);
		
		JComboBox comboBoxDers = new JComboBox();
		comboBoxDers.setBounds(134, 138, 222, 20);
		panelSinavBilgileri.add(comboBoxDers);
		
		JLabel lblDers = new JLabel("Ders :");
		lblDers.setBounds(25, 140, 56, 16);
		panelSinavBilgileri.add(lblDers);
		
		JLabel lblKategori = new JLabel("Kategori : ");
		lblKategori.setBounds(25, 185, 67, 16);
		panelSinavBilgileri.add(lblKategori);
		
		JComboBox comboBoxKategori = new JComboBox();
		comboBoxKategori.setBounds(134, 183, 222, 20);
		panelSinavBilgileri.add(comboBoxKategori);
		
		
		JLabel lblIstenilenPuan = new JLabel("\u0130stenilen Puan : ");
		lblIstenilenPuan.setBounds(24, 354, 97, 16);
		panelSinavBilgileri.add(lblIstenilenPuan);
		
		textFieldIstenilenPuan = new JTextField();
		textFieldIstenilenPuan.setBounds(133, 351, 67, 22);
		panelSinavBilgileri.add(textFieldIstenilenPuan);
		textFieldIstenilenPuan.setColumns(10);
		
		
		textFieldOgretimUyesi = new JTextField();
		textFieldOgretimUyesi.setEditable(false);
		textFieldOgretimUyesi.setColumns(10);
		textFieldOgretimUyesi.setBounds(25, 279, 331, 22);
		panelSinavBilgileri.add(textFieldOgretimUyesi);
		
		JLabel lblDersiVerenretim = new JLabel("Dersi Veren \u00D6\u011Fretim \u00DCyesi : ");
		lblDersiVerenretim.setBounds(25, 250, 237, 16);
		panelSinavBilgileri.add(lblDersiVerenretim);
		
		JTabbedPane tabbedPaneZorluk = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneZorluk.setBounds(527, 267, 360, 243);
		desktopPane.add(tabbedPaneZorluk);
		
		JPanel panelZorluk = new JPanel();
		tabbedPaneZorluk.addTab("Zorluk", null, panelZorluk, null);
		panelZorluk.setLayout(null);
		
		ButtonGroup btnGrp = new ButtonGroup();
		
		JRadioButton rdbtnKolay = new JRadioButton("Kolay (%50 Kolay %30 Orta %20 Zor)");
		rdbtnKolay.setBounds(32, 27, 245, 25);
		panelZorluk.add(rdbtnKolay);
		
		JRadioButton rdbtnOrta = new JRadioButton("Orta (%50 Orta %30 Kolay %20 Zor)");
		rdbtnOrta.setBounds(32, 83, 269, 25);
		panelZorluk.add(rdbtnOrta);
		
		JRadioButton rdbtnZor = new JRadioButton("Zor (%50 Zor %30 Orta %20 Kolay)");
		rdbtnZor.setBounds(32, 140, 245, 25);
		panelZorluk.add(rdbtnZor);
		btnGrp.add(rdbtnKolay);
		btnGrp.add(rdbtnOrta);
		btnGrp.add(rdbtnZor);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(527, 42, 360, 176);
		desktopPane.add(tabbedPane);
		
		JPanel panelSoruTipleri = new JPanel();
		panelSoruTipleri.setToolTipText("");
		tabbedPane.addTab("Soru Tipleri", null, panelSoruTipleri, null);
		panelSoruTipleri.setLayout(null);
		
		JLabel lblCoktanSecmeli = new JLabel("\u00C7oktan Se\u00E7meli");
		lblCoktanSecmeli.setBounds(38, 31, 95, 16);
		panelSoruTipleri.add(lblCoktanSecmeli);
		
		JLabel lblDogruYanlis = new JLabel("Do\u011Fru Yanl\u0131\u015F");
		lblDogruYanlis.setBounds(143, 31, 95, 16);
		panelSoruTipleri.add(lblDogruYanlis);
		
		JLabel lblKlasik = new JLabel("Klasik");
		lblKlasik.setBounds(248, 31, 56, 16);
		panelSoruTipleri.add(lblKlasik);
		
		
		spinnerCoktan.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		
		spinnerCoktan.setBounds(38, 67, 56, 36);
		panelSoruTipleri.add(spinnerCoktan);
		
		
		spinnerDY.setBounds(143, 67, 56, 36);
		panelSoruTipleri.add(spinnerDY);
		
		
		spinnerKlasik.setBounds(248, 67, 56, 36);
		panelSoruTipleri.add(spinnerKlasik);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(14, 549, 968, 2);
		desktopPane.add(separator_2);
		
		
		JButton btnIleri = new JButton("S\u0131nav\u0131 Olu\u015Ftur");
		btnIleri.setBounds(778, 591, 168, 39);
		desktopPane.add(btnIleri);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(527, 239, 360, 2);
		desktopPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(484, 70, 20, 440);
		desktopPane.add(separator_1);
		

			comboBoxDers.removeAllItems();
		
		//Ders Ad� ComboBox Doldurulmas�
				try {
					Connection con = DBConnection.dbConnector();
					Statement st = con.createStatement();
					ResultSet r = st.executeQuery("SELECT * FROM Ders");
					while (r.next()) {  
							comboBoxDers.addItem(r.getString("Adi"));
					}
					st.close();
					con.close();
					} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}  	 comboBoxDers.setSelectedItem(null);  
				
		
					comboBoxDers.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent arg0) {

							
							//��retimUyesi Ad� ComboBox Doldurulmas�
							try {
								Connection con = DBConnection.dbConnector();
								Statement st = con.createStatement();
								
								int OgretimUyesiID = 0;
								String OgretimUyesiAdi = null;
								ResultSet rs = st.executeQuery("SELECT OgretimUyesiID FROM Ders WHERE Adi = '" + comboBoxDers.getSelectedItem().toString() + "'");
									while(rs.next()) {
										OgretimUyesiID = rs.getInt(1);
							    	}
						    	ResultSet rs2 = st.executeQuery("SELECT Adi FROM OgretimUyesi WHERE ID = " + OgretimUyesiID);
							    	while(rs2.next()) {
										OgretimUyesiAdi = rs2.getString("Adi");
							    	}
							    		textFieldOgretimUyesi.setText(OgretimUyesiAdi);
						    		
								st.close();
								con.close();
								} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
								} 

							    
							    
							    
							comboBoxKategori.removeAllItems();

							//Kategori Ad� ComboBox Doldurulmas�
							try {
								Connection con = DBConnection.dbConnector();
								Statement st = con.createStatement();
								ResultSet rs = st.executeQuery("SELECT ID FROM Ders WHERE Adi = '" + comboBoxDers.getSelectedItem().toString() + "'");
						    	int DersID = 0;
						    	while(rs.next()) {
							    	DersID = rs.getInt(1);
						    	}
						    	ResultSet rs2 = st.executeQuery("SELECT Adi FROM Kategori WHERE DersID = " + DersID);
						    	while (rs2.next()) {  
									comboBoxKategori.addItem(rs2.getString("Adi"));
						    	}
								st.close();
								con.close();
								} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
								}  
							    comboBoxKategori.setSelectedItem(null);
							
						}
					});
		
		btnIleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnKolay.isSelected()){
					SoruZorlugu zz = new SoruZorlugu();
					KolaySinav kS = KolaySinav.getObj();
					zz.setZorlukAyari(kS);
					zz.zorlukAyarla();
					k=zz.kolaySoruSayisiKullan(kS);
					o=zz.ortaSoruSayisiKullan(kS);
					z=zz.zorSoruSayisiKullan(kS);
					Zorluk = "Kolay";
				}else if(rdbtnOrta.isSelected()) {
					SoruZorlugu zz = new SoruZorlugu();
					OrtaSinav oS = OrtaSinav.getObj();
					zz.setZorlukAyari(oS);
					zz.zorlukAyarla();
					k=zz.kolaySoruSayisiKullan(oS);
					o=zz.ortaSoruSayisiKullan(oS);
					z=zz.zorSoruSayisiKullan(oS);
					Zorluk = "Orta";
				}else if(rdbtnZor.isSelected()){
					SoruZorlugu zz = new SoruZorlugu();
					ZorSinav zS =ZorSinav.getObj();
					zz.setZorlukAyari(zS);
					zz.zorlukAyarla();
					k=zz.kolaySoruSayisiKullan(zS);
					o=zz.ortaSoruSayisiKullan(zS);
					z=zz.zorSoruSayisiKullan(zS);
					Zorluk = "Zor";
				}
				
				if(textFieldIstenilenPuan.getText().isEmpty() || textFieldSinavAdi.getText().isEmpty()
						|| comboBoxDers.getSelectedItem() == null || comboBoxKategori.getSelectedItem() == null
								|| ((Integer.parseInt(spinnerCoktan.getValue().toString()) == 0 
								&& Integer.parseInt(spinnerDY.getValue().toString()) == 0 
								&& Integer.parseInt(spinnerKlasik.getValue().toString()) == 0))
										|| (!rdbtnKolay.isSelected() && !rdbtnOrta.isSelected() && !rdbtnZor.isSelected())) {
							
					
					JOptionPane.showMessageDialog(null, "Tum alanlari eksiksiz giriniz.");
				
					
				}else {
					
					try {
						if(Integer.parseInt(textFieldIstenilenPuan.getText().toString()) > 0 && Integer.parseInt(textFieldIstenilenPuan.getText().toString()) < 200) {

							try {
							  	Connection con = DBConnection.dbConnector(); 						//Database ile ba�lant�m�z� olu�turduk.
							    Statement state = con.createStatement();
								state.executeUpdate("INSERT INTO Sinav (SinavAdi, Zorluk, CoktanSecSayi, DYSayi, KlasikSayi, Puan, DersAdi, KategoriAdi) VALUES ("
										+ " '" + textFieldSinavAdi.getText() + "'," 
											+ " '" + Zorluk + "',"
												+ " " + spinnerCoktan.getValue() + "," 
												+ " " + spinnerDY.getValue() + ","
												+ " " + spinnerKlasik.getValue() + ","
													+ " " + textFieldIstenilenPuan.getText() + ","
													+ " '" + comboBoxDers.getSelectedItem() + "',"
													+ " '" + comboBoxKategori.getSelectedItem() + "'"				
														+ ")");
							
								 String sinavID = null;
								 Statement state2 = con.createStatement();
								 ResultSet rs = state2.executeQuery("SELECT ID FROM Sinav WHERE SinavAdi = '" + textFieldSinavAdi.getText() + "'");
								 
								 while(rs.next()) {
									 sinavID = rs.getString(1);
								 }
								 
								 Statement state3 = con.createStatement();
								 state3.executeUpdate("UPDATE Derslik SET SinavID = " + sinavID);
							 
								 
								state.close();
								state2.close();
								state3.close();
								con.close();
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							

							
							//Tu�a bas�ld���nda yeni sayfam�z� child olarak �a��r�yoruz.
							SinavCikti sc = SinavCikti.getObj();
							if(!sc.isVisible()) {
							desktopPane.add(sc);
							sc.setVisible(true);
							}
							try {
								sc.setSelected(true);
							} catch (PropertyVetoException esc) {
								esc.printStackTrace();
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Puan alanina lutfen 0-200 arasinda bir sayi giriniz.");
						}
							
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null, "SQL Error");
						e1.printStackTrace();
					}
					
					
				}
			}
		});
	}
	public int getSpinnerCoktan() {
		int gecici = (int)spinnerCoktan.getValue();
		return gecici;
	}
	public int getSpinnerDY() {
		int gecici = (int)spinnerDY.getValue();
		return gecici;
	}
	public int getSpinnerKlasik() {
		int gecici = (int)spinnerKlasik.getValue();
		return gecici;
	}
	public double toplamSoruSayisi() {
		double soruSayisi = getSpinnerCoktan() + getSpinnerDY() + getSpinnerKlasik();
		return soruSayisi;
	}
	public static JTextField getTextFieldIstenilenPuan() {
		return textFieldIstenilenPuan;
	}
	public static JTextField getTextFieldSinavAdi() {
		return textFieldSinavAdi;
	}
}
