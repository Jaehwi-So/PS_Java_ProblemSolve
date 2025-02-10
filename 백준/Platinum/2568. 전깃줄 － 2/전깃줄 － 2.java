import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] line;
    static int[] dp;
    static int[] trace;
    static final int MAX = Integer.MAX_VALUE;
    static int binarySearch(int k){
        int left = 0;
        int right = n;
        while(left < right){
            int mid = (left + right) / 2;
            if(dp[mid] <= k){
                left = mid +1;
            }
            else{
                right = mid;
            }
        }
        return right - 1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        line = new int[n][3];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            line[i][0] = Integer.parseInt(st.nextToken());
            line[i][1] = Integer.parseInt(st.nextToken());
        }
        dp = new int[n+1];
        trace = new int[n+1];
        Arrays.fill(dp, MAX);
        dp[0] = 0;

        Arrays.sort(line, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int total = 0;
        for(int i = 0; i < n; i++){
            int max = binarySearch(line[i][1]) + 1;
            if(dp[max] > line[i][1]){
                dp[max] = line[i][1];
                trace[i] = max;
            }
            total = Math.max(total, max);
        }

        StringBuilder sb = new StringBuilder();
        int result = n - total;
        sb.append(result).append("\n");

//        System.out.println(Arrays.toString(trace));
//        System.out.println(Arrays.toString(dp));

        int seq = total;
        List<Integer> list = new ArrayList<Integer>();
        for(int i = n - 1; i >= 0; i--){
            if(trace[i] == seq){
                seq--;
            }
            else{
                list.add(line[i][0]);
            }
        }

        Collections.sort(list);
        for(int i : list){
            sb.append(i).append("\n");
        }
        System.out.println(sb);
    }
}