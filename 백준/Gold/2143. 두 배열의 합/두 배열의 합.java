import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long t = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int[] b = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            b[i] = Integer.parseInt(st.nextToken());
        }

        List<Long> sumA = new ArrayList<>();
        HashMap<Long, Integer> cntA = new HashMap<>();

        for(int i = 0; i < n; i++){
            long current = 0;
            for(int j = i; j < n; j++){
                current += a[j];
                if(cntA.containsKey(current)){
                    cntA.put(current, cntA.get(current) + 1);
                }
                else{
                    sumA.add(current);
                    cntA.put(current, 1);
                }
            }
        }

        Collections.sort(sumA);
        long result = 0;

        for(int i = 0; i < m; i++){
            long current = 0;
            for(int j = i; j < m; j++){
                current += b[j];
                long aNum = t - current;
                int index = Collections.binarySearch(sumA, aNum);
                if(index >= 0){
                    result += cntA.get(aNum);
                }
            }
        }


        System.out.println(result);

    }
}
