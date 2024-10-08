import java.util.*;

class Solution {
    static List<int[]> distances = new ArrayList();
    static int d;
    static boolean[] visited;
    static int[] origin;
    static int[] distance;
    static void getDist(int step){
        if(step == d){
            int[] temp = Arrays.copyOf(distance, d);
            distances.add(temp);
        }
        else{
            for(int i = 0; i < d; i++){
                if(!visited[i]){
                    visited[i] = true;
                    distance[step] = origin[i];
                    getDist(step + 1);
                    distance[step] = 0;
                    visited[i] = false;
                }
            }
        }
    }
    public int solution(int n, int[] weak, int[] dist) {
        int k = weak.length;
        int m = k * 2;
        int[] walls = new int[m];
        for(int i = 0; i < k; i++){
            walls[i] = weak[i];
        }
        for(int i = k; i < m; i++){
            walls[i] = n + weak[i - k];
        }
        
        origin = dist;
        d = dist.length;
        distance = new int[d];
        visited = new boolean[d];
        getDist(0);
        
        
        int result = Integer.MAX_VALUE;
        
        for(int[] distance : distances){
            for(int start = 0; start < k; start++){
                int index = 0;
                int current = distance[index];
                int range = 0;
                boolean success = true;
                for(int i = start + 1; i < start + k; i++){
                    int next = walls[i] - walls[i-1];;
                    if(current < range + next){
                        index++;
                        if(index >= d){
                            success = false;
                            break;
                        }
                        range = 0;
                        current = distance[index];
                    }
                    else{
                        range += next;
                    }               
                }

                if(success){
                    result = Math.min(result, index);
                }
            }
        }
        
        if(result == Integer.MAX_VALUE){
            return -1;
        }
        return result + 1;
        

    }
}
