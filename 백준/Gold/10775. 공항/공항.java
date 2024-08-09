import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;

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
        parent = new int[n+1];
        for(int i = 1; i <= n; i++){
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int count = 0;
        for(int i = 0; i < p; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int index = find(k);
            if(index == 0){
                break;
            }
            parent[index] = find(index - 1);
            count++;
        }
        System.out.println(count);

    }
}
