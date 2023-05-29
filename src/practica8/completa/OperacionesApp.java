package practica8.completa;

import aaa_clases_universales.Estudiante;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class OperacionesApp {

    //El método pasa a un archivo XML una Lista
    //Como parámetro recibe una lista, que convertirá en el archivo XML y una ruta, donde guardará el archivo
    public static void importarXML(List<Estudiante> listaEstudiantes, String rutaParaImportar){
        try {
            //Creamos el documento vacío para añadirle a continuación los nodos
            Document document = null;
            document = DocumentBuilderFactory.newDefaultInstance().newDocumentBuilder().newDocument();
            //Creamos el nodo raíz
            Element estudiantes = document.createElement("estudiantes");
            //Hacemos que cuelgue del documento (estructura de árbol)
            document.appendChild(estudiantes);

            //Hacemos un bucle para añadir todos los elementos de la lista al XML
            for (int i = 0; i < listaEstudiantes.size(); i++) {

                //Creamos la variable estudiante, se irá creando un elemento estudiante para cada uno de los elementos de la lista
                Estudiante estudiante = listaEstudiantes.get(i);
                Element estudianteElement = document.createElement("estudiante");
                estudiantes.appendChild(estudianteElement);

                //Creamos el elemento "nombreEstudiante" y hacemos que cuelgue de estudiante
                //el valor de esta variable irá cambiando con el bucle
                Element nombreEstudiante = document.createElement("nombre");
                estudianteElement.appendChild(nombreEstudiante);
                Text textNombreEstudiante = document.createTextNode(estudiante.getNombre());
                nombreEstudiante.appendChild(textNombreEstudiante);

                //Creamos el elemento "participacionEstudiante" y hacemos que cuelgue de estudiante
                //el valor de esta variable irá cambiando con el bucle
                Element participacionEstudiante = document.createElement("participacion");
                estudianteElement.appendChild(participacionEstudiante);
                Text textParticipacionEstudiante = document.createTextNode(String.valueOf(estudiante.getParticipacion()));
                participacionEstudiante.appendChild(textParticipacionEstudiante);

                //Creamos el elemento "fechaParticipacionEstudiante" y hacemos que cuelgue de estudiante
                //el valor de esta variable irá cambiando con el bucle
                Element fechaParticipacionEstudiante = document.createElement("fechaParticipacion");
                estudianteElement.appendChild(fechaParticipacionEstudiante);
                Text textFechaParticipacionEstudiante = document.createTextNode(estudiante.getfechaParticipacion().toString());
                fechaParticipacionEstudiante.appendChild(textFechaParticipacionEstudiante);

            }

            //Clases necesarias para finalizar la creación del archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(rutaParaImportar));

            //Se realiza la transformación, de Document a Fichero.
            transformer.transform(source, result); }

          catch (ParserConfigurationException e) {
              System.out.println("Ha fallado el parseo " + e.getMessage());
        } catch (TransformerConfigurationException e) {
            System.out.println("Ha fallado la transformación del documento " + e.getMessage());
        } catch (TransformerException e) {
            System.out.println("Ha fallado la transformación del documento " + e.getMessage());

        }
    }


    //Este método exporta un archivo XML de una ruta indicada
    //Como parámetro acepta un String con la ruta desde dónde hará la copia y otro String con dónde escribir esa copia
    public static void exportarXML(String rutaLectura, String rutaEscritura) {
        try {
            //Cargamos fichero que vamos a leer
            File file = new File(rutaLectura);

            //Parseamos el fichero al Document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            //Clases necesarias para la creación del archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(rutaEscritura));

            //Transformación, de Document a Fichero.
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {
            System.out.println("Ha fallado el parseo " + e.getMessage());
        } catch (TransformerConfigurationException e) {
            System.out.println("Ha fallado la transformación del documento " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ha fallado la entrada/salida " + e.getMessage());
        } catch (TransformerException e) {
            System.out.println("Ha fallado la transformación del documento " + e.getMessage());
        } catch (SAXException e) {
            System.out.println("Ha fallado el parseo a XML " + e.getMessage());
        }
    }


    //Este método busca al alumno menos participativo y lo devuelve, sumándole 1 a su participación
    //Como parámetro recibe la ruta del archivo XML del que queremos sacar el estudiante menos participativo
    public static Estudiante seleccionarAlumnoMenosParticipativo(String rutaXML) {
        //Carga una lista y guarda el primer elemento de esta como el alumno menos participativo
        List<Estudiante> listaEstudiantes = null;
        listaEstudiantes = pasarXML_A_Lista(rutaXML);
        Estudiante estudianteMenosParticipativo = listaEstudiantes.get(0);
        //Recorre la lista con un bucle y va cambiando el valor de la variable "estudianteMenosParticipativo" si encuentra que
        //el siguiente alumno de la lista tiene una participación menor que el alumno que está guardado en la variable
        for (int i = 1; i < listaEstudiantes.size(); i++) {
            if (listaEstudiantes.get(i).getParticipacion() < estudianteMenosParticipativo.getParticipacion()) {
                estudianteMenosParticipativo = listaEstudiantes.get(i);
            }
        }
        //Aumenta la participación del alumno que se ha elegido en uno
        estudianteMenosParticipativo.setParticipacion(estudianteMenosParticipativo.getParticipacion() + 1);
        LocalDateTime fechaActual = LocalDateTime.now();
        estudianteMenosParticipativo.setfechaParticipacion(fechaActual);
        //Para guardar los cambios en el XML se importa el archivo
        importarXML(listaEstudiantes, rutaXML);
        //Devuelve el alumno menos participativo
        return estudianteMenosParticipativo;
    }


    //Este método es usado por el método "seleccionarAlumnoMenosParticipaticvo" para que pueda cargar el archivo XML con las participaciones actualizadas
    // y "resetearParticipaciones" para poner a 0 las diferentes participaciones de los Alumnos
    //Como parámetro recibe una ruta donde se encuentra un archivo XML que pasará a una lista
    public static List<Estudiante> pasarXML_A_Lista(String rutaXML) {
        try {
            //Crea una Lista y un Archivo que cargará el archivo en la ruta indicada
            List<Estudiante> listaestudiantes = new LinkedList<>();
            File file = new File(rutaXML);
            //Parseamos el fichero al Document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            //Creamos una NodeList que irá guardando los elementos de la lista según el nombre de la etiqueta, en este caso "estudiante"
            NodeList estudiantes = document.getElementsByTagName("estudiante");

            //Con un bucle vamos iterando sobre los distintos nodos del XML e introduciendo los valores de las etiquetas "nombre",
            //"participacion" y "fechaParticipacion" en su correspondiente etiqueta "estudiante"
            for (int i = 0; i < estudiantes.getLength(); i++) {
                Node node = estudiantes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) node;
                    Estudiante estudiante = new Estudiante();
                    estudiante.setNombre(eElement.getElementsByTagName("nombre").item(0).getTextContent());
                    estudiante.setParticipacion(Integer.parseInt(eElement.getElementsByTagName("participacion").item(0).getTextContent()));
                    estudiante.setfechaParticipacion(LocalDateTime.parse(eElement.getElementsByTagName("fechaParticipacion").item(0).getTextContent()));

                    //Se va añadiendo cada estudiante con sus atributos ya recibidos en la lista
                    listaestudiantes.add(estudiante);
                }
            }
            //Se devuelve la lista
            return listaestudiantes;
        } catch (ParserConfigurationException e) {
            System.out.println("Ha fallado el parseo " + e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Ha fallado la entrada/salida " + e.getMessage());
            throw new RuntimeException(e);
        } catch (SAXException e) {
            System.out.println("Ha fallado el parseo a XML " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    //Este método pone a 0 las participaciones de todos los alumnos en un archivo XML
    //Como parámetro recibe la ruta del archivo XMl del que se desea poner las participaciones a 0
    public static void resetearParticipaciones(String rutaParaResetear) {
        //Crea la lista a partir del XML con el método anterior
            List<Estudiante> listaEstudiantes = null;
            listaEstudiantes = pasarXML_A_Lista(rutaParaResetear);
            //Con un bucle vamos poniendo la participación de cada alumno a 0
            for (int i = 0; i < listaEstudiantes.size(); i++){
                listaEstudiantes.get(i).setParticipacion(0);
            }
            //Y finalmente se importa el archivo, así acabamos con el mismo XML pero con todas las participaciones en 0
            importarXML(listaEstudiantes,rutaParaResetear);
            System.out.println("Se han reseteado las participaciones de los Alumnos");
    }

    public static void mostrarAlumnoMasYMenosParticipativo(String rutaXML){
        List<Estudiante> listaEstudiantes = pasarXML_A_Lista(rutaXML);
        Estudiante estudianteMenosParticipativo = listaEstudiantes.get(0);
        for (int i = 1; i < listaEstudiantes.size(); i++) {
            if (listaEstudiantes.get(i).getParticipacion() < estudianteMenosParticipativo.getParticipacion()) {
                estudianteMenosParticipativo = listaEstudiantes.get(i);
            }
        }
        System.out.println("El Alumno menos participativo es: " + estudianteMenosParticipativo.getNombre() + " con una participación de " + estudianteMenosParticipativo.getParticipacion());

        Estudiante estudianteMasParticipativo = listaEstudiantes.get(0);
        for (int i = 1; i < listaEstudiantes.size(); i++) {
            if (listaEstudiantes.get(i).getParticipacion() > estudianteMasParticipativo.getParticipacion()) {
                estudianteMasParticipativo = listaEstudiantes.get(i);
            }
        }
        System.out.println("El Alumno menos participativo es: " + estudianteMasParticipativo.getNombre() + " con una participación de " + estudianteMasParticipativo.getParticipacion());

    }

    public static void mostrarAlumnosPorDebajoDeLaMedia(String rutaArchivoXML){
        List<Estudiante> listaEstudiantes = pasarXML_A_Lista(rutaArchivoXML);
        int mediaParticipacion = 0;
        for (int i = 0; i < listaEstudiantes.size(); i++){
            mediaParticipacion += listaEstudiantes.get(i).getParticipacion();
        }
        mediaParticipacion = mediaParticipacion  / listaEstudiantes.size();
        System.out.println("La media de partipación es: " + mediaParticipacion);
        for (int i = 0 ; i < listaEstudiantes.size(); i++){
            if ( listaEstudiantes.get(i).getParticipacion() < mediaParticipacion){
                System.out.println(listaEstudiantes.get(i));
            }
        }
    }


    public static void mostrarAlumnosPorParticipacion(String rutaXML, int valorParticipaciones) {

        //Pasa el XML a una lista
        List<Estudiante> listaEstudiantes = pasarXML_A_Lista(rutaXML);
        //Listas para ordenar a los alumnos donde corresponda según el valor dado.
        List<Estudiante> alumnosValorMayor = new ArrayList<>();
        List<Estudiante> alumnosValorMenor = new ArrayList<>();
        List<Estudiante> alumnosValorIgual = new ArrayList<>();

        //Operación que recorre la lista de alumnos y los ordena.
        for (Estudiante estudiante : listaEstudiantes) {
            int participacion = estudiante.getParticipacion();
            if (participacion < valorParticipaciones) {
                alumnosValorMenor.add(estudiante);
            } else if (participacion > valorParticipaciones) {
                alumnosValorMayor.add(estudiante);
            } else {
                alumnosValorIgual.add(estudiante);
            }
        }

        //Se imprimen los resultados con un submenú.
        boolean subMenuActivo = true;
        Scanner scan = new Scanner(System.in);
        while (subMenuActivo) {
            System.out.println("Escoja una opción:");
            System.out.println("1- Alumnos con participaciones mayor a " + valorParticipaciones);
            System.out.println("2- Alumnos con participaciones menor a " + valorParticipaciones);
            System.out.println("3- Alumnos con participaciones igual a " + valorParticipaciones);
            System.out.println("4. Volver al menú principal");
            int opcionSubMenu = scan.nextInt();

            switch (opcionSubMenu) {
                case 1:
                    System.out.println("•Lista de alumnos con participaciones mayor a " + valorParticipaciones + ":");
                    for (Estudiante estudiante : alumnosValorMayor) {
                        System.out.println("- Nombre: " + estudiante.getNombre() + "\n- Participaciones: "
                                + estudiante.getParticipacion());
                    }
                    break;
                case 2:
                    System.out.println("•Lista de alumnos con participaciones menor a " + valorParticipaciones + ":");
                    for (Estudiante estudiante : alumnosValorMenor) {
                        System.out.println("- Nombre: " + estudiante.getNombre() + "\n- Participaciones: "
                                + estudiante.getParticipacion());
                    }
                    break;
                case 3:
                    System.out.println("•Lista de alumnos con participaciones igual a " + valorParticipaciones + ":");
                    for (Estudiante estudiante : alumnosValorIgual) {
                        System.out.println("-Nombre: " + estudiante.getNombre() + "\n- Participaciones: "
                                + estudiante.getParticipacion());
                    }
                    break;
                case 4:
                    subMenuActivo = false;
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }

    public static void ultimoAlumnoParticipar(String rutaXML) {
        //Pasa el XML a una lista
        List<Estudiante> listaEstudiantes = pasarXML_A_Lista(rutaXML);
        //Variables para almacenar la fecha y el nombre del último alumno
        LocalDateTime ultimaFechaParticipacion = LocalDateTime.MIN;
        Estudiante ultimoAlumno = null;

        //Bucle que recorre el XML para buscar un candidato
        //Compara la fecha y hora de los alumnos y guarda la más actual
        for (Estudiante estudiante : listaEstudiantes) {
            LocalDateTime fechaParticipacion = estudiante.getfechaParticipacion();
            if (fechaParticipacion != null && fechaParticipacion.isAfter(ultimaFechaParticipacion)) {
                ultimaFechaParticipacion = fechaParticipacion;
                ultimoAlumno = estudiante;
            }
        }

        //Imprime el resultado
        if (ultimoAlumno != null) {
            System.out.println("·Último alumno en participar:" + '\n' + "-Nombre: " + ultimoAlumno.getNombre() + '\n' +
                    "-Fecha: " + ultimoAlumno.getfechaParticipacion());

        } else {
            System.out.println("No existen alumnos. Registre alguno e inténtelo de nuevo.");
        }
    }


    public static void mostrarInformacionAlumno(String rutaConsulta, String nombreAlumno) {
        List<Estudiante> listaEstudiantes = pasarXML_A_Lista(rutaConsulta);
        for (Estudiante estudiante : listaEstudiantes) {
            if (estudiante.getNombre().equals(nombreAlumno)) {
                System.out.println("Información del alumno:");
                System.out.println("- Nombre: " + estudiante.getNombre());
                System.out.println("- Participaciones: " + estudiante.getParticipacion());
                System.out.println("- Fecha de la última participación: " + estudiante.getfechaParticipacion());
                return;
            }
        }
        System.out.println("No se encontró al alumno " + nombreAlumno + " en la lista.");
    }

    // Método para dar de alta, baja y modificar alumnos
    public static void gestionarAlumnos(String rutaGestion, String opcionGestion, String nombre) {
        List<Estudiante> listaEstudiantes = pasarXML_A_Lista(rutaGestion);
        switch (opcionGestion) {
            case "alta":
                // Crear un nuevo estudiante y agregarlo a la lista de estudiantes
                Estudiante nuevoEstudiante = new Estudiante(nombre, 0, LocalDateTime.now());
                listaEstudiantes.add(nuevoEstudiante);
                System.out.println("Alumno dado de alta correctamente.");
                break;
            case "baja":
                // Buscar el estudiante por nombre y eliminarlo de la lista de estudiantes
                Estudiante estudianteEliminar = null;
                for (Estudiante estudiante : listaEstudiantes) {
                    if (estudiante.getNombre().equals(nombre)) {
                        estudianteEliminar = estudiante;
                        break;
                    }
                }
                if (estudianteEliminar != null) {
                    listaEstudiantes.remove(estudianteEliminar);
                    System.out.println("Alumno dado de baja correctamente.");
                } else {
                    System.out.println("No se encontró ningún alumno con ese nombre.");
                }
                break;
            case "modificar":
                // Buscar el estudiante por nombre y modificar su participación
                Estudiante estudianteModificar = null;
                for (Estudiante estudiante : listaEstudiantes) {
                    if (estudiante.getNombre().equals(nombre)) {
                        estudianteModificar = estudiante;
                        break;
                    }
                }
                if (estudianteModificar != null) {
                    Scanner scan = new Scanner(System.in);
                    System.out.print("Introduce la nueva participación del alumno: ");
                    int nuevaParticipacion = scan.nextInt();
                    estudianteModificar.setParticipacion(nuevaParticipacion);
                    System.out.println("Participación del alumno modificada correctamente.");
                } else {
                    System.out.println("No se encontró ningún alumno con ese nombre.");
                }
                break;
            default:
                System.out.println("Opción de gestión no válida.");
        }
    }

}
