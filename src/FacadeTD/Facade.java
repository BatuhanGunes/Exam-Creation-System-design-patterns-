package FacadeTD;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;

public class Facade {
	
	private static Facade obj=null;
	private SinavKagidi sinavKagidi;
	private YoklamaListesi yoklamaListesi;
	private SinavYeriIlanListesi sinavYeriIlanListesi;
	
	private Facade() {
		sinavKagidi = new SinavKagidi();
		yoklamaListesi = new YoklamaListesi();
		sinavYeriIlanListesi = new SinavYeriIlanListesi();
	}
	private static Facade objOlustur() {
		if(obj == null) {
			obj = new Facade();
			
		}return obj;
	}
	public static Facade objDondur() {
		return objOlustur();
	}
	public void ciktiYoluAl(String exportPath) {
		sinavKagidi.setExportPath(exportPath);
		yoklamaListesi.setExportPath(exportPath);
		sinavYeriIlanListesi.setExportPath(exportPath);

	}
	public void allShowReport() throws JRException {
		sinavYeriIlanListesi.setVisible(true);
		sinavYeriIlanListesi.ShowReport();
		sinavKagidi.setVisible(true);
		sinavKagidi.ShowReport();
		yoklamaListesi.setVisible(true);
		yoklamaListesi.ShowReport();
	}
	public SinavYeriIlanListesi getSinavYeriIlanListesi() {
		return sinavYeriIlanListesi;
	}
	public YoklamaListesi getYoklamaListesi() {
		return yoklamaListesi;
	}
	public SinavKagidi getSinavKagidi() {
		return sinavKagidi;
	}
	
}
