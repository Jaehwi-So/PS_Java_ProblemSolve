import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int l;
    static int k;
    static int[][] stars;
    static int result = 0;

    static int calc(int minX, int maxX, int minY, int maxY){
        int result = 0;
        for(int[] s : stars){
            if(s[0] >= minX && s[0] <= maxX && s[1] >= minY && s[1] <= maxY){
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        stars = new int[k][2];

        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            stars[i][0] = col;
            stars[i][1] = row;
        }

        List<int[]> points = new ArrayList<>();
        for(int i = 0; i < k; i++){
            points.add(new int[]{stars[i][0], stars[i][1]});
            for(int j = i+1; j < k; j++){
                points.add(new int[]{stars[j][0], stars[i][1]});
                points.add(new int[]{stars[i][0], stars[j][1]});
            }
        }



        for(int i = 0; i < points.size(); i++){
            int[] p = points.get(i);
            result = Math.max(result, calc(p[0] - l, p[0], p[1] - l, p[1]));
            result = Math.max(result, calc(p[0] - l, p[0], p[1], p[1] + l));
            result = Math.max(result, calc(p[0],p[0] + l, p[1] - l, p[1]));
            result = Math.max(result, calc(p[0],p[0] + l, p[1], p[1] + l));
        }

        System.out.println(k - result);


    }
}