package reportGeneratorExe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {

	 @FXML Button w0LogInBtn;
	 @FXML Button w0FileSeiseiBtn;

	 @FXML
	    public void changeFromW0ToW3(ActionEvent e) {
	        new Main().changeView("w3.fxml");
	    }
	 @FXML
	    public void changeFromW0ToRU(ActionEvent e) {
		 	List<String> cmdOpen = new ArrayList<String>(Arrays.asList("cmd","/c","start","http://172.21.33.67/login"));
		 	ProcessBuilder pb = new ProcessBuilder(cmdOpen);
		 	Process p;
			try {
				p = pb.start();
	            p.waitFor();
			} catch (IOException | InterruptedException e1) {
				e1.printStackTrace();
			}
	 	}
}
