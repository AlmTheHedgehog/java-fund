package communicationProject.server;

import java.io.*;
import java.net.*;

public class ConnectionThread extends Thread{
    private Socket socket;
    private int id;
    private DataInputStream in;
    private DataOutputStream out;
    public TcpServer server;

    public ConnectionThread(ServerSocket serverSocket, int id, TcpServer server){
        this.id = id;
        try{
            socket = serverSocket.accept();
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            this.server = server;
            this.server.gui.showText("Client with id=" + id + " was connected");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void run(){
        String inData;
        while(true){
            try {
                inData = in.readUTF();
                server.gui.showText("Recieved from client id=" + id + ": " + inData);
                //post(inData);                
            } catch (IOException e) {
                close();
                return;
            }
        }       
    }

    private void sendData(String data){
        try {
            out.writeUTF(data);
        } catch (IOException e) {
            this.server.gui.showText("Impossible to connect to client!");
            e.printStackTrace();
        }
    }

    public void post(String data){
        sendData("POST␝" + data);
    }

    public void get(String parameterName){
        sendData("GET␝" + parameterName);
    }

    public void set(String argString){
        sendData("SET␝" + argString);
    }

    private void close(){
        server.gui.showText("Client with Id=" + id + " exited");
        try {
            socket.close();
            in.close();
            out.close();
            server.connections.remove(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
