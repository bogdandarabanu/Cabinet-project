package cabinet;

import java.util.*;

import java.io.*;

public class CabinetStomatologic {

	public static ArrayList<String> citireFisier() throws FileNotFoundException {
		Scanner fin = new Scanner(new File("C:\\Users\\darab\\eclipse-workspace\\Cabinet\\src\\cabinet\\cabinet.txt"));
		ArrayList<String> info_pacienti = new ArrayList<>();

		while (fin.hasNextLine()) {
			info_pacienti.add(fin.nextLine());
		}
		fin.close();
		return info_pacienti;
	}

	public static ArrayList<User> creareListaUtilizatori(ArrayList<String> info_pacienti) {
		ArrayList<User> lista_utilizatori = new ArrayList<User>();
		for (int i = 0; i < info_pacienti.size(); i++) {
			String[] info = info_pacienti.get(i).split("\\*");
			for (int j = 0; j < info.length; j++) {
				info[j] = info[j].trim();
			}
			if (utilizatorValid(info)) {
				if (info[2].equals("medic")) {
					lista_utilizatori.add(new Medic(info[0], info[1], info[2], info[3]));
				} else if (info[2].equals("pacient")) {
					lista_utilizatori.add(new Pacient(info[0], info[1], info[2], info[3]));
				} else if (info[2].equals("administrator")) {
					lista_utilizatori.add(new Administrator(info[0], info[1], info[2]));
				}
			}
		}
		return lista_utilizatori;
	}

	public static boolean utilizatorValid(String[] informatii) {
		if (informatii[0].length() < 3)
			return false;
		if (!(informatii[2].equals("medic") || informatii[2].equals("pacient")
				|| informatii[2].equals("administrator")))
			return false;
		int litereMari = 0;
		for (int i = 0; i < informatii[1].length(); i++) {
			char c = informatii[1].charAt(i);
			if (Character.isLetterOrDigit(c)) {
				if (Character.isUpperCase(c))
					litereMari++;
			} else
				return false;
		}
		if (litereMari < 1)
			return false;
		return true;
	}

	public static void afisarePrimSiUltimElem(ArrayList<User> a) {
		User prim = a.get(0);
		User ultim = a.get(a.size() - 1);
		System.out.println("Primul element este: " + prim + " si ultimul este: " + ultim);

	}

	public static boolean acelasiMedic(ArrayList<User> lista) {
		for (int i = 0; i < lista.size(); i++) {
			for (int j = i + 1; j < lista.size() - 1; j++) {
				if (!lista.get(i).getDoctor().equalsIgnoreCase("x")) {
					if (lista.get(i).getDoctor().equals(lista.get(j).getDoctor())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static int getNrCovidLuna(ArrayList<User> lista, int luna) {
		int count = 0;
		for (User a : lista) {
			if (a.getLunaCovid() == luna) {
				count++;
			}
		}
		return count;
	}

	public static void getCardiologi(ArrayList<User> lista, int k) {
		int count = 0;
		for (User a : lista) {
			if (a instanceof Medic) {
				if (((Medic) a).getActivitate().equalsIgnoreCase("cardiolog")) {
					count++;
				}
			}
		}
		if (count > k) {
			System.out.println("Corect");
		} else {
			System.out.println("Incorect");
		}
	}

	public static boolean validareAdaugat(User a) {
		if (a.getNume_utlilizator().length() < 3)
			return false;
		int litereMari = 0;
		for (int i = 0; i < a.getParola().length(); i++) {
			char c = a.getParola().charAt(i);
			if (Character.isLetterOrDigit(c)) {
				if (Character.isUpperCase(c))
					litereMari++;
			} else
				return false;
		}
		if (litereMari < 1)
			return false;
		return true;
	}

	public static void addPacient(ArrayList<User> lista) {
		Scanner s = new Scanner(System.in);
		System.out.print("Introduceti un nume de utilizator: ");
		String nume_utilizator = s.nextLine();
		System.out.print("Introduceti o parola: ");
		String parola = s.nextLine();
		String tip_utilizator = "pacient";
		System.out.print("Introduceti declaratie covid: ");
		String declaratie = s.nextLine();
		User deAdaugat = new Pacient(nume_utilizator, parola, tip_utilizator, declaratie);

		if (validareAdaugat(deAdaugat)) {
			boolean gasit = false;
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).equalsUser(deAdaugat)) {
					gasit = true;
					break;
				}

			}
			if (gasit == true) {
				System.out.println("Utiliatorul exista in lista deja.");
			} else {
				lista.add(deAdaugat);
				System.out.println("Utilizator adaugat cu succes.");
			}
		}

		else {
			System.out.println("Utilizatorul nu respecta conditiile pentru parola si username.");
		}
	}

	public static double getProcentCovid(ArrayList<User> lista) {
		int count = 0;
		for (User a : lista) {
			if (a instanceof Pacient) {
				if (((Pacient) a).isCovid()) {
					count++;
				}
			}
		}
		int total = lista.size();
		double procent = ((double) count / total) * 100;
		return procent;
	}

	public static void afisarePunctL(ArrayList<User> lista) {
		HashSet<User> unici = new HashSet<User>(lista);
		for (int i = 0; i < 5; i++) {
			System.out.println("TipUser= " + lista.get(i).getTip_utilizator() + ", nume utilizator= "
					+ lista.get(i).getNume_utlilizator() + ", parola= " + lista.get(i).getParola());

		}
	}

	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		ArrayList<String> info_pacienti = citireFisier();
		ArrayList<User> lista_utilizatori = creareListaUtilizatori(info_pacienti);
		ArrayList<User> medici = new ArrayList<>();
		ArrayList<User> admini = new ArrayList<>();
		ArrayList<User> pacienti = new ArrayList<>();

		for (User user : lista_utilizatori) {
			if (user.getTip_utilizator().equalsIgnoreCase("medic")) {
				medici.add((Medic) user);
			} else if (user.getTip_utilizator().equalsIgnoreCase("pacient")) {
				pacienti.add((Pacient) user);
			} else {
				admini.add((Administrator) user);
			}
		}
		System.out.println("In lista avem: " + medici.size() + " medici, " + admini.size() + " administratori si "
				+ pacienti.size() + " pacienti.");
		System.out.println("-----------------------");
		afisarePrimSiUltimElem(medici);
		afisarePrimSiUltimElem(admini);
		afisarePrimSiUltimElem(pacienti);
		System.out.println("-----------------------");
		// verifica daca toti pacientii au acelasi medic
		if (acelasiMedic(pacienti)) {
			System.out.println("Toti pacientii au acelasi medic.");
		} else {
			System.out.println("Nu toti pacientii au acelasi medic.");
		}

		System.out.println("-----------------------");

		// cati pacienti au avut covid intr-o luna
		System.out.print("Introduceti o luna: ");
		int luna = s.nextInt();
		System.out.println("In aceasta luna au avut covid: " + getNrCovidLuna(pacienti, luna) + " pacient");

		System.out.println("-----------------------");
		// cati cardiologi sunt ij lista medici, verifica daca sunt mai multi decat un
		// nr k
		System.out.println("Verificare cardiologi: ");
		getCardiologi(medici, 3);

		// am sortat adminu
		Collections.sort(admini, new ComparatorAdmini());

		// adauga un pacient de la tastatura in cazul in care e valid si nu exista in
		// lista
		System.out.println("-----------------------");
		addPacient(pacienti);
		System.out.println("Aceasta este lista de pacienti.");
		for (User a : pacienti)
			System.out.println(a);

		System.out.println("-----------------------");

		// vedem procentul de pacienti care au avut covid
		System.out.println("Procentul de pacienti care au avut covid este: " + getProcentCovid(pacienti) + " %.");
		
		System.out.println("-----------------------");
		//primii 5 utilizatori valizi unici, afisati
		afisarePunctL(lista_utilizatori);
	}

}
