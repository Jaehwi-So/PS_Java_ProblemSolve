import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int len = m*2 - 1;
        long[][] lines = new long[len][3];
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
            if(lines[i][1] < lines[i][0]) lines[i][1] += n;
            lines[i][2] = i+1;
        }
        for(int i = m ; i < len; i++){
            lines[i][0] = Integer.MAX_VALUE;
        }
        Arrays.sort(lines, new Comparator<long[]>(){
            public int compare(long[] a1, long[] a2){
                if(a1[0] == a2[0]){
                    if(a2[1] > a1[1]) return 1;
                    else if(a2[1] == a1[1]) return 0;
                    else return -1;
                }
                if(a1[0] > a2[0]) return 1;
                else return -1;
            }
        });

        for(int i = m ; i < len; i++){
            lines[i][0] = lines[i-m][0] + n;
            lines[i][1] = lines[i-m][1] + n;
            lines[i][2] = lines[i-m][2];
        }

//        for(long[] line : lines){
//            System.out.println(Arrays.toString(line));
//        }

        boolean[] deleted = new boolean[m+1];

        long end = lines[0][1];
        for(int i = 1; i < len; i++){
            if(lines[i][1] <= end){
                deleted[(int)lines[i][2]] = true;
            }
            else{
                end = lines[i][1];
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= m; i++){
            if(!deleted[i]) sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}