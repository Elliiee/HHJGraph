import java.util.Stack;

public class _695MaxAreaOfIsland {
    /*Approach 1
    DFS
    To ensure we don't count squares in a shape more than once,
    we will use "seen" to keep track of squares we haven't visited before.
     */
    int[][] grid;
    boolean[][] seen;

    public int area(int r, int c){
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || seen[r][c] || grid[r][c] == 0)
            return 0;
        seen[r][c] = true;
        return (1 + area(r+1, c) + area(r-1, c) + area(r, c+1) + area(r, c-1));
    }

    public int maxAreaOfIsland(int[][] grid){
        this.grid = grid;
        seen = new boolean[grid.length][grid[0].length];
        int ans = 0;
        for (int r = 0; r < grid.length; r++){
            for (int c = 0; c < grid[0].length; c++)
                ans = Math.max(ans, area(r, c));
        }
        return ans;
    }

    /*
    Approach 2
    DFS
    Iteration by using a stack
     */
    public int maxAreaOfIslandDFSIteration(int[][] grid){
        boolean[][] seen = new boolean[grid.length][grid[0].length];
        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};

        int ans = 0;
        for (int r0 = 0; r0 < grid.length; r0++){
            for (int c0 = 0; c0 < grid.length; c0++){
                if (grid[r0][c0] == 1 && !seen[r0][c0]){
                    int shape = 0;
                    Stack<int[]> stack = new Stack<>();
                    stack.push(new int[]{r0, c0});
                    seen[r0][c0] = true;
                    while (!stack.isEmpty()){
                        int[] node = stack.pop();
                        int r = node[0], c = node[1];
                        shape++;
                        for (int k = 0; k < 4; k++){
                            int nr = r + dr[k];
                            int nc = c + dc[k];
                            if (0 <= nr && nr < grid[0].length && grid[nr][nc] == 1 && !seen[nr][nc]){
                                stack.push(new int[]{nr, nc});
                                seen[nr][nc] = true;
                            }
                        }
                    }
                    ans = Math.max(ans, shape);
                }
            }
        }
        return ans;
    }
}

/*
Given a non-empty 2D array grid of 0's and 1's.
An island is a group of 1s connected 4-directionally (horizontal or vertical).
You may assume all 4 edges of the grid are surrounded by water.
Find the maximum area of an island in the given 2D array.
If there is no island, the maximum area is 0.
 */