import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    static void union(int n1, int n2){
        int p1 = find(n1);
        int p2 = find(n2);
        if(p1 == -1 || p2 == -1){
            parent[n1] = -1;
            parent[n2] = -1;
        }
        else if(p1 >= p2){
            parent[p2] = p1;
        }
        else{
            parent[p1] = p2;
        }
    }
    static int find(int n){
        if(n == -1) return -1;
        if(parent[n] != n){
            parent[n] = find(parent[n]);
        }
        return parent[n];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        boolean[] select = new boolean[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            int idx = Integer.parseInt(st.nextToken());
            select[idx] = true;
        }

        parent = new int[n+1];
        int p = -1;
        for(int i = n; i >= 1; i--){
            if(select[i]) p = i;
            parent[i] = p;
        }


        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < k; i++){
            int card = Integer.parseInt(st.nextToken());
            p = find(card + 1);
            union(card + 1, p+1);
            sb.append(p).append("\n");
//            System.out.println(p);
//            System.out.println(Arrays.toString(parent));
        }

        System.out.println(sb);
    }
}