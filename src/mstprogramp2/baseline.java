package mstprogramp2;

/**
 * baseline
 * Implements baseline encoder/decoder fixed-length 8 bits 
 * @author donia
 */
public class baseline {
    private String[] code = new String[26]; // Store the codes
    private String decoded = ""; // Decoded text
    private String encoded = ""; // Encoded text
     
    /**
     * Baseline fixed-length 8 bits encoder 
     * @param text the text to be encoded
     * @return Encoded text 
     */
    public String baselineEncoder(String text) {
        // Use StringBuilder instead of string concatenation for better performance
        StringBuilder sb = new StringBuilder(text.length() * 8);
        encoded = "";

        for (char ch : text.toCharArray()) {  
            // Convert character to 8-bit binary string, padding with zeros on the left
            String charCode = String.format("%8s", Integer.toBinaryString(ch & 0xFF)).replace(' ', '0');

            if (ch >= 'A' && ch <= 'Z') { // Condition checking the range
                // Store the binary 8-bit code in code[], index = char ASCII - 'A'
                code[ch - 'A'] = charCode;
            }

            // Append the code for the current character
            sb.append(charCode);
        }

        // Convert builder to final encoded string
        encoded = sb.toString();
        return encoded;
    }

    /**
     * Display table of characters and their codes
     */
    public void displayBaseline(){
        System.out.println("_____________________________");
        System.out.println("|    Char   |Baseline         |");
        System.out.println("|___________|_________________|");
        for(int i=0;i<code.length;i++){
            if(code[i]!=null){
           
            System.out.println("|  " + (char)(i+'A')+ "        |      " + code[i]+ "  |");
            }
             System.out.println("|____________________________|");
     }   
    }

    /**
     * The text encoded using baseline method fixed-length 8-bits 
     * @param encoded encoded text
     * @return Decoded text 
     */
    public String baselineDecoder(String encoded) {
        decoded = "";
        // Decoding each iteration: take 8 bits and convert it into char using ASCII
        for (int i = 0; (i + 8) <= encoded.length(); i += 8) { // Start from 0, increment by 8
            String charCode = encoded.substring(i, i + 8); // Cut 8 bits from encoded text
            int toDecimal = Integer.parseInt(charCode, 2); // Convert binary to decimal
            decoded = decoded + (char) toDecimal; // Convert decimal to char (ASCII)
        }
        return decoded;
    }
}
