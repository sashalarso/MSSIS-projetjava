package calc;
import java.util.Scanner;


public class CalcUI {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean loop=true;
        while (loop){
            input = scanner.nextLine();

            if (input.equals("q")) {
                System.out.println("Au revoir !");
                break; // Quitte la boucle si l'utilisateur entre 'q'
            }
            int firstreal = Integer.parseInt(args[0]);
            int firstcomplex=Integer.parseInt(args[1]);
            String operand =args[2];
            int secondreal=Integer.parseInt(args[3]);
            int secondcomplex=Integer.parseInt(args[4]);

            ObjEmp o1=new ObjEmp(firstreal,firstcomplex );
            ObjEmp o2=new ObjEmp(secondreal, secondcomplex);
            PileRPL pile = new PileRPL(5);
            pile.push(o1);
            System.out.println(pile);
            pile.push(o2);
            System.out.println(pile);
            pile.add();
            System.out.println(pile);

            
        }
        scanner.close();
        
    }
  

   

    

}

        


