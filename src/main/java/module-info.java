module co.edu.uniquindio.proyectoestructura {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.proyectoestructura to javafx.fxml;
    exports co.edu.uniquindio.proyectoestructura;
    exports co.edu.uniquindio.proyectoestructura.modelo;
    opens co.edu.uniquindio.proyectoestructura.modelo to javafx.fxml;
    exports co.edu.uniquindio.proyectoestructura.viewController;
    opens co.edu.uniquindio.proyectoestructura.viewController;
    exports co.edu.uniquindio.proyectoestructura.controller;
    opens co.edu.uniquindio.proyectoestructura.controller to javafx.fxml;


}