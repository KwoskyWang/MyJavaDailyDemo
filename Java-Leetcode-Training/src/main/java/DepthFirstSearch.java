
// 相关地址：https://developer.51cto.com/article/614590.html
// 说明：深度遍历
// 例子：前序遍历、中序遍历、后序遍历 都属于深度遍历 dfs
// 场景：拓扑排序、寻路(走迷宫)、搜索引擎、爬虫

// 写法解释: 从根节点开始,入栈,然后通过while来控制代码节奏(非空时弹出栈顶元素,判断并入栈其右/左节点,然后继续弹出栈顶...)即可实现深度遍历Jproject

import java.util.Stack;

public class DepthFirstSearch {
    // 定义节点
    private static class Node {
        /**
         * 节点值
         */
        public int value;
        /**
         * 左节点
         */
        public Node left;
        /**
         * 右节点
         */
        public Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    // 递归写法：容易栈溢出
    public static void recursionDfs(Node treeNode){
        if (treeNode==null){
            return;
        } // 先序遍历；如果是中序后序遍历，只需要移动遍历当前节点的位置顺序即可
        System.out.println(treeNode.value);
        recursionDfs(treeNode.left);
        recursionDfs(treeNode.right);
    }
    // 非递归写法：通过控制压栈顺序来控制遍历顺序
    public static void recursionUnDfs(Node root){
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<Node>();
        // 先把根节点压栈
        stack.push(root);
        while (!stack.isEmpty()) {
            Node treeNode = stack.pop();
            // 遍历节点
            System.out.println(treeNode.value);

            // 先压右节点
            if (treeNode.right != null) {
                stack.push(treeNode.right);
            }

            // 再压左节点
            if (treeNode.left != null) {
                stack.push(treeNode.left);
            }
        }
    }
}
