package es.aritzherrero.ejercicioe.controlador;

import es.aritzherrero.ejercicioe.modelo.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador para la ventana de "Nueva Persona" en la aplicación.
 * Permite gestionar la creación de nuevas instancias de la clase Persona
 * y cerrar la ventana modal correspondiente.
 */
public class EjercicioE_NuevaPersona_Control {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtNombre;

    /**
     * Porcedimiento que se ejecuta cuando se presiona el botón "Cancelar".
     * Cierra la ventana modal actual.
     *
     * @param event El evento de acción asociado al botón.
     */
    @FXML
    void cancelarVentana(ActionEvent event) {
        // Obtiene la ventana actual y la cierra.
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Procedimiento que se ejecuta cuando se presiona el botón "Guardar".
     * Intenta validar los datos ingresados y, si son correctos, guarda
     * una nueva persona. Si la persona ya existe o los datos son inválidos,
     * se muestra un mensaje de alerta.
     *
     * @param event El evento de acción asociado al botón.
     */
    @FXML
    void guardarPersona(ActionEvent event) {
        // Valida los campos del formulario y guarda el mensaje de error.
        String errorMessage = validarCampos();

        // Si hay un mensaje de error, muestra una alerta y termina la ejecución.
        if (!errorMessage.isEmpty()) {
            EjercicioE_Principal_Control.ventanaAlerta("E", errorMessage);
            return;
        }

        try {
            // Crea una nueva persona a partir de los datos del formulario.
            Persona nuevaPersona = crearPersonaDesdeCampos();

            // Verifica si la persona ya existe en la lista.
            if (EjercicioE_Principal_Control.listaPersonas.contains(nuevaPersona)) {
                EjercicioE_Principal_Control.ventanaAlerta("E", "La persona ya existe");
            } else {
                // Añade la nueva persona a la lista y muestra un mensaje de éxito.
                EjercicioE_Principal_Control.listaPersonas.add(nuevaPersona);
                EjercicioE_Principal_Control.ventanaAlerta("C", "Persona añadida correctamente");

                // Cierra la ventana modal después de guardar la persona.
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
        } catch (NumberFormatException e) {
            // Si la edad no es un número válido, muestra una alerta.
            EjercicioE_Principal_Control.ventanaAlerta("E", "El valor de edad debe ser un número mayor que cero");
        }
    }

    /**
     * Valida los campos del formulario y genera un mensaje de error si algún
     * campo obligatorio está vacío.
     *
     * @return Un mensaje de error con los campos faltantes, o una cadena vacía si no hay errores.
     */
    private String validarCampos() {
        StringBuilder errorMessage = new StringBuilder();

        // Valida que el campo nombre no esté vacío.
        if (txtNombre.getText().trim().isEmpty()) {
            errorMessage.append("El campo nombre es obligatorio.\n");
        }
        // Valida que el campo apellidos no esté vacío.
        if (txtApellidos.getText().trim().isEmpty()) {
            errorMessage.append("El campo apellidos es obligatorio.\n");
        }
        // Valida que el campo edad no esté vacío.
        if (txtEdad.getText().trim().isEmpty()) {
            errorMessage.append("El campo edad es obligatorio.\n");
        }

        return errorMessage.toString();
    }

    /**
     * Crea un nuevo objeto Persona a partir de los valores ingresados en los
     * campos de texto.
     *
     * @return Un nuevo objeto Persona con los datos ingresados.
     * @throws NumberFormatException
     */
    private Persona crearPersonaDesdeCampos() throws NumberFormatException {
        // Obtiene el nombre, apellidos y edad desde los campos de texto.
        String nombre = txtNombre.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        int edad = Integer.parseInt(txtEdad.getText().trim());

        // Verifica que la edad sea mayor a 0.
        if (edad < 1) {
            throw new NumberFormatException();
        }

        // Retorna una nueva instancia de Persona.
        return new Persona(nombre, apellidos, edad);
    }
}
