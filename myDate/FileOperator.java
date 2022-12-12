package myDate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileOperator {
    private File inputFile, outputFile;
    private Scanner fileScanner;
    private FileWriter fileWriter;
    
    /**
     * FileOperator which is using
     * @param fileInputPath as a file for input
     * @param fileOutputPath as a file for output
     */
    FileOperator(String fileInputPath, String fileOutputPath) throws FileNotFoundException{
        inputFile = new File(fileInputPath);
        outputFile = new File(fileOutputPath);
        try{
            fileScanner = new Scanner(inputFile);
            fileWriter = new FileWriter(outputFile);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * FileOperator which is using the <b>console</b> for input
     * @param fileOutputPath as a file for output
     */
    FileOperator(String fileOutputPath){
        outputFile = new File(fileOutputPath);
        try{
            fileWriter = new FileWriter(outputFile);
            fileScanner = new Scanner(System.in);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getLine(){
        if(!fileScanner.hasNextLine()){
            return null;
        }
        return fileScanner.nextLine();
    }

    public void writeLine(String line){
        try{
            fileWriter.write(line+"\n");
        }catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void closeAll(){
        fileScanner.close();
        try{
            fileWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }        
    }
}
