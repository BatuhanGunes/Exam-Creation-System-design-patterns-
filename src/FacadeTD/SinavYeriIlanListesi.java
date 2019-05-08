package FacadeTD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import View.SinavCikti;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

public class SinavYeriIlanListesi extends JFrame {
	static YoklamaListesiModel model = new YoklamaListesiModel();
	static ArrayList<String> pdfAdi = new ArrayList<String>();
	static boolean dogru = true;
	private static String exportPath =System.getProperty("user.home") + "/Desktop";
	
	public static String getExportPath() {
		return exportPath;
	}

	public static void setExportPath(String exportPath) {
		SinavYeriIlanListesi.exportPath = exportPath;
	}

	public SinavYeriIlanListesi() {
		
	}
	private void pdfAdiAyarla(int i) {
		pdfAdi.add(model.getSalonNo().get(i).toString());
	}
	private void jasperReportIslemleri(LinkedList<Map<String,Object>> dataSource,int dosyaAdiNumarasi) throws JRException {
		JRDesignStyle jrDesignStyle = new JRDesignStyle();
	    /*Set the Encoding to UTF-8 for pdf and embed font to arial*/
	    jrDesignStyle.setDefault(true);
	    jrDesignStyle.setPdfEncoding("Cp1254");
	    jrDesignStyle.setPdfEmbedded(true);
	    
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(dataSource);
		String sourceName = "SinavYeriIlanListesi.jrxml";
		JasperReport report = JasperCompileManager.compileReport(sourceName);
		JasperPrint filledReport = JasperFillManager.fillReport(report, null, jrDataSource);
	    filledReport.addStyle(jrDesignStyle);
	    
		this.getContentPane().add(new JRViewer(filledReport));
		this.pack();
		//PDF Olarak Çýktý Alma
		JRXmlLoader.load(sourceName);
		//JasperExportManager.exportReportToPdfFile(filledReport,getExportPath()+"/"+ SinavCikti.getTextFieldSinavAdi().getText().toString()+"SinavYeriIlanListesi.pdf");	
		JasperExportManager.exportReportToPdfFile(filledReport,getExportPath()+"/"+ SinavCikti.getTextFieldSinavAdi().getText().toString()+pdfAdi.get(dosyaAdiNumarasi)+"SinavYeriIlanListesi.pdf");	
		dataSource.clear();
	}
	public void ShowReport() throws JRException {	
		while(dogru) {
		LinkedList<Map<String,Object>> dataSource = new LinkedList<Map<String,Object>>();
		int sonKapasite = 0;
		
			for(int i =0;;) {
				pdfAdiAyarla(i);
				Map<String,Object> m = new HashMap<String, Object>();
				m.put("salonNo",model.getSalonNo().get(i));
				m.put("gozetmen",model.getGozetmen().get(i));
				m.put("binaAdi",model.getBinaAdi().get(0));
				m.put("sinavAdi",model.getSinavAdi());
				m.put("dersAdi",model.getDersAdi());
				
					m.put("ogrAdveSoyad",model.getOgrenciAdveSoyadi().get(sonKapasite));
					m.put("ogrNo",model.getOgrenciNumarasi().get(sonKapasite).toString());
					m.put("siraNo",1);
					sonKapasite++;
				dataSource.add(m);
					
				for(int j = 1;j<model.getKapasite().get(i);j++){
					Map<String,Object> m2 = new HashMap<String, Object>();
				    m2.put("ogrAdveSoyad",model.getOgrenciAdveSoyadi().get(sonKapasite));
					m2.put("ogrNo",model.getOgrenciNumarasi().get(sonKapasite).toString());
					m2.put("siraNo",j+1);
					dataSource.add(m2);
					sonKapasite++;
					if(model.getOgrenciNumarasi().size()/2 == sonKapasite) {
						dogru = false;
						break;
					}
					
				}
				jasperReportIslemleri(dataSource, i);
					
				if(model.getOgrenciNumarasi().size()/2 == sonKapasite) {
					dogru = false;
					break;
				}else i++;
			}		
		}
	}
}
