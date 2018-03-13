package dzikizachod;

import java.util.List;

public abstract class StrategiaBandyty extends Strategia {
	protected int ileDoSzeryfa;
	protected int ileDoSzeryfaZgodnieZRuchemGry;
	
	public void aktualizujDane(List<Gracz> grajacyGracze, Ruch ostatniBang) {
		this.grajacyGracze = grajacyGracze;
	}
	public boolean czyWypuscicDynamit() {
		int i = 0;
		while (this.grajacyGracze.get(i) != this.gracz) {
			i++;
		}
		int size = this.grajacyGracze.size();
		for (int j = 1; j <= 3; j++) {
			if (this.grajacyGracze.get((i + j) % size).tozsamosc() == "Szeryf") {
				return true;
			}
		}
		return false;
	}
	
	public boolean kogoLeczyc() {
		return true;
	}
}
