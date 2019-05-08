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

		setTitle("Derslik Islemleri");
		//setClosable(true);
		setBounds(0, 20, 1008, 700);
		getContentPane().setLayout(null);
		
		JSpinner spinnerKapasite = new JSpinner();
		spinnerKapasite.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerKapasite.setBounds(78, 192, 159, 24);
		getContentPane().add(spinnerKapasite);
		
		//Güncelle Butonu
				JButton btnNewButton_Soru_Guncelle = new JButton("Derslik G\u00FCncelle");
				btnNewButton_Soru_Guncelle.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							
						    Connection con = DBConnection.dbConnector(); 						
						    Statement state = con.createStatement();
						    if(!textField_SalonKodu.getText().isEmpty() || !textField_BinaAdi.getText().isEmpty()) {
						    	
						    	 
						    	
						    	state.executeUpdate("UPDATE Derslik SET SalonNo = '" + textFieldSalonNo.getText() + "', BinaAdi = '" + textField_BinaAdi.getText() + "', Kapasite = " 
						    														+ Integer.parseInt(spinnerKapasite.getValue().toString()) 
						    														+" WHERE SalonKodu = " + textField_SalonKodu.getText());
								JOptionPane.showMessageDialog(null, "Güncelleme iþlemi baþarýyla tamamlandý.");
						    }else
						    	JOptionPane.showMessageDialog(null, "Ýlk önce tablodan bir veri seçiniz.");
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
						    if(!textField_SalonKodu.getText().isEmpty() || !textField_BinaAdi.getText().isEmpty()) {
						    	state.executeUpdate("DELETE FROM Derslik WHERE SalonKodu = " + textField_SalonKodu.getText());
								JOptionPane.showMessageDialog(null, "Silme iþlemi baþarýyla tamamlandý.");
						    } else
						    	JOptionPane.showMessageDialog(null, "Ýlk önce tablodan bir veri seçiniz.");
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
						    
						    if(!textField_SalonKodu.getText().isEmpty() || !textField_BinaAdi.getText().isEmpty()) {
						    	
							   
						    	
						    	state.executeUpdate("INSERT INTO Derslik (SalonNo, BinaAdi, Kapasite) VALUES ('" + textFieldSalonNo.getText() + "','" + textField_BinaAdi.getText() + "',"
																															+ spinnerKapasite.getValue() + ")");
								JOptionPane.showMessageDialog(null, "Ekleme iþlemi baþarýyla tamamlandý.");
						    }else
						    	JOptionPane.showMessageDialog(null, "Gerekli Alanlarý giriniz.");
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
					}
				});
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(table);
		
		
		
		 
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
				separator_7.setBounds(12, 406, 225, 2);
				getContentPane().add(separator_7);
				
				textField_SalonKodu = new JTextField();
				textField_SalonKodu.setEditable(false);
				textField_SalonKodu.setColumns(10);
				textField_SalonKodu.setBounds(12, 275, 225, 24);
				getContentPane().add(textField_SalonKodu);
				
				JLabel label = new JLabel("Salon Kodu :");
				label.setBounds(12, 248, 79, 16);
				getContentPane().add(label);
				
				textFieldSalonNo = new JTextField();
				textFieldSalonNo.setColumns(10);
				textFieldSalonNo.setBounds(12, 134, 225, 24);
				getContentPane().add(textFieldSalonNo);
				


									
				TabloYazdir();									//Baþlangýçta en güncel verilerimizi yazdýrýyoruz.

	}
	
	
	// Her buton iþleminden sonra tablo güncellenmek amacý ile tabloyu tekrar tekrar yazdýrma iþlemlemlerinin terkrarýnýn önlenmesi için
	//Tablo yazdýr methodunun oluþturduk ve bu methodu yukarýda çaðýrdýk.
	public static void TabloYazdir() {
		
		try {
			
		    Connection con = DBConnection.dbConnector(); 						//Database ile baðlantýmýzý oluþturduk.
		    Statement state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT        Derslik.*\r\n" + 
												"FROM            Derslik");			//istenilen query 'nin yazýmýnýn geçcekleþtirileceði alan
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
			state.close();
			con.close();
		} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
	}
}

