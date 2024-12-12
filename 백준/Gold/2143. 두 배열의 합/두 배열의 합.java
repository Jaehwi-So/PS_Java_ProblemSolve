import java.io.*;
import java.util.*;

public class Main {
    static int binarySearchLower(List<Long> list, long number){
        int left = 0;
        int right = list.size();
        while(left < right){
            int mid = (left + right) / 2;
            if(list.get(mid) >= number){
                right = mid;
            }
            else{
                left = mid + 1;
            }
        }

        return right;
    }

    static int binarySearchUpper(List<Long> list, long number){
        int left = 0;
        int right = list.size();
        while(left < right){
            int mid = (left + right) / 2;
            if(list.get(mid) <= number){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }

        return right;
    }
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
        List<Long> sumB = new ArrayList<>();

        for(int i = 0; i < n; i++){
            long current = 0;
            for(int j = i; j < n; j++){
                current += a[j];
                sumA.add(current);
            }
        }

        for(int i = 0; i < m; i++){
            long current = 0;
            for(int j = i; j < m; j++){
                current += b[j];
                sumB.add(current);
            }
        }


        Collections.sort(sumA);
        Collections.sort(sumB);

        long result = 0;

        for(long aNum : sumA){
            long searchNum = t - aNum;
            int bStart = binarySearchLower(sumB, searchNum);
            int bEnd = binarySearchUpper(sumB, searchNum);

            if(bStart != -1 && bEnd != -1){
                result += (bEnd - bStart);
            }
        }

        System.out.println(result);

    }
}