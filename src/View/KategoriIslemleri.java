package View;


import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
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
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Model.DBConnection;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class KategoriIslemleri extends JInternalFrame {

	private static KategoriIslemleri obj=null;
	private JTextField textField_Kategori_Adi;
	private JTextField textField_Kategori_ID;
	private static JTable table;
	
	
	public static KategoriIslemleri getObj(){
        if(obj==null){
            obj=new KategoriIslemleri();
        }return obj;
    }
	
	private KategoriIslemleri() {
		setClosable(true);
		setTitle("Kategori Islemleri");
		setBounds(0, 20, 1008, 700);
		getContentPane().setLayout(null);
		
		JComboBox comboBox_Ders_Adi = new JComboBox();
		comboBox_Ders_Adi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboBox_Ders_Adi.setOpaque(true);
			}
		});
		comboBox_Ders_Adi.setBounds(12, 58, 225, 22);
		getContentPane().add(comboBox_Ders_Adi);
		
		//Güncelle Butonu
				JButton btnKategoriGncelle = new JButton("Kategori G\u00FCncelle");
				btnKategoriGncelle.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							
						    Connection con = DBConnection.dbConnector(); 						
						    Statement state = con.createStatement();
						    if(!textField_Kategori_ID.getText().isEmpty()) {
						    	ResultSet rs = state.executeQuery("SELECT ID FROM Ders WHERE Adi = '" + comboBox_Ders_Adi.getSelectedItem().toString() + "'");
						    	int DersID = 0;
						    	while(rs.next()) {
							    	DersID = rs.getInt(1);
						    	}
						    	state.executeUpdate("UPDATE Kategori SET Adi = '" + textField_Kategori_Adi.getText() + "', DersID = " + DersID + " WHERE ID = " + textField_Kategori_ID.getText());
								JOptionPane.showMessageDialog(null, "Güncelleme iþlemi baþarýyla tamamlandý.");
						    }else
						    	JOptionPane.showMessageDialog(null, "Ýlk önce tablodan bir veri seçiniz.");
							state.close();
							} catch (SQLException ee) {
								ee.printStackTrace();
							} 
							TabloYazdir();
							comboBox_Ders_Adi.setSelectedItem(null);
					    	textField_Kategori_ID.setText(null);
					    	textField_Kategori_Adi.setText(null);
					}
				});
				btnKategoriGncelle.setBounds(12, 561, 225, 75);
				getContentPane().add(btnKategoriGncelle);
				
				textField_Kategori_ID = new JTextField();
				textField_Kategori_ID.setColumns(10);
				textField_Kategori_ID.setBounds(12, 198, 225, 24);
				getContentPane().add(textField_Kategori_ID);
				
				//Sil Butonu
				JButton btnKategoriSil = new JButton("Kategori Sil");
				btnKategoriSil.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							
						    Connection con = DBConnection.dbConnector(); 						
						    Statement state = con.createStatement();
						    if(!textField_Kategori_ID.getText().isEmpty()) {
						    	state.executeUpdate("DELETE FROM Kategori WHERE ID = " + textField_Kategori_ID.getText());
								JOptionPane.showMessageDialog(null, "Silme iþlemi baþarýyla tamamlandý.");
						    } else
						    	JOptionPane.showMessageDialog(null, "Ýlk önce tablodan bir veri seçiniz.");
							state.close();
							} catch (SQLException ee) {
								ee.printStackTrace();
							} 
							TabloYazdir();
							comboBox_Ders_Adi.setSelectedItem(null);
					    	textField_Kategori_ID.setText(null);
					    	textField_Kategori_Adi.setText(null);
					    	
					}
				});
				btnKategoriSil.setBounds(12, 458, 225, 75);
				getContentPane().add(btnKategoriSil);
				
				//Ekle Butonu
				JButton btnKategoriEkle = new JButton("Kategori Ekle");
				btnKategoriEkle.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						try {
							
						    Connection con = DBConnection.dbConnector(); 						
						    Statement state = con.createStatement();
						    if(!textField_Kategori_Adi.getText().isEmpty()) {
						    	ResultSet rs = state.executeQuery("SELECT ID FROM Ders WHERE Adi = '" + comboBox_Ders_Adi.getSelectedItem().toString() + "'");
						    	int DersID = 0;
						    	while(rs.next()) {
							    	DersID = rs.getInt(1);
						    	}
						    	state.executeUpdate("INSERT INTO Kategori (Adi,DersID) VALUES ('" + textField_Kategori_Adi.getText() + "','" + DersID + "')");
								JOptionPane.showMessageDialog(null, "Ekleme iþlemi baþarýyla tamamlandý.");
						    }else
						    	JOptionPane.showMessageDialog(null, "Bir ders adý ve kategori adý giriniz.");
							state.close();
							con.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}	
							TabloYazdir();
					    	textField_Kategori_ID.setText(null);
					    	textField_Kategori_Adi.setText(null);
					    	comboBox_Ders_Adi.setSelectedItem(null);
					    	
					}
				});
				btnKategoriEkle.setBounds(12, 355, 225, 75);
				getContentPane().add(btnKategoriEkle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(261, 28, 705, 623);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int index = table.getSelectedRow();
				
				comboBox_Ders_Adi.setSelectedItem(model.getValueAt(index,2).toString());
				textField_Kategori_ID.setText(model.getValueAt(index,0).toString());
				textField_Kategori_Adi.setText(model.getValueAt(index,1).toString());
				
			}
		});
		scrollPane.setViewportView(table);
		
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
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(12, 93, 225, 2);
		getContentPane().add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(12, 234, 225, 2);
		getContentPane().add(separator_6);
		
		textField_Kategori_Adi = new JTextField();
		textField_Kategori_Adi.setColumns(10);
		textField_Kategori_Adi.setBounds(12, 124, 225, 24);
		getContentPane().add(textField_Kategori_Adi);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(12, 161, 225, 2);
		getContentPane().add(separator_7);
		

		
		JLabel lblKategoriAd = new JLabel("Kategori Ad\u0131");
		lblKategoriAd.setBounds(12, 99, 87, 14);
		getContentPane().add(lblKategoriAd);
		
		JLabel lblKategoriId = new JLabel("Kategori ID");
		lblKategoriId.setBounds(12, 174, 79, 14);
		getContentPane().add(lblKategoriId);
		

		
	    try {
		Connection con = DBConnection.dbConnector();
		Statement st = con.createStatement();
		ResultSet r = st.executeQuery("SELECT * FROM Ders");
		while (r.next()) {  
				comboBox_Ders_Adi.addItem(r.getString("Adi"));
		}
		st.close();
		con.close();
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}  
	    comboBox_Ders_Adi.setSelectedItem(null);
	    textField_Kategori_ID.setEditable(false);
	    
	    JLabel lblDersAd = new JLabel("Ders Ad\u0131");
	    lblDersAd.setBounds(12, 29, 87, 14);
	    getContentPane().add(lblDersAd);
	    TabloYazdir();

	}
	
	public static void TabloYazdir() {
		
		try {
			
		    Connection con = DBConnection.dbConnector(); 						//Database ile baðlantýmýzý oluþturduk.
		    Statement state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT Kategori.ID as \"Kategori ID\", Kategori.Adi as \"Kategori Adý\", Ders.Adi as \"Ders Adý\", Ders.ID as \"Ders ID\"\r\n" + 
					"FROM Ders\r\n" + 
					"INNER JOIN Kategori\r\n" + 
					"ON Ders.ID = Kategori.DersID;");			//istenilen query 'nin yazýmýnýn geçcekleþtirileceði alan
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
			table.setModel(dtm);							//tablomuzun modelini deðiþtiriyoruz.
			con.close();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
	}
}
