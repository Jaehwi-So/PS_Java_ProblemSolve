import java.util.*;

class Solution {
    
    static String[][] list;
    static boolean[] visited;
    static String[] temp;
    static String[] result;
    static boolean success = false;
    
    static void run(String start, int step){
        if(step == list.length && !success){
            for(int i = 0; i < temp.length; i++){
                result[i] = temp[i];
            }
            success = true;
            return;
        }
        else{
            for(int i = 0; i < list.length; i++){
                if(!visited[i] && list[i][0].equals(start)){
                    visited[i] = true;
                    temp[step + 1] = list[i][1];
                    run(list[i][1], step + 1);
                    visited[i] = false;
                }
            }
        }
    }
    
    public String[] solution(String[][] tickets) {
        
        list = tickets;
        visited = new boolean[list.length];
        temp = new String[list.length + 1];
        result = new String[list.length + 1];

        
        Arrays.sort(list, new Comparator<String[]>(){
            public int compare(String[] s1, String[] s2){
                return s1[1].compareTo(s2[1]);
            }
        });
    
        temp[0] = "ICN";
        run("ICN", 0);
        
        String[] answer = result;
        return answer;
    }
}