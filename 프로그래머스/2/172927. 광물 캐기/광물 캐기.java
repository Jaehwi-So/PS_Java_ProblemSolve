import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        List<int[]> list = new ArrayList();
        int[] min = new int[3];
        for(int i = 1; i <= minerals.length; i++){
            if(minerals[i-1].equals("diamond")){
                min[0] += 1;
                min[1] += 5;
                min[2] += 25;
            }
            if(minerals[i-1].equals("iron")){
                min[0] += 1;
                min[1] += 1;
                min[2] += 5;
            }
            if(minerals[i-1].equals("stone")){
                min[0] += 1;
                min[1] += 1;
                min[2] += 1;
            }
            
            if(i % 5 == 0){
                list.add(min);
                min = new int[3];
            }
        }
        list.add(min);
        
        int picksize = picks[0] + picks[1] + picks[2];
        while(list.size() > picksize){
            list.remove(list.size() - 1);
        }
        
        Collections.sort(list, new Comparator<int[]>(){
            public int compare(int[] n1, int[] n2){
                return n2[2] - n1[2];
            }
        });
        
        int result = 0;
        for(int[] m : list){
            for(int i = 0; i < 3; i++){
                if(picks[i] > 0){
                    result += m[i];
                    picks[i]--;
                    break;
                }
            }
        }
        
        return result;
    }
}