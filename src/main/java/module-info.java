module sowingmaster {
    requires javafx.controls;
    requires javafx.fxml;
    requires jFuzzyLogic;

    opens sowingmaster to javafx.fxml;
    exports sowingmaster;
}