package dzikizachod;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class StrategiaBandytyDomyslna extends StrategiaBandyty {
	
	
	List<Gracz> nieBandyciWZasiegu;
	
	public void setPoczatkoweWartosci(Gracz gracz, int zasieg) {
		this.gracz = gracz;
		this.zasieg = zasieg;
		this.spryt();
		this.nieBandyciWZasiegu = new ArrayList<Gracz>();
	}
	
	public  Gracz wKogoStrzelac(Gra gra) {
		this.nieBandyciWZasiegu = new ArrayList<Gracz>();
		for(int i = 0; i < this.graczeWZasiegu.size(); i++) {
			if (this.graczeWZasiegu.get(i).tozsamosc() != "Bandyta") {
				this.nieBandyciWZasiegu.add(this.graczeWZasiegu.get(i));
			}
		}	
		if (this.nieBandyciWZasiegu.contains(gra.getSzeryf())) {
			return gra.getSzeryf();
		}
		Random r = new Random();
		int k = r.nextInt(this.nieBandyciWZasiegu.size());
		return this.nieBandyciWZasiegu.get(k);
	}
	
	public boolean czyBangnacKogos() {
		this.graczeKtorzySaWZasiegu();
		for (int i = 0; i < this.graczeWZasiegu.size(); i++) {
			if (this.graczeWZasiegu.get(i).tozsamosc() != "Bandyta") {
				return true;
			}
		}
		return false;
	}

}
