import java.io.*;
import java.util.*;

public class Main {
    static long[][][] dp = new long[1002][1002][2]; //처음에 위치 & 끝에 위치
    static int[] array = new int[1002];
    static long[] prefixSum = new long[1002];

    static long sum(int start, int end){
        return prefixSum[end] - prefixSum[start - 1];
    }

    static void calc(int start, int end, int pos, long amount){
        if(dp[start][end][pos] >= amount){
            dp[start][end][pos] = amount;

            int current = 0;
            if(pos == 0) current = start;
            else current = end;

            for(int i = start - 1; i >= 1; i--){
                if(array[i] > 0){
                    long elec = Math.abs(i - current) * (sum(1, 1001) - sum(start, end));
                    // System.out.println(String.format("(%d, %d) -> (%d, %d) : %d, dist : %d, current : %d, pos : %d", start, end, i, end, elec, Math.abs(i - current), current, pos));
                    calc(i, end, 0, amount + elec);
                    break;
                }
            }
            for(int i = end + 1; i <= 1001; i++){
                if(array[i] > 0){
                    long elec = Math.abs(i - current) * (sum(1, 1001) - sum(start, end));
                    // System.out.println(String.format("(%d, %d) -> (%d, %d) : %d, dist : %d, current : %d, pos : %d", start, end, start, i, elec, Math.abs(i - current), current, pos));
                    calc(start, i, 1, amount + elec);
                    break;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int minSeq = Integer.MAX_VALUE;
        int maxSeq = Integer.MIN_VALUE;
        int start = 0;
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            int seq = Integer.parseInt(st.nextToken()) + 1;
            int amount = Integer.parseInt(st.nextToken());
            array[seq] = amount;
            minSeq = Math.min(minSeq, seq);
            maxSeq = Math.max(maxSeq, seq);
            if(i == m){
                start = seq;
            }
        }

        for(int i = 1; i <= 1001; i++){
            prefixSum[i] = prefixSum[i-1] + array[i];
        }

        for(long[][] mat : dp){
            for(long[] line : mat){
                Arrays.fill(line, Long.MAX_VALUE);
            }
        }

        calc(start, start, 0, 0);
        long result = Math.min(dp[minSeq][maxSeq][0], dp[minSeq][maxSeq][1]);
        if(result == Long.MAX_VALUE){
            System.out.println(0);
        }
        else{
            System.out.println(result);
        }
    }
}