import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileOperator {
    private File inputFile, outputFile;
    private Scanner fileScanner;
    private FileWriter fileWriter;
    
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
