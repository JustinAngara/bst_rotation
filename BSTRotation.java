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
    if(parent==null || child==null) {
      return;
    }
    
    // determine if we should rotate left or right
    boolean rotateLeft = parent.childRight().getData().equals(child.getData());
    if(rotateLeft) {
      parent.setChildRight(child.childLeft());
      child.setChildLeft(parent);
      
    } else if(parent.childLeft().getData().equals(child.getData())){
      parent.setChildRight(child.childLeft());
      child.setChildLeft(parent);
    } 
    
    
    BinaryTreeNode<T> grandparent = parent.parent(); // for later
    if(grandparent!=null && grandparent.childLeft().equals(parent)) {
      grandparent.setChildLeft(child);
    } else if(grandparent!=null) {
      grandparent.setChildRight(child);
    }
    
    child.setParent(grandparent);
    parent.setParent(child);
    
  }

  public static void main(String[] args) {
   
  }
}
