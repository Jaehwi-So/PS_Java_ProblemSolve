import java.util.*;
class Time implements Comparable<Time>{
    int start;
    int end;
    public Time(int start, int end){
        this.start = start;
        this.end = end;
    }
    public int compareTo(Time t){
        return this.start - t.start;
    }
    public String toString(){
        return this.start + ":" + this.end;
    }
}
class Solution {
    static int convert(String timestamp){
        int hour = Integer.parseInt(timestamp.substring(0, 2));
        int min = Integer.parseInt(timestamp.substring(3, 5));
        int sec = Integer.parseInt(timestamp.substring(6, 8));
        return (hour * 60 * 60) + (min * 60) + sec;
    }
    public String solution(String play_time, String adv_time, String[] logs) {
        
        Time[] times = new Time[logs.length];
        for(int i = 0; i < logs.length; i++){
            times[i] = new Time(convert(logs[i].substring(0, 8)), convert(logs[i].substring(9, 17)));
        }
        Arrays.sort(times);
        int total = convert(play_time);
        int advTime = convert(adv_time);
        
        PriorityQueue<Time> pq = new PriorityQueue<Time>(new Comparator<Time>(){
            public int compare(Time t1, Time t2){
                return t1.end - t2.end;
            }
        });
        
        int index = 0;
        int[] count = new int[total + 1];
        
        for(int i = 0; i <= total; i++){
            while(index < times.length && times[index].start == i){
                pq.offer(times[index]);
                index++;
            }
            while(!pq.isEmpty() && pq.peek().end == i){
                pq.poll();
            }
            count[i] = pq.size();
        }
        
        long[] dp = new long[total + 1];
        
        dp[0] = count[0];
        for(int i = 1; i <= total; i++){
            dp[i] = dp[i-1] + count[i];
        }
        
        int start = 0;
        long max = dp[advTime - 1];

        for(int i = 1; i <= total - advTime; i++){
            long current = dp[i + advTime - 1] - dp[i-1];
            
            if(max < current){
                max = current;
                start = i; 
            }
        }
        
        // dp[4]  = 길이 : 4, 시작점 0 00:00 ~ 00:05
        // dp[5] - dp[0] = 길이 : 4, 시작점 1 00:01 ~ 00:06
        // dp[6] - dp[1] = 길이 : 4, 시작점 2


        int hour = 0;
        int minute = 0;
        int second = 0;
        
        if(start > 0) hour = (start / 3600);
        if((start % 3600) > 0) minute = (start % 3600) / 60;
        if((start % 60) > 0) second = (start % 60);
        
        StringBuilder sb = new StringBuilder();
        if(hour < 10) sb.append("0");
        sb.append(hour);
        sb.append(":");
        if(minute < 10) sb.append("0");
        sb.append(minute);
        sb.append(":");
        if(second < 10) sb.append("0");
        sb.append(second);
        
                
        String answer = sb.toString();
        return answer;
    }
}

//60 * 60 * 100 = 360000 300000
// 100000000000

// 1. 시작 순서로 정렬.
// 2. 끝나는 순서로 PQ에 삽입.
// 3. 시간마다 PQ의 원소 수를 추가.
// 4. 시간 배열에 현재 시간의 큐(사용자수) 기록
// 5. 시간 배열의 누적합을 통해서 구간합 중 최대값 구하기