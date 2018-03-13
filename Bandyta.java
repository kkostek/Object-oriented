package dzikizachod;

import java.util.ArrayList;
import java.util.Random;

public class Bandyta extends Gracz {
		
	public Bandyta() {
		Random generator = new Random();
		this.liczbaZyc = 3 + generator.nextInt(2);
		this.poczatkowaLiczbaZyc = this.liczbaZyc;
		this.strategia = new StrategiaBandytyDomyslna();
		this.strategia.setPoczatkoweWartosci(this, 1);
		this.karty = new ArrayList<Akcja>();
	}
	
	public Bandyta(StrategiaBandyty s) {
		Random generator = new Random();
		this.liczbaZyc = 3 + generator.nextInt(2);
		this.poczatkowaLiczbaZyc = this.liczbaZyc;
		this.strategia = s;
		this.strategia.setPoczatkoweWartosci(this, 1);
		this.karty = new ArrayList<Akcja>();
	}
	
	public String tozsamosc() {
		return "Bandyta";
	}

}