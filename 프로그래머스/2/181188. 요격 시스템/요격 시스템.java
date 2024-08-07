import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        
        Arrays.sort(targets, new Comparator<int[]>(){
            @Override
            public int compare(int[] arr, int[] arr2){
                if(arr[0] == arr2[0]){
                    return arr[1] - arr2[1];
                }
                return arr[0] - arr2[0];
            }
        });
        
        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;
        
        for(int i = 0; i < targets.length; i++){
            start = Math.min(start, targets[i][0]);
            end = Math.max(end, targets[i][1]);
        }
        
        PriorityQueue<int[]> queue = new PriorityQueue(new Comparator<int[]>(){
            @Override
            public int compare(int[] arr, int[] arr2){
                return arr[1] - arr2[1];
            }
        });
        
        int index = 0;
        int count = 0;
        
        for(int i = start; i <= end; i++){
            while(index < targets.length && targets[index][0] <= i){
                queue.offer(targets[index]);
                index++;
            }
            
            if(!queue.isEmpty() && queue.peek()[1] - 1 == i){
                // System.out.println(queue.peek()[1] - 1 + " " + i);
                queue.clear();
                count++;
            }
            

        }
        
        return count;
    }
}