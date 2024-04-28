import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String[] strs = new String[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            strs[i] = st.nextToken();
        }

        Arrays.sort(strs);

        Character[] result = new Character[m];
        int count = 0;

        for(int i = 0; i < m; i++){
            Map<Character, Integer> map = new HashMap();
            for(int j = 0; j < n; j++){
                char ch = strs[j].charAt(i);
                if(map.containsKey(ch)){
                    map.put(ch, map.get(ch) + 1);
                }
                else{
                    map.put(ch, 1);
                }
            }
            int max = Integer.MIN_VALUE;
            char maxChar = 'A';
            for(Character key : map.keySet()){
                int cnt = map.get(key);
                if(cnt == max){
                    if(maxChar > key){
                        max = cnt;
                        maxChar = key;
                    }
                }
                else if(cnt > max){
                    max = cnt;
                    maxChar = key;
                }
            }
            result[i] = maxChar;
            count += (n - max);

        }

        StringBuilder sb = new StringBuilder();
        for(char ch : result){
            sb.append(ch);
        }
        System.out.println(sb);
        System.out.println(count);


    }
}
