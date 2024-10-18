module es.aritzherrero.ejercicioe {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.aritzherrero.ejercicioe to javafx.fxml;
    exports es.aritzherrero.ejercicioe;
}