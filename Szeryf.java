package dzikizachod;

import java.util.ArrayList;
 
public class Szeryf extends Gracz {

	public Szeryf() {
		this.liczbaZyc = 5;
		this.poczatkowaLiczbaZyc = 5;
		this.strategia = new StrategiaSzeryfaDomyslna();
		this.strategia.setPoczatkoweWartosci(this, 1);
		this.karty = new ArrayList<Akcja>();
	}
	
	public Szeryf(StrategiaSzeryfa s) {
		this.liczbaZyc = 5;
		this.poczatkowaLiczbaZyc = 5;
		this.strategia = s;
		this.strategia.setPoczatkoweWartosci(this, 1);;
		this.karty = new ArrayList<Akcja>();
	}
	
	public String tozsamosc() {
		return "Szeryf";
	}
	
}
