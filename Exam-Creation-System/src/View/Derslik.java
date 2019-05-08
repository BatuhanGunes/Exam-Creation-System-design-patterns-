package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Model.DBConnection;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Derslik extends JInternalFrame {

	
	//Veriables
			private  static Derslik obj=null;
			private  JTextField textField_BinaAdi;
			private static  JTable table = new JTable();
			private JTextField textField_SalonKodu;
			private JTextField textFieldSalonNo;
	
	public static Derslik getObj(){
        if(obj==null){
            obj=new Derslik();
        }return obj;
    }

	/**
	 * Create the frame.
	 */
	public Derslik() {
		setClosable(true);

		setTitle("Derslik i\u015Flemleri");
		//setClosable(true);
		setBounds(0, 20, 1008, 700);
		getContentPane().setLayout(null);
		
		JComboBox comboBoxGozetmenAdi = new JComboBox();
		comboBoxGozetmenAdi.setBounds(12, 283, 225, 20);
		getContentPane().add(comboBoxGozetmenAdi);
		
		JSpinner spinnerKapasite = new JSpinner();
		spinnerKapasite.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerKapasite.setBounds(78, 192, 159, 24);
		getContentPane().add(spinnerKapasite);
		
		//G�ncelle Butonu
				JButton btnNewButton_Soru_Guncelle = new JButton("Derslik G\u00FCncelle");
				btnNewButton_Soru_Guncelle.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							
						    Connection con = DBConnection.dbConnector(); 						
						    Statement state = con.createStatement();
						    if(comboBoxGozetmenAdi.getSelectedItem() != null || !textField_SalonKodu.getText().isEmpty() || !textField_BinaAdi.getText().isEmpty()) {
						    	
						    	 int GozetmenID = 0;
						    	 ResultSet rs = state.executeQuery("SELECT ID FROM Gozetmen WHERE Adi = '" + comboBoxGozetmenAdi.getSelectedItem().toString() + "'");
						    	while(rs.next()) {
						    		GozetmenID = rs.getInt(1);
						    	}
						    	
						    	state.executeUpdate("UPDATE Derslik SET SalonNo = '" + textFieldSalonNo.getText() + "', BinaAdi = '" + textField_BinaAdi.getText() + "', Kapasite = " 
						    														+ Integer.parseInt(spinnerKapasite.getValue().toString()) + ", GozetmenID = " + GozetmenID 
						    														+" WHERE SalonKodu = " + textField_SalonKodu.getText());
								JOptionPane.showMessageDialog(null, "G�ncelleme i�lemi ba�ar�yla tamamland�.");
						    }else
						    	JOptionPane.showMessageDialog(null, "�lk �nce tablodan bir veri se�iniz.");
							state.close();
							con.close();
							} catch (SQLException ee) {
								ee.printStackTrace();
							}
							TabloYazdir();
							textField_BinaAdi.setText(null);
					    	textField_SalonKodu.setText(null);
					    	textFieldSalonNo.setText(null);
					    	spinnerKapasite.setValue(0);
					    	comboBoxGozetmenAdi.setSelectedIndex(-1);
					    	
					}
				});
				btnNewButton_Soru_Guncelle.setBounds(12, 579, 225, 57);
				getContentPane().add(btnNewButton_Soru_Guncelle);
				
				//Sil Butonu
				JButton btnNewButton_Soru_Sil = new JButton("Derslik Sil");
				btnNewButton_Soru_Sil.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						try {
							
						    Connection con = DBConnection.dbConnector(); 						
						    Statement state = con.createStatement();
						    if(comboBoxGozetmenAdi.getSelectedItem() != null || !textField_SalonKodu.getText().isEmpty() || !textField_BinaAdi.getText().isEmpty()) {
						    	state.executeUpdate("DELETE FROM Derslik WHERE SalonKodu = " + textField_SalonKodu.getText());
								JOptionPane.showMessageDialog(null, "Silme i�lemi ba�ar�yla tamamland�.");
						    } else
						    	JOptionPane.showMessageDialog(null, "�lk �nce tablodan bir veri se�iniz.");
							state.close();
							con.close();
							} catch (SQLException ee) {
								ee.printStackTrace();
							} 
							TabloYazdir();
							textField_BinaAdi.setText(null);
					    	textField_SalonKodu.setText(null);
					    	textFieldSalonNo.setText(null);
					    	spinnerKapasite.setValue(0);
					    	comboBoxGozetmenAdi.setSelectedIndex(-1);
					    	
					}
				});
				btnNewButton_Soru_Sil.setBounds(12, 496, 225, 57);
				getContentPane().add(btnNewButton_Soru_Sil);
				
				//Ekle Butonu
				JButton btnNewButton_Soru_Ekle = new JButton("Derslik Ekle");
				btnNewButton_Soru_Ekle.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						try {
							
						    Connection con = DBConnection.dbConnector(); 						
						    Statement state = con.createStatement();
						    int GozetmenID = 0;
						    if(comboBoxGozetmenAdi.getSelectedItem() != null || !textField_SalonKodu.getText().isEmpty() || !textField_BinaAdi.getText().isEmpty()) {
						    	
							    ResultSet rs = state.executeQuery("SELECT ID FROM Gozetmen WHERE Adi = '" + comboBoxGozetmenAdi.getSelectedItem().toString() + "'");
						    	while(rs.next()) {
						    		GozetmenID = rs.getInt(1);
						    	}
						    	
						    	state.executeUpdate("INSERT INTO Derslik (SalonNo, BinaAdi, Kapasite, GozetmenID) VALUES ('" + textFieldSalonNo.getText() + "','" + textField_BinaAdi.getText() + "',"
																															+ spinnerKapasite.getValue() + "," + GozetmenID + ")");
								JOptionPane.showMessageDialog(null, "Ekleme i�lemi ba�ar�yla tamamland�.");
						    }else
						    	JOptionPane.showMessageDialog(null, "Gerekli Alanlar� giriniz.");
							state.close();
							con.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}	
							TabloYazdir();
					    	textField_BinaAdi.setText(null);
					    	textField_SalonKodu.setText(null);
					    	textFieldSalonNo.setText(null);
					    	spinnerKapasite.setValue(0);
					    	comboBoxGozetmenAdi.setSelectedIndex(-1);
					    	
					}
				});
				btnNewButton_Soru_Ekle.setBounds(12, 421, 225, 51);
				getContentPane().add(btnNewButton_Soru_Ekle);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(270, 38, 699, 623);
				getContentPane().add(scrollPane);
				
				table = new JTable();			
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						DefaultTableModel model = (DefaultTableModel)table.getModel();
						int index = table.getSelectedRow();
						
						textField_SalonKodu.setText(model.getValueAt(index,0).toString());
						textFieldSalonNo.setText(model.getValueAt(index,1).toString());
						textField_BinaAdi.setText(model.getValueAt(index,2).toString());
						spinnerKapasite.setValue(Integer.parseInt(model.getValueAt(index, 3).toString()));
						comboBoxGozetmenAdi.setSelectedItem(model.getValueAt(index,6).toString());
					}
				});
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(table);
		
		
		
		/*
		 * 
		 * 
		 * 
		 * try {
					
				    Connection con = DBConnection.dbConnector(); 						
				    Statement state = con.createStatement();
				    if(!textField_Bina_Adi.getText().isEmpty() || !textFieldKapasite.getText().isEmpty() || !textFieldSalonNo.getText().isEmpty()) {
				    	state.executeUpdate("INSERT INTO Derslik (SalonKodu, SalonNo, BinaAdi, Kapasite, GozetmenID, SinavID) "
				    			+ "VALUES (" + textField_SalonKodu.getText() + "," + textFieldSalonNo.getText() + ", '"
				    							+ textField_Bina_Adi.getText() + "', " + textFieldKapasite.getText() + ", " 
				    							+  ", "
				    							+ ")");
						JOptionPane.showMessageDialog(null, "Ekleme i�lemi ba�ar�yla tamamland�.");
				    }else
				    	JOptionPane.showMessageDialog(null, "L�tfen Bo� Alanlar� doldurunuz.");
					state.close();
					con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}	
					TabloYazdir();
			    	textField_Bina_Adi.setText(null);
			    	textField_SalonKodu.setText(null);
			    	textFieldKapasite.setText(null);
			    	textFieldSalonNo.setText(null);
			    	
				
		 * */
		 
		//textFields
				textField_BinaAdi = new JTextField();
				textField_BinaAdi.setBounds(12, 61, 225, 24);
				getContentPane().add(textField_BinaAdi);
				textField_BinaAdi.setColumns(10);
				
				//Separators
				JSeparator separator = new JSeparator();
				separator.setBounds(12, 314, 225, 2);
				getContentPane().add(separator);
				
				JSeparator separator_1 = new JSeparator();
				separator_1.setBounds(12, 483, 225, 2);
				getContentPane().add(separator_1);
				
				JSeparator separator_2 = new JSeparator();
				separator_2.setBounds(12, 566, 225, 2);
				getContentPane().add(separator_2);
				
				JSeparator separator_3 = new JSeparator();
				separator_3.setBounds(12, 649, 225, 2);
				getContentPane().add(separator_3);
				
				JSeparator separator_4 = new JSeparator();
				separator_4.setBounds(12, 96, 225, 2);
				getContentPane().add(separator_4);
				
				JSeparator separator_5 = new JSeparator();
				separator_5.setBounds(12, 169, 225, 2);
				getContentPane().add(separator_5);
				
				JLabel lblBinaAd = new JLabel("Bina Ad\u0131 :");
				lblBinaAd.setBounds(12, 38, 56, 16);
				getContentPane().add(lblBinaAd);
				
				JLabel lblSalonNo = new JLabel("Salon No :");
				lblSalonNo.setBounds(12, 116, 79, 16);
				getContentPane().add(lblSalonNo);
				
				JLabel lblKapasite = new JLabel("Kapasite :");
				lblKapasite.setBounds(12, 196, 56, 16);
				getContentPane().add(lblKapasite);
				
				JSeparator separator_6 = new JSeparator();
				separator_6.setBounds(12, 233, 225, 2);
				getContentPane().add(separator_6);
				
				JSeparator separator_7 = new JSeparator();
				separator_7.setBounds(12, 395, 225, 2);
				getContentPane().add(separator_7);
				
				textField_SalonKodu = new JTextField();
				textField_SalonKodu.setEditable(false);
				textField_SalonKodu.setColumns(10);
				textField_SalonKodu.setBounds(12, 360, 225, 24);
				getContentPane().add(textField_SalonKodu);
				
				JLabel label = new JLabel("Salon Kodu :");
				label.setBounds(12, 333, 79, 16);
				getContentPane().add(label);
				
				textFieldSalonNo = new JTextField();
				textFieldSalonNo.setColumns(10);
				textFieldSalonNo.setBounds(12, 134, 225, 24);
				getContentPane().add(textFieldSalonNo);
				
				JLabel lblGozetmenId = new JLabel("G\u00F6zetmen Ad\u0131 :");
				lblGozetmenId.setBounds(12, 252, 159, 16);
				getContentPane().add(lblGozetmenId);
				


				
				//G�zetmen Ad� ComboBox Doldurulmas�		
				try {		
					Connection con = DBConnection.dbConnector();		
					Statement st = con.createStatement();		
					ResultSet r = st.executeQuery("SELECT * FROM Gozetmen");			
					while (r.next()) {  			
						comboBoxGozetmenAdi.addItem(r.getString("Adi"));		
					}			
					st.close();			
					con.close();			
				} catch (SQLException e) {		
					// TODO Auto-generated catch block	
					e.printStackTrace();		
				} comboBoxGozetmenAdi.setSelectedItem(null);
				
				TabloYazdir();									//Ba�lang��ta en g�ncel verilerimizi yazd�r�yoruz.

	}
	
	
	// Her buton i�leminden sonra tablo g�ncellenmek amac� ile tabloyu tekrar tekrar yazd�rma i�lemlemlerinin terkrar�n�n �nlenmesi i�in
	//Tablo yazd�r methodunun olu�turduk ve bu methodu yukar�da �a��rd�k.
	public static void TabloYazdir() {
		
		try {
			
		    Connection con = DBConnection.dbConnector(); 						//Database ile ba�lant�m�z� olu�turduk.
		    Statement state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT Derslik.*, Gozetmen.Adi AS \"G�zetmen Ad�\"\r\n" + 
												"FROM Derslik INNER JOIN\r\n" + 
												"Gozetmen ON Derslik.GozetmenID = Gozetmen.ID");			//istenilen query 'nin yaz�m�n�n ge�cekle�tirilece�i alan
			ResultSetMetaData rsmd = rs.getMetaData();							//Al�nan sonucu metadata olarak rsmd de�i�kenine aktar�yoruz.
			int columns = rsmd.getColumnCount();								//S�tun say�s�n� al�yoruz.
			DefaultTableModel dtm = new DefaultTableModel();					//al�nan verileri hangi format do�rultusunda tabloya yazd�r�l�ca��n� s�yl�yoruz.
			Vector columns_name = new Vector();
			Vector data_rows = new Vector();
			
			for (int i=1; i<=columns; i++) {									//s�t�n say�s� kadar d�nd�rerek s�t�n isimlerini al�p ekliyoruz.
				columns_name.addElement(rsmd.getColumnName(i));
			}
			
			dtm.setColumnIdentifiers(columns_name);								  
			
			while(rs.next()) {
				data_rows = new Vector();
				for(int j=1;j<=columns;j++) {									//de�erlerin say�s� kadar d�nd�r�p de�erleri tabloya ekliyoruz.
					data_rows.addElement(rs.getString(j));
				}
				dtm.addRow(data_rows);											
				
			}
			table.setModel(dtm);												//tablomuzun modelini de�i�tiriyoruz.
			state.close();
			con.close();
		} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
	}
}

