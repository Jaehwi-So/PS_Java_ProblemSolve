import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] matrix;
    static short[][] dp;
    static int[][] score;

    static int transform(char c){
        switch(c){
            case 'A' : return 0;
            case 'B' : return 1;
            case 'C' : return 2;
            case 'D' : return 3;
            case 'F' : return 4;
            default: return -1;
        }
    }

    /**
     oox--
     --
     */
    static short operate(int index, int visited){ // 00001 ->
        if(index >= n*m) return 0;
        if(dp[index][visited] == -1){
//            System.out.println(index + " " + visited);
            if((visited & 1) == 0){
                if((visited & (1 << 1)) == 0 && (index % m) < m - 1){ //옆
                    int a = matrix[index / m][index % m];
                    int b = matrix[(index + 1) / m][(index + 1) % m];
                    int next = ((visited >> 1) | 1); // 11010 -> 01101
                    dp[index][visited] = (short) Math.max(dp[index][visited], score[a][b] + operate(index + 1, next));
                }
                if((index / m) < n - 1){ //아래
                    int a = matrix[index / m][index % m];
                    int b = matrix[(index + m) / m][(index + m) % m];
                    int next = (visited >> 1) | (1 << m - 1);
                    dp[index][visited] = (short) Math.max(dp[index][visited], score[a][b] + operate(index + 1, next));
                }
            }

            int next = (visited >> 1); // 비우기
            dp[index][visited] = (short) Math.max(dp[index][visited], operate(index + 1, next));
        }
        return dp[index][visited];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        matrix = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            char[] chs = st.nextToken().toCharArray();
            for(int j = 0; j < m; j++){
                matrix[i][j] = transform(chs[j]);
            }

        }

        dp = new short[n*m][(int)Math.pow(2, m)];
        for(short[] line : dp){
            Arrays.fill(line, (short) -1);
        }

        score = new int[5][5];
        score[0] = new int[]{10, 8, 7, 5, 1};
        score[1] = new int[]{8, 6, 4, 3, 1};
        score[2] = new int[]{7, 4, 3, 2, 1};
        score[3] = new int[]{5, 3, 2, 2, 1};
        score[4] = new int[]{1, 1, 1, 1, 0};

        System.out.println(operate(0, 0));

    }
}