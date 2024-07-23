import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] array = new int[n];
        boolean[] isTarget = new boolean[MAX + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; ++i) {
            array[i] = Integer.parseInt(st.nextToken());
            isTarget[array[i]] = true;
        }

        int[] score = new int[MAX + 1];

        for(int i : array){
            for(int j = i * 2; j <= MAX; j += i){
                if(isTarget[j]){
                    score[i] += 1;
                    score[j] -= 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int num : array){
            sb.append(score[num]).append(' ');
        }
        System.out.println(sb);
    }
}