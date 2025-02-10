import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());


        int len = (n/2) + 1;
        int[][] maxDP = new int[len][len];
        int[][] minDP = new int[len][len];
        char[] operator = new char[n/2];

        for(int[] line : maxDP){
            Arrays.fill(line, -1000);
        }
        for(int[] line : minDP){
            Arrays.fill(line, 1000);
        }


        st = new StringTokenizer(br.readLine());
        char[] sequence = st.nextToken().toCharArray();
        int a = 0;
        int b = 0;
        for(int i = 0; i < n; i++){
            if(i % 2 == 0){
                maxDP[a][a] = Character.getNumericValue(sequence[i]);
                minDP[a][a] = maxDP[a][a];
                a++;
            }
            else operator[b++] = sequence[i];
        }



        for(int k = 1; k < len; k++){
            for(int i = 0; i < len - k; i++){
                for(int j = i; j <= i + k - 1; j++){
                    int maxD = 0;
                    int minD = 0;
                    if(operator[j] == '+'){
                        maxD = maxDP[i][j] + maxDP[j+1][i+k];
                        minD = minDP[i][j] + minDP[j+1][i+k];
                    }
                    else if(operator[j] == '-'){
                        maxD = maxDP[i][j] - minDP[j+1][i+k];
                        minD = minDP[i][j] - maxDP[j+1][i+k];
                    }
                    else if(operator[j] == '*'){
                        int pp = maxDP[i][j] * maxDP[j+1][i+k];
                        int pn = maxDP[i][j] * minDP[j+1][i+k];
                        int np = minDP[i][j] * maxDP[j+1][i+k];
                        int nn = minDP[i][j] * minDP[j+1][i+k];

                        maxD = Math.max(Math.max(pp, pn), Math.max(np, nn));
                        minD = Math.min(Math.min(pp, pn), Math.min(pp, pn));
                    }
                    maxDP[i][i+k] = Math.max(maxDP[i][i+k], maxD);
                    minDP[i][i+k] = Math.min(minDP[i][i+k], minD);
                }
            }
        }

//        for(int[] line : maxDP){
//            System.out.println(Arrays.toString(line));
//        }
        System.out.println(maxDP[0][len-1]);


    }
}