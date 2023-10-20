import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import calc.PileRPL;

public class Server {
    protected void startRemoteServer(PileRPL pile) {
        try {
            ServerSocket serverSocket = new ServerSocket(2222); // Choisissez le port de votre choix
            System.out.println("Serveur distant démarré. Attente de connexions...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connecté depuis " + clientSocket.getInetAddress().getHostAddress());

                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

                String command;
                boolean continueRunning = true;

                while (continueRunning && (command = input.readLine()) != null) {
                    continueRunning = processCommand(pile, command);

                    // Rediriger la sortie de la commande vers le client
                    if (continueRunning) {
                        output.println(pile);
                    }
                }

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
