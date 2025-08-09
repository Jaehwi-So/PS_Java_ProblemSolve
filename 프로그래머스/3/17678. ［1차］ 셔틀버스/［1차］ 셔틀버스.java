import java.util.*;

class Solution {
    
    static int convert(String s){
        String time = s.substring(0, 2);
        String minute = s.substring(3, 5);
        
        return Integer.parseInt(time) * 60 + Integer.parseInt(minute);
    }
    
    static String reverse(int k){
        
        
        String time = Integer.toString(k / 60);
        String minute = Integer.toString(k % 60);
        StringBuilder sb = new StringBuilder();
        if(time.length() == 1) sb.append("0");
        sb.append(time);
        sb.append(":");
        if(minute.length() == 1) sb.append("0");
        sb.append(minute);
        
        return sb.toString();
    }
    
    public String solution(int n, int t, int m, String[] timetable) {
        int len = timetable.length;
        int[] time = new int[len];
        
        for(int i = 0; i < len; i++){
            time[i] = convert(timetable[i]);
        }
        Arrays.sort(time);
        // System.out.println(Arrays.toString(time));
        
        int index = 0;
        int current = 540;
        int max = 0;
        int gcnt = 0;
        Queue<Integer> queue = new LinkedList();
        for(int i = 0; i < n; i++){
            while(index < len && time[index] <= current){
                queue.offer(time[index]);
                index++;
            }
            
            int count = 0;
            while(!queue.isEmpty()){
                if(count >= m - 1 || gcnt >= (n*m) - 1) break;
                queue.poll();
                count++;
                gcnt++;
            }
            
            if(queue.isEmpty()){
                max = current;
            }
            else{
                int k = queue.poll();
                gcnt++;
                max = k - 1;
            }
            
            // System.out.println(reverse(current) + " : " + reverse(max));
            
            if(gcnt == (n*m)) break;
            current += t;
        }
        
        // System.out.println(max);
        
        String answer = reverse(max);
        return answer;
    }
}



// 25 * 12
// 
/**
n = 3, t = 1, m = 2, timetable = ["06:00", "23:59", "05:48", "00:01", "00:01"], Return = "09:02"
n = 10, t = 25, m = 1, timetable = ["09:00", "09:10", "09:20", "09:30", "09:40", "09:50", "10:00", "10:10", "10:20", "10:30", "10:40", "10:50"], Return = "10:29"
n = 2, t = 10, m = 2, timetable = ["09:10", "09:10", "09:10"], Return = "09:09"
n = 2, t = 5, m = 1, timetable = ["09:05", "09:05"], Return = "09:04"
n = 1, t = 1, m = 1, timetable = ["09:00", "09:05"], Return = "08:59"
n = 1, t = 1, m = 1, timetable = ["08:55"], Return = "08:54"
**/


/**
import java.util.*;

class Solution {
    
    static int convert(String s){
        String time = s.substring(0, 2);
        String minute = s.substring(3, 5);
        
        return Integer.parseInt(time) * 60 + Integer.parseInt(minute);
    }
    
    static String reverse(int k){
        
        
        String time = Integer.toString(k / 60);
        String minute = Integer.toString(k % 60);
        StringBuilder sb = new StringBuilder();
        if(time.length() == 1) sb.append("0");
        sb.append(time);
        sb.append(":");
        if(minute.length() == 1) sb.append("0");
        sb.append(minute);
        
        return sb.toString();
    }
    
    public String solution(int n, int t, int m, String[] timetable) {
        int len = timetable.length;
        int[] time = new int[len];
        
        for(int i = 0; i < len; i++){
            time[i] = convert(timetable[i]);
        }
        Arrays.sort(time);
        // System.out.println(Arrays.toString(time));
        
        int index = 0;
        int current = 540;
        int max = 0;
        Queue<Integer> queue = new LinkedList();
        for(int i = 0; i < n; i++){
            while(index < len && time[index] <= current){
                queue.offer(time[index]);
                index++;
            }
            
            int count = 0;
            while(!queue.isEmpty()){
                queue.poll();
                count++;
                if(count >= m - 1) break;
            }
            
            if(queue.isEmpty()){
                max = current;
            }
            else{
                int k = queue.poll();
                max = k - 1;
            }
            

            current += t;
        }
        
        // System.out.println(max);
        
        String answer = reverse(max);
        return answer;
    }
}

**/