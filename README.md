  
# Huffman Coding Compression Program

## Introduction

This program implements the Huffman Coding compression algorithm in order to reduce the size of a text file. It is written in Java and allows the user to choose what file to compress, as well as working out the percentage reduction in size of the file. 

## Prerequisites

Developed in Java 15.0.2 2021-01-19. 

Before running the program, the user must download the text files they want to compress in the res (resources) folder. Each file must end in .txt and you will need to know the file path to run the program. These are the files I used in development: 

 - [Romeo and Juliet in English](https://www.gutenberg.org/files/1513/1513-0.txtt)
 - [Romeo and Juliet in French](https://www.gutenberg.org/cache/epub/18143/pg18143.txt) 
 - [Romeo and Juliet in German](https://www.gutenberg.org/cache/epub/6996/pg6996.txt) 
 - [Alice in Wonderland in English](https://www.gutenberg.org/files/11/11-0.txt)
 - [Alice in Wonderland in French](https://www.gutenberg.org/ebooks/55456) 
 - [Alice in Wonderland in German](https://www.gutenberg.org/cache/epub/19778/pg19778.txt)
 - [Oliver Twist in English](https://www.gutenberg.org/files/730/730-0.txt)
 - [Oliver Twist in French](https://www.gutenberg.org/files/61994/61994-0.txt)
 - [Oliver Twist in German](https://www.gutenberg.org/ebooks/56586)
 - [Large Data Set 1](http://pizzachili.dcc.uchile.cl/repcorpus/artificial/fib41.7z)
 - [Large Data Set 2](http://pizzachili.dcc.uchile.cl/repcorpus/pseudo-real/dna.001.1.7z)
 - [Large Data Set 3](http://pizzachili.dcc.uchile.cl/repcorpus/real/cere.7z)

The user also needs the correct version of Java installed. 

## Installation

There is no prior instillation required for this program, other than the correct version of Java and the JVM. 

## Getting Started 

Use command line to call the Java compiler (from the directory src in huffman) to compile the program CompressionInterface.java. Then use the Java command to run it. Because the program deals with large files, you need to increase the stack size when running by using the flag -Xss515m. An example of this is below. Follow the instructions given to you in the command line once the program runs, and if the program crashes please restart it as this means you have entered an incorrect input. 

```bash
cd huffman\src
javac CompressionInterface.java
java -Xss515m CompressionInterface
```

## Developer Documentation
Classes: 
1. CompressionInterface.java - this class provides the interface from which the compression program is run. 
	Functions: 
	- getCharacterFrequency(Scanner  fileReader, HashMap<Character, Integer> characterFrequency) - this function works out the frequency of each unique character in the file 
	- GetBinary(String  s) - this function gets the binary string in bytes in a string
	- GetString(byte[] bytes) - this function gets the binary string in bytes in a string
	- saveToFile(String  binaryString, String  outputName) - this function saves to a file 
	- loadFromFile(String  outputName) - this function loads from a compressed file
	- compareSizes (String  original, String  compressed) - this function compares the sizes of the compressed file against the original 
	- main( String[] args ) - the main function from which the program is run 
2. Node.java - this class creates a node for a given tree. 
      Functions:
	- Node (int  frequency, char  character, Node  left, Node  right) - constructor for the node 
	- setFrequency(int  frequency) - sets the frequency variable 
	- setCharacter(char  character) - sets the character variable 
	- setLeft(Node  left) - sets the node to the left branch in the tree 
	- setRight(Node  right) - sets the node to the right branch in the tree 
	- getFrequency() - returns the frequency variable 
	- getCharacter() - returns the character variable 
	- getLeft() - returns the left variable
	- getRight() - returns the right variable 
3. Huffman.java - this class is for encoding and decoding using the Huffman coding algorithm 
	Functions:
	- Huffman(Node  root) - the constructor for the Huffman class 
	- encodeHuffman(Node  root, String  binaryString) - compresses using Huffman. 
	- decodeHuffman(ArrayList$<$Node$>$ nodes, String  codedString, Scanner  in) - decompresses using Huffman 
	- nextBranch(Node  root, String  codedString, Node  topNode, String  fileName) - used recursively in the decompression algorithm 
4.  QuickSort.java - this class implements the Quicksort sorting algorithm. 
	Functions:
	- partition(ArrayList$<$Node$>$ tree, int  start, int  end) - this function is used to split and sort the ArrayList from smallest to largest values frequencies of the nodes
	- quickSort(ArrayList$<$Node$>$ tree, int  start, int  end) - implements the Quicksort algorithm 

For more information please look at the attached JavaDocs. 

## Literature Review and Testing

For information on the data structures and algorithms used, a pdf of the source files, a weekly log of work, as well as a referenced literature review of compression algorithms and a performance analysis of this program, please read the file titled LiteratureReview.pdf in the huffman\doc\LITERATURE_REVIEW folder. 

For a video of the algorithm working, please look in the huffman\doc\TESTING folder. 

## Authors 

- Kate Frances Belson (Undergraduate Student studying BSc Computer Science at the University of Exeter)

## Handle

https://github.com/kfb19/huffman-coding

## Publish Date 

- Version 0.0.1 was published on 12/03/2021

## License
[MIT](https://choosealicense.com/licenses/mit/)

Please look in the huffman\doc\LICENSE folder for more information. 
