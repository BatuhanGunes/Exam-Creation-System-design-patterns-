package Strategy;

public class SoruZorlugu {
	private KolaySinav kSinav = KolaySinav.getObj();
	private OrtaSinav oSinav = OrtaSinav.getObj();
	private ZorSinav zSinav = ZorSinav.getObj();
	private ZorlukAyari zorlukAyari;
	
	public SoruZorlugu() {
		
	}
	
	public void setZorlukAyari(ZorlukAyari zorlukAyari) {
		this.zorlukAyari = zorlukAyari;
	}
	
	public void zorlukAyarla() {
		zorlukAyari.zorlukAyarla();
		
	}
	
	public int kolaySoruSayisiKullan(ZorlukAyari zorlukAyari) {
		return zorlukAyari.getKolaySoruSayisi();
	}
	public int ortaSoruSayisiKullan(ZorlukAyari zorlukAyari) {
		return zorlukAyari.getOrtaSoruSayisi();
	}
	public int zorSoruSayisiKullan(ZorlukAyari zorlukAyari) {
		return zorlukAyari.getZorSoruSayisi();
	}
	
}
