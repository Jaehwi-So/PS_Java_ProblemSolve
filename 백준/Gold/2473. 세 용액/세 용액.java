import java.io.*;
import java.util.*;

public class Main {
    static long[] array;
    static long binarySearch(int start, int end){
        int left = start + 1;
        int right = end - 1;
        int mid = (left + right) / 2;
        long number = (array[start] + array[end]) * -1;
        while(left < right){
            mid = (left + right) / 2;
            if(array[mid] < number){
                left = mid + 1;
            }
            else if(array[mid] > number){
                right = mid - 1;
            }
            else{
                return array[mid];
            }
        }

//        System.out.println(left + " " + mid + " " + right);
        long result = array[mid];
        if(left > start && left < end) {
            if(Math.abs(array[left] - number) < Math.abs(result - number)){
                result = array[left];
            }
        }
        if(right > start && right < end) {
            if(Math.abs(array[right] - number) < Math.abs(result - number)){
                result = array[right];
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        array = new long[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            array[i] =  Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array);

        long min = Long.MAX_VALUE;
        long[] result = new long[3];
        for(int i = 0; i < n - 2; i++){
            for(int j = i + 2; j < n; j++){
                long number = binarySearch(i, j);
//                System.out.println(array[i] + " " + number + " " + array[j]);
                long diff = Math.abs(array[i] + number + array[j]);
                if(diff < min){
                    result[0] = array[i];
                    result[1] = number;
                    result[2] = array[j];
                    min = diff;
                }
            }
        }
        System.out.println(result[0] + " " + result[1] + " " + result[2]);

    }
}