import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();
        int count = 0;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            if(s.equals("ENTER")){
                count += set.size();
                set.clear();
            }
            else{
                set.add(s);
            }

        }

        count += set.size();
        System.out.println(count);
    }
}
