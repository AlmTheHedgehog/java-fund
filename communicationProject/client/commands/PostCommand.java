package communicationProject.client.commands;

import communicationProject.client.TcpClient;

public class PostCommand extends Command{

    public PostCommand(TcpClient sender) {
        super(sender);
    }

    @Override
    public void execute() {
        sender.post(sender.gui.textField.getText());
    }
    
}
