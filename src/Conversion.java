package src;

/**
 * Conversion class which holds all the neccessary methods for conversion.
 */
public class Conversion {

   /**
    * operator method which contains the entire list of possible operators.
    * 
    * @param element Compares the element in the stack with the posssible
    *                operators.
    * @return If the element in the stack is an operator, return true; otherwise,
    *         return false.
    */
   private boolean operator(Character element) {
      if (element == '+' || element == '-' || element == '*' || element == '/' ||
            element == '$') {
         return true;
      } else {
         return false;
      }
   }

   /**
    * newLineDelimiter method which contains the possible characters to indicate
    * the end of an equation.
    * 
    * @param element Compares the element in the stack to determine if it is the
    *                end of equation to begin to perform conversion.
    * @return If the element is a new line character, return true; otherwise,
    *         return false.
    */
   private boolean newLineDelimiter(Character element) {
      if (element == '\n' || element == '\r') {
         return true;
      } else {
         return false;
      }
   }

   /**
    * invalidCharacters method which contains the possible characters to indicate
    * an invalid character.
    * 
    * @param element Compares the element in the stack to determine whether it
    *                should be ignored or not.
    * @return If the element is an invalid character, return true; otherwise,
    *         return false.
    */
   public boolean invalidCharacters(Character element) {
      if (element == '`' || element == '~' || element == '!' || element == '@' || element == '#' || element == '%'
            || element == '^' || element == '&' || element == '(' || element == ')' || element == '_' || element == '='
            || element == '{' || element == '[' || element == '}' || element == ']' || element == '|' || element == ':'
            || element == ';' || element == '"' || element == '<' || element == ',' || element == '>' || element == '.'
            || element == '?' || element == ' ') {
         return true;
      } else {
         return false;
      }
   }

   /**
    * revereStack method which reverses the mainStack pushed in from the file.
    * 
    * @param mainStack Accepts the mainStack from the main method to be reversed.
    * @return A reversed version of the stack pushed in from the file.
    */
   private LinkedStack<Character> reverseStack(LinkedStack<Character> mainStack) {
      LinkedStack<Character> reverseStack = new LinkedStack<>();
      while (!mainStack.isEmpty()) {
         reverseStack.push(mainStack.pop());
      }
      return reverseStack;
   }

   /**
    * convert method to perform the conversion from prefix to postfix.
    * 
    * @param mainStack Accept the mainStack from the main method to store the
    *                  reverseStack method as a variable inside convert method.
    * @return Return the converted string.
    */
   public String convert(LinkedStack<Character> mainStack) {
      LinkedStack<Character> reverseStack = reverseStack(mainStack);
      LinkedStack<Character> auxStack = new LinkedStack<>();
      LinkedStack<String> outputStack = new LinkedStack<>();
      String outputString = "The converted results are:";
      // loop through the entire reversed stack
      do {
         // if the current reverse stack element is a new character OR the reverse stack
         // is empty, perform conversion as this is an equation
         if (newLineDelimiter(reverseStack.peek()) || reverseStack.size() == 0) {
            // loop through the auxillary stack which is holding the values for the
            // equations
            do {
               // if the element is an operator and the stack has at least 2 characters,
               // perform calculation
               // push the calculation to outputStack
               if (operator(auxStack.peek()) && outputStack.size() >= 2) {
                  String top = outputStack.pop() + "";
                  String bottom = outputStack.pop() + "";
                  String combined = top + bottom + auxStack.pop() + "";
                  outputStack.push(combined);
               }
               // otherwise, push to the output stack
               else {
                  outputStack.push(auxStack.pop() + "");
               }
               // if the auxStack is empty, create the outputString to represent the conversion
               if (auxStack.size() == 0) {
                  outputString += "\n";
                  String top = outputStack.pop();
                  while (!outputStack.isEmpty()) {
                     outputString += outputStack.pop() + "";
                  }
                  outputString += top;
               }
            } while (!auxStack.isEmpty());
         }
         auxStack.push(reverseStack.pop());
      } while (!reverseStack.isEmpty());
      return outputString;
   }

}
