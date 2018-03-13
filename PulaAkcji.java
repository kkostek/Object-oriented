package dzikizachod;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class PulaAkcji {
	private List<Akcja> talia;
	private List<Akcja> odrzucone;
	
	public PulaAkcji() {
		this.talia = new ArrayList<Akcja>();
		this.odrzucone = new ArrayList<Akcja>();
	}
	
	public void dodaj(Akcja a, int nr) {
		for (int i = 0; i < nr; i++) {
			this.odrzucone.add(a);
		}
	}
	public void odrzucKarte(Akcja a) {
		this.odrzucone.add(a);
	}
	
	public void tasuj() {
		if (this.talia.size() == 0) {
			Random los = new Random();
			int l, m;
			for (int i = 0; i < 10457; i++) {
				l = los.nextInt(this.odrzucone.size());
				m = los.nextInt(this.odrzucone.size());
				while(l == m) {
					m = los.nextInt(this.odrzucone.size());
				}
				Akcja pom = this.odrzucone.get(l);
				this.odrzucone.set(l, this.odrzucone.get(m));
				this.odrzucone.set(m, pom);
			}
			while(!this.odrzucone.isEmpty()) {
				this.talia.add(this.odrzucone.remove(0));
			}
		}
	}
	
	public Akcja wezKarte() {
		Akcja a = null;
		if (!this.talia.isEmpty()) {
		a = this.talia.remove(0);
		}
		return a;
	}
}
