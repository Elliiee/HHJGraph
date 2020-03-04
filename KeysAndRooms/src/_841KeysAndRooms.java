import java.util.List;
import java.util.Stack;

public class _841KeysAndRooms {
    /*
    DFS
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms){
        boolean[] seen = new boolean[rooms.size()];
        seen[0] = true;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        while (!stack.isEmpty()){
            int node = stack.pop();
            for (int nei : rooms.get(node)){
                if (!seen[nei]){
                    seen[nei] = true;
                    stack.push(nei);
                }
            }
        }

        for (boolean v : seen){
            if (!v)
                return false;
        }

        return true;
    }
}

/*
There are N rooms and you start from room 0.
Each room has a distinct number from 0, 1, 2, ..., N - 1.
Each room may have some keys to access to the next room.
Each room i has a list of keys rooms[i].
Each key rooms[i][j] is an integer from [0, 1, ..., N - 1] where N = rooms.length.
A key rooms[i][j] = v opens the room with number v.
Initially, all the rooms are locked except the first one room 0.
You can walk back and forth freely.
Return true if and only if you can enter every room.
 */
