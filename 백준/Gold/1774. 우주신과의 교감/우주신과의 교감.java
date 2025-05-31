import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] points;
    static List<double[]> edges = new ArrayList<>();
    static int[] parents;

    static boolean union(int n1, int n2){
        int p1 = find(n1);
        int p2 = find(n2);
        if(p1 == p2){
            return false;
        }
        else{
            if(p1 > p2) parents[p1] = p2;
            else parents[p2] = p1;
            return true;
        }
    }

    static int find(int n){
        if(parents[n] != n){
            parents[n] = find(parents[n]);
        }
        return parents[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        points = new int[n+1][2];
        parents = new int[n+1];
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
            parents[i] = i;
        }

        // 기존 간선 체크
        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            double dist = Math.sqrt(Math.pow(points[a][0] - points[b][0], 2) + Math.pow(points[a][1] - points[b][1], 2));
            edges.add(new double[]{a, b, dist});
        }

        Collections.sort(edges, new Comparator<double[]>() {
            public int compare(double[] o1, double[] o2) {
                if(o1[2] > o2[2]) return 1;
                else if(o1[2] < o2[2]) return -1;
                else return 0;
            }
        });

        int number = 0;

        for(double[] edge : edges){
            int a = (int)edge[0];
            int b = (int)edge[1];
            if(union(a, b)){
                number++;
            }
        }


        // 나머지 간선 체크
        edges.clear();

        for(int i = 1; i <= n-1; i++) {
            for (int j = i + 1; j <= n; j++) {
                double dist = Math.sqrt(Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2));
                edges.add(new double[]{i, j, dist});
            }
        }

        Collections.sort(edges, new Comparator<double[]>() {
            public int compare(double[] o1, double[] o2) {
                if(o1[2] > o2[2]) return 1;
                else if(o1[2] < o2[2]) return -1;
                else return 0;
            }
        });

        double distance = 0;
        for(double[] edge : edges){
            if(number >= n - 1) break;
            int a = (int)edge[0];
            int b = (int)edge[1];
            if(union(a, b)){
                number++;
                distance += edge[2];
            }
        }

        System.out.println(String.format("%.2f", distance));

    }
}