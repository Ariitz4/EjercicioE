package es.aritzherrero.ejercicioe.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import es.aritzherrero.ejercicioe.HelloApplication;
import es.aritzherrero.ejercicioe.modelo.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Controlador principal del ejercicio D, que gestiona la ventana principal y
 * la tabla de personas.
 */
public class EjercicioE_Principal_Control implements Initializable {

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private TableView<Persona> tblvTabla;

    @FXML
    private TableColumn<Persona, String> tblcApellidos;

    @FXML
    private TableColumn<Persona, Integer> tblcEdad;

    @FXML
    private TableColumn<Persona, String> tblcNombre;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtNombre;

    static ObservableList<Persona> listaPersonas;

    /**
     * Procedimiento que se llama cuando se inicializa la vista.
     * Configura las columnas de la tabla y enlaza los datos.
     *
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Inicializa la lista observable de personas.
        listaPersonas = FXCollections.observableArrayList();

        // Configura las columnas de la tabla para enlazar con los campos de Persona.
        tblcNombre.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
        tblcApellidos.setCellValueFactory(new PropertyValueFactory<Persona, String>("apellidos"));
        tblcEdad.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("edad"));

        // Asigna la lista observable a la tabla.
        tblvTabla.setItems(listaPersonas);
    }

    /**
     * Procedimiento que abre una nueva ventana para agregar una persona.
     *
     * @param event El evento que dispara la acción, cuando se presiona el botón agregar.
     */
    @FXML
    void agregarPersona(ActionEvent event) {
        Stage escena = new Stage();
        escena.setTitle("NUEVA PERSONA");
        FlowPane flwPanel;

        try {
            // Carga la vista FXML para agregar una nueva persona.
            flwPanel = FXMLLoader.load(HelloApplication.class.getResource("ejerciciod_NuevaPersona.fxml"));
            Scene scene = new Scene(flwPanel, 600, 300);
            escena.setScene(scene);

            // Configura los mínimos para el tamaño de la ventana.
            escena.setMinHeight(300);
            escena.setMinWidth(600);
            escena.show();
        } catch (IOException e) {
            // Si hay un error al cargar la vista, se imprime el stack trace.
            System.out.println("No ha sido posible abrir la ventana");
            e.printStackTrace();
        }
    }

    /**
     * Muestra una alerta según el tipo (error o confirmación) con un mensaje específico.
     *
     * @param tipoAlerta El tipo de alerta ("E" para error, "C" para confirmación).
     * @param mensaje El mensaje que se mostrará en la alerta.
     */
    static void ventanaAlerta(String tipoAlerta, String mensaje) {
        Alert alert = null;

        // Configura el tipo de alerta según el parámetro tipoAlerta.
        switch (tipoAlerta) {
            case ("E"):
                alert = new Alert(Alert.AlertType.ERROR);
                break;
            case ("C"):
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                break;
        }

        // Asigna el mensaje a la alerta y la muestra.
        if (alert != null) {
            alert.setContentText(mensaje);
            alert.showAndWait();
        }
    }
}

