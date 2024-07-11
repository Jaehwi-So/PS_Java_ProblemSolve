import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] position;
    static int[] current;
    static int max;
    static void backtrack(int step){
        if(step == 11){
            int sum = 0;
            for(int i : current){
                sum += i;
            }
            max = Math.max(max, sum);
        }
        else{
            for(int i = 0; i < 11; i++){
                if(current[i] == 0 && position[step][i] != 0){
                    current[i] = position[step][i];
                    backtrack(step + 1);
                    current[i] = 0;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        StringBuffer sb = new StringBuffer();


        for(int l = 0; l < t; l++){
            current = new int[11];
            position = new int[11][11];
            max = Integer.MIN_VALUE;

            for(int i = 0; i < 11; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 11; j++){
                    position[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            backtrack(0);
            sb.append(max).append("\n");

        }

        System.out.println(sb);

    }
}