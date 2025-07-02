import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] works = new int[n][3];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            works[i][0] = i+1;
            works[i][1] = Integer.parseInt(st.nextToken()); //T
            works[i][2] = Integer.parseInt(st.nextToken()); //S
        }

        Arrays.sort(works, new Comparator<int[]>(){
            public int compare(int[] w1, int[] w2){
                int comp1 = (w1[1] * w1[2]) + ((w1[1] + w2[1]) * w2[2]);
                int comp2 = (w2[1] * w2[2]) + ((w1[1] + w2[1]) * w1[2]);

                if(comp1 == comp2){
                    return w1[0] - w2[0];
                }
                else{
                    return comp1 - comp2;
                }
            }
        });

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            sb.append(works[i][0]).append(" ");
        }
        System.out.println(sb);
    }
}