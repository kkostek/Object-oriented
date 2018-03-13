package dzikizachod;

public class StrategiaBandytyCierpliwa extends StrategiaBandyty {

	public void setPoczatkoweWartosci(Gracz gracz, int zasieg) {
		this.gracz = gracz;
		this.zasieg = zasieg;
		this.spryt();
	}
	
	public Gracz wKogoStrzelac(Gra gra) {
		int i = 0;
		while (this.graczeWZasiegu.get(i).tozsamosc() != "Szeryf") {
			i++;
		}
		return this.graczeWZasiegu.get(i);
	}
	
	public boolean czyBangnacKogos() {
		this.graczeKtorzySaWZasiegu();
		for (int i = 0; i < this.graczeWZasiegu.size(); i++) {
			if (this.graczeWZasiegu.get(i).tozsamosc() == "Szeryf") {
				return true;
			}
		}
		return false;
	}
}
