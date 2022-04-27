
// 广度优先遍历
// 从以为未遍历的节点出发，先遍历它的相邻节点，然后再遍历相邻节点的相邻节点，表现为一层一层的遍历
// 一般使用队列来操作，因为队列先进先出，适合广度遍历

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {
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
    private static void bfs(Node root){
        if(root==null){
            return;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);

        while (!queue.isEmpty()){
            Node node = queue.poll(); //这里需要新建Node，不然下面的左右子节点就只能写成 root.left/right，就错了(第三层没法遍历了)
            System.out.println(node.value);
            Node left = node.left;
            if (left!=null){
                queue.add(left);
            }
            Node right = node.right;
            if (right!=null){
                queue.add(right);
            }
        }
    }
}

// root节点先进队列，root出列，并且排入相邻节点 root.left、root.right，left、right分别出列并且排入他们的相邻节点进队列，没有则不排
// 需要注意的是，放入队列的时候需要新建 Node来存放当前节点和它的左右子节点，不然没有办法遍历