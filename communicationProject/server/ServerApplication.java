package communicationProject.server;

import javax.swing.*;

import communicationProject.server.commands.GetCommand;
import communicationProject.server.commands.PostCommand;
import communicationProject.server.commands.SetCommand;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.awt.Dimension;

public class ServerApplication extends JPanel implements ActionListener{
    public JTextField textField;
    private JTextArea textArea;
    private static TcpServer server;
    final private int SEPARATED_COMMAND_ELEMENTS = 3;

    ServerApplication(){
        super(new GridBagLayout());
        textField = new JTextField(20);
        textField.addActionListener(this);
        textField.setFont(new Font("Arial", Font.BOLD, 20));
 
        textArea = new JTextArea(15, 20);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.BOLD, 20));
        JScrollPane scrollPane = new JScrollPane(textArea);
 
        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
  
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 2;
        c.weighty = 1.0;
        add(scrollPane, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        add(textField, c);
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        showText(textField.getText());
        textField.selectAll();

        try{
            executeCommand(textField.getText());
        }catch(Exception e){
            showText(e.getMessage());
        }
    }

    public void showText(String text){
        textArea.append(text + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    public void executeCommand(String commandString) throws IllegalArgumentException{
        //command format clientId:commandType:data (5␝POST␝Hi there)
        String[] commandArray = textField.getText().split("␝");
        int clientId;
        if(commandArray.length != SEPARATED_COMMAND_ELEMENTS){
            throw new IllegalArgumentException("Invalid command(not 3 elements separated by group separator)");
        }
        try{
            clientId = Integer.parseInt(commandArray[0]);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("Invalid command(cleintId not number)", e);
        }
        switch(commandArray[1]){
            case "POST":
                new PostCommand(server, clientId, commandArray[2]).execute();
                break;
            case "GET":
                new GetCommand(server, clientId, commandArray[2]).execute();
                break;
            case "SET":
                new SetCommand(server, clientId, commandArray[2]).execute();
                break;
            default:
                throw new IllegalArgumentException("Invalid command(unknown commandType)");
        }
    }

    private static void startGUI() {
        JFrame frame = new JFrame("TcpServer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ServerApplication gui = new ServerApplication();
 
        frame.add(gui);

        try {
            server = new TcpServer(8080, gui);
        } catch (IOException e) {
            e.printStackTrace();
            frame.dispose();
        }

        frame.setMinimumSize(new Dimension(500, 500));
        frame.setSize(500,500);
        frame.setVisible(true);
    }
    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                startGUI();
            }
        });

    }
}
