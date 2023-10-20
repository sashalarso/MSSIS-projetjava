package calc;
import calc.ObjEmp;
import calc.PileRPL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class CalcUI {
    private PileRPL pile;
    private boolean loop=true;
    private boolean logbool=false;
    String[] args;

    private BufferedReader inputuser;
    private PrintStream outputlog;
    private PrintStream outputuser;

    public CalcUI(String[] args) throws IOException {
        pile = new PileRPL(5);
        this.args=args;
        initStreams(args);
        run();
    }

    public void run() throws IOException {
        
        while (loop) {
            System.out.println("Entrez une commande : complexe (partie réelle, partie imaginaire), add, print, quit ");
            String input = inputuser.readLine();
            if (input.equals("quit")){
                loop=false;
            }
            
            if ( logbool){
                outputlog.println(input);
            }
            cmdParser(input, pile);
        }
    }

    public void initStreams(String[] args) throws UnknownHostException, IOException{
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++){
            switch (args[i]) {
                case "replay":

                    break;
                case "remote":
                    initFullRemote();
                    break;
                case "local":
                    initFullLocal(); 
                    break;
                case "remoteshared":
                   
                    break;
                case "remotenotshared":
                    
                    break;
                case "log":
                    logrecording();
                    break;
                default:
                    initFullLocal();
                    break;
            }
        }
    }
    }
    public void cmdParser(String cmd,PileRPL pile){
        String[] tokens = cmd.split(" ");

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
            
            
            
        } else if (tokens[0].equals("print")) {
            System.out.println("Contenu de la pile :");
            System.out.println(pile);
        } else {
            System.out.println("Commande non reconnue");
        }
    }

    public void initFullLocal() throws FileNotFoundException{
        inputuser = new BufferedReader(new InputStreamReader(System.in));
        //outputlog = new BufferedReader(new FileReader("log.txt"));
        outputuser = System.out;
        
    }
    public void initFullRemote() throws UnknownHostException, IOException{
        
        int serverPort = 2222; 

  
        ServerSocket socket = new ServerSocket( serverPort);
        Socket client=socket.accept();
        

        inputuser = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outputuser=new PrintStream(client.getOutputStream());
        
         
    }
    public void initReplayLocal(){

    }
    public void initReplayNetwork(){

    }

    public void logrecording() throws FileNotFoundException{
        this.logbool=true;
        this.outputlog = new PrintStream(new FileOutputStream("log.txt"));
    }

    public void replay(){

    }
}

   //3 flux
   //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    



        


