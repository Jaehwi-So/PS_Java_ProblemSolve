import java.io.*;
import java.util.*;

public class Main {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        double[][] nodes = new double[n][2];
        parents = new int[n];
        for(int i = 0; i < n; i++){
            parents[i] = i;
        }
        List<double[]> edges = new ArrayList<>();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            nodes[i][0] = Double.parseDouble(st.nextToken());
            nodes[i][1] = Double.parseDouble(st.nextToken());
            for(int j = 0; j < i; j++){
                double weight = Math.sqrt(Math.pow(nodes[i][0] - nodes[j][0], 2) + Math.pow(nodes[i][1] - nodes[j][1], 2));
                weight = Math.round(weight * 100) / 100.0;
                edges.add(new double[]{i, j, weight});
            }
        }

        Collections.sort(edges, new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                if(o1[2] - o2[2] == 0) return 0;
                else if(o1[2] - o2[2] > 0) return 1;
                else return -1;
            }
        });
        double sum = 0;
        int count = 0;
        for(double[] edge : edges){
            if(count == n - 1) break;
            if(union((int)edge[0], (int)edge[1])){
                sum += edge[2];
                count++;
            }
        }
        System.out.println(sum);
    }

    static boolean union(int n1, int n2){
        int f1 = find(n1);
        int f2 = find(n2);
        if(f1 == f2) return false;
        else{
            if(f1 < f2) parents[f1] = f2;
            else parents[f2] = f1;
            return true;
        }
    }
    static int find(int n){
        if(parents[n] != n){
            parents[n] = find(parents[n]);
        }
        return parents[n];
    }
}