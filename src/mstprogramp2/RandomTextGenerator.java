package mstprogramp2;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random; 
import java.io.File;
import java.util.ArrayList;
import java.util.List;
	 
public class RandomTextGenerator { 
	public static String generateRandomText(int length) { 
	    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
	    Random random = new Random(); 
	    StringBuilder sb = new StringBuilder(length); 
	 
	    for (int i = 0; i < length; i++) { 
	        int index = random.nextInt(characters.length()); 
	        sb.append(characters.charAt(index)); 
	    } 
	    
	    return sb.toString(); 
	} 
	 
	public static void main(String[] args) throws IOException {
		
		//no. of characters = 50x1024 = 51200
		int size50KB = 51200;
		String text50KB = generateRandomText(size50KB); 
		
		//generate text file of 50KB 
		System.out.println("Generating file: file_50KB.txt "); 
		System.out.println("Generated text length: " + text50KB.length() + " characters");
		System.out.println();
		//Save to a file 
		Files.writeString(Path.of("file_50KB.txt"), text50KB); 
		
		//no. of characters = 200x1024 = 204800
		int size200KB = 204800;
		String text200KB = generateRandomText(size200KB);
		
		//generate text file of 200KB 
		System.out.println("Generating file: file_200KB.txt ");		
		System.out.println("Generated text length: " + text200KB.length() + " characters"); 
		System.out.println();
		//Save to a file 
		Files.writeString(Path.of("file_200KB.txt"), text200KB); 
		
		//no. of characters = 1x1024x1024 = 1048576
		int size1MB = 1048576;
		String text1MB = generateRandomText(size1MB); 
		
		//generate text file of 1MB 
		System.out.println("Generating file: file_1MB.txt ");		
		System.out.println("Generated text length: " + text1MB.length() + " characters");
		System.out.println();
		//Save to a file 
		Files.writeString(Path.of("file_1MB.txt"), text1MB);
                
                
                /* -------------------------------------------------------
                 * Requirement 2: Implementaion
                 * ------------------------------------------------------- */
                
                //Create HuffmanAlg Object
                HuffmanAlg huffmanObj = new HuffmanAlg();
                
                //Huffman Encoder 50KB
                String encoded50KB=huffmanObj.huffmanEncoder(text50KB); 
                //Disply 50KB table
                System.out.println("File 50MB");
                huffmanObj.display();
              
                //Huffman Decoder 50KB
                String decoded50KB=huffmanObj.huffmanDecoder(encoded50KB);
                
                //Baseline Object 50KB
                baseline baselineobj=new baseline();
                // Baseline encoder 50KB
                String encodedBaseline50KB=baselineobj.baselineEncoder(text50KB);
                //Disply table 50KB 
                System.out.println("File 50MB");
                baselineobj.displayBaseline();
               
                String baselineDecoder50KB=baselineobj.baselineDecoder(encodedBaseline50KB);
                
                System.out.println("Huffman Decoded Equals to The Text (50KB): "+decoded50KB.equals(text50KB));
                System.out.println("Baseline Decoded Equals to The Text (50KB): "+baselineDecoder50KB.equals(text50KB));
	 
                
                //Huffman Encoder 200KB
                String encoded200KB=huffmanObj.huffmanEncoder(text200KB); 
                //Disply 200KB table
                System.out.println("\nFile 200KB");
                huffmanObj.display();
             
                //Decoder
                String decoded200KB=huffmanObj.huffmanDecoder(encoded200KB);
                
               
                // Baseline encoder 200KB
                String encodedBaseline200KB=baselineobj.baselineEncoder(text200KB);
                //Disply table 200KB 
                System.out.println("File 200KB");
                baselineobj.displayBaseline();
             
                String baselineDecoder200KB=baselineobj.baselineDecoder(encodedBaseline200KB);
                
                System.out.println("Huffman Decoded Equals to The Text (200KB): "+decoded200KB.equals(text200KB));
                System.out.println("Baseline Decoded Equals to The Text (200KB): "+baselineDecoder200KB.equals(text200KB));
	 
                //Huffman Encoder 1M
                String encoded1MB=huffmanObj.huffmanEncoder(text1MB); 
                //Disply 200KB table
                System.out.println("\nFile 1MB");
                huffmanObj.display();
                
                //Decoder
                String decoded1MB=huffmanObj.huffmanDecoder(encoded1MB);
                
               
                // Baseline encoder 200KB
                String encodedBaseline1MB=baselineobj.baselineEncoder(text1MB);
                //Disply table 200KB 
                System.out.println("File 1MB");
                baselineobj.displayBaseline();
              
                String baselineDecoder1MB=baselineobj.baselineDecoder(encodedBaseline1MB);
                
                System.out.println("Huffman Decoded Equals to The Text (1MB): "+decoded1MB.equals(text1MB));
                System.out.println("Baseline Decoded Equals to The Text (1MB): "+baselineDecoder1MB.equals(text1MB));
	 

        /* -------------------------------------------------------
         * Requirement 3: Performance Measurement
         * This section measures the performance of both
         * Huffman and Baseline encoding algorithms by:
         * - Reading different file sizes
         * - Measuring encoding and decoding time
         * - Calculating compressed size and compression ratio
         * - Storing the results in an ArrayList for reporting
         * ------------------------------------------------------- */

         // List of files to be tested (50KB, 200KB, 1MB)
        String[] files = {"file_50KB.txt", "file_200KB.txt", "file_1MB.txt"};

        // ArrayList to store all measurement entries
        List<Measurement> results = new ArrayList<>();

        // Loop through each file and evaluate both algorithms
        for (String fileName : files) {

            // Read the original text content from the file
            String originalText = new String(Files.readAllBytes(Path.of(fileName)));
            long originalSize = new File(fileName).length();

            // Display progress message for debugging/monitoring
            System.out.println("\n>>> Starting measurements for: " + fileName);

            /* =======================
             * 1) Huffman Encoding
             * ======================= */

            HuffmanAlg h = new HuffmanAlg();

            // Measure Huffman encoding time
            long startHuffEncode = System.nanoTime();
            String encodedHuff = h.huffmanEncoder(originalText);
            long endHuffEncode = System.nanoTime();
            double encodeHuffMs = (endHuffEncode - startHuffEncode) / 1_000_000.0;

            // Calculate compressed size in bytes
            long compressedHuffSize = (encodedHuff.length() + 7) / 8;

            // Measure Huffman decoding time
            long startHuffDecode = System.nanoTime();
            String decodedHuff = h.huffmanDecoder(encodedHuff);
            long endHuffDecode = System.nanoTime();
            double decodeHuffMs = (endHuffDecode - startHuffDecode) / 1_000_000.0;

            // Calculate compression ratio
            double ratioHuff = (double) compressedHuffSize / originalSize;

            // Store Huffman measurement entry
            results.add(new Measurement(
                    fileName,
                    "Huffman",
                    originalSize,
                    compressedHuffSize,
                    ratioHuff,
                    encodeHuffMs,
                    decodeHuffMs
            ));

            /* =======================
             * 2) Baseline Encoding
             * ======================= */

            baseline b = new baseline();

            // Measure Baseline encoding time
            long startBaseEncode = System.nanoTime();
            String encodedBase = b.baselineEncoder(originalText);
            long endBaseEncode = System.nanoTime();
            double encodeBaseMs = (endBaseEncode - startBaseEncode) / 1_000_000.0;

            // Baseline uses fixed 8 bits per character → convert to bytes
            long compressedBaseSize = (encodedBase.length() + 7) / 8;

            // Measure Baseline decoding time
            long startBaseDecode = System.nanoTime();
            String decodedBase = b.baselineDecoder(encodedBase);
            long endBaseDecode = System.nanoTime();
            double decodeBaseMs = (endBaseDecode - startBaseDecode) / 1_000_000.0;

            // Calculate compression ratio for baseline
            double ratioBase = (double) compressedBaseSize / originalSize;

            // Store Baseline measurement entry
            results.add(new Measurement(
                    fileName,
                    "Baseline",
                    originalSize,
                    compressedBaseSize,
                    ratioBase,
                    encodeBaseMs,
                    decodeBaseMs
            ));
        }

        /* --------------------------------------------------------
         * Print the results table from the ArrayList<Measurement>
         * This section formats and displays all collected metrics
         * -------------------------------------------------------- */
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.printf("%-12s %-10s %-15s %-18s %-18s %-12s %-12s%n",
                "File", "Method", "Original(B)", "Compressed(B)", "Compression_Ratio", "Encode(ms)", "Decode(ms)");
        System.out.println("----------------------------------------------------------------------------------------------");

        // Print each measurement entry in table format
        for (Measurement m : results) {
            System.out.printf("%-12s %-10s %-15d %-18d %-18.4f %-12.3f %-12.3f%n",
                    m.fileName, m.method, m.originalSize, m.compressedSize,
                    m.CompressionRatio, m.encodeTimeMs, m.decodeTimeMs);
        }

        System.out.println("----------------------------------------------------------------------------------------------");

        }
        /* --------------------------------------------------------
         * Measurement Class
         * Helper class used to store performance metrics for each
         * algorithm (Huffman, Baseline) and each file size
         * -------------------------------------------------------- */
        static class Measurement {
            String fileName;
            String method;
            long originalSize;
            long compressedSize;
            double CompressionRatio;
            double encodeTimeMs;
            double decodeTimeMs;

            public Measurement(String fileName, String method,
                               long originalSize, long compressedSize,
                               double CompressionRatio, double encodeTimeMs, double decodeTimeMs) {

                this.fileName = fileName;
                this.method = method;
                this.originalSize = originalSize;
                this.compressedSize = compressedSize;
                this.CompressionRatio = CompressionRatio;
                this.encodeTimeMs = encodeTimeMs;
                this.decodeTimeMs = decodeTimeMs;
            }
      } 

}

