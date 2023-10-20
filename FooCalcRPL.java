import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import calc.CalcUI;

public class FooCalcRPL {
    public static void main(String[] args) throws IOException {
        String logFileName = "log.txt";
        boolean recordMode = false;

        if (args.length > 0) {
            if (args[0].equals("replay")) {
                Scanner fileScanner = new Scanner(new File(logFileName));
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    System.out.println(line);
                }
                fileScanner.close();
                System.exit(0);
            } else if (args[0].equals("record")) {
                recordMode = true;
            }
            else if (args[0].equals("remote")) {
                recordMode = true;
            }
        }

        CalcUI calcUI = new CalcUI(logFileName, recordMode);
        
    }
}
