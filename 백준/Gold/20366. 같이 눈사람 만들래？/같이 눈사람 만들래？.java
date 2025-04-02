import java.io.*;
import java.util.*;

public class Main {
    static int[] array;
    static int search(int start, int end, int number){
        int min = Integer.MAX_VALUE;
        while(start < end){
            int current = array[start] + array[end];
            min = Math.min(min, Math.abs(number - current));
            if(current == number){
                break;
            }
            else if(current > number){
                end--;
            }
            else{
                start++;
            }
        }
        return min;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        array = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(array);
//        System.out.println(Arrays.toString(array));

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n - 3; i++){
            for(int j = i + 3; j < n; j++){
                int number = array[i] + array[j];
                min = Math.min(min, search(i+1, j-1, number));
            }
        }
        System.out.println(min);

    }
}