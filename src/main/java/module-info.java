module eus.ehu.eliminate {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens eus.ehu.eliminate to javafx.fxml;
    exports eus.ehu.eliminate;
}