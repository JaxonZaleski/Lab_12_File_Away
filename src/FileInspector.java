import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;

public class FileInspector {
    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        File currentDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(currentDirectory);

        if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
            selectedFile=chooser.getSelectedFile();
            Path file = selectedFile.toPath();
            String[] files = selectedFile.toString().split("\\\\");
            System.out.println("Selected file: " + files[files.length-1]);
            try {
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));
                int numOfWords=0;
                int numOfCharacters = 0;
                int numOfLines = 0;
                String rec="";
                String[] lines;
                String[] words;
                while (reader.ready()) {
                    rec = reader.readLine();
                    numOfLines++;
                    words = rec.split(" ");
                    numOfWords = numOfWords + words.length;
                    for (int i=0; i<words.length; i++) {
                        numOfCharacters = numOfCharacters + words[i].length();
                    }
                }
                System.out.println("The number of lines are: " + numOfLines);
                System.out.println("The number of words are: " + numOfWords);
                System.out.println("The number of characters are: " + numOfCharacters);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No file selected? Choose a file.");
        }
    }
}