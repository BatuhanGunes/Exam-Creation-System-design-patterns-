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

public class Gozetmen extends JInternalFrame {

	

	private  JTextField textField_Gozetmen_Adi;
	private  JTextField textField_Gozetmen_ID;
	private static  JTable table = new JTable();
	private static Gozetmen obj=null;
	
	
	public static Gozetmen getObj(){
        if(obj==null){
            obj=new Gozetmen();
        }return obj;
    }

	/**
	 * Create the frame.
	 */
	public Gozetmen() {
		setClosable(true);

		setTitle("Gozetmen Islemleri");
		//setClosable(true);
		setBounds(0, 20, 1008, 700);
		getContentPane().setLayout(null);
		
		//Güncelle Butonu
		JButton btnNewButton_Soru_Guncelle = new JButton("Gözetmen G\u00FCncelle");
		btnNewButton_Soru_Guncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
				    Connection con = DBConnection.dbConnector(); 						
				    Statement state = con.createStatement();
				    if(!textField_Gozetmen_ID.getText().isEmpty()) {
				    	state.executeUpdate("UPDATE Gozetmen SET Adi = '" + textField_Gozetmen_Adi.getText() + "' WHERE ID = " + textField_Gozetmen_ID.getText());
						JOptionPane.showMessageDialog(null, "Güncelleme iþlemi baþarýyla tamamlandý.");
				    }else
				    	JOptionPane.showMessageDialog(null, "Ýlk önce tablodan bir veri seçiniz.");
					state.close();
					con.close();
					} catch (SQLException ee) {
						ee.printStackTrace();
					}
					TabloYazdir();
			    	textField_Gozetmen_ID.setText(null);
			    	textField_Gozetmen_Adi.setText(null);
			}
		});
		btnNewButton_Soru_Guncelle.setBounds(12, 561, 225, 75);
		getContentPane().add(btnNewButton_Soru_Guncelle);
		
		//Sil Butonu
		JButton btnNewButton_Soru_Sil = new JButton("Gözetmen Sil");
		btnNewButton_Soru_Sil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					
				    Connection con = DBConnection.dbConnector(); 						
				    Statement state = con.createStatement();
				    if(!textField_Gozetmen_ID.getText().isEmpty()) {
				    	state.executeUpdate("DELETE FROM Gozetmen WHERE ID = " + textField_Gozetmen_ID.getText());
						JOptionPane.showMessageDialog(null, "Silme iþlemi baþarýyla tamamlandý.");
				    } else
				    	JOptionPane.showMessageDialog(null, "Ýlk önce tablodan bir veri seçiniz.");
					state.close();
					con.close();
					} catch (SQLException ee) {
						ee.printStackTrace();
					} 
					TabloYazdir();
			    	textField_Gozetmen_ID.setText(null);
			    	textField_Gozetmen_Adi.setText(null);
			    	
			}
		});
		btnNewButton_Soru_Sil.setBounds(12, 458, 225, 75);
		getContentPane().add(btnNewButton_Soru_Sil);
		
		//Ekle Butonu
		JButton btnNewButton_Soru_Ekle = new JButton("Gözetmen Ekle");
		btnNewButton_Soru_Ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
				    Connection con = DBConnection.dbConnector(); 						
				    Statement state = con.createStatement();
				    if(!textField_Gozetmen_Adi.getText().isEmpty()) {
				    	state.executeUpdate("INSERT INTO Gozetmen (Adi) VALUES ('" + textField_Gozetmen_Adi.getText() + "')");
						JOptionPane.showMessageDialog(null, "Ekleme iþlemi baþarýyla tamamlandý.");
				    }else
				    	JOptionPane.showMessageDialog(null, "Bir Gozetmen adý giriniz.");
					state.close();
					con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}	
					TabloYazdir();
			    	textField_Gozetmen_ID.setText(null);
			    	textField_Gozetmen_Adi.setText(null);
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
				
				textField_Gozetmen_ID.setText(model.getValueAt(index,0).toString());
				textField_Gozetmen_Adi.setText(model.getValueAt(index,1).toString());
				
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		//textFields
		textField_Gozetmen_Adi = new JTextField();
		textField_Gozetmen_Adi.setBounds(12, 58, 225, 24);
		getContentPane().add(textField_Gozetmen_Adi);
		textField_Gozetmen_Adi.setColumns(10);
		
		textField_Gozetmen_ID = new JTextField();
		textField_Gozetmen_ID.setBounds(12, 132, 225, 24);
		getContentPane().add(textField_Gozetmen_ID);
		textField_Gozetmen_ID.setColumns(10);
		
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
	
		textField_Gozetmen_ID.setEditable(false);			//ID textField dinin yazýlabilirlik özelliðini kapatýyoruz.
		
		JLabel lblGozetmenAd = new JLabel("Gozetmen Ad\u0131 :");
		lblGozetmenAd.setBounds(12, 38, 97, 16);
		getContentPane().add(lblGozetmenAd);
		
		JLabel lblGozetmenId = new JLabel("Gozetmen ID :");
		lblGozetmenId.setBounds(12, 105, 97, 16);
		getContentPane().add(lblGozetmenId);
		TabloYazdir();									//Baþlangýçta en güncel verilerimizi yazdýrýyoruz.

	}
	
	
	// Her buton iþleminden sonra tablo güncellenmek amacý ile tabloyu tekrar tekrar yazdýrma iþlemlemlerinin terkrarýnýn önlenmesi için
	//Tablo yazdýr methodunun oluþturduk ve bu methodu yukarýda çaðýrdýk.
	public static void TabloYazdir() {
		
		try {
			
		    Connection con = DBConnection.dbConnector(); 						//Database ile baðlantýmýzý oluþturduk.
		    Statement state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM Gozetmen");			//istenilen query 'nin yazýmýnýn geçcekleþtirileceði alan
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
