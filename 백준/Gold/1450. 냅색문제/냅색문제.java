import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int c;
    static long[] array;

    static List<Long> l1 = new ArrayList();
    static List<Long> l2 = new ArrayList();

    static int binarySearch(List<Long> list, long number, int low, int high){
        while(low < high){
            int mid = (low + high) / 2;
            if(list.get(mid) <= number){
                low++;
            }
            else{
                high--;
            }
        }
        return high;
    }

    static void operate(List result, int current, int end, long number){
        if(number > c) return;
        if(current == end + 1){
            result.add(number);
        }
        else{
            operate(result, current + 1, end, number + array[current]);
            operate(result, current + 1, end,  number);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        array = new long[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            array[i] = Long.parseLong(st.nextToken());
        }
        operate(l1, 1, n/2, 0);
        operate(l2, (n/2) + 1, n, 0);

        Collections.sort(l1);
        Collections.sort(l2, Collections.reverseOrder());

        long result = 0;

        int idx1 = 0;
        int idx2 = 0;

//        System.out.println(l1);
//        System.out.println(l2);

        // 9 9 8 8 7
        while(idx1 < l1.size() && idx2 < l2.size()){
            while(l1.get(idx1) + l2.get(idx2) > c && idx2 < l2.size()){
                idx2++;
            }
            result += l2.size() - idx2;
            idx1++;
        }

        System.out.println(result);

    }
}