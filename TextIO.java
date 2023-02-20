import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;

public class TextIO {
    public static String input () {
        String source = Cript.source;
        String input = Cript.input;
        String text;


        //If file was used --> read the content of the file into a variable and return
        if ("file".equals(source)) {
            try {
                File inputFile = new File(input);
                text = Files.readString(inputFile.toPath());
                return text;
            }
            catch (NoSuchFileException e) {
                System.err.println("No such file detected (NoSuchFileException).");
                System.exit(1);
            }
            catch (IOException e) {
                System.err.println("Cannot read file (IOException).");
                System.exit(1);
            }
        }
        //If text was used, return the text
        else
            return input;

        return null;
    }


    public static String output (String outputText) {
        //Making the first letter uppercase
        String method = Cript.method.substring(0, 1).toUpperCase() + Cript.method.substring(1);
        String process = Cript.process.substring(0, 1).toUpperCase() + Cript.process.substring(1);
        String source = Cript.source;
        String input = Cript.input;
        String cleanedOutputText = outputText.replace("[", "").replace("]", "").replace(",", "").trim();


        //If file was used --> create a file and write the results into
        if ("file".equals(source)) {
            try {
                File outputFile = new File(input.replace(".txt", method + process + "d.txt"));
                FileWriter output = new FileWriter(outputFile);


                if ("binary".equals(Cript.method))
                    output.write(cleanedOutputText);
                else
                    output.write(outputText);

                output.close();


                if ("encode".equals(Cript.process))
                    return "The encoded text was saved in " + outputFile.getCanonicalPath();
                else
                    return "The decoded text was saved in " + outputFile.getCanonicalPath();
            }
            catch (IOException e) {
                System.err.println("The file cannot be created or cannot be written into (IOException).");
                System.exit(1);
            }
        }
        //If it was a text, clean up and return the encoded text
        else {
            if ("encode".equals(Cript.process))
                return "The encoded text: " + cleanedOutputText;
            else
                return "The decoded text: " + cleanedOutputText;
        }

        return null;
    }
}
