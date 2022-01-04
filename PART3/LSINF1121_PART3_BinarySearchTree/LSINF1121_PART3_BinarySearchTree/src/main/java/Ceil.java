
public class Ceil {
	/**
	 * Find in the tree the smallest element greater than or equal to value
	 * (so either the element itself or the element located directly after it
	 * in order of magnitude). If such an element does not exist,
	 * it must return null.
	 *
	 * Inserez votre reponse ici
	 */
	public static Integer ceil(Node root, int value) {
		if (root == null){
			return null;
		}
		else if (root.getValue() == value){
			return root.getValue();
		}
		else if (root.getValue() < value){
			return ceil(root.getRight(),value);
		}
		else{
			if (ceil(root.getLeft(),value) != null){
				return ceil(root.getLeft(),value);
			}
			else {
				return root.getValue();
			}
		}
    }
}
