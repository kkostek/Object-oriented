package dzikizachod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StrategiaSzeryfaDomyslna extends StrategiaSzeryfa {
	
	public void aktualizujDane(List<Gracz> grajacyGracze, Ruch ostatniBang) {
		if (ostatniBang != null) {
			if (ostatniBang.getOfiara().tozsamosc() == "Szeryf") {
				if (!this.graczeKtorzyStrzelaliDoSzeryfa.contains(ostatniBang.getStrzelec()))
					this.graczeKtorzyStrzelaliDoSzeryfa.add(ostatniBang.getStrzelec());
			}
		}
		this.grajacyGracze = grajacyGracze;
	}
	
	public void setPoczatkoweWartosci(Gracz gracz, int zasieg) {
		this.gracz = gracz;
		this.zasieg = zasieg;
		this.spryt();
		this.graczeKtorzyStrzelaliDoSzeryfa = new ArrayList<Gracz>();
	}
	
	public Gracz wKogoStrzelac(Gra gra) {
		this.graczeKtorzySaWZasiegu();
		this.graczeKtorzySaWZasieguIStrzelali();
		Random r = new Random();
		int k;
		if (this.graczeKtorzySpelniajaObaWarunki.size() > 0) {
			k = r.nextInt(this.graczeKtorzySpelniajaObaWarunki.size());
			return this.graczeKtorzySpelniajaObaWarunki.get(k);
		}
		else {
			assert (this.graczeWZasiegu.size() > 0);
			k = r.nextInt(this.graczeWZasiegu.size());
			return this.graczeWZasiegu.get(k);
		}
	}
}
