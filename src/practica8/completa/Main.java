package practica8.completa;

import aaa_clases_universales.Estudiante;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main extends OperacionesApp {
    public static void main(String[] args){

        //Creamos todos los alumnos de la clase
        LocalDateTime fechaPablo = LocalDateTime.parse("2023-05-18T08:32:55"),
                fechaJuanMaria = LocalDateTime.parse("2023-05-16T12:22:35"),
                fechaVictor = LocalDateTime.parse("2023-05-16T12:22:00"),
                fechaJorge = LocalDateTime.parse("2023-05-21T09:10:05"),
                fechaRicardo = LocalDateTime.parse("2023-05-21T09:13:12"),
                fechaAdrian = LocalDateTime.parse("2023-05-17T12:22:44"),
                fechaJuanManuelH = LocalDateTime.parse("2023-05-18T10:14:23"),
                fechaDanielAlfonso = LocalDateTime.parse("2023-05-22T09:37:02"),
                fechaDiego = LocalDateTime.parse("2023-05-23T13:33:44"),
                fechaJonathan = LocalDateTime.parse("2023-05-10T10:10:15"),
                fechaJuanManuelS = LocalDateTime.parse("2023-05-16T12:28:44"),
                fechaJulian = LocalDateTime.parse("2023-05-12T09:45:41"),
                fechaJoseAntonio= LocalDateTime.parse("2023-05-16T12:01:44"),
                fechaAntonioJesus= LocalDateTime.parse("2023-05-20T09:01:09");

        Estudiante Pablo = new Estudiante("Pablo Mateos Palas", 5, fechaPablo),
                JuanMaria = new Estudiante("Juan María Mateos Ponce", 0, fechaJuanMaria),
                Victor = new Estudiante("Victor Chacón Calle", 0, fechaVictor),
                Jorge = new Estudiante("Jorge Coronil Villalba", 9, fechaJorge),
                Ricardo = new Estudiante("Ricardo Gabriel Moreno Cantea", 4, fechaRicardo),
                Adrian = new Estudiante("Adrián Merino Gamaza", 4, fechaAdrian),
                JuanManuelH = new Estudiante("Juan Manuel Herrera Ramírez", 4, fechaJuanManuelH),
                DanielAlfonso = new Estudiante("Daniel Alfonso Rodríguez Santos", 5, fechaDanielAlfonso),
                Diego = new Estudiante("Diego González Romero", 2, fechaDiego),
                Jonathan = new Estudiante("Jonathan León Canto", 5, fechaJonathan),
                JuanManuelS = new Estudiante("Juan Manuel Saborido Baena", -1, fechaJuanManuelS),
                Julian = new Estudiante("Julian García Velázquez", 1, fechaJulian),
                JoseAntonio = new Estudiante("Jose Antonio Jaén Gómez", 0, fechaJoseAntonio),
                AntonioJesus = new Estudiante("Antonio Jesús Téllez Perdigones", 2, fechaAntonioJesus);

        List<Estudiante> listaEstudiantes = new ArrayList<>();
        listaEstudiantes.add(Pablo);
        listaEstudiantes.add(JuanMaria);
        listaEstudiantes.add(Victor);
        listaEstudiantes.add(Jorge);
        listaEstudiantes.add(Ricardo);
        listaEstudiantes.add(Adrian);
        listaEstudiantes.add(JuanManuelH);
        listaEstudiantes.add(DanielAlfonso);
        listaEstudiantes.add(Diego);
        listaEstudiantes.add(Jonathan);
        listaEstudiantes.add(JuanManuelS);
        listaEstudiantes.add(Julian);
        listaEstudiantes.add(JoseAntonio);
        listaEstudiantes.add(AntonioJesus);

            //Boolean para ejecución
            boolean exit = false;
            // Declaramos variable Scanner, que la que usaremos para introducir parametros por pantalla
            Scanner scan=new Scanner(System.in);

            while (!exit) {
                System.out.println("MENU:");
                System.out.println("1. Importar archivo XML");
                System.out.println("2. Exportar archivo XML");
                System.out.println("3. Seleccionar alumno menos participativo");
                System.out.println("4. Resetear participaciones");
                System.out.println("5. Mostrar alumnos según su participación");
                System.out.println("6. Mostrar el alumno con la participación más reciente");
                System.out.println("7. Salir");
                System.out.print("Introduce una opcion (1-7): ");
                int opcion = scan.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("Introduce la ruta del archivo XML a importar: ");
                        String rutaImportar = scan.next();
                        importarXML(listaEstudiantes,rutaImportar);
                        break;
                    case 2:
                        System.out.println("Introduce la ruta del archivo XML a exportar: ");
                        String rutaLectura = scan.next();
                        System.out.println("Introduce la ruta donde quieres guardar el archivo exportado: ");
                        String rutaEscritura = scan.next();
                        exportarXML(rutaLectura, rutaEscritura);
                        break;
                    case 3:
                        System.out.println("Introduce la ruta del archivo XML de alumnos: ");
                        String rutaAlumnos = scan.next();
                        System.out.println(seleccionarAlumnoMenosParticipativo(rutaAlumnos));
                        break;
                    case 4:
                        System.out.println("Introduce la ruta del archivo XML a resetear: ");
                        String rutaReset = scan.next();
                        resetearParticipaciones(rutaReset);
                        break;
                    case 5:
                        System.out.println("Introduce la ruta del archivo XML a analizar: ");
                        String rutaAlumnosParticipacion = scan.next();
                        System.out.print("Introduce un valor para filtrar por participación: ");
                        int valor = scan.nextInt();
                        mostrarAlumnosPorParticipacion(rutaAlumnosParticipacion, valor);
                        break;
                    case 6:
                        System.out.println("Introduce la ruta del archivo XML a analizar: ");
                        String rutaXML = scan.next();
                        ultimoAlumnoParticipar(rutaXML);
                        break;
                    case 7:
                        exit = true;
                        System.out.println("Gracias por confiar en Remedy Softworks");
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            }
    }
}