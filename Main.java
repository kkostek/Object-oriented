package dzikizachod;

import java.util.List;
import java.util.ArrayList;

public class Main {
	//przykladowy main
	public static void main(String[] args) {
		List<Gracz> gracze = new ArrayList<Gracz>();
		gracze.add(new Szeryf());
		for(int i=0;i<3;i++) gracze.add(new PomocnikSzeryfa());
		for(int i=0;i<3;i++) gracze.add(new Bandyta());
		
		StrategiaPomocnikaSzeryfa s1 = new StrategiaPomocnikaSzeryfaZliczajaca();
		StrategiaPomocnikaSzeryfa s2 = new StrategiaPomocnikaSzeryfaZliczajaca();
		StrategiaBandyty b1 = new StrategiaBandytyCierpliwa();
		StrategiaBandyty b2 = new StrategiaBandytySprytna();
		gracze.add(new PomocnikSzeryfa(s1));
		gracze.add(new PomocnikSzeryfa(s2));
		gracze.add(new Bandyta(b1));
		gracze.add(new Bandyta(b2));
		PulaAkcji pulaAkcji = new PulaAkcji();
		pulaAkcji.dodaj(Akcja.ULECZ, 20);
		pulaAkcji.dodaj(Akcja.STRZEL, 60);
		pulaAkcji.dodaj(Akcja.ZASIEG_PLUS_JEDEN, 3);
		pulaAkcji.dodaj(Akcja.ZASIEG_PLUS_DWA, 1);
		pulaAkcji.dodaj(Akcja.DYNAMIT, 1);
		
		Gra gra = new Gra();
		gra.rozgrywka(gracze, pulaAkcji);
	}
	
}