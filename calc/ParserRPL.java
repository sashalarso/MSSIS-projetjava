package calc;

public class ParserRPL {
    String input;
    PileRPL pile;

    public ParserRPL(String input,PileRPL pile){
        this.input=input;
        this.pile=pile;
        cmdParser(input, pile);
    }
    public void cmdParser(String cmd,PileRPL pile){
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
            
            System.exit(0);
            
        } else if (tokens[0].equals("print")) {
            System.out.println("Contenu de la pile :");
            System.out.println(pile);
        } else {
            System.out.println("Commande non reconnue");
        }
    }
    
}
