package communicationProject.Client;

import java.io.*;
import java.net.*;

import communicationProject.Client.middleware.Communicator;

public class TcpClient implements AutoCloseable{
    private final String CLOSING_REQUEST = "close.app.final_";
    final String HOSTNAME = "localhost";
    final int PORT = 8080;
    private Communicator gui;
    private Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream out;

    public TcpClient(Communicator gui) throws IOException{
        clientSocket = new Socket(HOSTNAME, PORT);
        in = new DataInputStream(clientSocket.getInputStream());
        out = new DataOutputStream(clientSocket.getOutputStream());
        this.gui = gui;
        this.gui.showData("Connected to " + HOSTNAME + " on port " + PORT);
    }

    public void request(String data){
        gui.showData("Sending to server:\n" + data);
        try {
            out.writeUTF(data);
            gui.showData("Response:" + in.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        clientSocket.close();
        in.close();
        out.close();        
    }
}
