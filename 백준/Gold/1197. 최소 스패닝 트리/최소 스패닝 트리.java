import java.io.*;
import java.util.*;

public class Main {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] edges = new int[m][3];
        parents = new int[n+1];
        for(int i = 1; i <= n; i++){
            parents[i] = i;
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            edges[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        Arrays.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        int count = 0;
        int sum = 0;
        for(int i = 0; i < m; i++){
            if(count == n - 1) break;
            if(union(edges[i][0], edges[i][1])){
                sum += edges[i][2];
                count++;
            }
        }
        System.out.println(sum);
    }

    static boolean union(int n1, int n2){
        int p1 = find(n1);
        int p2 = find(n2);
        if(p1 == p2) return false;
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
}