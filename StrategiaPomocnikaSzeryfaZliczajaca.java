package dzikizachod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StrategiaPomocnikaSzeryfaZliczajaca extends StrategiaPomocnikaSzeryfa {

	
	List<DaneZliczajacego> dane = new ArrayList<DaneZliczajacego>();
	protected List<Gracz> graczeKtorzyStrzelaliDoSzeryfa;
	protected List<Gracz> graczeKtorzySpelniajaObaWarunki;
	private boolean czyUstawilesDane = false;
	
	protected void graczeKtorzySaWZasieguIStrzelali() {
		this.graczeKtorzySpelniajaObaWarunki = new ArrayList<Gracz>();
		for (int i = 0; i < this.graczeKtorzyStrzelaliDoSzeryfa.size(); i++) {
			if (this.graczeWZasiegu.contains(this.graczeKtorzyStrzelaliDoSzeryfa.get(i))) {
				this.graczeKtorzySpelniajaObaWarunki.add(this.graczeKtorzyStrzelaliDoSzeryfa.get(i));
			}
		}
	}
	
	public void setPoczatkoweWartosci(Gracz gracz, int zasieg) {
		this.gracz = gracz;
		this.zasieg = zasieg;
		this.spryt();
		this.graczeKtorzyStrzelaliDoSzeryfa = new ArrayList<Gracz>();
		this.graczeKtorzySpelniajaObaWarunki = new ArrayList<Gracz>();
	}
	
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
			while(this.dane.get(j).getGracz() != g) {
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
				dane.add(new DaneZliczajacego(grajacyGracze.get(h)));
			}
			this.czyUstawilesDane = true;
		}
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
	
	private boolean czyWiecejPomocnikow(Gracz g) {
		int i = 0;
		while(i < this.dane.size() && this.dane.get(i).getGracz() != g) {
			i++;
		}
		return this.dane.get(i).czyWiecejPomocnikow();
	}
	
	public boolean czyWypuscicDynamit() {
		int i = 0;
		int ilePodejrzanych = 0;
		while (this.grajacyGracze.get(i) != this.gracz) {
			i++;
		}
		int j = 1;
		int size = this.grajacyGracze.size();
		while (this.grajacyGracze.get((i + j)% size).tozsamosc() != "Szeryf") {
			if (czyWiecejPomocnikow(this.grajacyGracze.get((i + j) % size))) {
				ilePodejrzanych++;
			}
			j++;
		}
		if (j > 3 && 3 * ilePodejrzanych >= 2 * j) {
			return true;
		}
		return false;
	}
}
