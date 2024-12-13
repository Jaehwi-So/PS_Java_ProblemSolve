import java.io.*;
import java.util.*;

public class Main {

    static boolean search(long[] array, long number, int index){
        int left = 0;
        int right = array.length - 1;
        while(left < right){
            long current = array[left] + array[right];
            if(current > number){
                right--;
            }
            else if(current < number){
                left++;
            }
            else{
                if(left != index && right != index){
                    return true;
                }
                else{
                    if(index == left){
                        left++;
                    }
                    else{
                        right--;
                    }
                }
            }
        }
        return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long[] array = new long[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            array[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(array);

        long result = 0;
        for(int i = 0; i < n; i++){
            long number = array[i];
            if(search(array, number, i)) result++;

        }

        System.out.println(result);

    }
}