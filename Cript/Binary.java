import java.util.Arrays;

public class Binary {
    public static String encode () {
        //Storing the text in a variable
        String textToBeEncoded = TextIO.input();
        StringBuilder encodedBinaries = new StringBuilder();


        //Converting the text to binary
        for (int i = 0; i < textToBeEncoded.length(); i++) {
            int currentCharAscii = textToBeEncoded.charAt(i);
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

            //Adding the converted binary and cleaning up
            encodedBinaries.append((Arrays.toString(binary)).replace(" ", "")).append(" ");
        }


        return TextIO.output(encodedBinaries.toString());
    }


    public static String decode () {
        //Storing the binary in an array with splitting the spaces
        String[] binaryToBeDecoded = TextIO.input().split(" ");
        StringBuilder decodedText = new StringBuilder();


        //Decoding the text and storing it
        for (String s : binaryToBeDecoded) {
            int sum = 0;
            int n = 0;

            //Starting at the end of the octet
            for (int j = s.length() - 1; j != 0; j--) {
                char currentNumber = s.charAt(j);
                int twoPower = (int) Math.pow(2, n);

                //If the number is not 0 or 1
                if (currentNumber != '0' && currentNumber != '1') {
                    System.err.println("Not a binary.");
                    System.exit(1);
                }

                if (currentNumber == '1') {
                    sum += twoPower;
                }

                n++;
            }

            char currentNumberAsAscii = (char) sum;
            decodedText.append(currentNumberAsAscii);
        }


        return TextIO.output(decodedText.toString());
    }
}
