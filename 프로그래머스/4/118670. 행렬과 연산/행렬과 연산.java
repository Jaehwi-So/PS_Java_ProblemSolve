import java.util.*;

class Solution {
    static int n;
    static int m;
    
    static ArrayDeque<ArrayDeque<Integer>> queue;
    static ArrayDeque<Integer> lqueue;
    static ArrayDeque<Integer> rqueue;
    
    static void init(int[][] rc){
        queue = new ArrayDeque();
        lqueue = new ArrayDeque();
        rqueue = new ArrayDeque();

        for(int i = 0; i < n; i++){
            
            lqueue.addLast(rc[i][0]);
            
            ArrayDeque<Integer> inner = new ArrayDeque();
            for(int j = 1; j < m - 1; j++){
                inner.addLast(rc[i][j]);
            }
            queue.addLast(inner);
                
            rqueue.addLast(rc[i][m-1]);
            // System.out.println(queue[i]);
        }
        
        // System.out.println(lqueue);
        // System.out.println(rqueue);
    
    }
    
    static int[][] getResult(){
        
        int[][] matrix = new int[n][m];
        
        for(int i = 0; i < n; i++){
            matrix[i][0] = lqueue.pollFirst();
            matrix[i][m-1] = rqueue.pollFirst();
            ArrayDeque<Integer> inner = queue.pollFirst();
            for(int j = 1; j < m - 1; j++){
                matrix[i][j] = inner.pollFirst();
            }
        }
        
        return matrix;
    }
    
    static void shiftRow(){
        lqueue.addFirst(lqueue.pollLast());
        rqueue.addFirst(rqueue.pollLast());
        queue.addFirst(queue.pollLast());

    }
    
    
    static void rotate(){ 
        ArrayDeque<Integer> first = queue.pollFirst();
        ArrayDeque<Integer> last = queue.pollLast();
        
        first.addFirst(lqueue.pollFirst());
        rqueue.addFirst(first.pollLast());
        last.addLast(rqueue.pollLast());
        lqueue.addLast(last.pollFirst());
        
        queue.addFirst(first);
        queue.addLast(last);
    }
    
    
    public int[][] solution(int[][] rc, String[] operations) {
        n = rc.length;
        m = rc[0].length;
        init(rc);
        
        for(String operate : operations){
            if(operate.equals("Rotate")){
                rotate();
            }
            else{
                shiftRow();
            }
        }
        
        int[][] answer = getResult();
        // for(int[] line : answer){
        //     System.out.println(Arrays.toString(line));
        // }
        
        return answer;
    }
}

//2500000000