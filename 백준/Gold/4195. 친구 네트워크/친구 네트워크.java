import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 2000002;
    static int[] parent;
    static int[] count;
    static HashMap<String, Integer> map;

    static void union(int n1, int n2){
        int k1 = find(n1);
        int k2 = find(n2);
        if(k1 < k2){
            parent[k2] = k1;
            count[k1] += count[k2];
            count[k2] = 0;
        }
        else if(k2 < k1){
            parent[k1] = k2;
            count[k2] += count[k1];
            count[k1] = 0;
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
        int test = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < test; t++){
            parent = new int[MAX];
            count = new int[MAX];
            map = new HashMap();
            int index = 1;
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for(int i = 0; i < n; i++){
                String[] friends = br.readLine().split(" ");
                for(int j = 0; j < 2; j++){
                    if(!map.containsKey(friends[j])){
                        map.put(friends[j], index);
                        parent[index] = index;
                        count[index] = 1;
                        index++;
                    }
                }
                union(map.get(friends[0]), map.get(friends[1]));
                sb.append(count[find(map.get(friends[0]))]).append("\n");
            }
        }
        System.out.println(sb);

    }
}
