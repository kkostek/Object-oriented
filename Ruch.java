package dzikizachod;

public class Ruch {
	private Gracz strzelec;
	private Gracz ofiara;
	private String tozsamosc; //jest rowna "" jesli delikwent zyje
	
	public Gracz getStrzelec() {
		return this.strzelec;
	}
	public Gracz getOfiara() {
		return this.ofiara;
	}
	public String tozsamosc() {
		return this.tozsamosc;
	}
	
	public Ruch(Gracz strzelec, Gracz ofiara, String tozsamosc) {
		this.strzelec = strzelec;
		this.ofiara = ofiara;
		this.tozsamosc = tozsamosc;
	}
}
