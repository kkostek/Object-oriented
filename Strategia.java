package dzikizachod;

import java.util.ArrayList;
import java.util.List;

public abstract class Strategia {
	protected Gracz gracz;
	protected int zasieg;
	protected boolean spryt;
	protected List<Gracz> graczeWZasiegu;
	protected List<Gracz> grajacyGracze;
	public abstract void aktualizujDane(List<Gracz> grajacyGracze, Ruch ostatniBang);
	public abstract boolean kogoLeczyc(); //true - siebie; false - szeryfa;
	public abstract boolean czyWypuscicDynamit();
	public abstract Gracz wKogoStrzelac(Gra gra);
	public abstract boolean czyBangnacKogos();
	
	public abstract void setPoczatkoweWartosci(Gracz gracz, int zasieg);
	
	public void setZasieg(int zasieg) {
		this.zasieg = zasieg;
	}
	
	public void spryt() {
		this.spryt = false;
	}
	public int getZasieg() {
		return this.zasieg;
	}
	
	public void daneZPoczatku(List<Gracz> grajacyGracze) {
		this.grajacyGracze = grajacyGracze;
	}
	
	protected void graczeKtorzySaWZasiegu() {
		this.graczeWZasiegu = new ArrayList<Gracz>();
		int i = 0;
		for (int k = 0; k < this.grajacyGracze.size(); k++) {
			if (this.grajacyGracze.get(k) == this.gracz) {
				i = k;
				break;
			}
		}
		int j = 1;
		int size = this.grajacyGracze.size();
		while(j <= this.zasieg && !graczeWZasiegu.contains(this.grajacyGracze.get((i + j) % size)) && 
			  this.grajacyGracze.get((i + j) % size) != this.gracz) {
			this.graczeWZasiegu.add(grajacyGracze.get((i + j) % size));
		}
		while(j <= this.zasieg && !graczeWZasiegu.contains(this.grajacyGracze.get((i - j + size) % size)) && 
				  this.grajacyGracze.get((i - j + size) % size) != this.gracz) {
				this.graczeWZasiegu.add(grajacyGracze.get((i - j + size) % size));
			}
	}
}
