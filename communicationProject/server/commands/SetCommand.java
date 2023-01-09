package communicationProject.server.commands;

import communicationProject.server.TcpServer;

public class SetCommand extends Command{
    String arguments;

    public SetCommand(TcpServer sender, int clientId, String equipmentName, int newValue) {
        super(sender, clientId);
        this.arguments = equipmentName + ":" + newValue;
    }

    public SetCommand(TcpServer sender, int clientId, String arguments) {
        super(sender, clientId);
        this.arguments = arguments;
    }

    @Override
    public void execute() throws IllegalArgumentException {
        checkClientIdExicting();
        clientConnection.set(arguments);
    }
    
}
