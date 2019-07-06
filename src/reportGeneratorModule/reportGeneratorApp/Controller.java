package reportGeneratorApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

	 @FXML Button w0LogInBtn;
	 @FXML Button w0FileSeiseiBtn;

	 @FXML
	    public void changeFromW0ToW3(ActionEvent e) {
	        new Main().changeView("w3.fxml");
	    }
	 @FXML
	    public void changeFromW0ToRU(ActionEvent e) {
			List<String> cmdOpen = new ArrayList<String>(Arrays.asList("open","http://172.21.33.67/login"));
		 	try {
		 		ProcessBuilder pb = new ProcessBuilder(cmdOpen);
		 		Process p = pb.start();
	            p.waitFor();
		 	}catch (IOException | InterruptedException ex){
	            ex.printStackTrace();
			}
	    }
}
