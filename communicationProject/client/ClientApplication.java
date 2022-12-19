package communicationProject.client;

import javax.swing.*;

import communicationProject.client.commands.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.awt.Dimension;

public class ClientApplication extends JPanel implements ActionListener{
    public JTextField textField;
    private JTextArea textArea;
    private static TcpClient client;

    ClientApplication(){
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

        new PostCommand(client).execute();
    }

    public void showText(String text){
        textArea.append(text + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    private static void startGUI() {
        JFrame frame = new JFrame("TcpClient");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ClientApplication gui = new ClientApplication();
 
        frame.add(gui);

        try {
            client = new TcpClient(gui);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            frame.dispose();
            return;
        }

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                try {
                    client.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

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
