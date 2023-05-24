package cabinet;

import java.util.ArrayList;
import java.util.Objects;

public class User {
	private String nume_utlilizator, parola, tip_utilizator;

	public User(String nume_utlilizator, String parola, String tip_utilizator) {
		super();
		this.nume_utlilizator = nume_utlilizator;
		this.parola = parola;
		this.tip_utilizator = tip_utilizator;
	}

	public String getNume_utlilizator() {
		return nume_utlilizator;
	}

	public void setNume_utlilizator(String nume_utlilizator) {
		this.nume_utlilizator = nume_utlilizator;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public String getTip_utilizator() {
		return tip_utilizator;
	}

	public void setTip_utilizator(String tip_utilizator) {
		this.tip_utilizator = tip_utilizator;
	}

	@Override
	public String toString() {
		return "User [nume_utlilizator=" + nume_utlilizator + ", parola=" + parola + ", tip_utilizator="
				+ tip_utilizator + ", ";
	}

	public String getDoctor() {
		String string = null;
		return string;
	}
	
	public int getLunaCovid() {
		int nr = 0;
		return nr;
	}
	public boolean equalsUser(User a) {
		if(this.getNume_utlilizator().equals(a.getNume_utlilizator()) && this.getParola().equals(a.getParola()))
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nume_utlilizator, parola, tip_utilizator);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(nume_utlilizator, other.nume_utlilizator) && Objects.equals(parola, other.parola)
				&& Objects.equals(tip_utilizator, other.tip_utilizator);
	}
	
	
	

}
