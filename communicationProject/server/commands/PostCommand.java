package communicationProject.server.commands;

import communicationProject.server.TcpServer;

public class PostCommand extends Command{
    String data;

    public PostCommand(TcpServer sender, int clientId, String data) {
        super(sender, clientId);
        this.data = data;
    }

    @Override
    public void execute() throws IllegalArgumentException{
        checkClientIdExicting();
        clientConnection.post(data);
    }    
}
