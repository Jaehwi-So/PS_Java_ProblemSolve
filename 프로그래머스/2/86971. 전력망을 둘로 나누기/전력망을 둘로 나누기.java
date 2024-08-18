import java.util.*;

class Solution {
    static int[] parent;
    static int n;

    static void union(int k1, int k2){
        int p1 = find(k1);
        int p2 = find(k2);
        if(p1 > p2){
            parent[p1] = p2;
        }
        else if(p2 > p1){
            parent[p2] = p1;
        }
    }
    
    static int find(int k1){
        if(parent[k1] != k1){
            parent[k1] = find(parent[k1]);
        }
        return parent[k1];
    }
    
    static void initParent(){
        for(int i = 1; i <= n; i++){
            parent[i] = i;
        }
    }
    public int solution(int n, int[][] wires) {
        this.n = n;
        parent = new int[n+1];
        Map<Integer, Integer> map = new HashMap();
        
        int answer = Integer.MAX_VALUE;
        
        for(int i = 0; i < wires.length; i++){
            initParent();
            map.clear();
            for(int j = 0; j < wires.length; j++){
                if(i == j) continue;
                union(wires[j][0], wires[j][1]);
            }
            for(int j = 1; j <= n; j++){
                int k = find(j);
                if(!map.containsKey(k)){
                    map.put(k, 1);
                }
                else{
                    map.put(k, map.get(k) + 1);
                }
            }
            int first = -1;
            int second = -1;
            for(int key : map.keySet()){
                if(first == -1){
                    first = map.get(key);
                }
                else{
                    second = map.get(key);
                }
            }
            answer = Math.min(answer, Math.abs(first - second));
            
        }
        

        return answer;
    }
}