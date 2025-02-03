public class BSTRotation<T extends Comparable<T>> extends BinarySearchTree<T> {
  
  /**
   * Performs the rotation operation on the provided nodes within this tree.
   * When the provided child is a left child of the provided parent, this
   * method will perform a right rotation. When the provided child is a right
   * child of the provided parent, this method will perform a left rotation.
   * When the provided nodes are not related in one of these ways, this
   * method will either throw a NullPointerException: when either reference is
   * null, or otherwise will throw an IllegalArgumentException.
   *
   * @param child is the node being rotated from child to parent position
   * @param parent is the node being rotated from parent to child position
   * @throws NullPointerException when either passed argument is null
   * @throws IllegalArgumentException when the provided child and parent
   *     nodes are not initially (pre-rotation) related that way
   */
  protected void rotate(BinaryTreeNode<T> child, BinaryTreeNode<T> parent)
    throws NullPointerException, IllegalArgumentException {
    // added base case for null pointer
    if(parent==null || child==null) {
      return;
    }
   
    // determine if we should rotate left or right
    boolean rotateLeft = parent.childRight()==child;
    boolean rotateRight = parent.childLeft()==child;
   
    // block of if statements ensures that we have the correct pointers to the nodes
    if(rotateLeft) {
     
      // move the left subtree to the new right subtree
      parent.setChildRight(child.childLeft());
     
      // update the parent reference to the correct parent
      if(child.childLeft()!=null) {
        child.childLeft().setParent(parent);
      }
     
      // make the child the new parent
      child.setChildLeft(parent);
     
    } else if(rotateRight){
     
      // basically same logic
      parent.setChildLeft(child.childRight());
      if(child.childRight()!=null) {
        child.childRight().setParent(parent);
      }
      child.setChildRight(parent);
    }
   
   
   
    BinaryTreeNode<T> grandparent = parent.parent();
    // basically assign the grandparent's node to the 'new parent' aka child
    if(grandparent!=null && grandparent.childLeft()==parent) {
      grandparent.setChildLeft(child);
    } else if(grandparent!=null && grandparent.childRight() == parent) {
      grandparent.setChildRight(child);
    } else {
      // update the root reference to point to the child after the rotation
      root = child;
    }
   
    // create the pointers
    child.setParent(grandparent);
    parent.setParent(child);
   
  }


  /*
   * left rotation tests for root and non root 
   */
  public boolean test1() {
    BSTRotation<Integer>  tree= new BSTRotation<>();
    
    tree.insert(15);
    tree.insert(12);
    tree.insert(16);
    tree.insert(13);
    BinaryTreeNode<Integer> parent = tree.root; // Root node (15)
    BinaryTreeNode<Integer> child = tree.root.childRight(); // Right child (16)
    tree.rotate(child, parent);
    if(tree.root!=child) { // root!=child
     
      return false;
    }else if(tree.root.left != parent) {
      return false;
    }else if(!tree.root.left.left.getData().equals(12)) {
      System.out.println("failed here");
      return false;
    }else if(!tree.root.left.left.right.getData().equals(13)) {
      System.out.println("failed here");
      return false;
    }
BSTRotation<Integer>  tree1= new BSTRotation<>();
    
    tree1.insert(15);
    tree1.insert(12);
    tree1.insert(11);
    tree1.insert(16);
    tree1.insert(13);
    BinaryTreeNode<Integer> parent1 = tree1.root.childLeft(); // Root node (15)
    BinaryTreeNode<Integer> child1 = parent1.childRight(); // Right child (16)
    tree1.rotate(child1, parent1);
    if(!tree1.root.getData().equals(15)) { // root!=child
     
      return false;
    }else if(tree1.root.left != child1) {
      return false;
    }else if(tree1.root.left.left != parent1) {
      System.out.println("failed here");
      return false;
    }else if(!tree1.root.left.left.left.getData().equals(11)) {
      System.out.println("failed here");
      return false;
    }
    return true;
  }
  /*
   * right rotation tests for root and non root 
   */
  public boolean test2() {
    BSTRotation<Integer>  tree= new BSTRotation<>();
    
    tree.insert(15);
    tree.insert(12);
    tree.insert(16);
    tree.insert(13);
    BinaryTreeNode<Integer> parent = tree.root; // Root node (15)
    BinaryTreeNode<Integer> child = tree.root.childLeft(); // Right child (16)
    tree.rotate(child, parent);
    if(tree.root!=child) { // root!=child
     
      return false;
    }else if(tree.root.right != parent) {
      return false;
    }else if(!tree.root.right.right.getData().equals(16)) {
      System.out.println("failed here");
      return false;
    }else if(!tree.root.right.left.getData().equals(13)) {
      System.out.println("failed here");
      return false;
    }
BSTRotation<Integer>  tree1= new BSTRotation<>();
    
    tree1.insert(15);
    tree1.insert(12);
    tree1.insert(11);
    tree1.insert(16);
    tree1.insert(13);
    BinaryTreeNode<Integer> parent1 = tree1.root.childLeft(); // Root node (15)
    BinaryTreeNode<Integer> child1 = parent1.childLeft(); // Right child (16)
    tree1.rotate(child1, parent1);
    if(!tree1.root.getData().equals(15)) { // root!=child
     
      return false;
    }else if(tree1.root.left != child1) {
      return false;
    }else if(tree1.root.left.right != parent1) {
      System.out.println("failed here");
      return false;
    }else if(!tree1.root.left.right.right.getData().equals(13)) {
      System.out.println("failed here");
      return false;
    }
    return true;
  }
  public static void main(String[] args) {
    BSTRotation<Integer>  test= new BSTRotation<>();
    boolean test1 = test.test1();
    System.out.println("rotate right: " + (test1 ? "PASS" : "FAIL"));
    boolean test2 = test.test2();
    System.out.println("rotate left: " + (test1 ? "PASS" : "FAIL"));


  }
}
