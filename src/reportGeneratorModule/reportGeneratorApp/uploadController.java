package reportGeneratorApp;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class uploadController {
	@FXML WebView upload;
	@FXML Button chageTopBtn;

	public void initialize() {
		String url = "http://127.0.0.1:5000/login";

		WebEngine engine = upload.getEngine();
		engine.load(url);
	}

	@FXML
    public void changeFromW0ToW3(ActionEvent e) {
		Alert backAlert = new Alert(AlertType.CONFIRMATION);
		backAlert.setTitle("ナビゲーションの確認");
		backAlert.setHeaderText("このページを離れると入力したデータが削除されます。");
		backAlert.setContentText("このページから移動してもよろしいですか？");

		Optional<ButtonType> result = backAlert.showAndWait();
		if (result.get() == ButtonType.OK){
	        new Main().changeView("w0.fxml");
		}
    }
}
