import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int len;
    static int[][] score;

    static int searchNext(int index, int room){
        while(index < len - 1){
            index++;
            if(score[index][1] == room){
                return index;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        score = new int[n*m][2];

        int seq = 0;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                int s = Integer.parseInt(st.nextToken());
                score[seq][0] = s;
                score[seq][1] = i;
                seq++;
            }
        }
        len = score.length;

        Arrays.sort(score, new Comparator<int[]>(){
            public int compare(int[] s1, int[] s2){
                return s1[0] - s2[0];
            }
        });

        int max = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++){
            int idx = searchNext(-1, i);
            int value = score[idx][0];
            max = Math.max(max, value);
        }


        int result = Integer.MAX_VALUE;
        for(int i = 0; i < len; i++){
            int[] current = score[i];
            int diff = max - current[0];
            result = Math.min(diff, result);

            int nextIdx = searchNext(i, current[1]);
            if(nextIdx == -1){
                break;
            }
            else{
                max = Math.max(max, score[nextIdx][0]);
            }
        }
        System.out.println(result);
    }
}