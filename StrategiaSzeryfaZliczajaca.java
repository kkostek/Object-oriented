package dzikizachod;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class StrategiaSzeryfaZliczajaca extends StrategiaSzeryfa {
	List<DaneZliczajacego> dane = new ArrayList<DaneZliczajacego>();
	private boolean czyUstawilesDane = false;
	
	public void aktualizujDane(List<Gracz> grajacyGracze, Ruch ostatniBang) {
		this.grajacyGracze = grajacyGracze;
		if (ostatniBang != null) {
			if (ostatniBang.getOfiara().tozsamosc() == "Szeryf") {
				if (!this.graczeKtorzyStrzelaliDoSzeryfa.contains(ostatniBang.getStrzelec()))
					this.graczeKtorzyStrzelaliDoSzeryfa.add(ostatniBang.getStrzelec());
			}
			int i = 0;
			while (this.grajacyGracze.get(i) != ostatniBang.getStrzelec()) {
				i++;
			}
			int j = 0;
			Gracz g = this.grajacyGracze.get(i);
			while(this.dane.get(i).getGracz() != g) {
				j++;
			}
			if (ostatniBang.tozsamosc() == "Bandyta") {
				this.dane.get(j).zwiekszBandytow();
			}
			else if (ostatniBang.tozsamosc() == "Pomocnik Szeryfa") {
				this.dane.get(j).zwiekszPomocnikow();
			}
		}
		if (!czyUstawilesDane) {
			for (int h = 0; h < grajacyGracze.size(); h++) {
				DaneZliczajacego dana = new DaneZliczajacego(grajacyGracze.get(h));
				dane.add(dana);
			}
			this.czyUstawilesDane = true;
		}
	}
	
	public void setPoczatkoweWartosci(Gracz gracz, int zasieg) {
		this.gracz = gracz;
		this.zasieg = zasieg;
		this.spryt();
		this.graczeKtorzyStrzelaliDoSzeryfa = new ArrayList<Gracz>();
	}
	
	private void proponowaneOfiary() {
		for (int i = 0; i < this.dane.size(); i++) {
			if (this.dane.get(i).czyWiecejPomocnikow() && 
				this.graczeWZasiegu.contains(this.dane.get(i).getGracz()) &&
				!this.graczeKtorzySpelniajaObaWarunki.contains(dane.get(i).getGracz())) {
					this.graczeKtorzySpelniajaObaWarunki.add(this.dane.get(i).getGracz());
			}
		}
	}
	
	@Override 
	public boolean czyBangnacKogos() {
		this.graczeKtorzySaWZasiegu();
		this.graczeKtorzySaWZasieguIStrzelali();
		this.proponowaneOfiary();
		return (this.graczeKtorzySpelniajaObaWarunki.size() != 0);
	}
	
	public Gracz wKogoStrzelac(Gra gra) {
		Random r = new Random();
		int k = r.nextInt(this.graczeKtorzySpelniajaObaWarunki.size());
		return this.graczeKtorzySpelniajaObaWarunki.get(k);
	}
}
