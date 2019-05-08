package FacadeDeneme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.codehaus.groovy.tools.shell.commands.ClearCommand;

import View.SinavCikti;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

public class YoklamaListesi extends JFrame {
	YoklamaListesiModel model = new YoklamaListesiModel();
	
	
	private static String exportPath =System.getProperty("user.home") + "/Desktop";
	
	public static String getExportPath() {
		return exportPath;
	}

	public static void setExportPath(String exportPath) {
		YoklamaListesi.exportPath = exportPath;
	}

	public YoklamaListesi() {
		
	}
	
	public void ShowReport() {
		try {
					
			LinkedList<Map<String,Object>> dataSource = new LinkedList<Map<String,Object>>();
			
			for (int x=0 ; x<model.getOgrenciNumarasi().size()/2; x++  ) {
				Map<String,Object> m = new HashMap<String, Object>();
				m.put("dersAdi",model.getDersAdi());
				//m.put("derslik",model.getDerslik());
				m.put("gozetmen",model.getGozetmen());
				m.put("sinavAdi",model.getSinavAdi());
				
				if(x==model.getOgrenciNumarasi().size()/2-1) {
					m.put("ogrAdveSoyad",model.getOgrenciAdveSoyadi().get(x));
					m.put("ogrNo",model.getOgrenciNumarasi().get(x).toString());
					x++;
					m.put("ogrAdveSoyad2","");
					m.put("ogrNo2","");	
					dataSource.add(m);
					
				}
				else {
				
					m.put("ogrAdveSoyad",model.getOgrenciAdveSoyadi().get(x));
					m.put("ogrNo",model.getOgrenciNumarasi().get(x).toString());
					x++;
					m.put("ogrAdveSoyad2",model.getOgrenciAdveSoyadi().get(x));
					m.put("ogrNo2",model.getOgrenciNumarasi().get(x).toString());	
				    dataSource.add(m);
				}
			}
		
				
			JRDesignStyle jrDesignStyle = new JRDesignStyle();
		    /*Set the Encoding to UTF-8 for pdf and embed font to arial*/
		    jrDesignStyle.setDefault(true);
		    jrDesignStyle.setPdfEncoding("Cp1254");
		    jrDesignStyle.setPdfEmbedded(true);
		    
			JRDataSource jrDataSource = new JRBeanCollectionDataSource(dataSource);
			String sourceName = "YoklamaListesi.jrxml";
			JasperReport report = JasperCompileManager.compileReport(sourceName);
			JasperPrint filledReport = JasperFillManager.fillReport(report, null, jrDataSource);
		    filledReport.addStyle(jrDesignStyle);
		    
			this.getContentPane().add(new JRViewer(filledReport));
			this.pack();
			//PDF Olarak Çýktý Alma
			JRXmlLoader.load(sourceName);
					
			JasperExportManager.exportReportToPdfFile(filledReport,getExportPath()+"/"+ SinavCikti.getTextFieldSinavAdi().getText().toString()+"YoklamaListesi.pdf");	
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
