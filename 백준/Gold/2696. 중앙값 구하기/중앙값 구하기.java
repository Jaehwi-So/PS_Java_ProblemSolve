import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int test = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < test; t++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] numbers = new int[n];

            int idx = 0;
            while(idx < n){
                if(idx % 10 == 0) st = new StringTokenizer(br.readLine());
                numbers[idx] = Integer.parseInt(st.nextToken());
                idx++;
            }

//            System.out.println(Arrays.toString(numbers));

            List<Integer> results = new ArrayList<>();

            int mid = numbers[0];
            results.add(mid);
            PriorityQueue<Integer> ascQ = new PriorityQueue<>(); //큰 수
            PriorityQueue<Integer> descQ = new PriorityQueue<>(Collections.reverseOrder()); //작은 수
            for(int i = 1; i < n; i++){
                if(mid <= numbers[i]) ascQ.offer(numbers[i]);
                else descQ.offer(numbers[i]);
                if(i % 2 == 0){
                    if(ascQ.size() != descQ.size()){
                        if(ascQ.size() > descQ.size()){
                            descQ.offer(mid);
                            mid = ascQ.poll();
                        }
                        else{
                            ascQ.offer(mid);
                            mid = descQ.poll();
                        }
                    }
                    results.add(mid);
                }
            }

            sb.append(results.size());
            idx = 0;
            while(idx < results.size()){
                if(idx % 10 == 0) sb.append("\n");
                sb.append(results.get(idx)).append(" ");
                idx++;
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}