/**
* The Node class creates a node for a given tree. 
* @author Kate Belson
*/

public class Node { 

    private int frequency;

    private char character;

    private Node left;

    private Node right;

    /**
	 * The constructor for the Node class. 
	 * @author Kate Belson
	 * @param frequency is the frequency of the node. 
	 * @param character is the character represented. 
     * @param left is the node to the left. 
     * @param right is the node to the right. 
	 */
	public Node (int frequency, char character, Node left, Node right) {
        setFrequency(frequency);
        setCharacter(character);
        setLeft(left);
        setRight(right);
    }

    //setter methods 

	/**
	 * Sets the frequency variable. 
	 * @author Kate Belson
	 * @param frequency is the frequency of the node. 
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

    /**
	 * Sets the character variable. 
	 * @author Kate Belson
	 * @param character is the character represented by the node. 
	 */
	public void setCharacter(char character) {
		this.character = character;
	}

    /**
	 * Sets the left variable. 
	 * @author Kate Belson
	 * @param left is the node to the left of this node. 
	 */
	public void setLeft(Node left) {
		this.left = left;
	}

    /**
	 * Sets the right variable. 
	 * @author Kate Belson
	 * @param right is the node to the right of this node. 
	 */
	public void setRight(Node right) {
		this.right = right;
	}

    //getter methods 

    /**
	 * Returns the frequency of the node. 
	 * @author Kate Belson
	 * @return the frequency of the node. 
	 */
	public int getFrequency() {
		return this.frequency;
	}

    /**
	 * Returns the character represented by the node. 
	 * @author Kate Belson
	 * @return the character represented by the node. 
	 */
	public char getCharacter() {
		return this.character;
	}

    /**
	 * Returns the node to the left of this node. 
	 * @author Kate Belson
	 * @return the node to the left of this node.
	 */
	public Node getLeft() {
		return this.left;
	}

    /**
	* Returns the node to the right of this node. 
	* @author Kate Belson
	* @return the node to the right of this node.
	*/
	public Node getRight() {
		return this.right;
	}
    
}