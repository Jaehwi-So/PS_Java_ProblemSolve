import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int MAX = 200000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            list.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list);

        List<Integer> sum = new ArrayList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                long poo = list.get(i) + list.get(j);
                if(poo > MAX){
                    continue;
                }
                sum.add((int)poo);
            }
        }

        // a + b + c = d
        // a + b = d - c
        Collections.sort(sum);

        int result = 0;
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                int minus = list.get(j) - list.get(i);
                int number = Collections.binarySearch(sum, minus);
                if(number >= 0){
                    result = Math.max(result, sum.get(number) + list.get(i));
                }

            }
        }

        System.out.println(result);

    }
}
