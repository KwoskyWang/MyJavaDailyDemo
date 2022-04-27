import java.util.ArrayList;
import java.util.List;

// 题目地址：https://leetcode-cn.com/problems/pacific-atlantic-water-flow/

public class PacificAtlantic {
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[][] heights;
    int m, n;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.heights = heights;
        this.m = heights.length;
        this.n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(i, 0, pacific);
        }
        for (int j = 1; j < n; j++) {
            dfs(0, j, pacific);
        }
        for (int i = 0; i < m; i++) {
            dfs(i, n - 1, atlantic);
        }
        for (int j = 0; j < n - 1; j++) {
            dfs(m - 1, j, atlantic);
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> cell = new ArrayList<Integer>();
                    cell.add(i);
                    cell.add(j);
                    result.add(cell);
                }
            }
        }
        return result;
    }

    public void dfs(int row, int col, boolean[][] ocean) {
        if (ocean[row][col]) {
            return;
        }
        ocean[row][col] = true;
        for (int[] dir : dirs) {
            int newRow = row + dir[0], newCol = col + dir[1];
            if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && heights[newRow][newCol] >= heights[row][col]) {
                dfs(newRow, newCol, ocean);
            }
        }
    }
}

// 暴力解法
// 1.找到一个方法判断，任意一个点是否可以同时有路径到达: 岛屿长m，宽n, 左边界[?][0]/上边界[0][？] 和 右边界[?][m-1]/下边界[?][n-1], 通过递归该点的上下左右四个方向是否可以流动(是否大于它),来尝试触达边界。
// 2.然后遍历数组，套用 1)中的方法
// 3.满足条件的点就加入我们的 result里，遍历完成之后，返回

// 深度遍历 dfs(如题)、广度遍历 bfs

// 笔记:
// 1. 既然雨水是从高到低流，那么可以想象这是地图上的一条山脊，如果通过每个元素遍历，那么单元格会被重复遍历，因为只有走到边界时才能确定是否能够到达边界。但是如果反推，从边界开始往高处计算，那么从一开始就可以给路径进行标记，如此就可以去掉重复的部分。
// 2. 在一个二维数组的场景里，想要遍历 上下左右四个点，只要同样借助一个二位数组就可以很方便的操作：int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}}, for(int[] dir:dirs){ row += dir[0]; col += dir[1] } 就可以得到上下左右四个点
//    再配合边界值，和其他限制条件，既可以实现递归
// 3. 深度遍历和广度遍历是解图类问题的基础算法，需要牢记。