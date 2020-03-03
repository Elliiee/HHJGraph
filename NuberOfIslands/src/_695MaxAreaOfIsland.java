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
}

/*
Given a non-empty 2D array grid of 0's and 1's.
An island is a group of 1s connected 4-directionally (horizontal or vertical).
You may assume all 4 edges of the grid are surrounded by water.
Find the maximum area of an island in the given 2D array.
If there is no island, the maximum area is 0.
 */