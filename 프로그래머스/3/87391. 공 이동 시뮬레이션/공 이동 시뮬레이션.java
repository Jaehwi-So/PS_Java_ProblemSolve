import java.util.*;

class Solution {
    static int n;
    static int m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    
    public int[] calc(int start, int end, int range, int n){
        int ns = n;
        int ne = -1;     

        if(range < 0){ //감소 범위 찾기
            for(int s = start; s <= end; s++){
                if(s + range >= 0){
                    ns = s + range;
                    ne = end + range;
                    break;
                } 
            }
            if(end == n - 1){
                ne = end;
                ns = Math.max(0, Math.min(ns, end + range));
            }
        }
        
        //3 5 4 6
        
        else if(range > 0){ //증가 범위 찾기
            for(int e = end; e >= start; e--){
                if(e + range < n){
                    ns = start + range;
                    ne = e + range;
                    break;
                } 
            }
            if(start == 0){
                ns = start;
                ne = Math.min(n-1, Math.max(ne, start + range));
            }
        }
        
        // System.out.println(start + " " + end + " " + range + " " + n);
        // System.out.println(ns + " " + ne);
        return new int[]{ns, ne};
        
    }
    
    public long solution(int n, int m, int x, int y, int[][] queries) {
        
        this.n = n;
        this.m = m;
        
        
        int startX = y;
        int startY = x;
        int endX = y;
        int endY = x;
        
        long answer = 0;
    
        
        for(int i = queries.length - 1; i >= 0; i--){
            int direct = queries[i][0];
            int range = queries[i][1];
            
            if(direct == 0 || direct == 1){
                int[] next = calc(startX, endX, (range * dx[direct] * -1), m);
                if(next[0] >= m || next[1] < 0) return 0;
                
                startX = next[0];
                endX = next[1];
            }
            else{
                int[] next = calc(startY, endY, (range * dy[direct] * -1), n);
                if(next[0] >= n || next[1] < 0) return 0;
                
                startY = next[0];
                endY = next[1];
            }
            
        }
        
        answer = (long)(endX - startX + 1) * (endY - startY + 1);
        
       
        
        
        return answer;
    }
}