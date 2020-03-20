import org.antlr.runtime.tree.Tree;

import java.util.*;

public class JoinTree{
    public TreeNode root = null;

    public void JoinTree(HashMap<Integer, Set<Integer>> conn, int cls) {
        Set<Integer> temp = new HashSet<>();
        TreeNode cur = new TreeNode(-1);
        for (int i = 0; i < cls; i++) {
            if (temp.contains(i)) continue;
            TreeNode node = new TreeNode(-1);
            if (conn.containsKey(i)) {
                Set<Integer> edge = conn.get(i);
                Iterator<Integer> it = edge.iterator();
                int r = it.next();
                if (conn.containsKey(r)) {
                    while (it.hasNext()) {
                        int t = it.next();
                        if (!conn.containsKey(t))
                            r = t;
                    }
                }

                temp.add(i);
                temp.add(r);
                node.left = new TreeNode(i);
                node.right = new TreeNode(r);
            } else {
                node = new TreeNode(i);
                temp.add(i);
            }
            if (cur.left == null) {
                cur.left = node;
            }
            else if (cur.right == null) {
                cur.right = node;
            }
            else {
                TreeNode newCur = new TreeNode(-1);
                newCur.left = cur;
                newCur.right = node;
                cur = newCur;
            }
        }
        this.root = cur;
    }

    public void minhTree(int [] array) {
        LinkedList<TreeNode> nodeList = new LinkedList<>();

        for (int i = 0; i < array.length; i++) {
            nodeList.add(new TreeNode(array[i]));
        }

        for (int parentIndex = 0; parentIndex < array.length / 2; parentIndex++) {
            // left child
            nodeList.get(parentIndex).left = nodeList
                    .get(parentIndex * 2 + 1);
            // right child
            nodeList.get(parentIndex).right = nodeList
                    .get(parentIndex * 2 + 2);
        }
        this.root = nodeList.get(0);
    }

}

