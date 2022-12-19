package communicationProject.client;

import java.io.*;
import java.net.*;

public class TcpClient implements AutoCloseable{
    final String HOSTNAME = "localhost";
    final int PORT = 8080;
    public ClientApplication gui;
    private Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private Thread listeningThread;

    public TcpClient(ClientApplication gui) throws IOException{
        clientSocket = new Socket(HOSTNAME, PORT);
        in = new DataInputStream(clientSocket.getInputStream());
        out = new DataOutputStream(clientSocket.getOutputStream());
        this.gui = gui;
        this.gui.showText("Connected to " + HOSTNAME + " on port " + PORT);
        
        listeningThread = new Thread(){
            String inData;
            public void run(){
                while(!interrupted()){
                    try {
                        inData = in.readUTF();
                        gui.showText("Recieved from server:" + inData);             
                    } catch (IOException e) {
                        gui.showText("No connection to server!");
                        return;
                    }
                }
            }
        };
        listeningThread.start();
    }

    public void post(String data){
        try {
            out.writeUTF("POST␝" + data);
        } catch (IOException e) {
            this.gui.showText("Impossible to connect to server!");
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        listeningThread.interrupt();
        clientSocket.close();
        in.close();
        out.close();        
    }
}
