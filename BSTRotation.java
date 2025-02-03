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
      
      if (parent == null || child == null) {
          throw new NullPointerException("Parent or child cannot be null.");
      }

      // Ensure child is a direct left or right child of parent
      boolean rotateLeft = parent.childRight() != null && parent.childRight().equals(child);
      boolean rotateRight = parent.childLeft() != null && parent.childLeft().equals(child);

      if (!rotateLeft && !rotateRight) {
          throw new IllegalArgumentException("The provided child is not a direct left or right child of the parent.");
      }

      // Perform rotation
      if (rotateLeft) { // Left rotation: child (right) moves up
          parent.setChildRight(child.childLeft());
          if (child.childLeft() != null) {
              child.childLeft().setParent(parent);
          }
          child.setChildLeft(parent);
      } else { // Right rotation: child (left) moves up
          parent.setChildLeft(child.childRight());
          if (child.childRight() != null) {
              child.childRight().setParent(parent);
          }
          child.setChildRight(parent);
      }

      // Update grandparent references
      BinaryTreeNode<T> grandparent = parent.parent();
      if (grandparent != null) {
          if (grandparent.childLeft() == parent) {
              grandparent.setChildLeft(child);
          } else {
              grandparent.setChildRight(child);
          }
      } else {
          // If parent was root, update the root reference
          root = child;
      }

      // Update parent references
      child.setParent(grandparent);
      parent.setParent(child);
  }

  public boolean test1() {
    BSTRotation<Integer>  tree= new BSTRotation<>();
    
    tree.insert(15);
    tree.insert(12);
    tree.insert(16);
    tree.insert(13);
    BinaryTreeNode<Integer> parent = tree.root; // Root node (15)
    BinaryTreeNode<Integer> child = tree.root.childRight();; // Right child (16)
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
    return true;
  }
  public static void main(String[] args) {
    BSTRotation<Integer>  test= new BSTRotation<>();
    boolean test1 = test.test1();
    System.out.println("rotate: " + (test1 ? "PASS" : "FAIL"));

  }
}
