package br.unisinos.esti.bst;

public class BinarySearchTree<K extends Comparable<K>, V>  implements BinarySearchTreeADT<K, V> {
	protected Node root;
	protected class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node next(K other) {
            return other.compareTo(key) < 0 ? left : right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }
        @Override
        public String toString() {
            return root == null ? "[empty]" : printTree(new StringBuffer());
        }

        private String printTree(StringBuffer sb) {
            if (root.right != null) {
                printTree(root.right, true, sb, "");
            }
            sb.append(root + "\n"); 
            if (root.left != null) {
                printTree(root.left, false, sb, "");
            }
                
            return sb.toString();
        }

        private void printTree(Node node, boolean isRight, StringBuffer sb, String indent) {
            if (node.right != null) {
                printTree(node.right, true, sb, indent + (isRight ? "        " : " |      "));
            }
            sb.append(indent + (isRight ? " /" : " \\") + "----- " + node + "\n"); 
            if (node.left != null) {
                printTree(node.left, false, sb, indent + (isRight ? " |      " : "        "));
            }
        }
	}



	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V search(K key) {
	    return search(root, key);
	}

	private V search(Node node, K key) {
	    if (node == null) {
	        return null;
	    } else if (key.compareTo(node.key) == 0) {
	        return node.value;
	    }
	    return search(node.next(key), key);
	}


	@Override
	public void insert(K key, V value) {
	    root = insert(root, key, value);
	}

	private Node insert(Node node, K key, V value) {
	    if (node == null) {
	        return new Node(key, value);
	    } else if (key.compareTo(node.key) > 0) {
	        node.right = insert(node.right, key, value);
	    } else if (key.compareTo(node.key) < 0) {
	        node.left = insert(node.left, key, value);
	    }

	    return node;
	}


	@Override
	public boolean delete(K key) {
	   return deleteByCopying(key);
	}

	
	private boolean deleteByCopying(K key) {
	    Node parent = null, current = root;
	    for (; current != null && key.compareTo(current.key) != 0; parent = current, current = current.next(key));
	    
	    if (current == null) 
	        return false;
	    else if (current.left != null && current.right != null) {
	        // Caso 3
	        Node tmp = current.left;     
	        while (tmp.right != null) tmp = tmp.right;
	        deleteByCopying(tmp.key); 
	        current.key = tmp.key; 
	    } else {
	        // Caso 1 ou Caso 2
	        Node nextNode = current.right == null ? current.left : current.right;
	        if (current.equals(root)) root = nextNode;
	        else if (parent.left.equals(current)) parent.left = nextNode;
	        else parent.right = nextNode;
	    }

	    return true;
	}


	@Override
	public void preOrder() {
	   preOrder(root);
	}

	private void preOrder(Node node) {
	   if (node != null) {
	       System.out.print(node + " ");
	       preOrder(node.left);
	       preOrder(node.right);
	   }
	}


	@Override
	public void inOrder() {
	    inOrder(root);
	}

	private void inOrder(Node node) {
	   if (node != null) {
	      inOrder(node.left);
	      System.out.print(node + " ");
	      inOrder(node.right);
	   }
	}


	@Override
	public void postOrder() {
	   postOrder(root);
	}

	private void postOrder(Node node) {
	   if (node != null) {
	     postOrder(node.left);
	     postOrder(node.right);
	     System.out.print(node + " ");
	   }
	}


	@Override
	public void levelOrder() {
		// TODO Auto-generated method stub
		
	}
	


}

