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
    FileOperator(String fileInputPath, String fileOutputPath) throws FileNotFoundException, IOException{
        inputFile = new File(fileInputPath);
        outputFile = new File(fileOutputPath);
        fileScanner = new Scanner(inputFile);
        fileWriter = new FileWriter(outputFile);
    }

    /**
     * FileOperator which is using the <b>console</b> for input
     * @param fileOutputPath as a file for output
     */
    FileOperator(String fileOutputPath) throws IOException{
        outputFile = new File(fileOutputPath);
        fileWriter = new FileWriter(outputFile);
        fileScanner = new Scanner(System.in);
    }

    public String getLine(){
        if(!fileScanner.hasNextLine()){
            return null;
        }
        return fileScanner.nextLine();
    }

    public void writeLine(String line) throws IOException{
        fileWriter.write(line+"\n");
    }

    public void closeAll() throws IOException{
        fileScanner.close();
        fileWriter.close();      
    }
}
