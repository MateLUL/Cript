import java.util.Base64;

public class B64 {
    public static String encode () {
        //Storing the text in a variable
        String textToBeEncoded = TextIO.input();
        String encodedText = Base64.getEncoder().encodeToString(textToBeEncoded.getBytes());

        return TextIO.output(encodedText);
    }

    public static String decode () {
        //Storing the text in a variable
        String b64ToBeDecoded = TextIO.input();
        String decodedText = "";


        //Decoding the text and handling error
        try {
            byte[] decodedTextInBytes = Base64.getDecoder().decode(b64ToBeDecoded);
            decodedText = new String(decodedTextInBytes);
        } catch (IllegalArgumentException e) {
            System.err.println("Not a Base64 string (IllegalArgumentException).");
            System.exit(1);
        }

        return TextIO.output(decodedText);
    }
}
