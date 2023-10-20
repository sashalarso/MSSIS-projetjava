import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import calc.ObjEmp;
import calc.PileRPL;

public class FooCalcRPL {
    public static void main(String[] args) throws IOException {
        PileRPL pile = new PileRPL(5);
        Scanner scanner = new Scanner(System.in);
        PrintWriter writer = null;
        boolean recordMode = false;
        String logFileName = "log.txt";

        if (args.length > 0 && args[0].equals("replay")) {
            Scanner fileScanner = new Scanner(new File(logFileName));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(line);
            }
            fileScanner.close();
            System.exit(0);
        }

        if (args.length > 0 && args[0].equals("record")) {
            new PrintWriter(logFileName).close();
            writer = new PrintWriter(new FileWriter("log.txt", true), true);
            recordMode = true;
        }

        while (true) {
            System.out.println("Entrez une commande : complexe, add, print, quit ");
            String input = scanner.nextLine();

            if (recordMode) {
                writer.println(input);
            }

            String[] tokens = input.split(" ");

            if (tokens.length >= 2) {
                int real = Integer.parseInt(tokens[0]);
                int complex = Integer.parseInt(tokens[1]);
                ObjEmp objEmp = new ObjEmp(real, complex);
                pile.push(objEmp);
            } else if (tokens[0].equals("add")) {
                pile.add();
                System.out.println("Addition effectuée.");
            } else if (tokens[0].equals("quit")) {
                System.out.println("Au revoir!");
                scanner.close();
                if (recordMode && writer != null) {
                    writer.close();
                }
                System.exit(0);
                break;
            } else if (tokens[0].equals("print")) {
                System.out.println("Contenu de la pile :");
                System.out.println(pile);
            } else {
                System.out.println("Commande non reconnue");
            }
        }
    }
}
