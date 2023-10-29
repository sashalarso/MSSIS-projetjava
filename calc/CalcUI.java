package calc;
import calc.ObjEmp;
import calc.PileRPL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class CalcUI {
    private PileRPL pile;
    
    private boolean logbool=false;
    private boolean replaybool=false;
    String[] args;

    private BufferedReader inputuser;
    private PrintStream outputlog;
    private PrintStream outputuser;
    int default_size=5;

    public CalcUI(String[] args) throws IOException {
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (isNumeric(args[i])) {
                    default_size=Integer.parseInt(args[i]);
                    
                }
            }
        }
        pile = new PileRPL(default_size);
        this.args=args;
        initStreams(args);
        run(outputuser,inputuser,pile);
        
    }

    public void run(PrintStream outputuser,BufferedReader inputuser,PileRPL pile) throws IOException {
        boolean loop=true;
        
        while (loop) {
            outputuser.println("Entrez une commande : nombre (valeur), vecteur 3d (x,y,z),complexe (partie réelle, partie imaginaire), add, sub, div, mul, print, quit ");
            String input = inputuser.readLine();
            
            if (input.equals("quit")){
                loop=false;
            }
            if(replaybool){
                outputuser.println(input);
            }
            if ( logbool ){
                outputlog.println(input);
            }
            cmdParser(input, pile,outputuser);
        }
    }

    public void initStreams(String[] args) throws UnknownHostException, IOException{
        
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++){
            switch (args[i]) {
                case "replaylocal":
                    initReplayLocal();
                    break;
                case "replayremote":
                    initReplayNetwork();
                    break;
                case "remote":
                    initFullRemote();
                    break;
                case "local":
                    initFullLocal(); 
                    break;
                case "remoteshared":
                   initFullRemoteShared();
                    break;
                case "remotenotshared":
                    initFullRemoteNotShared();
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
        else{
            initFullLocal();
        }
    }
    public void cmdParser(String cmd,PileRPL pile,PrintStream outputuser){
        String[] tokens = cmd.split(" ");

        if (tokens.length == 3) {
            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);
            int z = Integer.parseInt(tokens[2]);
            ObjEmp vecteur = new Vecteur3D(x,y,z);
            pile.push(vecteur);}
        
        else if (tokens.length == 2) {
            int real = Integer.parseInt(tokens[0]);
            int complex = Integer.parseInt(tokens[1]);
            ObjEmp objEmp = new Complexe(real, complex);
            pile.push(objEmp);
        } else if (tokens[0].equals("add")) {
            
            pile.add();
            outputuser.println("Addition effectuée.");
        }
        else if (tokens[0].equals("sub")) {
            
            pile.sub();
            outputuser.println("Soustraction effectuée.");
        }
        else if (tokens[0].equals("mul")) {
            
            pile.mul();
            outputuser.println("Multiplication effectuée.");
        }
        else if (tokens[0].equals("div")) {
            
            pile.div();
            outputuser.println("Division effectuée.");
        }
        else if (tokens[0].equals("quit")) {
            outputuser.println("Au revoir!");
            
            
            
        } else if (tokens[0].equals("print")) {
            outputuser.println("Contenu de la pile :");
            outputuser.println(pile);
        } 
        else if(tokens.length==1 && isNumeric(tokens[0])   ){
            
            int value=Integer.parseInt(tokens[0]);
            ObjEmp nombre=new Nombre(value);
            pile.push(nombre);
        }
        else {
            outputuser.println("Commande non reconnue");
        }
    }

    public void initFullLocal(){
        inputuser = new BufferedReader(new InputStreamReader(System.in));
        
        outputuser = System.out;
        
    }
    public void initFullRemote() throws UnknownHostException, IOException{
        
        int serverPort = 2222; 
         
        ServerSocket socket = new ServerSocket( serverPort);
        Socket client=socket.accept();
        inputuser = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outputuser = new PrintStream(client.getOutputStream());
        socket.close();
    }

    public void initFullRemoteShared() throws UnknownHostException, IOException{
        
        int serverPort = 2222; 

  
        ServerSocket socket = new ServerSocket( serverPort);
        while (true){
            Socket client=socket.accept();
        
            Thread clientThread = new Thread(() -> handleRemoteConnection(client));
            clientThread.start();
        }             
    }
    public void initFullRemoteNotShared() throws UnknownHostException, IOException{
        
        int serverPort = 2222; 

  
        ServerSocket socket = new ServerSocket( serverPort);
        while (true){
            Socket client=socket.accept();
        
            Thread clientThread = new Thread(() -> handleRemoteConnectionNotShared(client));
            clientThread.start();
        }
                    
    }
    public void initReplayLocal() throws IOException{
        replaybool=true;

        File file=new File("log.txt");
        outputuser=System.out;
        inputuser=new BufferedReader(new FileReader(file));
    }
    public void initReplayNetwork() throws IOException{
        replaybool=true;

        int serverPort = 2222; 
         
        ServerSocket socket = new ServerSocket( serverPort);
        Socket client=socket.accept();
        File file=new File("log.txt");
        outputuser=new PrintStream(client.getOutputStream());
        inputuser=new BufferedReader(new FileReader(file));
    }

    public void logrecording() throws FileNotFoundException{
        this.logbool=true;
        this.outputlog = new PrintStream(new FileOutputStream("log.txt"));
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public void handleRemoteConnection(Socket client) {
        try {
            inputuser = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outputuser = new PrintStream(client.getOutputStream());
            
            run(outputuser,inputuser,pile);
            client.close();
            
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void handleRemoteConnectionNotShared(Socket client) {
        PileRPL pile=new PileRPL(5);
        try {
            inputuser = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outputuser = new PrintStream(client.getOutputStream());
            
            run(outputuser,inputuser,pile);
            client.close();
            
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

  

    



        


