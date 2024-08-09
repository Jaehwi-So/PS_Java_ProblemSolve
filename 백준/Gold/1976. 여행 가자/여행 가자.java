import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int parent[];
    static void union(int k1, int k2){
        int n1 = find(k1);
        int n2 = find(k2);
        if(n1 < n2){
            parent[n2] = n1;
        }
        else{
            parent[n1] = n2;
        }
    }

    static int find(int k){
        if(parent[k] != k){
            parent[k] = find(parent[k]);
        }
        return parent[k];
    }

    static boolean connected(int k1, int k2){
        return find(k1) == find(k2);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];

        for(int i = 1; i <= n; i++){
            parent[i] = i;
        }

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                int k = Integer.parseInt(st.nextToken());
                if(k == 1){
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int parent = 0;
        for(int i = 0; i < m; i++){
            if(i == 0){
                parent = Integer.parseInt(st.nextToken());
            }
            else{
                int k = Integer.parseInt(st.nextToken());
                if(!connected(k, parent)){
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");

    }
}