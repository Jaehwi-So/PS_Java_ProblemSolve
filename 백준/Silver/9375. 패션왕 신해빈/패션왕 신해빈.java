import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());


        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            Map<String, Integer> map = new HashMap();
            for(int j = 0; j < m; j++){
                st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String kind = st.nextToken();
                if(map.containsKey(kind)){
                    map.replace(kind, map.get(kind) + 1);
                }
                else{
                    map.put(kind, 2);
                }
            }
            int result = 1;
            for(String key : map.keySet()){
                result *= map.get(key);
            }
            result--;
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
}
