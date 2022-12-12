package communicationProject.Server;

import java.io.*;
import java.net.*;

import communicationProject.Server.middleware.Communicator;

public class TcpServer {

    public TcpServer(int connectionPort, Communicator gui) throws IOException{
        final int PORT = connectionPort;
        ServerSocket serverSocket = new ServerSocket(PORT);

        gui.showData("Listening on port " + PORT);

        new Thread(){
            int id = 0;
            public void run(){
                while (true) {
                    new ConnectionThread(serverSocket, id++, gui).start();
                }
            }
        }.start();

    }
}
