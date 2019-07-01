package reportGeneratorApp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderCompile {
	public static String compile(String sourceFilePath) {

		String path = System.getProperty("user.dir");
		String compileText = "";

		//パステスト
		Path pathtest=null;
		try {
			pathtest = getApplicationPath(OrderCompile.class);
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}

		List<String> cmdCpl = new ArrayList<String>(Arrays.asList("script","-q","compile.log","gcc", sourceFilePath, "-o", path+"/a.out"));
		List<String> cmdRm = new ArrayList<String>(Arrays.asList("rm",path+"/compile.log"));
		Process p1 = null;
		Process p2 = null;
		try {
			ProcessBuilder pb1 = new ProcessBuilder(cmdCpl);
			ProcessBuilder pb2 = new ProcessBuilder(cmdRm);

			p1 = pb1.start();
            p1.waitFor();

            File logFile = new File(path + "/compile.log");
            BufferedReader br = new BufferedReader(new FileReader(logFile));
            String logLine;
            while((logLine = br.readLine()) != null) {
            	compileText += logLine+"\n";
            }
            br.close();

            p2 = pb2.start();
            p2.waitFor();

        	return compileText;
		} catch (InterruptedException | IOException e){
            e.printStackTrace();
            compileText = "Erroer by Application";
        	return compileText;
        }
	}

	public static Path getApplicationPath(Class<?> cls) throws URISyntaxException {
		ProtectionDomain pd = cls.getProtectionDomain();
		CodeSource cs = pd.getCodeSource();
		URL location = cs.getLocation();
		URI uri = location.toURI();
		Path path = Paths.get(uri);
		return path;
	}
}
