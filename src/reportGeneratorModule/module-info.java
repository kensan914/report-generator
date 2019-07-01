module reportGeneratorModule {
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires transitive javafx.fxml;
    requires transitive javafx.web;
    opens reportGeneratorApp;
    exports reportGeneratorApp;
}
