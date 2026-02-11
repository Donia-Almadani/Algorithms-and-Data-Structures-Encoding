# Algorithms-and-Data-Structures-Encoding

-Compare the efficiency of encoding text file using Huffman coding (Greedy algorithm) and fix-length encoding.
-Data Model: Text files with variety of sizes.
-Target: Implement and evaluate both encoding methods to
measure file size reduction, compression ratio, and
encoding/decoding runtime.
-Two algorithms implemented in Java:
*Huffman coding
*Baseline Fixed-length coding (8 bits)

---

### 1.Huffman Coding:
Calculate characters frequency (Priority Queue)⟶ Build
Huffman tree ⟶ Generate prefix-free codewords (left 0,
right 1) to each character ⟶ Decode using Huffman tree

### 2. Fixed-Length Encoding:
Assign 8-bits codes to all characters (ASCII) ⟶ Encode the
text ⟶ Decode the text using the same encoding technique

---

## Project Structure

- `RandomTextGenerator.java`  # Main class: Generates random text files of varying sizes (50 KB, 200 KB, 1 MB) containing random uppercase letters A-Z. Also contains the main method and implements the comparison.
- `HuffmanAlg.java`           # Huffman algorithm implementation class.
- `baseline.java`             # Fixed-length coding (8 bits) implementation.

---

## How to Run

1. Download all three files.
2. Run `RandomTextGenerator.java`, which contains the `main()` method.
3. The program will generate random text files and perform comparisons between Huffman and baseline fixed-length coding.

---

## CONCLUSION

*Huffman coding consistently achieves a compression ratio of
about 0.72 across all file sizes, corresponding to roughly a 28%
reduction in file size.
*Baseline fixed-length encoding keeps a constant ratio of 1.0,
meaning no compression.
*Huffman encoding time stays low even for large files.
Decoding time increases for very large inputs because of tree
traversal
*baseline encoding is straightforward but offers no improvement
in storage or bandwidth usage.
*Overall, Huffman is more effective and scalable, while baseline
is unsuitable for applications needing reduced storage or
optimized transmission.
