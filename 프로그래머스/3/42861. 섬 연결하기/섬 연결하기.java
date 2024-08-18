import java.util.*;

class Solution {
    static int[] parent;
    
    public boolean union(int k1, int k2){
        int n1 = find(k1);
        int n2 = find(k2);
        if(n1 > n2){
            parent[n1] = n2;
        }
        else if(n2 > n1){
            parent[n2] = n1;
        }
        else{
            return false;
        }
        return true;
    }
    
    public int find(int k){
        if(parent[k] != k){
            parent[k] = find(parent[k]);
        }
        return parent[k];
    }
    
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                return arr1[2] - arr2[2];
            }
        });
        parent = new int[n+1];
        for(int i = 1; i <= n; i++){
            parent[i] = i;
        }
        
        int result = 0;
        for(int[] cost : costs){
            if(union(cost[0], cost[1])){
                result += cost[2];
            }
        }


        return result;
    }
}