package communicationProject.server.commands;

import communicationProject.server.ConnectionThread;
import communicationProject.server.TcpServer;

abstract class Command {
    //ConnectionThread sends POST(show data) to TcpClient
    protected TcpServer sender;
    protected int clientId;
    protected ConnectionThread clientConnection;
    
    public Command(TcpServer sender, int clientId){
        this.sender = sender;
        this.clientId = clientId;
        if(sender.connections.containsKey(clientId)){
            clientConnection = sender.connections.get(clientId);
        }
    }

    protected void checkClientIdExicting() throws IllegalArgumentException{
        if(!sender.connections.containsKey(clientId)){
            throw new IllegalArgumentException("Now client with such clientId to execute PostCommand!");
        }
    }
    public abstract void execute() throws IllegalArgumentException;
}
