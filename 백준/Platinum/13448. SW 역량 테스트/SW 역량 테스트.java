import java.io.*;
import java.util.*;

class Source implements Comparable<Source>{
    int score;
    int cost; //P
    int time; //R
    public Source(int score, int cost, int time) {
        this.score = score;
        this.cost = cost;
        this.time = time;
    }

    public int compareTo(Source s){
        double origin = this.time / (double)this.cost;
        double comp = s.time / (double)s.cost;
        if(origin < comp) return -1;
        else if(origin == comp) return 0;
        else return 1;
    }
}
public class Main {
    static int n;
    static int t;
    static Source[] sources;
    static long[][] dp;

    static long solve(int index, int time){
        if(dp[index][time] == -1){
            long cost = (long)sources[index].cost * (time + sources[index].time);
            if(index == n){
                if(sources[index].score > cost && time + sources[index].time <= t){
                    dp[index][time] = sources[index].score - (int)cost;
                }
                else{
                    dp[index][time] = 0;
                }
            }
            else{
                if(sources[index].score > cost && time + sources[index].time <= t){
                    dp[index][time] = Math.max(dp[index][time],
                            sources[index].score - (int)cost + solve(index + 1, time + sources[index].time));
                }
                dp[index][time] = Math.max(dp[index][time], solve(index + 1, time));
            }
        }
        return dp[index][time];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        sources = new Source[n+1];
        st = new StringTokenizer(br.readLine());
        sources[0] = new Source(0, 100001, 0);
        for(int i = 1; i <= n; i++){
            sources[i] = new Source(Integer.parseInt(st.nextToken()), 0, 0);
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            sources[i].cost = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            sources[i].time = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sources);

//        for(int i = 1; i <= n; i++){
//            System.out.print(sources[i].score + " ");
//        }
//        System.out.println();

        dp = new long[n+1][t+1];
        for(long[] line : dp) Arrays.fill(line, -1);

        System.out.println(solve(1, 0));
        
    }
}