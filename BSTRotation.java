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

  public static void main(String[] args) {
   
  }
}
