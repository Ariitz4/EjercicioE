module es.aritzherrero.ejercicioe {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.aritzherrero.ejercicioe to javafx.fxml;
    exports es.aritzherrero.ejercicioe;
    opens es.aritzherrero.ejercicioe.controlador to javafx.fxml;
    opens es.aritzherrero.ejercicioe.modelo to javafx.fxml, javafx.base;
}