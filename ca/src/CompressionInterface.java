import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.nio.file.*;

/**
* The CompressionInterface class gives a user interface to the Huffman coding algorithm for compression. 
* @author Kate Belson
*/
public class CompressionInterface {
    
    /**
    * Calculates all the characters used in the file and the frequencies of these characters. 
    * @author Kate Belson
    * @param fileReader is the readable verion of the file used. 
    * @param characterFrequency is the HashMap of the characters and their frequencies. 
    * @return the completed version of the characterFrequency HashMap. 
	*/
    public static HashMap<Character, Integer> getCharacterFrequency(Scanner fileReader, HashMap<Character, Integer> characterFrequency) {
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            for (int i=0; i<data.length(); i++) {
                boolean added = false;
                for (Character j : characterFrequency.keySet()) {
                    if (data.charAt(i) == j) {
                        characterFrequency.put(j, characterFrequency.get(j) + 1);
                        added = true;
                    }
                }
                if (added == false) {
                    characterFrequency.put(data.charAt(i), 1);
                }
            }
        }
          return characterFrequency;
    }

    /**
	* A function that gets the binary string in bytes in a string. 
    * @author Kate Belson [ref Chico Camargo]
    * @param data is the data to return. 
	*/
    static byte[] GetBinary(String s) {
        while (s.length() % 8 != 0) {
            s = s + '0';
        }

        byte[] data = new byte[s.length() / 8];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '1') {
                data[i >> 3] |= 0x80 >> (i & 0x7);
            }
        }
        return data;
    }

    /**
	* A function that gets the binary string in bytes in a string. 
    * @author Kate Belson [ref Chico Camargo]
    * @param bytes is the string in bytes. 
	*/
    static String GetString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
        for (int i = 0; i < Byte.SIZE * bytes.length; i++)
            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
        return sb.toString();
    }

    /**
	* A function to save to a file. 
    * @author Kate Belson [ref Chico Camargo]
    * @param binaryString is the string to save to the file. 
    * @param outputName is the name of the outpt destination file. 
	*/
    public static void saveToFile(String binaryString, String outputName) {
        byte[] converted = GetBinary(binaryString);

        // Save bit array to file
        try {
            OutputStream outputStream = new FileOutputStream(outputName);
            outputStream.write(converted);
            outputStream.close();

        } catch (IOException e) {
            System.out.println("Error writing to file!");
        }
    }

    /**
	* A function to load the compressed file.  
    * @author Kate Belson [ref Chico Camargo]
    * @param outputName is the name of the ouput file. 
    * @return the string from the file. 
	*/
    public static String loadFromFile(String outputName) {
        // Load bit array from file
       try {
            byte[] allBytes = Files.readAllBytes(Paths.get(outputName));

            return GetString(allBytes);

        } catch (IOException ex) {
            return "An error has accoured.";
        }
    }

    /**
	* A function to compare the file sizes. 
    * @author Kate Belson
    * @param original is the name of the original file. 
    * @param compressed is the name of the compressed file. 
	*/
    public static void compareSizes (String original, String compressed) {
        File originalFile = new File(original);
        File compressedFile = new File(compressed);
        double originalSize = originalFile.length();
        double compressedSize = compressedFile.length();
        System.out.println("The original file's size is " + originalSize);
        System.out.println("The compressed file's size is " + compressedSize);
        double percentageReduction = ((originalSize - compressedSize) / originalSize) * 100;
        System.out.println("This is a reduction of " + percentageReduction + "%");
    }

    /**
	* The main function from which the program is run. 
    * @author Kate Belson
    * @param args is the arguments to run. 
	*/
    public static void main( String[] args ) {
        HashMap<Character, Integer> characterFrequency = new HashMap<Character, Integer>();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a file name, ending in .txt, to compress.");
        System.out.println("Please include the whole file path: ");
        String fileName = in.nextLine();
        try {
            File file = new File(fileName);
            Scanner fileReader = new Scanner(file);
            characterFrequency = getCharacterFrequency(fileReader, characterFrequency);
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred with reading the file.");
            System.out.println("Please restart the program.");
        }

        ArrayList<Node> tree = new ArrayList<Node>(); 

        for (Character i : characterFrequency.keySet()) {
            Node node = new Node(characterFrequency.get(i), i, null, null);
            tree.add(node);
        }

        int start = 0;
        int end = tree.size() - 1;
        ArrayList<Node> sortedTree = QuickSort.quickSort(tree, start, end);

        Node root = null;

        while (sortedTree.size() > 1) {

            Node right = sortedTree.get(0);
            sortedTree.remove(0);

            Node left = sortedTree.get(0);
            sortedTree.remove(0);

            Node f = new Node(right.getFrequency() + left.getFrequency(), '-', left, right);

            root = f;

            sortedTree.add(f); 
            sortedTree = QuickSort.quickSort(sortedTree, start, sortedTree.size() - 1);        
        }

        System.out.println("Compressing file...");
        Huffman huffmanCode = new Huffman(root);
        String binary = "";
        try {
            File file = new File(fileName);
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                for (int l=0; l<data.length(); l++) {
                    for (Character i : huffmanCode.relevantCodes.keySet()) {
                        if (i == data.charAt(l)) {
                            binary = binary + huffmanCode.relevantCodes.get(i);
                        }
                    }
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred with reading the file.");
            System.out.println("Please restart the program.");
        }
        System.out.println("Enter a file name for the compressed file.");
        System.out.println("To put it in a certain folder, include the file path, or it will be in the src folder.");
        System.out.println("It must end with .bin: ");
        String outputName = in.nextLine();
        saveToFile(binary, outputName);
        System.out.println("File compressed.");
        String fileString = loadFromFile(outputName);
        compareSizes(fileName, outputName);
        huffmanCode.decodeHuffman(sortedTree, fileString, in);
        in.close();
        
    }
}
