import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<int[]> points = new ArrayList<>();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                int p = Integer.parseInt(st.nextToken());
                if(p == 1) points.add(new int[]{i, j});
            }
        }

        List<int[]> current = new ArrayList<>();
        int count = 0;
        for(int[] point : points){

            Collections.sort(current, new Comparator<int[]>(){
                public int compare(int[] a1, int[] a2){
                    return a2[1] - a1[1];
                }
            });

            boolean success = false;
            for(int[] p : current){
                if(p[1] <= point[1]){
                    p[0] = point[0];
                    p[1] = point[1];
                    success = true;
                    break;
                }
            }
            if(!success){
                current.add(point);
                count++;
            }
        }
        System.out.println(count);
    }
}