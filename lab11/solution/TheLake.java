package solution;
import java.util.Stack;
public class TheLake {
    public int findTotalMaxDepth(int[][] input){
        int totalMaxDepth = 0;
        boolean visited[][] = new boolean[input.length][input[0].length];
        for(int i = 0; i < input.length; i++){
            for(int j = 0; j < input[0].length; j++){
                if(input[i][j] > 0 && !visited[i][j]){
                    int curDepth = DFS(input, i, j, visited);
                    totalMaxDepth = Math.max(totalMaxDepth, curDepth);
                }
            }
        }

        return totalMaxDepth;
    }

    private int DFS(int[][] input, int i, int j, boolean[][] visited){
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{i,j});
        int totalDepth = 0;

        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int x = current[0];
            int y = current[1];
            
            //to check index out of bound
            if (x < 0 || x >= input.length || y < 0 || y >= input[0].length || visited[x][y] || input[x][y] == 0) {
                continue;
            }

            visited[x][y] = true;
            totalDepth += input[x][y];

            for(int[] ele : directions){
                stack.push(new int[]{x + ele[0], ele[1] + y});
            }
        }

        return totalDepth;

    }
}


