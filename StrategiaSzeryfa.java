package dzikizachod;

import java.util.ArrayList;
import java.util.List;

public abstract class StrategiaSzeryfa extends Strategia {

	protected List<Gracz> graczeKtorzyStrzelaliDoSzeryfa;
	protected List<Gracz> graczeKtorzySpelniajaObaWarunki;
	
	protected void graczeKtorzySaWZasieguIStrzelali() {
		this.graczeKtorzySpelniajaObaWarunki = new ArrayList<Gracz>();
		for (int i = 0; i < this.graczeKtorzyStrzelaliDoSzeryfa.size(); i++) {
			if (this.graczeWZasiegu.contains(this.graczeKtorzyStrzelaliDoSzeryfa.get(i))) {
				this.graczeKtorzySpelniajaObaWarunki.add(this.graczeKtorzyStrzelaliDoSzeryfa.get(i));
			}
		}
	}
	
	public boolean kogoLeczyc() {
		return true;
	}
	
	public boolean czyWypuscicDynamit() {
		return false;
	}
	
	public boolean czyBangnacKogos() {
		this.graczeKtorzySaWZasiegu();
		return (this.graczeWZasiegu.size() > 0);
	}
}
