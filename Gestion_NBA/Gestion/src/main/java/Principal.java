package examen;

import java.util.List;
import java.util.Scanner;



import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Principal {

	static Scanner scanner;
	private static Session sesion;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		int option;

		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}

		do {
			System.out.println("Ingrese opción:");
			System.out.println("1. Mostrar jugadores que superen una anotación máxima de un equipo");
			System.out.println("2. Mostrar porcentaje de victorias de un equipo");
			System.out.println("3. Insertar o actualizar jugador en un equipo");
			System.out.println("0. Salir");
			option = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (option) {
			case 1:
				mostrarJugadoresSuperanAnotacion();
				break;
			case 2:
				mostrarPorcentajeVictoriasEquipo();
				break;
			case 3:
				insertarActualizarJugador();
				break;
			case 0:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opción no válida, intente de nuevo.");
			}
		} while (option != 0);

		try {
			sesion.close();
			HibernateUtil.shutdown();
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}


	}

	private static void mostrarJugadoresSuperanAnotacion() {
		System.out.println("Ingrese nombre del equipo:");
		String nombreEquipo = scanner.nextLine();

		Query query = sesion.createQuery("FROM Equipo WHERE nombre = :nombre");
		query.setParameter("nombre", nombreEquipo);
		Equipo equipo = (Equipo) query.uniqueResult();

		if(equipo!=null) {

			if(!equipo.getJugadores().isEmpty()) {

				System.out.println("Puntos a superar:");
				int puntos = scanner.nextInt();

				System.out.println("Jugadores de los Celtics con más de "+ puntos + " puntos:");
				for (Jugador j : equipo.getJugadores()) {
					if(j.getMaximaAnotacion() > puntos) {
						System.out.println(j);
					}
				}

			}
			// Lista vacía, no hay jugadores
			else {
				System.out.println("No hay jugadores registrados en los 76ers");
			}
		}
		// No existe el equipo, es nulo
		else {
			System.out.println("El equipo no existe");
		}
	}

	private static void mostrarPorcentajeVictoriasEquipo() {
		System.out.println("Ingrese id del equipo:");
		int idEquipo = scanner.nextInt();

		Equipo equipo = sesion.get(Equipo.class, idEquipo);

		if(equipo!=null) {
			List<Partido> partidosCasa = equipo.getPartidosLocal();
			// Hay partidos, vamos a calcular el % victorias
			if(!partidosCasa.isEmpty()) {
				double victorias = 0;
				
				for (Partido p : partidosCasa) {
					if(p.getResultadoLocal()>p.getResultadoVisitante())
						victorias++;
				}
				
				double porcentajeVictorias = victorias / partidosCasa.size() * 100;
				System.out.println("El porcentaje de victorias de " + equipo.getNombre() + " en casa es "+porcentajeVictorias + "%");
			}
			// Está vacío, no hay partidos como local
			else {
				System.out.println("No hay partidos locales registrados para este equipo");
			}
		}
		// El equipo con el id obtenido no existe
		else {
			System.out.println("El equipo no existe");
		}
	}

	private static void insertarActualizarJugador() {
		System.out.println("Ingrese nombre del jugador:");
		String nombre = scanner.nextLine();
		System.out.println("Ingrese apellido del jugador:");
		String apellido = scanner.nextLine();
		System.out.println("Ingrese máxima anotación del jugador:");
		int maximaAnotacion = scanner.nextInt();
		System.out.println("Ingrese id del equipo:");
		int idEquipo = scanner.nextInt();

		Equipo equipo = sesion.get(Equipo.class, idEquipo);

		// Existe el equipo del id leido
		if(equipo!=null) {

			Query query = sesion.createQuery("FROM Jugador WHERE apellido = :apellido");
			query.setParameter("apellido", apellido);
			Jugador jugador = (Jugador) query.uniqueResult();

			// El jugador ya existe
			boolean existe = false;
			if(jugador != null) {
				existe = true;
				jugador.setNombre(nombre);
				jugador.setMaximaAnotacion(maximaAnotacion);
				jugador.setEquipo(equipo);
			}
			else {
				jugador = new Jugador(nombre, apellido, maximaAnotacion, equipo);
			}

			Transaction t = null;
			try {
				t = sesion.beginTransaction();
				if(existe) {
					sesion.update(jugador);
					System.out.println("Se ha actualizado jugador en los "+equipo.getNombre());
				}
				else {
					sesion.save(jugador);
					System.out.println("Se ha actualizado jugador en los "+equipo.getNombre());
				}
				t.commit();
			}
			catch (Exception e) {
				// TODO: handle exception
				t.rollback();
			}
		}
		// El equipo con el id obtenido no existe
		else {
			System.out.println("El equipo no existe");
		}
	}
}
