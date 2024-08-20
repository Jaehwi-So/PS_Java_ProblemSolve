import java.util.*;

class Word{
    int index;
    int step;
    public Word(int index, int step){
        this.index = index;
        this.step = step;
    }
}
class Solution {
    static int n;
    static boolean[][] matrix;
    static boolean[] visited;
    static int target;
    
    static boolean isConvert(String s1, String s2){
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        boolean iss = false;
        for(int i = 0; i < chs1.length; i++){
            if(chs1[i] != chs2[i]){
                if(iss) return false;
                else iss = true;
            }
        }
        return true;
    }
    
    static int bfs(){
        Word start = new Word(0, 0);
        Queue<Word> queue = new LinkedList();
        queue.offer(start);
        visited[0] = true;
        
        while(!queue.isEmpty()){
            Word current = queue.poll();
            if(current.index == target){
                return current.step;
            }
            for(int i = 0; i < n; i++){
                if(matrix[current.index][i] && !visited[i]){
                    visited[i] = true;
                    queue.offer(new Word(i, current.step + 1));
                }
            }
        }
        return 0;
    }
    
    public int solution(String begin, String target, String[] words) {
        n = words.length + 1;
        matrix = new boolean[n][n];
        visited = new boolean[n];
        
        for(int i = 0; i < words.length; i++){
            boolean k = isConvert(begin, words[i]);
            matrix[0][i+1] = k;
            matrix[i+1][0] = k;
            if(words[i].equals(target)){
                this.target = i + 1;
            }
            for(int j = i + 1; j < words.length; j++){
                boolean l = isConvert(words[j], words[i]);
                matrix[j+1][i+1] = l;
                matrix[i+1][j+1] = l;
            }
        }
        

        int answer = bfs();
        return answer;
    }
}