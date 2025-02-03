/*
 * this is a binary search tree class that will deal with functional methods to search for stuff and insert nodes
 * */
public class BinarySearchTree<T extends Comparable<T>> implements SortedCollection<T>{
  // create instance variables
  protected BinaryTreeNode<T> root;
  private int size;
  
  /*
   * constructor method that initialzies the iv
   * */
  public BinarySearchTree() {
    // init variables
    root = null;
    size =0;
  }
  /*
   * insert a node in the appropriate place in the tree
   * @param data which is the node
   * @return void
   * */
  @Override
  public void insert(T data) throws NullPointerException {
    // TODO Auto-generated method stub
    // base case
    if(data==null) {
      return;
    }
    
    // tmep variable to place new node in loc
    BinaryTreeNode<T> t = new BinaryTreeNode<>(data);
    if(root==null) {
      root = t;
    }else {
      insertHelper(t, root);
    }
    
    size++;   
    
  }
  
  /**
   * Performs the naive binary search tree insert algorithm to recursively
   * insert the provided newNode (which has already been initialized with a
   * data value) into the provided tree/subtree.  When the provided subtree
   * is null, this method does nothing. 
   */
  protected void insertHelper(BinaryTreeNode<T> newNode, BinaryTreeNode<T> subtree) {
    // TODO: define and make use of this method in BinarySearchTree class
    
    // first check if the subtree should either be in the right subtree
    if(newNode.getData().compareTo(subtree.getData()) > 0) {
      // found an avaliable spot to put node
      if(subtree.childRight()==null) {
        newNode.setParent(subtree);
        subtree.setChildRight(newNode);
      } else {
        //keep searching
        insertHelper(newNode, subtree.childRight());
      }
    }
    
    
    // check the left subtree
    if(newNode.getData().compareTo(subtree.getData()) <= 0) {
      // find the next avaliable place
      if(subtree.childLeft()==null) {
        // since it's not populated yet, set up the pointers to each other
        newNode.setParent(subtree);
        subtree.setChildLeft(newNode);
      } else {
        // keep searching
        insertHelper(newNode, subtree.childLeft());
      }
    }
  }

  /*
   * return t/f depending if data is inside the binary tree
   * @param data, the data we want to search for
   * @return a boolean if it has the node that matches the data 
   * */
  @Override
  public boolean contains(Comparable<T> data) {
    // TODO Auto-generated method stub
    return containHelper(data, root);
  }
  
  /*
   * helper method for the recursion search
   * @param data to search for the thang
   * @param node basically keeping track of where we are in the binary tree
   * @return a boolean if it contains it
   * */
  private boolean containHelper(Comparable<T> data, BinaryTreeNode<T> node) {
    // base case 
    if(node==null) {
      return false;
    }
    
    // see if the data is equal
    if(data.compareTo(node.getData()) == 0) {
      return true;
    }
    
    // go to the right subtree
    if(data.compareTo(node.getData())>0) {
      return containHelper(data, node.childRight());
    }
    
    // go to the left subtree
    return containHelper(data, node.childLeft());
  }
  /*
   * getter method for size
   * @return int ofo the size of the binary tree
   */
  @Override
  public int size() {
    // TODO Auto-generated method stub
    return size;
  }
  /*
   * getter method to check if a binary tree is empty
   * @return t/f  if it is empty
   * */
  @Override
  public boolean isEmpty() {
    // TODO Auto-generated method stub
    return size==0;
  }
  
  /*
   * mutator method 
   * basically reinits everything since there rae no longerp ointers
   * */
  @Override
  public void clear() {
    // TODO Auto-generated method stub
    root = null; // no longer has a pointer to another node whilst removing the root
    size = 0;
  }
  
  /*
   * tester method to see if insert works
   * @return boolean if it passes
   * */
  public boolean test1() {
    
    BinarySearchTree<Integer> t = new BinarySearchTree<Integer>();
    t.insert(2);
    t.insert(1);
    t.insert(3);
    
    // should be balanced
    return t.root.getData()==2&&t.root.left.getData()==1&&t.root.right.getData()==3;
  }
  
  /*
   * tester method to see if contains works
   * @return boolean if it passes
   * */
  public boolean test2() {
    BinarySearchTree<String> t = new BinarySearchTree<String>();
    t.insert("beta");
    t.insert("alpha");
    t.insert("gamma");
    
    return !t.contains("delta") && t.contains("alpha") && t.contains("beta") && t.contains("gamma");
  }
  /*
   * tester method to see if size and clear works
   * @return boolean if it passes
   * */
  public boolean test3() {
    BinarySearchTree<String> t = new BinarySearchTree<String>();
    t.insert("beta");
    t.insert("alpha");
    t.insert("gamma");
    int size = t.size();
    t.clear();
    return size==3 && t.size()==0;
  }
  public static void main(String[] args) {
    BinarySearchTree<Integer> t = new BinarySearchTree<Integer>();//could be whatever type we don't care
    boolean t1 = t.test1();
    boolean t2 = t.test2();
    boolean t3 = t.test3();
    System.out.println("test1: "+t1);
    System.out.println("test2: "+t2);
    System.out.println("test3: "+t3);
  }
}
