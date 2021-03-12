import java.util.ArrayList;
import java.util.HashMap;
import java.nio.file.*;
import java.io.*;
import java.util.Scanner;

/**
* The Huffman class for Huffman encoding and decoding. 
* @author Kate Belson
*/
public class Huffman {

    String binaryCode;

    String decompressedCode;

    HashMap<Character, String> relevantCodes;

    /**
	 * The constructor for the Huffman class. 
	 * @author Kate Belson
	 * @param root is the root of the tree. 
	 */
    public Huffman(Node root) {
        this.binaryCode = "";
        this.decompressedCode = "";
        this.relevantCodes = new HashMap<Character, String>();
        String binaryString = "";
        encodeHuffman(root, binaryString);
    }
    
    /**
    * Implements Huffman Coding to compress the file.   
    * @author Kate Belson
    * @param root is the root node of the tree. 
    * @param binaryString is the string of binary digits for that character's coding. 
	*/
    public void encodeHuffman(Node root, String binaryString) {        
        if (root.getLeft() == null && root.getRight() == null) {   
            relevantCodes.put(root.getCharacter(), binaryString);
            binaryString = "";
            return;
          }
          if (root.getLeft() != null) {
              encodeHuffman(root.getLeft(), binaryString + "0");
          }
          if (root.getRight() != null) {
              encodeHuffman(root.getRight(), binaryString + "1");
          }         
    }

    /**
    * Implements Huffman Decoding to decompress the file.   
    * @author Kate Belson
    * @param nodes is the tree structure for the type of encoding. 
    * @param codedString is the string of bits for the file. 
    * @param in is the scanner used to take user input. 
	*/
    public void decodeHuffman(ArrayList<Node> nodes, String codedString, Scanner in) {
        System.out.println("Enter a file name, ending in .txt, to put the decompressed file in.");
        System.out.println("To put it in a certain folder, include the file path, or it will be in the src folder.");
        System.out.println("It must end in .txt: ");
        String fileName = in.nextLine();
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
              System.out.println("File created: " + file.getName());
            } else {
              System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        Node root = nodes.get(0);
        nextBranch(root, codedString, root, fileName);
    }

    /**
    * Helps to implement the Huffman Decoding to decompress the file.   
    * @author Kate Belson
    * @param root is the root node of the tree. 
    * @param codedString is the string of binary digits for that character's coding. 
    * @param topNode is the constant root node of the tree. 
    * @param fileName is the name of the destination file. 
	*/
    public void nextBranch(Node root, String codedString, Node topNode, String fileName) {
        while (codedString != "") {
            while (root.getLeft() != null && root.getRight() != null) {
                if (root.getLeft() != null && codedString.charAt(0) == '0') {
                    nextBranch(root.getLeft(), codedString.substring(1), topNode, fileName);
                    return;
                }
                else if (root.getRight() != null && codedString.charAt(0) == '1') {
                    nextBranch(root.getRight(), codedString.substring(1), topNode, fileName);
                    return;
                }
            }
            try {               
                FileWriter writer = new FileWriter(fileName, true);
                PrintWriter printWriter = new PrintWriter(writer);
                printWriter.print(root.getCharacter());  
                printWriter.close();
                writer.close();
            } catch (IOException w) {
                System.out.println("An error occurred.");     
            }   
            nextBranch(topNode, codedString, topNode, fileName);
            return;
        }
    }
    
}
