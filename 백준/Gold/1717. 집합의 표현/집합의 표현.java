import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] node;
    static void union(int n1, int n2){
        int a = find(n1);
        int b = find(n2);
        if(a > b){
            node[b] = a;
        }
        else{
            node[a] = b;
        }
    }
    static int find(int k){
        if(node[k] != k){
            node[k] = find(node[k]);
        }
        return node[k];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        node = new int[n+1];
        for(int i = 0; i <= n; i++){
            node[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for(int j = 0; j < m; j++){
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            if(command == 0){
                union(n1, n2);
            }
            else{
                if(find(n1) == find(n2)){
                    sb.append("YES").append("\n");
                }
                else{
                    sb.append("NO").append("\n");
                }
            }
        }
        System.out.println(sb);

    }
}
