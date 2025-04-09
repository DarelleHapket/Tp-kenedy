module TP2 {
	requires javafx.controls;
	requires jdk.jdi;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
	opens application.Controller to javafx.graphics, javafx.fxml;
}
