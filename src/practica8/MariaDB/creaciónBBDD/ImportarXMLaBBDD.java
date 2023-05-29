package practica8.MariaDB.creaciónBBDD;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImportarXMLaBBDD {

    public static void main(String[] args) throws SQLException, IOException, JDOMException, ClassNotFoundException {
        importarXML("estudiantes", "clase_daw1", "C:\\Users\\1DAW_2223_01\\IdeaProjects\\Practica8-PROG\\alumnos.xml");
    }
    static void importarXML(String baseDatos, String tabla, String rutaXML) throws SQLException, IOException, JDOMException, ClassNotFoundException {
        String enlazar = "jdbc:mariadb://localhost:3306/estudiantes";
        Class.forName("org.mariadb.jdbc.Driver");
        Connection connection = DriverManager.getConnection(enlazar, "jonathanleon", "jonathanleon");
        Document document = new SAXBuilder().build(new File(rutaXML));
        Element rootElement = document.getRootElement();

            for (Element rowElement : rootElement.getChildren("row")) {
                int id = Integer.parseInt(rowElement.getChildText("id"));
                String nombre = rowElement.getChildText("nombre");
                int participacion = Integer.parseInt(rowElement.getChildText("participacion"));
                String fechaParticipacion = rowElement.getChildText("fechaParticipacion");

                PreparedStatement stm = connection.prepareStatement("INSERT INTO " + tabla + " (id, nombre, participacion, fechaParticipacion) VALUES (?, ?, ?, ?)");
                stm.setInt(1, id);
                stm.setString(2, nombre);
                stm.setInt(3, participacion);
                stm.setString(4, fechaParticipacion);
                stm.executeUpdate();
                stm.close();
            }

            System.out.println("Se ha importado el XML a la Base de Datos");
            connection.close();

        }
}


