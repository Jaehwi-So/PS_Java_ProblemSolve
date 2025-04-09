import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static List<Integer>[] childs;
    static int[] dp;

    static int operate(int index){
        if(dp[index] == -1){
            int max = 0;

            List<Integer> childTimes = new ArrayList<>();
            for(int child : childs[index]){
                childTimes.add(operate(child));
            }
            Collections.sort(childTimes, Collections.reverseOrder());

            for(int i = 0; i < childTimes.size(); i++){
                max = Math.max(max, childTimes.get(i) + i + 1);
            }

            dp[index] = max;
        }
        return dp[index];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        childs = new ArrayList[n];
        dp = new int[n];
        Arrays.fill(dp, -1);

        for(int i = 0; i < n; i++){
            childs[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int parent = Integer.parseInt(st.nextToken());
            if(parent != -1) childs[parent].add(i);
        }
        System.out.println(operate(0));
//        System.out.println(Arrays.toString(dp));
    }
}