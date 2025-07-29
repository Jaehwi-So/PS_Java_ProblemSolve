import java.util.*;

class Solution {
    
    static int n;
    
    static Queue<Integer>[] graph;
    
    static int search(int index){
        if(graph[index].isEmpty()){
            return index;
        }
        int next = graph[index].poll();
        graph[index].offer(next);
        return search(next);
    }
    
    static int[] calc(int count, int target){ // 4 5 -> 3 2
        int[] result = new int[4];
        while(count > 0){
            if(3 + count - 1 <= target){
                result[3]++;
                target-=3;
            }
            else if(2 + count - 1 <= target){
                result[2]++;
                target-=2;
            }
            else{
                result[1]++;
                target--;
            } 
            count--;
        }
        return result;
    }
    
    public int[] solution(int[][] edges, int[] target) {
        n = target.length;
        List<Integer>[] g = new ArrayList[n+1];
        graph = new LinkedList[n+1];
        
        for(int i = 1; i <= n; i++){
            g[i] = new ArrayList();
            graph[i] = new LinkedList();
        }
        
        for(int[] edge : edges){
            g[edge[0]].add(edge[1]);
        }
        
        for(int i = 1; i <= n; i++){
            Collections.sort(g[i]);
            for(Integer k : g[i]){
                graph[i].offer(k);
            }
        }
    
        
        int[] count = new int[n+1];
        List<Integer> order = new ArrayList();
        

        boolean success = true;
        while(true){
            int idx = search(1);
            count[idx]++;
            order.add(idx);
            
            if(count[idx] > target[idx-1]){
                success = false;
                break;
            }
            
            // 3개 = 3~9점
            boolean exit = true;
            for(int i = 1; i <= n; i++){
                if(!(count[i] <= target[i-1] && target[i-1] <= (count[i] * 3))){
                    exit = false;
                    break;
                }
            }
            if(exit) break;
        }
        
        if(!success) return new int[]{-1};
        
        
        int[][] numbers = new int[n+1][4];
        int[] answer = new int[order.size()];
        
        for(int i = 1; i <= n; i++){
            numbers[i] = calc(count[i], target[i-1]);
        }
        
        // System.out.println(Arrays.toString(count));
        // System.out.println(success);
        // System.out.println(order);
        
        for(int i = 0; i < answer.length; i++){
            int index = order.get(i);
            for(int j = 1; j <= 3; j++){
                if(numbers[index][j] > 0){
                    answer[i] = j;
                    numbers[index][j]--;
                    break;
                }
            }         
        }

        return answer;
    }
}