package cabinet;

public class Pacient extends User{
	protected String declaratie;

	public Pacient(String nume_utlilizator, String parola, String tip_utilizator, String declaratie) {
		super(nume_utlilizator, parola, tip_utilizator);
		this.declaratie = declaratie;
	}

	public String getDeclaratie() {
		return declaratie;
	}

	public void setDeclaratie(String declaratie) {
		this.declaratie = declaratie;
	}
	public String toString() {
		return super.toString() + " declaratie: "+ declaratie+ "]";
	}
	
	public String  getDoctor() {
		String[] info = getDeclaratie().split("_");
		if(!info[2].equalsIgnoreCase("x"))
			return info[2];
		return "x";
	}
	
	public int getLunaCovid() {
		String[] info = getDeclaratie().split("_");
		if(info[0].equalsIgnoreCase("DA")){
			String [] lunile = info[1].split("\\.");
			 int luna = Integer.parseInt(lunile[1]);
			 return luna;
		}
		return 0;
		
	}
	
	public boolean isCovid() {
		String[] info = getDeclaratie().split("_");
		
		if(info[0].equalsIgnoreCase("DA")){
			return true;
		}
		return false;
		
	}
}
