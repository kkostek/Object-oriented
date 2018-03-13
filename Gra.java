package dzikizachod;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Gra {
	
	private boolean dynamit;
	private Gracz ktoryGraczTeraz;
	private int ktoryGraczTerazNumer;
	private List<Gracz> gracze;
	private List<Gracz> grajacyGracze;
	private Ruch ostatniBang;
	private int liczbaBandytow;
	private int gdzieSiedziSzeryf;
	private boolean czySzeryfZyje;
	private PulaAkcji pulaAkcji;
	private int ileRazySzeryfMialKolejke;
	
	public Gra() {
		this.dynamit = false;
		this.czySzeryfZyje = true;
		this.pulaAkcji = new PulaAkcji();
		this.ileRazySzeryfMialKolejke = 0;
		this.gracze = new ArrayList<Gracz>();
		this.grajacyGracze = new ArrayList<Gracz>();
	}
	
	public Gracz getSzeryf() {
		return this.gracze.get(gdzieSiedziSzeryf);
	}
	
	public Ruch getOstatniBang() {
		return this.ostatniBang;
	}
	
	public int numerSzeryfa() {
		return this.gdzieSiedziSzeryf + 1;
	}
	
	public int numerGracza(Gracz g) {
		int i = 0;
		while(i < this.gracze.size() && this.gracze.get(i) != g) {
			i++;
		}
			return (i + 1);
	}
	
	public List<Gracz> getGracze() {
		return this.gracze;
	}
	
	public List<Gracz> getGrajacychGraczy() {
		return this.grajacyGracze;
	}
		
	public int terazGracz() {
		return this.ktoryGraczTerazNumer + 1;
	}
	
	public boolean czyZyje(Gracz g) {
		return (!(g.getLiczbaZyc() == 0));
	}
	
	public void Bang(Gracz gracz) {
		gracz.zostalemBangniety(this);
		String s;
		if (!czyZyje(gracz)) {
			s = gracz.tozsamosc();
		this.grajacyGracze.remove(gracz);
			if (s == "Szeryf") {
				this.czySzeryfZyje = false;
			}
			else if (s == "Bandyta") {
				this.liczbaBandytow--;
			}
		}
		else {
			s = "";
		}
		
		this.ostatniBang = new Ruch(this.ktoryGraczTeraz, gracz, s);
		for (int i = 0; i < grajacyGracze.size(); i++) {
			gracze.get(i).aktualizujDane(this.getGrajacychGraczy(), this.getOstatniBang());
		}
	}
	
	public void stworzDynamit() {
		this.dynamit = true;
	}

	public void usunDynamit() {
		this.dynamit = false;
	}
	
	public void uleczSzeryfa() {
		gracze.get(gdzieSiedziSzeryf).uleczSie();
	}
	
	public void wypiszRuchMartwego() {
		System.out.println("GRACZ " + this.terazGracz() + " (" + this.ktoryGraczTeraz.tozsamosc() + ")");
		System.out.println("MARTWY");
		System.out.println("");
	}
	
	public void wypiszRuchZywego() {
		System.out.println("GRACZ " + this.terazGracz() + " (" + this.ktoryGraczTeraz.tozsamosc() + ")");
		System.out.print("Akcje: ");
		this.ktoryGraczTeraz.wypiszAkcje();
		System.out.println("");
		System.out.println("Ruchy:");
	}
	
	public void wypiszRuchZywegoJakDynamit(boolean czyWybuchl, boolean czyZyje) {
		System.out.println("GRACZ " + this.terazGracz() + " (" + this.ktoryGraczTeraz.tozsamosc() + ")");
		System.out.print("Akcje: ");
		this.ktoryGraczTeraz.wypiszAkcje();
		System.out.println("");
		System.out.print("Dynamit: ");
		if (czyWybuchl) {
			System.out.println("WYBUCHŁ");
		}
		else {
			System.out.println("PRZECHODZI DALEJ");
		}
		System.out.println("Ruchy:");
		if (!czyZyje) {
			System.out.println("MARTWY");
		}
	}
	
	public void koniec() {
		System.out.println("** KONIEC");
		if (!this.czySzeryfZyje) {
			System.out.println("WYGRANA STRONA: bandyci");
		}
		else if (this.liczbaBandytow == 0) {
			System.out.println("WYGRANA STRONA: szeryf i pomocnicy");
		}
		else {
			System.out.println("REMIS - OSIĄGNIĘTO LIMIT TUR");
		}
	}
	
	public void wypiszStart() {
		System.out.println("** START");
	}
	
	public void wypiszTure() {
		System.out.println("** TURA " + this.ileRazySzeryfMialKolejke);
	}
	
	public void wypiszGraczy() {
		System.out.println("  Gracze:");
		for (int i = 1; i <= this.gracze.size(); i++) {
			if (this.czyZyje(gracze.get(i - 1))) {
			System.out.println("    " + i + ": " + gracze.get(i - 1).tozsamosc() + " (liczba żyć: " + gracze.get(i - 1).getLiczbaZyc() + ")");
			}
			else {
				System.out.println("    " + i + ": X (" + gracze.get(i - 1).tozsamosc() + ")");
			}
		}
		System.out.println("");
	}
	
	public void obslozGracza(Gracz g) {
		if (g.tozsamosc() == "Szeryf") {
			this.ileRazySzeryfMialKolejke++;
			this.wypiszTure();
		}
		if (this.czyZyje(g)) {
			g.dobierzKarty(this.pulaAkcji);
			if (this.dynamit) {
				g.rzucajODynamit(this);
				if (this.dynamit) {
					this.wypiszRuchZywegoJakDynamit(false, true);
					g.wykonajRuch(this, this.pulaAkcji);
					this.wypiszGraczy();
				}
				else if (this.czyZyje(g)) {
						this.wypiszRuchZywegoJakDynamit(true, true);
						g.wykonajRuch(this, this.pulaAkcji);
						this.wypiszGraczy();
					}
					else {
						this.wypiszRuchZywegoJakDynamit(true, false);
					}
			}
			else {	
				this.wypiszRuchZywego();
				g.wykonajRuch(this, this.pulaAkcji);
				this.wypiszGraczy();
			}
		}
		else {
			this.wypiszRuchMartwego();
		}
	}
	
	public void nastepnyGracz() {
		int size = this.gracze.size();
		this.ktoryGraczTerazNumer = (this.ktoryGraczTerazNumer + 1) % size;
		this.ktoryGraczTeraz = this.gracze.get(this.ktoryGraczTerazNumer);		
	}
	
	private List<Gracz> losujKolejnosc(List<Gracz> gracze) {
		Random r = new Random();
		int j, k; 
		Gracz pom;
		for (int i = 0; i < gracze.size() * 30; i++) {
			j = r.nextInt(gracze.size());
			k = r.nextInt(gracze.size());
			while(j == k) {
				j = r.nextInt(gracze.size());
			}
			pom = gracze.get(j);
			gracze.set(j, gracze.get(k));
			gracze.set(k, pom);
		}
		return gracze;
	}
	public void rozgrywka(List<Gracz> gracze, PulaAkcji pulaAkcji) {
		this.gracze = losujKolejnosc(gracze);
		for (int i = 0; i < this.gracze.size(); i++) {
			this.grajacyGracze.add(this.gracze.get(i));
		}
		
		for (int i = 0; i < gracze.size(); i++) {
			if (gracze.get(i).tozsamosc() == "Bandyta") {
				this.liczbaBandytow++;
			}
		}
		
		this.pulaAkcji = pulaAkcji;
		this.pulaAkcji.tasuj();
		int i = 0; 
		while(grajacyGracze.get(i).tozsamosc() != "Szeryf") {
			i++;
		}
		this.gdzieSiedziSzeryf = i;
		this.ktoryGraczTerazNumer = i;
		for (int u = 0; u < this.gracze.size(); u++) {
			this.gracze.get(u).daneZPoczatku(this.grajacyGracze);
		}
		this.ktoryGraczTeraz = grajacyGracze.get(i);
		this.ileRazySzeryfMialKolejke = 0;
		this.wypiszStart();
		this.wypiszGraczy();
		for (int h = 0; h < this.grajacyGracze.size(); h++) {
			grajacyGracze.get(h).aktualizujDane(grajacyGracze, null);
		}
		while(this.ileRazySzeryfMialKolejke <= 42 && this.czySzeryfZyje && 
			  this.liczbaBandytow > 0) {
			this.obslozGracza(ktoryGraczTeraz);
			this.pulaAkcji.tasuj();
			nastepnyGracz();
		}
		this.koniec();
	}

}

