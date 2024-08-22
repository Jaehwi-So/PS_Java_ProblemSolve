import java.util.*;

class Solution {
    static int convert(String s){
        int time = Integer.parseInt(s.substring(0, 2));
        int minute = Integer.parseInt(s.substring(3, 5));
        return (time * 60) + minute;
    }
    public int solution(String[][] book_time) {
        int n = book_time.length;
        int[][] booking = new int[book_time.length][2];
        for(int i = 0; i < n; i++){
            booking[i][0] = convert(book_time[i][0]);
            booking[i][1] = convert(book_time[i][1]) + 10;
        }

        Arrays.sort(booking, new Comparator<int[]>(){
            public int compare(int[] t1, int[] t2){
                if(t1[0] == t2[0]) return t1[1] - t2[1];
                return t1[0] - t2[0];
            }
        });
        
        PriorityQueue<int[]> queue = new PriorityQueue(new Comparator<int[]>(){
            public int compare(int[] t1, int[] t2){
                return t1[1] - t2[1];
            }
        });
        
        for(int i = 0; i < n; i++){
            if(!queue.isEmpty()){
                int[] t = queue.peek();
                if(t[1] <= booking[i][0]){
                    queue.poll();
                    queue.offer(new int[]{t[0], booking[i][1]});
                }
                else{
                    queue.offer(booking[i]);
                }
            }
            else{
                queue.offer(booking[i]);
            }
        }
            
        int answer = queue.size();
        return answer;
    }
}