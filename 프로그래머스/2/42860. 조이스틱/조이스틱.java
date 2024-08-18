import java.util.*;

class Solution {
    static int[] cnt = new int[26];
    
    static int parse(char c){
        return cnt[c - 'A'];
    }
    public int solution(String name) {
        int p = 1;
        for(int i = 1; i <= cnt.length / 2; i++){
            cnt[i] = p;
            cnt[cnt.length - i] = p;
            p++;
        }
        
        char[] chs = name.toCharArray();
        
        int answer = 0;
        
        int length = chs.length;
        int cnt = length - 1;

        // A B C D E
        for(int i = 0; i < length; i++){
            answer += parse(chs[i]);
            
            int nextIdx = i+1;
            
            while(nextIdx < length && chs[nextIdx] == 'A'){
                nextIdx++;
            }
            
            cnt = Math.min(cnt, i + (length - nextIdx) + Math.min(i, length - nextIdx));
                       
        }
        
        answer += cnt;

        return answer;
    }
}