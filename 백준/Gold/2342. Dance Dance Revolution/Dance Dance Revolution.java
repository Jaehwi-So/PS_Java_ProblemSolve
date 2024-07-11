import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final int max = 1000000;
    static int getEnergy(int current, int next){
        if(current == 0){
            return 2;
        }
        else if(Math.abs(current - next) == 2){
            return 4;
        }
        else if(current == next){
            return 1;
        }
        else{
            return 3;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> list = new ArrayList<>();
        String s = "";
        list.add(0);

        while(!(s = st.nextToken()).equals("0")){
            list.add(Integer.parseInt(s));
        }

        int[][][] dp = new int[list.size()][5][5];

        if(list.size() == 1){
            System.out.println(0);
        }
        else{
            for(int[][] mat : dp){
                for(int[] line: mat){
                    Arrays.fill(line, max);
                }
            }

            dp[0][0][0] = 0;
            for(int i = 1; i < list.size(); i++){
                int next = list.get(i);
                
                for(int l = 0; l < 5; l++){
                    for(int r = 0; r < 5; r++){
                        int lValue = getEnergy(l, next);
                        int rValue = getEnergy(r, next);
                        dp[i][next][r] = Math.min(dp[i][next][r], dp[i-1][l][r] + lValue);
                        dp[i][l][next] = Math.min(dp[i][l][next], dp[i-1][l][r] + rValue);
                    }
                }

            }


            int min = Integer.MAX_VALUE;
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    min = Math.min(min, dp[list.size() - 1][i][j]);
                }
            }
            System.out.println(min);
        }

    }
}
