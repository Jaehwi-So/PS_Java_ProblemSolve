import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        StringBuilder sb = new StringBuilder();
        while((s = br.readLine()) != null && !s.isEmpty()){
            StringTokenizer st = new StringTokenizer(s);
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            sb.append(i+j).append("\n");
        }
        System.out.println(sb);
    }
}