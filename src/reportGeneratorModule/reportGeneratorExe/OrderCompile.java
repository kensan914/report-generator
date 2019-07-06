package reportGeneratorExe;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderCompile {

	public static String compile(String sourceFilePath) {
		int num = sourceFilePath.lastIndexOf("\\");
		String[] sourceFilePaths = {sourceFilePath.substring(0,num), sourceFilePath.substring(num+1,sourceFilePath.length())};

		String compileText = "";

		List<String> cmdCpl = new ArrayList<String>(Arrays.asList("compile.bat","\""+sourceFilePaths[0]+"\"",sourceFilePaths[1]));
		List<String> cmdCpl2 = new ArrayList<String>(Arrays.asList("compile2.bat","\""+sourceFilePaths[0]+"\""));
		List<String> cmdRm = new ArrayList<String>(Arrays.asList("cmd","/c","del","compile.log"));
		Process p1 = null;
		Process p2 = null;
		Process p3 = null;
		try {
			ProcessBuilder pb1 = new ProcessBuilder(cmdCpl);
			ProcessBuilder pb2 = new ProcessBuilder(cmdCpl2);
			ProcessBuilder pb3 = new ProcessBuilder(cmdRm);

			p1 = pb1.start();
            p1.waitFor();

            p2 = pb2.start();
            p2.waitFor();

            File logFile = new File("compile.log");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(logFile)));
            String logLine="";
            List<String> logList = new ArrayList<String>();
            int count = -1;
            while((logLine = br.readLine()) != null) {
                logList.add(logLine+"\n");
            	count++;
            }
            logList.remove(count);
            logList.remove(count-1);
            logList.remove(0);
            for(int i=0;i<logList.size();i++) {
            	compileText += logList.get(i);
            }
            br.close();

            p3 = pb3.start();
            p3.waitFor();

        	return compileText;
		} catch (InterruptedException | IOException e){
            e.printStackTrace();
            compileText = "Erroer by Application";
        	return compileText;
        }
	}
}
