import java.util.*;

class Solution
{
    static int n;
    static char[] sequence;
    static boolean[][] visited;
    static int max;
    
    static void operate(int start, int end){
        if(visited[start][end]) return;
        if(start == end){
            visited[start][end] = true;
            return;
        }
        
        // System.out.println("Operate " + start + " " + end);
        int mid = (start + end) / 2;
        int left = mid - 1;
        int right = mid + 1;
        
        boolean isPalindrome = true;
        
        if((end - start) % 2 == 1){ //짝수개
            if(right > end) return;
            if(sequence[mid] != sequence[right]) isPalindrome = false;
            
            visited[mid][right] = true;
            if(isPalindrome) max = Math.max(max, right - mid + 1);
            right++;
        }
        
        while(left >= start && right <= end){
            if(sequence[left] != sequence[right]) isPalindrome = false;
            visited[left][right] = true;
            
            if(isPalindrome) max = Math.max(max, right - left + 1);
            left--;
            right++;
        }
    }
    
    
    
    public int solution(String s)
    {
        n = s.length();
        sequence = s.toCharArray();
        visited = new boolean[n][n];
        max = 1;
        
        for(int step = n-1; step >= 1; step--){
            for(int i = 0; i < n-step; i++){
                // System.out.println(i + " " + step);
                operate(i, i+step);
                
            }
        }
        
        
        int answer = max;

        return answer;
    }
}