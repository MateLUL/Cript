public class Cript {
    private static String method;
    private static String process;
    private static String source;
    private static String input;


    //Getter-setters
    public static String getMethod() {
        return method;
    }

    public static String getProcess() {
        return process;
    }

    public static String getSource() {
        return source;
    }

    public static String getInput() {
        return input;
    }

    private static void setMethod(String method) {
        Cript.method = method;
    }

    private static void setProcess(String process) {
        Cript.process = process;
    }

    private static void setSource(String source) {
        Cript.source = source;
    }

    private static void setInput(String input) {
        Cript.input = input;
    }

    //Handling errors
    private static void errorHandling(String[] args) {
        //Checking the length of the arguments array
        if (args.length != 1) {
            if (args.length > 4) {
                System.err.println("Too many arguments. Check the documentation, or type 'java Cript help' for further help.");
                System.exit(1);
            } else if (args.length < 4) {
                System.err.println("Not enough arguments. Check the documentation, or type 'java Cript help' for further help.");
                System.exit(1);
            }
        } else {
            //Help documentation
            if ("help".equals(args[0])) {
                System.out.println("Usage: java Cript <Method> <Process> <Source> <Content>");
                System.out.println("\t- Method: binary / base64");
                System.out.println("\t- Process: encode / decode");
                System.out.println("\t- Source: text / file");
                System.out.println("\t- Content: \n\t\tif source was text: \"This is the text I want to encode\"\n\t\tif source was file: /path/to/file.txt");
                System.out.println("Ex.: java Cript base64 encode text \"I like turtles\"");
                System.exit(0);
            } else {
                System.err.println("Invalid argument. Check the documentation, or type 'java Cript help' for further help.");
                System.exit(1);
            }
        }


        //If one of the arguments is not valid
        if ((!"binary".equals(args[0]) && !"base64".equals(args[0])) || (!"encode".equals(args[1]) && !"decode".equals(args[1])) || (!"text".equals(args[2]) && !"file".equals(args[2]))) {
            System.err.println("Invalid argument(s). Check the documentation, or type 'java Cript help' for further help.");
            System.exit(1);
        }
    }

    //Calling the appropriate methods based on user arguments
    private static void callMethods() {
        //Binary
        if ("binary".equals(getMethod())) {
            if ("encode".equals(getProcess())) {
                //Encoding
                System.out.println(Binary.encode());
            } else {
                //Decoding
                System.out.println(Binary.decode());
            }
        }

        //Base64
        if ("base64".equals(getMethod())) {
            if ("encode".equals(getProcess())) {
                //Encoding
                System.out.println(B64.encode());
            } else {
                //Decoding
                System.out.println(B64.decode());
            }
        }
    }

    public static void main(String[] args)  {
        //Error handling
        errorHandling(args);

        //Setting the values of String variables
        setMethod(args[0]);
        setProcess(args[1]);
        setSource(args[2]);
        setInput(args[3]);

        //Calling the appropriate methods based on user arguments
        callMethods();
    }
}
