package Strategy;


import View.SinavOlustur;
public class KolaySinav extends ZorlukAyari {
	
	private static KolaySinav obj=null;
	SinavOlustur sinavOlustur = SinavOlustur.getObj();
	private int kolaySoruSayisi, ortaSoruSayisi, zorSoruSayisi;

	private KolaySinav() {
		
	}
	public static KolaySinav getObj(){
		if(obj==null){
          obj=new KolaySinav();
      }return obj;
	}
	@Override
	public void zorlukAyarla() {
		
		soruSayisiHesapla();
	
	}
	
	public void soruSayisiHesapla() {
		soruHesapla();
		if(sinavOlustur.toplamSoruSayisi() == hesaplananToplamSoruSayisi()) {
			
		}
		else if(sinavOlustur.toplamSoruSayisi() < hesaplananToplamSoruSayisi()) {
			int gecici = (int) (hesaplananToplamSoruSayisi()-sinavOlustur.toplamSoruSayisi());
			setKolaySoruSayisi(kolaySoruSayisi-gecici);
			
		}else {
			int gecici = (int) (sinavOlustur.toplamSoruSayisi()-hesaplananToplamSoruSayisi());
			setKolaySoruSayisi(kolaySoruSayisi+gecici);
		}
	}
	@Override
	public int getKolaySoruSayisi() {
		return kolaySoruSayisi;
	}
	@Override
	public int getOrtaSoruSayisi() {
		return ortaSoruSayisi;
	}
	@Override
	public int getZorSoruSayisi() {
		return zorSoruSayisi;
	}
	public int hesaplananToplamSoruSayisi() {
		return kolaySoruSayisiHesapla()+ortaSoruSayisiHesapla()+zorSoruSayisiHesapla();
	}
	public int kolaySoruSayisiHesapla() {
		kolaySoruSayisi = (int)(sinavOlustur.toplamSoruSayisi()/10*5);
		return kolaySoruSayisi;
	}
	public int ortaSoruSayisiHesapla() {
		ortaSoruSayisi =(int)(sinavOlustur.toplamSoruSayisi()/10*3);
		return ortaSoruSayisi;
	}
	public int zorSoruSayisiHesapla() {
		zorSoruSayisi = (int)(sinavOlustur.toplamSoruSayisi()/10*2);
		return zorSoruSayisi;
	}
	public void setKolaySoruSayisi(int kolaySoruSayisi) {
		this.kolaySoruSayisi = kolaySoruSayisi;
	}
	public void setOrtaSoruSayisi(int ortaSoruSayisi) {
		this.ortaSoruSayisi = ortaSoruSayisi;
	}
	public void setZorSoruSayisi(int zorSoruSayisi) {
		this.zorSoruSayisi = zorSoruSayisi;
	}
	public void soruHesapla() {
		kolaySoruSayisiHesapla();
		ortaSoruSayisiHesapla();
		zorSoruSayisiHesapla();
	}
	
}
