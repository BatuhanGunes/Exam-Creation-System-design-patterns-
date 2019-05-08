package View;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import Model.DBConnection;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;


public class DersIslemleri extends JInternalFrame {

	//Veriables
	private  static DersIslemleri obj=null;
	private  JTextField textField_Ders_Adi;
	private  JTextField textField_Ders_ID;
	private static  JTable table = new JTable();
	
	
	public static DersIslemleri getObj(){
        if(obj==null){
            obj=new DersIslemleri();
        }return obj;
    }	

	//constructors
	private DersIslemleri() {
		setClosable(true);

		setTitle("Ders Islemleri");
		//setClosable(true);
		setBounds(0, 20, 1008, 700);
		getContentPane().setLayout(null);
		
		JComboBox comboBoxOgretim = new JComboBox();
		comboBoxOgretim.setBounds(12, 214, 227, 20);
		getContentPane().add(comboBoxOgretim);
		
		//Güncelle Butonu
		JButton btnNewButton_Soru_Guncelle = new JButton("Ders G\u00FCncelle");
		btnNewButton_Soru_Guncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
				    Connection con = DBConnection.dbConnector(); 						
				    Statement state = con.createStatement();
				    if(!textField_Ders_ID.getText().isEmpty() || comboBoxOgretim.getSelectedItem() != null) {
				    	
				    	 int OgretimUyesiID = 0;
				    	ResultSet rs = state.executeQuery("SELECT ID FROM OgretimUyesi WHERE Adi = '" + comboBoxOgretim.getSelectedItem().toString() + "'");
				    	while(rs.next()) {
				    		OgretimUyesiID = rs.getInt(1);
				    	}
				    	
				    	state.executeUpdate("UPDATE Ders SET Adi = '" + textField_Ders_Adi.getText() + "', OgretimUyesiID = " + OgretimUyesiID + " WHERE ID = " + textField_Ders_ID.getText());
						JOptionPane.showMessageDialog(null, "Güncelleme iþlemi baþarýyla tamamlandý.");
				    }else
				    	JOptionPane.showMessageDialog(null, "Ýlk önce tablodan bir veri seçiniz.");
					state.close();
					con.close();
					} catch (SQLException ee) {
						ee.printStackTrace();
					}
					TabloYazdir();
			    	textField_Ders_ID.setText(null);
			    	textField_Ders_Adi.setText(null);
			    	comboBoxOgretim.setSelectedIndex(-1);
			}
		});
		btnNewButton_Soru_Guncelle.setBounds(12, 561, 225, 75);
		getContentPane().add(btnNewButton_Soru_Guncelle);
		
		//Sil Butonu
		JButton btnNewButton_Soru_Sil = new JButton("Ders Sil");
		btnNewButton_Soru_Sil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					
				    Connection con = DBConnection.dbConnector(); 						
				    Statement state = con.createStatement();
				    if(!textField_Ders_ID.getText().isEmpty() || comboBoxOgretim.getSelectedItem() != null) {
				    	state.executeUpdate("DELETE FROM Ders WHERE ID = " + textField_Ders_ID.getText());
						JOptionPane.showMessageDialog(null, "Silme iþlemi baþarýyla tamamlandý.");
				    } else
				    	JOptionPane.showMessageDialog(null, "Ýlk önce tablodan bir veri seçiniz.");
					state.close();
					con.close();
					} catch (SQLException ee) {
						ee.printStackTrace();
					} 
					TabloYazdir();
			    	textField_Ders_ID.setText(null);
			    	textField_Ders_Adi.setText(null);
			    	comboBoxOgretim.setSelectedIndex(-1);
			}
		});
		btnNewButton_Soru_Sil.setBounds(12, 458, 225, 75);
		getContentPane().add(btnNewButton_Soru_Sil);
		
		//Ekle Butonu
		JButton btnNewButton_Soru_Ekle = new JButton("Ders Ekle");
		btnNewButton_Soru_Ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
				    Connection con = DBConnection.dbConnector(); 						
				    Statement state = con.createStatement();
				    int OgretimUyesiID = 0;
				    if(comboBoxOgretim.getSelectedItem() != null || !textField_Ders_Adi.getText().isEmpty()) {
				    	
					    ResultSet rs = state.executeQuery("SELECT ID FROM OgretimUyesi WHERE Adi = '" + comboBoxOgretim.getSelectedItem().toString() + "'");
				    	while(rs.next()) {
				    		OgretimUyesiID = rs.getInt(1);
				    	}
				    	
				    	state.executeUpdate("INSERT INTO Ders (Adi, OgretimUyesiID) VALUES ('" + textField_Ders_Adi.getText() + "'," + OgretimUyesiID + ")");
						JOptionPane.showMessageDialog(null, "Ekleme iþlemi baþarýyla tamamlandý.");
				    }else
				    	JOptionPane.showMessageDialog(null, "Bir ders adý ve öðretim üyesi giriniz.");
					state.close();
					con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}	
					TabloYazdir();
			    	textField_Ders_ID.setText(null);
			    	textField_Ders_Adi.setText(null);
			    	comboBoxOgretim.setSelectedIndex(-1);
			}
		});
		btnNewButton_Soru_Ekle.setBounds(12, 355, 225, 75);
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
				
				textField_Ders_ID.setText(model.getValueAt(index,0).toString());
				textField_Ders_Adi.setText(model.getValueAt(index,1).toString());
				comboBoxOgretim.setSelectedItem(model.getValueAt(index,2).toString());
				
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		//textFields
		textField_Ders_Adi = new JTextField();
		textField_Ders_Adi.setBounds(12, 58, 225, 24);
		getContentPane().add(textField_Ders_Adi);
		textField_Ders_Adi.setColumns(10);
		
		textField_Ders_ID = new JTextField();
		textField_Ders_ID.setBounds(12, 132, 225, 24);
		getContentPane().add(textField_Ders_ID);
		textField_Ders_ID.setColumns(10);
		
		//Separators
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 340, 225, 2);
		getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 443, 225, 2);
		getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(12, 546, 225, 2);
		getContentPane().add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(12, 649, 225, 2);
		getContentPane().add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(12, 95, 225, 2);
		getContentPane().add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(12, 168, 225, 2);
		getContentPane().add(separator_5);
	
		textField_Ders_ID.setEditable(false);			//ID textField dinin yazýlabilirlik özelliðini kapatýyoruz.
		
		JLabel lblDersAd = new JLabel("Ders Ad\u0131 :");
		lblDersAd.setBounds(12, 38, 56, 16);
		getContentPane().add(lblDersAd);
		
		JLabel lblDersId = new JLabel("Ders ID :");
		lblDersId.setBounds(12, 105, 56, 16);
		getContentPane().add(lblDersId);
		
		
		//Ogretim Uyesi Adý ComboBox Doldurulmasý		
		try {		
			Connection con = DBConnection.dbConnector();		
			Statement st = con.createStatement();		
			ResultSet r = st.executeQuery("SELECT * FROM OgretimUyesi");			
			while (r.next()) {  			
				comboBoxOgretim.addItem(r.getString("Adi"));		
			}			
			st.close();			
			con.close();			
		} catch (SQLException e) {		
			// TODO Auto-generated catch block	
			e.printStackTrace();		
		} comboBoxOgretim.setSelectedItem(null);
		
		
		JLabel lblretimyesi = new JLabel("\u00D6\u011Fretim \u00DCyesi :");
		lblretimyesi.setBounds(12, 187, 127, 16);
		getContentPane().add(lblretimyesi);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(12, 245, 225, 2);
		getContentPane().add(separator_6);
		TabloYazdir();									//Baþlangýçta en güncel verilerimizi yazdýrýyoruz.

	}
	
	
	// Her buton iþleminden sonra tablo güncellenmek amacý ile tabloyu tekrar tekrar yazdýrma iþlemlemlerinin terkrarýnýn önlenmesi için
	//Tablo yazdýr methodunun oluþturduk ve bu methodu yukarýda çaðýrdýk.
	public static void TabloYazdir() {
		
		try {
			
		    Connection con = DBConnection.dbConnector(); 						//Database ile baðlantýmýzý oluþturduk.
		    Statement state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT Ders.ID, Ders.Adi AS \"Ders Adý\", OgretimUyesi.Adi AS \"Öðretim Üyesi Adý\", Ders.OgretimUyesiID AS \"Öðretim Üyesi ID\" \r\n" + 
												"FROM Ders INNER JOIN\r\n" + 
												"OgretimUyesi ON Ders.OgretimUyesiID = OgretimUyesi.ID");			//istenilen query 'nin yazýmýnýn geçcekleþtirileceði alan
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
