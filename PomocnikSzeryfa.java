package dzikizachod;

import java.util.ArrayList;
import java.util.Random;

public class PomocnikSzeryfa extends Gracz {

	public PomocnikSzeryfa() {
		Random generator = new Random();
		this.liczbaZyc = 3 + generator.nextInt(2);
		this.poczatkowaLiczbaZyc = this.liczbaZyc;
		this.strategia = new StrategiaPomocnikaSzeryfaDomyslna();
		this.strategia.setPoczatkoweWartosci(this, 1);
		this.karty = new ArrayList<Akcja>();
	}
	
	public PomocnikSzeryfa(StrategiaPomocnikaSzeryfa s) {
		Random generator = new Random();
		this.liczbaZyc = 3 + generator.nextInt(2);
		this.poczatkowaLiczbaZyc = this.liczbaZyc;
		this.strategia = s;
		this.strategia.setPoczatkoweWartosci(this, 1);
		this.karty = new ArrayList<Akcja>();
	}
	
	public String tozsamosc() {
		return "Pomocnik Szeryfa";
	}
}
