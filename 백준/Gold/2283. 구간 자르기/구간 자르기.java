import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int k;
    static int[][] lines;
    static final int MAX = 1000000;
    static int getLength(int idx, int start, int end){
        int sum = end - start;
        if(start <= lines[idx][0] && end <= lines[idx][0]) return 0;
        if(start >= lines[idx][1] && end >= lines[idx][1]) return 0;
        if(start < lines[idx][0]) sum -= (lines[idx][0] - start);
        if(end > lines[idx][1]) sum -= (end - lines[idx][1]);
        return sum;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        lines = new int[n][2];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 1;

        while(right <= MAX){
            int sum = 0;
            for(int i = 0; i < n; i++){
                int append = getLength(i, left, right);
                sum += append;
            }
//            System.out.println(sum + " : " + left + " " + right);

            if(sum == k){
                System.out.println(left + " " + right);
                return;
            }
            else if(sum > k){
                left++;
                if(left == right) right = left + 1;
            }
            else{
                right++;
            }
        }
        System.out.println("0 0");
    }
}