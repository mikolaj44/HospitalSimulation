module com.example.hospitalsimulation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens com.example.hospitalsimulation to javafx.fxml;
    exports com.example.hospitalsimulation;
}