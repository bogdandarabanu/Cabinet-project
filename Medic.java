package cabinet;

public class Medic extends User {
	protected String activitate;

	public Medic(String nume_utlilizator, String parola, String tip_utilizator, String activitate) {
		super(nume_utlilizator, parola, tip_utilizator);
		this.activitate = activitate;
	}

	public String getActivitate() {
		return activitate;
	}

	public void setActivitate(String activitate) {
		this.activitate = activitate;
	}
	
	public String toString() {
		return super.toString()+ " caracterizare:" + activitate+ "]";
	}
	
	

}
