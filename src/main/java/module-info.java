module ies.pedro.explorer_demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.javafx;
    requires org.controlsfx.controls;


    opens ies.pedro.explorer_demo to javafx.fxml;
    exports ies.pedro.explorer_demo;
}