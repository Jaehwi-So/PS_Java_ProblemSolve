import java.util.*;

class Solution {
    static String[] user_id;
    static String[] banned_id;
    static int n;
    static boolean[] visited;
    static boolean[] contains;
    static int result = 0;
    static Set<String> set = new HashSet();
    
    static boolean match(String id, String secId){
        char[] chs1 = id.toCharArray();
        char[] chs2 = secId.toCharArray();
        
        if(chs1.length != chs2.length) return false;
        
        for(int i = 0; i < chs1.length; i++){
            if(chs2[i] == '*') continue;
            if(chs1[i] != chs2[i]) return false;
        }
        
        return true;
    }
    
    static void dfs(int sequence, int count){
        if(sequence == user_id.length){
            if(count == n){
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < sequence; i++){
                    if(contains[i]){
                        sb.append(user_id[i]);
                    }
                }
                String key = sb.toString();
                
                if(!set.contains(key)){
                    set.add(key);
                    result++;
                }
            }
        }
        else{
            // "frodo"를 match되는 banned_id에 할당 or 할당X
            for(int i = 0; i < n; i++){
                if(!visited[i] && match(user_id[sequence], banned_id[i])){
                    visited[i] = true;
                    contains[sequence] = true;
                    dfs(sequence + 1, count + 1);
                    visited[i] = false;
                    contains[sequence] = false;
                }
            }
            
            dfs(sequence + 1, count);
            
        }        
    }
    
    
    public int solution(String[] user_id, String[] banned_id) {
        this.user_id = user_id;
        this.banned_id = banned_id;
        n = banned_id.length;
        visited = new boolean[n];
        contains = new boolean[user_id.length];
        
        dfs(0, 0);
        int answer = 0;
        return result;
    }
}