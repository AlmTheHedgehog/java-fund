package communicationProject.client;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import communicationProject.client.commands.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.awt.Dimension;

public class ClientApplication extends JPanel implements ActionListener, ChangeListener{
    public JTextField textField;
    public JSlider tempSlider;
    public JLabel tempLabel;
    public int requestedTemp;
    private Font standartFont;
    private JTextArea textArea;
    private static TcpClient client;

    ClientApplication(){
        super(new GridBagLayout());
        standartFont = new Font("Arial", Font.BOLD, 20);
        requestedTemp = 0;

        textField = new JTextField(20);
        textField.addActionListener(this);
        textField.setFont(standartFont);
 
        textArea = new JTextArea(15, 20);
        textArea.setEditable(false);
        textArea.setFont(standartFont);
        JScrollPane scrollPane = new JScrollPane(textArea);

        tempSlider = new JSlider(JSlider.HORIZONTAL, -20, 40, 0);
        tempSlider.addChangeListener(this);
        tempSlider.setFont(standartFont);
        tempSlider.setMajorTickSpacing(10);
        tempSlider.setMinorTickSpacing(1);
        tempSlider.setPaintLabels(true);
        tempSlider.setPaintTicks(true);

        tempLabel = new JLabel("Requested temperature:None", JLabel.LEFT);
        tempLabel.setFont(standartFont);
 
        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        add(tempSlider, c);
        
        add(tempLabel, c);
  
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 2;
        c.weighty = 1.0;
        add(scrollPane, c);

        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0;
        add(textField, c);
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        showText(textField.getText());
        textField.selectAll();

        new CmdLinePostCommand(client).execute();
    }

    @Override
    public void stateChanged(ChangeEvent event) {
        int value = tempSlider.getValue();
        if((value < -5) || (value > 20)){
            new PostCommand(client, "Temp allert!:" + value).execute();;
        }
        
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
