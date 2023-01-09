package communicationProject.server;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class TcpServer {
    final int PORT;
    ServerSocket serverSocket;
    public Map<Integer, ConnectionThread> connections;
    ServerApplication gui;

    public TcpServer(int connectionPort, ServerApplication gui) throws IOException{
        PORT = connectionPort;
        serverSocket = new ServerSocket(PORT);
        connections = new HashMap<Integer, ConnectionThread>();
        this.gui = gui;

        gui.showText("Listening on port " + PORT);

        new Thread(){
            int id = 0;
            ConnectionThread newConnection;
            TcpServer server;
            public void start(TcpServer server){
                this.server = server;
                start();
            }
            public void run(){
                while (true) {
                    newConnection = new ConnectionThread(serverSocket, id, server);
                    newConnection.start();
                    connections.put(id, newConnection);
                    id++;
                }
            }
        }.start(this);

    }
}
