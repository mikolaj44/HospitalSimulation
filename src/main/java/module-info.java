module com.example.hospitalsimulation {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires org.controlsfx.controls;


    opens com.example.hospitalsimulation to javafx.fxml;
    exports com.example.hospitalsimulation;
}