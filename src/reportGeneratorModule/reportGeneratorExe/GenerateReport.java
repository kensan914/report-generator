package reportGeneratorExe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateReport {
	public static String generateReport(String sourceFilePath,String kadaiNum,String claText) {
		int num = sourceFilePath.lastIndexOf("\\");
		String[] sourceFilePaths = {sourceFilePath.substring(0,num), sourceFilePath.substring(num+1,sourceFilePath.length())};
		String reportText = "", resultText = "", sourceText = "";
		String[] claTexts = claText.split(" ");

		List<String> cmdExcution = new ArrayList<String>();
		if(claTexts.length>0) {
			cmdExcution.addAll(Arrays.asList("excution.bat","\""+sourceFilePaths[0]+"\""));
			for(int i=0;i<claTexts.length;i++) {
				cmdExcution.add(claTexts[i]);
			}
		}else {
			cmdExcution.addAll(Arrays.asList("excution.bat","\""+sourceFilePaths[0]+"\""));
		}
		List<String> cmdExcution2 = new ArrayList<String>(Arrays.asList("excution2.bat","\""+sourceFilePaths[0]+"\""));
		List<String> cmdCat = new ArrayList<String>(Arrays.asList("cmd","/c","type","\""+sourceFilePath+"\""));
		List<String> cmdRm = new ArrayList<String>(Arrays.asList("cmd","/c","del","output.log"));
		List<String> cmdRm2 = new ArrayList<String>(Arrays.asList("cmd","/c","del","\""+sourceFilePaths[0]+"\\"+"a.out"+"\""));

        try {
            ProcessBuilder pb1 = new ProcessBuilder(cmdExcution);
            ProcessBuilder pb2 = new ProcessBuilder(cmdExcution2);
            ProcessBuilder pb3 = new ProcessBuilder(cmdCat);
            ProcessBuilder pb4 = new ProcessBuilder(cmdRm);
            ProcessBuilder pb5 = new ProcessBuilder(cmdRm2);

            Process p1 = pb1.start();
            p1.waitFor();
            Process p2 = pb2.start();
            p2.waitFor();
            Process p3 = pb3.start();
            p3.waitFor();

            InputStream is = p3.getInputStream();
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	        String sourceLine;
            while ((sourceLine = br.readLine()) != null) {
            	 sourceText += sourceLine+"\n";
            }
            br.close();

            File logFile = new File("output.log");
            BufferedReader br2 = new BufferedReader(new FileReader(logFile));
            String logLine="";
            List<String> logList = new ArrayList<String>();
            int count = -1;
            while((logLine = br2.readLine()) != null) {
                logList.add(logLine+"\n");
            	count++;
            }
            logList.remove(count);
            logList.remove(0);
            for(int i=0;i<logList.size();i++) {
            	resultText += logList.get(i);
            }
            br2.close();

            Process p4 = pb4.start();
            p4.waitFor();
            Process p5 = pb5.start();
            p5.waitFor();
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
            return "Erroer by Application";
		}

        reportText += "課題"+kadaiNum+"\n\n";
        reportText += "ソースコード\n";
        reportText += sourceText+"\n";
        reportText += "実行結果\n";
        reportText += resultText;

        return reportText;
	}
}
