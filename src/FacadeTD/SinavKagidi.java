package FacadeTD;



import javax.swing.JFrame;
import javax.swing.JOptionPane;

import View.SinavCikti;
import View.SinavOlustur;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRProperties;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

import java.util.*;

public class SinavKagidi extends JFrame {
	SinavKagidiModel model = new SinavKagidiModel();
	SinavOlustur sinavOlustur = SinavOlustur.getObj();
	
	private static String exportPath =System.getProperty("user.home") + "/Desktop";
	
	public static String getExportPath() {
		return exportPath;
	}

	public static void setExportPath(String exportPath) {
		SinavKagidi.exportPath = exportPath;
	}

	public SinavKagidi() {
		
	}
	
	public void ShowReport() {
		try {
					
			List<Map<String,Object>> dataSource = new ArrayList<Map<String,Object>>();
			for (int x=1 ; x<=(int)sinavOlustur.toplamSoruSayisi() ; x++  ) {
				
				Map<String,Object> m = new HashMap<String, Object>();
				m.put("SoruNumarasi",x +")");
				m.put("ogretimUyesi", model.getOgretimUyesi());
				m.put("DersAdi", model.getDersAdi());
				m.put("SinavAdi", model.getSinavAdi());
				
				if(model.getTipID().get(x-1) == 1) {
					m.put("Soru",model.getSoru().get(x-1));
					m.put("SikA", "A) "+model.sikA.get(x-1));
					m.put("SikB", "B) "+model.sikB.get(x-1));
					m.put("SikC", "C) "+model.sikC.get(x-1));
					m.put("SikD", "D) "+model.sikD.get(x-1));
					m.put("Puan", "("+model.getPuan().get(x-1)+" Puan)");
				}else if(model.getTipID().get(x-1) == 2) {
					m.put("Soru",model.getSoru().get(x-1));
					m.put("SikA", "Bu ifadeye göre aþaðýdakilerden birisini yuvarlak içerisine alýnýz.");
					m.put("SikB", "Doðru");
					m.put("SikC", "Yanlýþ");
					m.put("SikD", "");
					m.put("Puan","("+model.getPuan().get(x-1)+" Puan)");
				}else if(model.getTipID().get(x-1) == 3) {
					m.put("Soru",model.getSoru().get(x-1));
					m.put("SikA", "Cevabı Giriniz :");
					m.put("SikB", "");
					m.put("SikC", "");
					m.put("SikD", "");
					m.put("Puan", "("+model.getPuan().get(x-1)+" Puan)");
					
				}
				
				dataSource.add(m);
				
			}
			
		    JRDesignStyle jrDesignStyle = new JRDesignStyle();
		    /*Set the Encoding to UTF-8 for pdf and embed font to arial*/
		    jrDesignStyle.setDefault(true);
		    jrDesignStyle.setPdfEncoding("Cp1254");
		    jrDesignStyle.setPdfEmbedded(true);
		    
			JRDataSource jrDataSource = new JRBeanCollectionDataSource(dataSource);
			String sourceName = "SinavKagidi.jrxml";
			JasperReport report = JasperCompileManager.compileReport(sourceName);
			JasperPrint filledReport = JasperFillManager.fillReport(report, null, jrDataSource);
		    filledReport.addStyle(jrDesignStyle);
		    
			this.getContentPane().add(new JRViewer(filledReport));
			this.pack();
			//PDF Olarak Çýktý Alma
			JRXmlLoader.load(sourceName);
					
			JasperExportManager.exportReportToPdfFile(filledReport,getExportPath()+"/"+ SinavCikti.getTextFieldSinavAdi().getText().toString()+".pdf");	
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
