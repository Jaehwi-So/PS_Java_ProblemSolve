import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
//    static int find(int[] array, int number){
//        int start = 0;
//        int end = array.length - 1;
//        while(start <= end){
//            int mid = (end + start) / 2;
//            if(number == array[mid]){
//                return 1;
//            }
//            else if(number > array[mid]){
//                start = mid + 1;
//            }
//            else{
//                end = mid - 1;
//            }
//        }
//        return 0;
//    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] array = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array); // 정렬

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            int number = Integer.parseInt(st.nextToken());
            int index = Arrays.binarySearch(array, number);
            int result = index < 0 ? 0 : 1;
            System.out.println(result);
        }

    }
}
