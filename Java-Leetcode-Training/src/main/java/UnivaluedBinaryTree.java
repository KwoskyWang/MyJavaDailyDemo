import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目: https://leetcode.cn/problems/univalued-binary-tree/solution/dan-zhi-er-cha-shu-by-leetcode-solution-15bn/
 * <p>
 * 说明:   如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 *        只有给定的树是单值二叉树时，才返回 true；否则返回 false。
 */

public class UnivaluedBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 我的解法: 变量太多,写法中规中矩
    public boolean isUnivalTree1(TreeNode root) {
        if(root == null){ return false;}
        int value = root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node;
            node = queue.poll();
            if(node.left != null){
                if(node.left.val != value){
                    return false;
                }else{
                    queue.offer(node.left);
                }
            }
            if(node.right != null){
                if(node.right.val != value){
                    return false;
                }else{
                    queue.offer(node.right);
                }
            }

        }
        return true;
    }

    // 官方解法:简短干净
    public boolean isUnivalTree2(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null) {
            if (root.val != root.left.val || !isUnivalTree2(root.left)) {
                return false;
            }
        }
        if (root.right != null) {
            if (root.val != root.right.val || !isUnivalTree2(root.right)) {
                return false;
            }
        }
        return true;
    }
}
