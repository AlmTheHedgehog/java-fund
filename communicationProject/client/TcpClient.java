package communicationProject.client;

import java.io.*;
import java.net.*;

import communicationProject.client.commands.PostCommand;

public class TcpClient implements AutoCloseable{
    final String HOSTNAME = "localhost";
    final int PORT = 8080;
    public ClientApplication gui;
    private Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private Thread listeningThread;

    public TcpClient(ClientApplication gui) throws IOException{
        clientSocket = new Socket(HOSTNAME, PORT);
        in = new DataInputStream(clientSocket.getInputStream());
        out = new DataOutputStream(clientSocket.getOutputStream());
        this.gui = gui;
        this.gui.showText("Connected to " + HOSTNAME + " on port " + PORT);
        
        listeningThread = new Thread(){
            String inData;
            public void run(){
                while(!interrupted()){
                    try {
                        inData = in.readUTF();
                        try{
                            processRequests(inData);
                        }catch(IllegalArgumentException e){
                            post(e.getMessage());
                        }
                    } catch (IOException e) {
                        gui.showText("No connection to server!");
                        return;
                    }
                }
            }
        };
        listeningThread.start();
    }

    public void post(String data){
        try {
            out.writeUTF("POST␝" + data);
        } catch (IOException e) {
            this.gui.showText("Impossible to connect to server!");
            e.printStackTrace();
        }
    }

    public String getDataFromSensor(String sensorName) throws IllegalArgumentException{
        switch(sensorName.toLowerCase()){
            case "temp":
                return "Current temperature:" + gui.tempSlider.getValue();
            default:
                throw new IllegalArgumentException("Invalid sensor name");
        }
    }

    public void SetDataToEquipment(String equipmentData) throws IllegalArgumentException{
        String[] separatedEquipmentData = equipmentData.split(":");
        if(separatedEquipmentData.length != 2){
            throw new IllegalArgumentException("Invalid equipment data");
        }
        String equipmentName = separatedEquipmentData[0];
        String value = separatedEquipmentData[1];
        
        switch(equipmentName.toLowerCase()){
            case "temp":
                try{
                    gui.requestedTemp = Integer.parseInt(value);
                }catch(NumberFormatException e){
                    throw new IllegalArgumentException("Invalid value arg for equipment", e);
                }
                gui.tempLabel.setText("Requested temperature:" + value);
                break;
            default:
                throw new IllegalArgumentException("Invalid equipment name");
        }
    }

    public void processRequests(String inData) throws IllegalArgumentException{
        //Each command part separated by "␝" and SET argument is a string with equipment name and value separated by ":"
        String[] separatedRequest = inData.split("␝");
        if(separatedRequest.length != 2){
            throw new IllegalArgumentException("Invalid command format");
        }
        switch(separatedRequest[0]){
            case "GET":
                new PostCommand(this,getDataFromSensor(separatedRequest[1])).execute();
                break;
            case "POST":
                gui.showText("Recieved from server:" + separatedRequest[1]);
                break;
            case "SET":
                SetDataToEquipment(separatedRequest[1]);
                break;
            default:
                throw new IllegalArgumentException("Invalid command type");
        }
    }

    @Override
    public void close() throws Exception {
        listeningThread.interrupt();
        clientSocket.close();
        in.close();
        out.close();        
    }
}
