import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.*;

public class Cript {

    public static String binaryEncode (String source, String input) {
        ArrayList<String> textToBeEncoded = new ArrayList<>();
        ArrayList<String> encodedBinaries = new ArrayList<>();


        //Reading and storing the file's content in a variable
        if ("file".equals(source)) {
            try {
                File inputFile = new File(input);
                String fileContent = Files.readString(inputFile.toPath());
                textToBeEncoded.add(fileContent);
            }
            catch (NoSuchFileException e) {
                System.err.println("No such file detected.");
                System.exit(1);
            }
            catch (IOException e) {
                System.err.println("Cannot read file.");
                System.exit(1);
            }
        }
        else if ("text".equals(source)) {
            textToBeEncoded.add(input);
        }


        //Converting the text to binary
        //Number of lines
        for (int i = 0; i < textToBeEncoded.size(); i++) {
            //Number of letters in a line
            for (int j = 0; j < textToBeEncoded.get(i).length(); j++) {
                int currentCharAscii = textToBeEncoded.get(i).charAt(j);
                int[] binary = new int[8];

                //Getting the binary form and storing in an array
                int k = 0;
                while (currentCharAscii > 0) {
                    binary[k] = currentCharAscii % 2;
                    currentCharAscii = currentCharAscii / 2;
                    k++;
                }

                //Reversing the array
                for (int l = 0; l < binary.length / 2; l++) {
                    int temp = binary[l];
                    binary[l] = binary[binary.length - l - 1];
                    binary[binary.length - l - 1] = temp;
                }

                //Adding the converted binary to the encodedBinaries ArrayList
                //it's disgusting, I know, needs refactoring
                encodedBinaries.add(Arrays.toString(binary).replace(",", "").replace(" ", "").replace("[", "").replace("]", "").trim());
            }
        }


        //Creating the output file, writing the binaries into it, and returning
        if ("file".equals(source)) {
            try {
                File outputFile = new File(input.replace(".txt", "Encoded.txt"));
                FileWriter output = new FileWriter(outputFile);

                for (int i = 0; i < encodedBinaries.size(); i++) {
                    output.write(encodedBinaries.get(i) + " ");
                }

                output.close();
                return "The encoded text was saved in " + outputFile.getCanonicalPath();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //If it was a text, clean up and return the encoded text
        else {
            String encodedText = encodedBinaries.toString().replace("[", "").replace("]", "").replace(",", "").trim();

            return "The encoded text: " + encodedText;
        }
    }

    public static String binaryDecode (String source, String input) {
        ArrayList<String> binaryToBeDecoded = new ArrayList<>();
        ArrayList<String> decodedText = new ArrayList<>();


        //Reading and storing the file's content in a variable
        if ("file".equals(source)) {
            try {
                File inputFile = new File(input);
                String fileContent = Files.readString(inputFile.toPath());
                String[] binaries = fileContent.split(" ");
                binaryToBeDecoded.addAll(Arrays.asList(binaries));
            }
            catch (NoSuchFileException e) {
                System.err.println("No such file detected.");
                System.exit(1);
            }
            catch (IOException e) {
                System.err.println("Cannot read file.");
                System.exit(1);
            }
        }
        else if ("text".equals(source)) {
            String[] binaries = input.split(" ");
            binaryToBeDecoded.addAll(Arrays.asList(binaries));
        }

        //Decoding the text and storing it in the ArrayList
        for (int i = 0; i < binaryToBeDecoded.size(); i++) {
            int sum = 0;
            int n = 0;

            //Starting at the end of the octet
            for (int j = binaryToBeDecoded.get(i).length() - 1; j != 0 ; j--) {
                char currentNumber = binaryToBeDecoded.get(i).charAt(j);

                if (currentNumber != '0' && currentNumber != '1') {
                    System.err.println("Not a binary.");
                    System.exit(1);
                }

                int twoPower = (int) Math.pow(2, n);
                if (currentNumber == '1')
                    sum += twoPower;

                n++;
            }

            char currentNumberAsAscii = (char) sum;
            decodedText.add(String.valueOf(currentNumberAsAscii));
        }


        //Creating the output file
        if ("file".equals(source)) {
            try {
                File outputFile = new File(input.replace(".txt", "Decoded.txt"));
                FileWriter output = new FileWriter(outputFile);

                for (int i = 0; i < decodedText.size(); i++)
                    output.write(decodedText.get(i));

                output.close();
                return "The decoded text was saved in " + outputFile.getCanonicalPath();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //If it was a text, clean up and return the decoded text
        else {
            StringBuilder decodedBinaries = new StringBuilder();
            for (int i = 0; i < decodedText.size(); i++) {
                if (decodedText.get(i).equals("\n"))
                    decodedBinaries.append(" <enter> ");
                else
                    decodedBinaries.append(decodedText.get(i));
            }

            return "The decoded text: " + decodedBinaries;
        }
    }

    public static void errorHandling(String[] args) {
        if (args.length != 1) {
            if (args.length > 4) {
                System.err.println("Too many arguments. Check the documentation, or type 'java Cript help' for further help.");
                System.exit(1);
            }
            else if (args.length < 4) {
                System.err.println("Not enough arguments. Check the documentation, or type 'java Cript help' for further help.");
                System.exit(1);
            }
        }
        else {
            if ("help".equals(args[0])) {
                System.out.println("Usage: java Cript <Method> <Process> <Source> <Content>");
                System.out.println("\t- Method: binary");
                System.out.println("\t- Process: encode / decode");
                System.out.println("\t- Source: text / file");
                System.out.println("\t- Content: \n\t\tif source was text: \"This is the text I want to encode\"\n\t\tif source was file: /path/to/file.txt");
                System.out.println("Ex.: java Cript binary encode text \"I like turtles\"");
                System.exit(0);
            }
            else {
                System.err.println("Wrong type of argument. Check the documentation, or type 'java Cript help' for further help.");
                System.exit(1);
            }
        }

        if ((!"binary".equals(args[0])) || (!"encode".equals(args[1]) && !"decode".equals(args[1])) || (!"text".equals(args[2]) && !"file".equals(args[2]))) {
            System.err.println("Wrong type of arguments. Check the documentation, or type 'java Cript help' for further help.");
            System.exit(1);
        }
    }

    public static void main(String[] args)  {
        //Error handling
        errorHandling(args);


        //Storing the arguments in variables for readability
        String method = args[0];
        String process = args[1];
        String source = args[2];
        String input = args[3];


        //Binary
        if ("binary".equals(method)) {
            if ("encode".equals(process))
                //Encoding
                System.out.println(binaryEncode(source, input));
            else
                //Decoding
                System.out.println(binaryDecode(source, input));
        }
    }
}