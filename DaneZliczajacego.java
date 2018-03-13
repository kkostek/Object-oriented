package dzikizachod;

public class DaneZliczajacego {
	private Gracz gracz;
	private int ilePomocnikowZabil;
	private int ileBandytowZabil;
	
	public Gracz getGracz() {
		return this.gracz;
	}
	
	public boolean czyWiecejPomocnikow() {
		return (this.ilePomocnikowZabil > this.ileBandytowZabil);
	}
	
	public void zwiekszBandytow() {
		this.ileBandytowZabil++;
	}
	
	public void zwiekszPomocnikow() {
		this.ilePomocnikowZabil++;
	}
	
	public DaneZliczajacego(Gracz gracz) {
		this.gracz = gracz;
		this.ileBandytowZabil = 0;
		this.ilePomocnikowZabil = 0;
	}
}
