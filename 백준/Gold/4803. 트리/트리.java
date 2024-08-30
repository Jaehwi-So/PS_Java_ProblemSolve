import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static boolean[] isCycle;

    static void union(int k1, int k2){
        int n1 = find(k1);
        int n2 = find(k2);
        if(n1 == n2){
            isCycle[n1] = true;
        }
        else if(n1 > n2){
            parent[n1] = n2;
        }
        else if(n1 < n2){
            parent[n2] = n1;
        }
    }

    static int find(int k1){
        if(parent[k1] != k1){
            parent[k1] = find(parent[k1]);
        }
        return parent[k1];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int c = 1;
        while(true){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if(n == 0 && m == 0) break;

            parent = new int[n+1];
            isCycle = new boolean[n+1];
            for(int i = 1; i <= n; i++){
                parent[i] = i;
            }

            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }

            Set<Integer> set = new HashSet<>();
            for(int i = 1; i <= n; i++){
                if(isCycle[i]){
                    isCycle[find(i)] = true;
                }
            }

            for(int i = 1; i <= n; i++){
                int p = find(i);
                if(!isCycle[p]){
                    set.add(p);
                }
            }

            if(set.size() == 0){
                sb.append(String.format("Case %d: No trees.", c));
            }
            else if(set.size() == 1){
                sb.append(String.format("Case %d: There is one tree.", c));
            }
            else{
                sb.append(String.format("Case %d: A forest of %d trees.", c, set.size()));
            }
            sb.append("\n");

            c++;
        }
        System.out.println(sb);
    }
}