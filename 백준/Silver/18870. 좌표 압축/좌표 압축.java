import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        List<Integer> lists = new ArrayList<>();
        List<Integer> sorted = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int number = Integer.parseInt(st.nextToken());
            lists.add(number);
            sorted.add(number);
        }


        Collections.sort(sorted);

        HashMap map = new HashMap<Integer, Integer>();

        int number = 0;
        for(Integer i : sorted){
            if(!map.containsKey(i)){
                map.put(i, number);
                number++;
            }

        }

        StringBuilder sb = new StringBuilder();
        for(int p : lists){
            sb.append(map.get(p)).append(' ');
        }
        System.out.println(sb);
    }
}

