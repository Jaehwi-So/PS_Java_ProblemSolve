import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static int n = 0;
    static int[] values;
    static int[][] steps;

    static void drink(){

        steps[0][0] = 0;
        steps[0][1] = 0;
        steps[0][2] = 0;
        steps[1][0] = 0; //1트
        steps[1][1] = values[0]; //연속
        steps[1][2] = values[0];
        steps[2][0] = values[1]; //1트
        steps[2][1] = values[0] + values[1];  //연속
        steps[2][2] = values[0] + values[1];

        for(int i = 3; i <= n; i++){
            steps[i][0] = steps[i-2][2] + values[i-1]; //1트
            steps[i][1] = steps[i-1][0] + values[i-1];
            steps[i][2] = Math.max(Math.max(steps[i][0], steps[i][1]), steps[i-1][2]);
        }

//        for(int i = 1; i <= n; i++){
//            System.out.println(values[i-1] + ": " + steps[i][0] + " " + steps[i][1] + " " + steps[i][2]);
//        }

        System.out.println(Math.max(Math.max(steps[n][0], steps[n][1]), steps[n-1][1]));
    }

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        values = new int[n + 3];
        steps = new int[n + 3][3];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            values[i] = k;

        }
        drink();

    }
}