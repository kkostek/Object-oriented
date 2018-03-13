package dzikizachod;

import java.util.List;
import java.util.Random;

public abstract class Gracz {
	protected int liczbaZyc;
	protected List<Akcja> karty;
	protected Strategia strategia;
	protected int poczatkowaLiczbaZyc;
	
	public void dobierzKarty(PulaAkcji pulaAkcji) {
		int size = this.karty.size();
		for (int i = 0; i < 5 - size; i++) {
			Akcja a = pulaAkcji.wezKarte();
			if (a != null) {
				karty.add(a);
			}
		}
	}
	
	public void wypiszAkcje() {
		System.out.println(this.karty);
	}
	
	public int getLiczbaZyc() {
		return this.liczbaZyc;
	}
	
	
	public void zostalemBangniety(Gra gra) {
		this.liczbaZyc -= 1; 
	}
	
	public void uleczSie() {
		this.liczbaZyc ++;
	}
	
	public void uleczSzeryfa(Gra gra) {
		gra.uleczSzeryfa();
	}
	
	public boolean czyTrzebaUleczyc() {
		return (!(this.liczbaZyc == this.poczatkowaLiczbaZyc));
	}
	 
	public void wykonajRuch(Gra gra, PulaAkcji pulaAkcji) {
		this.karty.sort((o1, o2) -> o1.compareTo(o2));
		this.strategia.spryt();
		int iterator = 0;
		while(iterator < karty.size()) {
			if (karty.get(iterator) == Akcja.ULECZ) {
				if (this.liczbaZyc < this.poczatkowaLiczbaZyc) {
					if (this.strategia.kogoLeczyc()) {
						uleczSie();
						System.out.println("      " + karty.get(iterator));
					}
					else {
						uleczSzeryfa(gra);
						System.out.println("      " + karty.get(iterator) + " " + gra.numerSzeryfa());
					} 
					pulaAkcji.odrzucKarte(karty.remove(iterator));
				}
				else {
					iterator++;
				}
			}
			else if (karty.get(iterator) == Akcja.ZASIEG_PLUS_JEDEN) {
				this.strategia.setZasieg(this.strategia.getZasieg() + 1);
				System.out.println("      " + karty.get(iterator));
				pulaAkcji.odrzucKarte(karty.remove(iterator));
			}
			else if (karty.get(iterator) == Akcja.ZASIEG_PLUS_DWA) {
				this.strategia.setZasieg(this.strategia.getZasieg() + 2);
				System.out.println("      " + karty.get(iterator));
				pulaAkcji.odrzucKarte(karty.remove(iterator));
			}
			else if (karty.get(iterator) == Akcja.STRZEL) {
				if (this.strategia.czyBangnacKogos()) {
					Gracz ofiara = this.strategia.wKogoStrzelac(gra);
					gra.Bang(ofiara);
				
					System.out.println("      " + karty.get(iterator) + " " + gra.numerGracza(ofiara));
					pulaAkcji.odrzucKarte(karty.remove(iterator));
				}
				else {
					iterator++;
				}
			}
			
			else if (karty.get(iterator) == Akcja.DYNAMIT) {
				if (this.strategia.czyWypuscicDynamit()) {
					gra.stworzDynamit();
					System.out.println("      " + karty.get(iterator));
					karty.remove(iterator);
				}
				else {
					iterator++;
				}
			}
		}
	}
	
	public void aktualizujDane(List<Gracz> grajacyGracze, Ruch ostatniBang) {
		this.strategia.aktualizujDane(grajacyGracze, ostatniBang);
	}
	public void daneZPoczatku(List<Gracz> grajacyGracze) {
		this.strategia.daneZPoczatku(grajacyGracze);
	}
	
	public void oddajKarty(PulaAkcji pulaAkcji) {
		while (!this.karty.isEmpty()) {
			pulaAkcji.odrzucKarte(karty.remove(0));
		}
	}

	public void rzucajODynamit(Gra gra) {
		Random los = new Random();
		int k = los.nextInt(6) + 1;
		if (k == 1) {
			if (this.liczbaZyc <= 3) {
				this.liczbaZyc = 0;
			}
			else {
				this.liczbaZyc -= 3;
			}
			gra.usunDynamit();
		}
	}
	
	public abstract String tozsamosc();
}
