import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1 (jump) 10
 * 2 (jump) 20(+0) / (step) 30(+10)
 * 3 (jump) 25(+10) / (step) 35(+20)
 * 4 (jump) 45(+20) 55(+30) / (step) 50(+25)
 * 5 (jump) (+25, +35)/ (step) (+45), (+55)
 * (jump) = Max(n-2) / (step) = Max(n-1)(jump)
 *
 * 1 (10): 10 / 0
 * 2 (20): 20(+0) / 30(+10)
 * 3 (15): 25(+10) / 35(+20)
 * 4 (25): 55(+30) / 50(+25)
 * 5 (10): 55(+45) / 65(+55)
 * 6 (20): 80(+60) / 75(+55)
 */

// 1 2 4 5 6 -> 10 20 25 10 20

public class Main {
    static int n = 0;
    static int[] values;
    static int[][] steps;

    static int getMax(int i, int j){
        return Math.max(i, j);
    }
    static void stepOut(){

        steps[0][0] = values[0];
        steps[1][0] = values[1];
        steps[2][0] = values[2];
        steps[2][1] = values[2] + steps[1][0];
        for(int i = 3; i <= n; i++){
            steps[i][0] = getMax(steps[i-2][0], steps[i-2][1]) + values[i];
            steps[i][1] = steps[i-1][0] + values[i];
        }
        int max = Integer.MIN_VALUE;

        for(int j = 0; j < 2; j++){
            max = getMax(max, steps[n][j]);
        }

        System.out.println(max);

    }
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        values = new int[n + 3];
        steps = new int[n + 3][2];
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            values[i] = Integer.parseInt(st.nextToken());
        }
        stepOut();

    }
}