package dzikizachod;


import java.util.List;
import java.util.Random;

public class StrategiaPomocnikaSzeryfaDomyslna extends StrategiaPomocnikaSzeryfa {
	public void aktualizujDane(List<Gracz> grajacyGracze, Ruch ostatniBang) {
		this.grajacyGracze = grajacyGracze;
	}
	
	public void setPoczatkoweWartosci(Gracz gracz, int zasieg) {
		this.gracz = gracz;
		this.zasieg = zasieg;
		this.spryt();
	}
	
	public boolean czyWypuscicDynamit() {
		return false;
	}
	
	public Gracz wKogoStrzelac(Gra gra) {
		this.graczeKtorzySaWZasiegu();
		Gracz szeryf = this.gracz; //bo musi bys jakis ustawiony;
		boolean c = false;
		for (int i = 0; i < this.graczeWZasiegu.size(); i++) {
			if (this.graczeWZasiegu.get(i).tozsamosc() == "Szeryf") {
				szeryf = this.graczeWZasiegu.remove(i);
				c = true;
				break;
			}
		}
		Random r = new Random();
		int k = r.nextInt(this.graczeWZasiegu.size());
		Gracz ofiara = this.graczeWZasiegu.get(k);
		if (c) {
			this.graczeWZasiegu.add(szeryf);
		}
		return ofiara;
	}
	
	public boolean czyBangnacKogos() {
		return true;
	}
}
