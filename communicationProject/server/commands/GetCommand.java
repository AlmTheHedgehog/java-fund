package communicationProject.server.commands;

import communicationProject.server.TcpServer;

public class GetCommand extends Command{
    String parameterName;

    public GetCommand(TcpServer sender, int clientId, String parameterName) {
        super(sender, clientId);
        this.parameterName = parameterName;
    }

    @Override
    public void execute() throws IllegalArgumentException {
        checkClientIdExicting();
        clientConnection.get(parameterName); 
    }
    
}
