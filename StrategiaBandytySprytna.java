package dzikizachod;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class StrategiaBandytySprytna extends StrategiaBandyty {
	
	List<Gracz> nieBandyciWZasiegu;
	List<Gracz> bandyciZJednymZyciem;
	
	public void setPoczatkoweWartosci(Gracz gracz, int zasieg) {
		this.gracz = gracz;
		this.zasieg = zasieg;
		this.spryt();
		this.nieBandyciWZasiegu = new ArrayList<Gracz>();
		this.bandyciZJednymZyciem = new ArrayList<Gracz>();
	}
	
	private void bandyciDoZabicia() {
		this.graczeKtorzySaWZasiegu();
		this.bandyciZJednymZyciem = new ArrayList<Gracz>();
		for (int i  = 0; i < this.graczeWZasiegu.size(); i++) {
			if (this.graczeWZasiegu.get(i).tozsamosc() == "Bandyta" &&
				this.graczeWZasiegu.get(i).getLiczbaZyc() == 1) {
				this.bandyciZJednymZyciem.add(this.graczeWZasiegu.get(i));
			}
		}
	}
	
	 
	public  Gracz wKogoStrzelac(Gra gra) {
		this.nieBandyciWZasiegu = new ArrayList<Gracz>();
		if (!this.spryt && this.bandyciZJednymZyciem.size() > 0) {
			Random r = new Random();
			int k = r.nextInt(this.bandyciZJednymZyciem.size());
			this.spryt = true;
			return this.bandyciZJednymZyciem.get(k);
		}
		for(int i = 0; i < this.graczeWZasiegu.size(); i++) {
			if (this.graczeWZasiegu.get(i).tozsamosc() != "Bandyta") {
				this.nieBandyciWZasiegu.add(this.graczeWZasiegu.get(i));
			}
		}	
		Random r = new Random();
		int k = r.nextInt(this.nieBandyciWZasiegu.size());
		return this.nieBandyciWZasiegu.get(k);
	}
	
	public boolean czyBangnacKogos() {
		this.bandyciDoZabicia();
		this.graczeKtorzySaWZasiegu();
		if (!this.spryt && this.bandyciZJednymZyciem.size() > 0) {
			return true;
		}
		for (int i = 0; i < this.graczeWZasiegu.size(); i++) {
			if (this.graczeWZasiegu.get(i).tozsamosc() != "Bandyta") {
				return true;
			}
		}
		
		return false;
	}
}
