package calc;
import calc.ObjEmp;
import calc.PileRPL;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;



public class CalcUI {
    private PileRPL pile;
    private PrintWriter writer;
    private boolean recordMode;

    public CalcUI(String logFileName, boolean recordMode) {
        pile = new PileRPL(5);
        this.recordMode = recordMode;

        if (recordMode) {
            try {
                writer = new PrintWriter(logFileName, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

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

   

    



        


