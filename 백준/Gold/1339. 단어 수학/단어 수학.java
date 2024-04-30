import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            char[] chs = st.nextToken().toCharArray();
            for(int j = 0; j < chs.length; j++){
                char c = chs[j];
                int num = (int)Math.pow(10, chs.length - 1 - j);
                if(map.containsKey(c)){
                    map.replace(c, map.get(c) + num);
                }
                else{
                    map.put(c, num);
                }
            }
        }
        List<Integer> list = new ArrayList<>();
        for(char ch : map.keySet()){
            list.add(map.get(ch));
        }
        Collections.sort(list, Collections.reverseOrder());
        int k = 9;
        int result = 0;
        for(int i = 0; i < list.size(); i++){
            result += (list.get(i) * k);
            k--;
        }
        System.out.println(result);

    }
}
