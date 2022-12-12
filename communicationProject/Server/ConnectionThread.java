package communicationProject.Server;

import java.io.*;
import java.net.*;

import communicationProject.Server.middleware.Communicator;

class ConnectionThread extends Thread{
    private Socket socket;
    private int id;
    private DataInputStream in;
    private DataOutputStream out;
    private Communicator gui;

    public ConnectionThread(ServerSocket serverSocket, int id, Communicator gui){
        this.id = id;
        try{
            socket = serverSocket.accept();
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            this.gui = gui;
            this.gui.showData("Client with id=" + id + " was connected");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void run(){
        while(true){
            try {
                out.writeUTF(in.readUTF());
            } catch (IOException e) {
                close();
                return;
            }
        }       
    }

    private void close(){
        gui.showData("Client with Id=" + id + " exited");
        try {
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
