package src;

// necessary imports
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * PrefixToPostfix main class to execute the required code.
 * This project requires an input and output file.
 * The input file is of the prefix form; the output file converts the input file
 * to postfix form.
 * 
 * @version 1.0 07-04=2023
 * @author Johnnie Massart
 */
public class PrefixToPostfix {

    // max number of arguments provided in terminal by user
    public static int ARGS_LENGTH = 2;

    /**
     * Main method to execute the conversion.
     * 
     * @param args The user provides two args - input, output file
     * @throws IOException Throw exception if operation cannot be performed
     *                     Include the approriate exception message in string format
     */
    public static void main(String[] args) throws IOException {
        // create instance of stack
        LinkedStack<Character> mainStack = new LinkedStack<>();
        // create instance of conversion class which holds the required methods
        Conversion conversion = new Conversion();

        // ensure the entered the appropriate number of arguments in terminal
        if (args.length != ARGS_LENGTH) {
            System.out.println("You have entered an incorrect number of command line arguments. The program requires "
                    + ARGS_LENGTH + " command lines arguments.");
            System.exit(1);
        }

        BufferedReader reader;
        BufferedWriter writer;

        // open the files which we be used for input and output
        try {
            reader = new BufferedReader(new FileReader(args[0]));
            writer = new BufferedWriter(new FileWriter(args[1]));
        } catch (Exception ioe) {
            System.err.println(ioe.toString());
            return;
        }

        // push the file character by character onto the stack
        // ignore any characters that are deemed invalid
        int c;
        while ((c = reader.read()) != -1) {
            char ch = (char) c;
            if (conversion.invalidCharacters(ch)) {
                continue;
            }
            mainStack.push(ch);
        }

        // add new line character to end of file in case file does not end with new line
        if (mainStack.peek() != '\n') {
            mainStack.push('\n');
        }

        // write the conversion to output file
        writer.write(conversion.convert(mainStack));

        // close the operating sytem
        try {
            reader.close();
            writer.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }

    }

}
