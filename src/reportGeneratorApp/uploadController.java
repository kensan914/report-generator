package reportGeneratorApp;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class uploadController {

	@FXML WebView upload;
	public void initialize() {
		WebEngine engine = upload.getEngine();
		engine.load("http://172.21.33.67/get_can_submit_homework_info?userID=1f");
	}
}
