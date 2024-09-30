import java.util.*;

class Solution {
    static int[][] dice;
    static int[] visited;
    static int n;
    static int gLength;
    static int max = Integer.MIN_VALUE;
    static int[] answer;
    static List<int[]> cases = new ArrayList();
    static int[] cas;
    
    
    static int binarySearch(int[] array, int number){
        int left = 0;
        int right = array.length - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(array[mid] >= number){
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }
        return right;
    }
    static void getCase(int step){
        if(step == gLength){
            int[] c = Arrays.copyOf(cas, gLength);
            cases.add(c);
        }
        else{
            for(int i = 0; i < 6; i++){
                cas[step] = i;
                getCase(step + 1);
                cas[step] = 0;
            }
        }
    }
    
    static void calc(int step, int index){
        if(step == gLength){
            int[] home = new int[cases.size()];
            int[] bottom = new int[cases.size()];
            int[] hIdx = new int[gLength];
            int[] bIdx = new int[gLength];
            int h = 0;
            int b = 0;
            for(int i = 0; i < n; i++){
                if(visited[i] == 1){
                    hIdx[h] = i;
                    h++;
                }
                else{
                    bIdx[b] = i;
                    b++;
                }
            }
            h = 0;
            b = 0;
            for(int i = 0; i < cases.size(); i++){
                int hNum = 0;
                int bNum = 0;
                for(int j = 0; j < gLength; j++){
                    bNum += dice[bIdx[j]][cases.get(i)[j]];
                    hNum += dice[hIdx[j]][cases.get(i)[j]];
                }
                home[i] = hNum;
                bottom[i] = bNum;
            }
            
            int result = 0;
            Arrays.sort(bottom);
            for(int i = 0; i < cases.size(); i++){
                result += binarySearch(bottom, home[i]) + 1;
            }
            if(result > max){
                max = result;
                for(int i = 0; i < gLength; i++){
                    answer[i] = hIdx[i] + 1;
                }
            }
            
            
            // System.out.println(Arrays.toString(visited));
            // System.out.println(Arrays.toString(home));
            // System.out.println(Arrays.toString(bottom));
            
            
        }
        else{
            for(int i = index; i < n; i++){
                if(visited[i] == 0){
                    visited[i] = 1;
                    calc(step + 1, index + 1);
                    visited[i] = 0;
                } 
            }
        }
    }
    
    
    public int[] solution(int[][] dice) {
        this.dice = dice;
        this.n = dice.length;
        this.visited = new int[n];
        this.gLength = dice.length / 2;
        this.answer = new int[gLength];
        this.cas = new int[gLength];
        getCase(0);

        calc(0, 0);
        

        return answer;
    }
}

// 2^10 1024
// 30 