package View;


import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import java.awt.Label;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import Model.DBConnection;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Zorluk extends JInternalFrame {

	private static Zorluk obj=null;
	private JTextField textField_Zorluk;
	private JTextField textField_Zorluk_ID;
	private static JTable table;
	
	
	public static Zorluk getObj(){
        if(obj==null){
            obj=new Zorluk();
        }return obj;
    }
	
	private Zorluk() {
		setTitle("Zorluk Islemleri");
		setClosable(true);
		setBounds(0, 20, 1008, 700);
		getContentPane().setLayout(null);
		
		Label label_Zorluk_Adi = new Label("Zorluk");
		label_Zorluk_Adi.setBounds(12, 28, 70, 24);
		getContentPane().add(label_Zorluk_Adi);
		
		Label label_Zorluk_ID = new Label("Zorluk ID");
		label_Zorluk_ID.setBounds(12, 102, 70, 24);
		getContentPane().add(label_Zorluk_ID);
		
		JButton btnZorlukGncelle = new JButton("Zorluk G\u00FCncelle");
		btnZorlukGncelle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
				    Connection con = DBConnection.dbConnector(); 						
				    Statement state = con.createStatement();
				    if(!textField_Zorluk_ID.getText().isEmpty()) {
				    	state.executeUpdate("UPDATE Zorluk SET Adi = '" + textField_Zorluk.getText() + "' WHERE ID = " + textField_Zorluk_ID.getText());
						JOptionPane.showMessageDialog(null, "Güncelleme iþlemi baþarýyla tamamlandý.");
				    }else
				    	JOptionPane.showMessageDialog(null, "Ýlk önce tablodan bir veri seçiniz.");
					state.close();
					con.close();
					} catch (SQLException ee) {
						ee.printStackTrace();
					}
					TabloYazdir();
			    	textField_Zorluk_ID.setText(null);
			    	textField_Zorluk.setText(null);
			}
		});
		btnZorlukGncelle.setBounds(12, 561, 225, 75);
		getContentPane().add(btnZorlukGncelle);
		
		JButton btnZorlukSil = new JButton("Zorluk Sil");
		btnZorlukSil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
				    Connection con = DBConnection.dbConnector(); 						
				    Statement state = con.createStatement();
				    if(!textField_Zorluk_ID.getText().isEmpty()) {
				    	state.executeUpdate("DELETE FROM Zorluk WHERE ID = " + textField_Zorluk_ID.getText());
						JOptionPane.showMessageDialog(null, "Silme iþlemi baþarýyla tamamlandý.");
				    } else
				    	JOptionPane.showMessageDialog(null, "Ýlk önce tablodan bir veri seçiniz.");
					state.close();
					con.close();
					} catch (SQLException eee) {
						eee.printStackTrace();
					} 
					TabloYazdir();
			    	textField_Zorluk_ID.setText(null);
			    	textField_Zorluk.setText(null);
			}
		});
		btnZorlukSil.setBounds(12, 458, 225, 75);
		getContentPane().add(btnZorlukSil);
		
		JButton btnZorlukEkle = new JButton("Zorluk Ekle");
		btnZorlukEkle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
				    Connection con = DBConnection.dbConnector(); 						
				    Statement state = con.createStatement();
				    if(!textField_Zorluk.getText().isEmpty()) {
				    	state.executeUpdate("INSERT INTO Zorluk (Adi) VALUES ('" + textField_Zorluk.getText() + "')");
						JOptionPane.showMessageDialog(null, "Ekleme iþlemi baþarýyla tamamlandý.");
				    }else
				    	JOptionPane.showMessageDialog(null, "Bir ders adý giriniz.");
					state.close();
					con.close();
					} catch (SQLException ee) {
						ee.printStackTrace();
					}	
					TabloYazdir();
			    	textField_Zorluk_ID.setText(null);
			    	textField_Zorluk.setText(null);
			}
		});
		btnZorlukEkle.setBounds(12, 355, 225, 75);
		getContentPane().add(btnZorlukEkle);
		
		textField_Zorluk = new JTextField();
		textField_Zorluk.setColumns(10);
		textField_Zorluk.setBounds(12, 58, 225, 24);
		getContentPane().add(textField_Zorluk);
		
		textField_Zorluk_ID = new JTextField();
		textField_Zorluk_ID.setColumns(10);
		textField_Zorluk_ID.setBounds(12, 132, 225, 24);
		getContentPane().add(textField_Zorluk_ID);
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(261, 28, 705, 623);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int index = table.getSelectedRow();
				
				textField_Zorluk_ID.setText(model.getValueAt(index,0).toString());
				textField_Zorluk.setText(model.getValueAt(index,1).toString());
				
			}
		});
		scrollPane.setViewportView(table);
		
		textField_Zorluk_ID.setEditable(false);			//ID textField dinin yazýlabilirlik özelliðini kapatýyoruz.
		TabloYazdir();									//Baþlangýçta en güncel verilerimizi yazdýrýyoruz.

	}

	
	public static void TabloYazdir() {
		
		try {
			
		    Connection con = DBConnection.dbConnector(); 						//Database ile baðlantýmýzý oluþturduk.
		    Statement state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM Zorluk");			//istenilen query 'nin yazýmýnýn geçcekleþtirileceði alan
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
