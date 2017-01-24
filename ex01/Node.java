
/**Node of a singly linked list of strings*/
public class Node {

    private String element; //we assume that elements are character strings
    private Node next;
	
    /**default constructor*/
    public Node(){this(null,null);}
	
    /** Creates a node with the given element 
     * and next node */
    public Node(String s, Node n){element = s; next = n;}	
	
    /** Returns the element of this node */
    public String getElement(){return element;}
	
    /** Returns the next node of this node. */
    public Node getNext(){return next;}

    /** Sets the element of this node */
    public void setElement(String s){element = s;}
	
    /** Sets the next field of this node. */
    public void setNext(Node node){next = node;}

    public String toString(){return element;}

    public int compareTo(String s){return element.compareTo(s);}
	
}


