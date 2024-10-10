import java.util.*;

class Log implements Comparable<Log>{
    int start;
    int end;
    public Log(int start, int end){
        this.start = start;
        this.end = end;
    }
    
    public int compareTo(Log l){
        return this.start - l.start;
    }
}
class Solution {
    static Log convert(String s){
        int hour = Integer.parseInt(s.substring(11, 13));
        int minute = Integer.parseInt(s.substring(14, 16));
        int second = Integer.parseInt(s.substring(17, 19));
        int milisecond = Integer.parseInt(s.substring(20, 23));
        
        int length = (int)(Double.parseDouble(s.substring(24, s.length() - 1)) * 1000);
        
        int end = hour * (1000 * 60 * 60) +
            minute * (1000 * 60) +
            second * (1000) +
            milisecond;
        
        int start = end - length + 1;
        
        return new Log(start, end);
    }
    public int solution(String[] lines) {
        int n = lines.length;
        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;
        
        PriorityQueue<Log> inQ = new PriorityQueue();
        PriorityQueue<Log> outQ = new PriorityQueue(new Comparator<Log>(){
            public int compare(Log l1, Log l2){
                return l1.end - l2.end;
            }
        });

        for(int i = 0; i < n; i++){
            Log log = convert(lines[i]);
            start = Math.min(log.start, start);
            end = Math.max(log.end, end);
            inQ.offer(log);
        }
        
        
        int max = 0;
        for(int i = start; i <= end; i++){
            while(!inQ.isEmpty() && inQ.peek().start <= i){
                outQ.offer(inQ.poll());
            }
            max = Math.max(max, outQ.size());
            while(!outQ.isEmpty() && outQ.peek().end <= i - 999){
                outQ.poll();
            }
        }


        return max;
    }
}