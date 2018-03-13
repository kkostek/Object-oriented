package dzikizachod;

public abstract class StrategiaPomocnikaSzeryfa extends Strategia {
	
	public boolean kogoLeczyc() {
		int i = 0;
		int size = this.grajacyGracze.size();
		while (i < size && this.grajacyGracze.get(i) != this.gracz) {
			i++;
		}
		if (this.grajacyGracze.get((i + 1) % size).tozsamosc() == "Szeryf" &&
			this.grajacyGracze.get((i + 1) % size).czyTrzebaUleczyc()) {
			return false;
		}
		if (this.grajacyGracze.get((i + size - 1) % size).tozsamosc() == "Szeryf" &&
			this.grajacyGracze.get((i + size - 1) % size).czyTrzebaUleczyc()) {
			return false;
		}
		return true; 
	}
}
