package cabinet;

import java.util.Comparator;

public class ComparatorAdmini implements Comparator<User>{

	@Override
	public int compare(User o1, User o2) {
		if(o1.getNume_utlilizator().compareToIgnoreCase(o2.getNume_utlilizator()) ==1)
			return 1;
		if(o1.getNume_utlilizator().compareToIgnoreCase(o2.getNume_utlilizator()) ==-1)
			return -1;
		return 0;
	}

}
