package practica8.completa;

import aaa_clases_universales.Estudiante;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main extends OperacionesApp {
    public static void main(String[] args){

        //Creamos todos los alumnos de la clase
        Estudiante Pablo = new Estudiante("Pablo Mateos Palas", 5),
                JuanMaria = new Estudiante("Juan María Mateos Ponce", 0),
                Victor = new Estudiante("Victor Chacón Calle", 0),
                Jorge = new Estudiante("Jorge Coronil Villalba", 9),
                Ricardo = new Estudiante("Ricardo Gabriel Moreno Cantea", 4),
                Adrian = new Estudiante("Adrián Merino Gamaza", 4),
                JuanManuelH = new Estudiante("Juan Manuel Herrera Ramírez", 4),
                DanielAlfonso = new Estudiante("Daniel Alfonso Rodríguez Santos", 5),
                Diego = new Estudiante("Diego González Romero", 2),
                Jonathan = new Estudiante("Jonathan León Canto", 5),
                JuanManuelS = new Estudiante("Juan Manuel Saborido Baena", -1),
                Julian = new Estudiante("Julian García Velázquez", 1),
                JoseAntonio = new Estudiante("Jose Antonio Jaén Gómez", 0),
                AntonioJesus = new Estudiante("Antonio Jesús Téllez Perdigones", 2);

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
                System.out.println("5. Salir");
                System.out.print("Introduce una opcion (1-5): ");
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
                        exit = true;
                        System.out.println("Gracias por confiar en Remedy Softworks");
                        break;
                    default:
                        System.out.println("Opción no valida");
                }
            }
    }
}
