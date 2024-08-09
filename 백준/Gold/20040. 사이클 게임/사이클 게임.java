import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;

    static boolean union(int n1, int n2){
        int k1 = find(n1);
        int k2 = find(n2);
        if(k1 > k2){
            parent[k1] = k2;
            return false;
        }
        else if(k2 > k1){
            parent[k2] = k1;
            return false;
        }
        else{
            return true;
        }
    }
    static int find(int n){
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
        parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }

        int result = 0;
        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            if(union(n1, n2)){
                result = i;
                break;
            }
        }
        System.out.println(result);


    }
}