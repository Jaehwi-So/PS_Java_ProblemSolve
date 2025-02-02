import java.io.*;
import java.util.*;

class Ball implements Comparable<Ball>{
    int index;
    int color;
    int weight;
    public Ball(int index, int color, int weight){
        this.index = index;
        this.color = color;
        this.weight = weight;
    }

    public int compareTo(Ball b){
        return this.weight - b.weight;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Ball[] balls = new Ball[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            balls[i] = new Ball(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(balls);
        long[] result = new long[n];
        long[] P = new long[200001];
        long sum = 0;

        int next = 0;
        for(int i = 0; i < n; i++){
            while(next < n && balls[next].weight < balls[i].weight){
                P[balls[next].color] += balls[next].weight;
                sum += balls[next].weight;
                next++;
            }
            result[balls[i].index] = sum - P[balls[i].color];
        }
        StringBuilder sb = new StringBuilder();
        for(long i : result){
            sb.append(i).append("\n");
        }
        System.out.println(sb);
    }
}