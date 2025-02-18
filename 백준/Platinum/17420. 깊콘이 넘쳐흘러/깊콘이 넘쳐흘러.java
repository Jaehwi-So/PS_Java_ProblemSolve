import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] array = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        long[][] date = new long[n][2];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            date[i][0] = Integer.parseInt(st.nextToken());
            date[i][1] = array[i];
        }
        Arrays.sort(date, new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                if(o1[0] > o2[0]) return 1;
                else if(o1[0] == o2[0]) return 0;
                else return -1;
            }
        });

        long beforeMax = -1;
        long currentMax = -1;
        long current = -1;
        long result = 0;

        for(int i = 0; i < n; i++){
            if(current < date[i][0]){
                current = date[i][0];
                beforeMax = currentMax;
                currentMax = -1;
            }
            long th = Math.max(beforeMax, current);

            if(date[i][1] < th){
                long cnt = (long)Math.ceil((th - date[i][1]) / 30.0);
                date[i][1] += (30 * cnt);
                result += cnt;
            }
            currentMax = Math.max(date[i][1], currentMax);
        }

        System.out.println(result);
    }
}