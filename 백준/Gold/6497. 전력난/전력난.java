import java.io.*;
import java.util.*;

public class Main {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(true){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if(n == 0 && m == 0) break;
            long[][] edges = new long[m][3];
            long total = 0;
            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                edges[i] = new long[]{Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())};
                total += edges[i][2];
            }
            parents = new int[n+1];
            for(int i = 1; i <= n; i++){
                parents[i] = i;
            }

            Arrays.sort(edges, new Comparator<long[]>(){
                public int compare(long[] a1, long[] a2){
                    if(a1[2] > a2[2]) return 1;
                    else if(a1[2] < a2[2]) return -1;
                    else return 0;
                }
            });

            int count = 0;
            long diff = 0;
            for(int i = 0; i < m; i++){
                if(count == n-1) break;
                if(union((int)edges[i][0], (int)edges[i][1])){
                    count++;
                    diff += edges[i][2];
                }
            }

            sb.append(total - diff).append("\n");
        }
        System.out.println(sb);
    }
    static boolean union(int n1, int n2){
        int k1 = find(n1);
        int k2 = find(n2);
        if(k1 == k2) return false;
        else{
            if(k1 > k2) parents[k1] = k2;
            else parents[k2] = k1;
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