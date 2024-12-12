import java.io.*;
import java.util.*;

public class Main {

    static int binarySearchLower(int[] array, int number, int right){
        int left = 0;
        while(left < right){
            int mid = (left + right) / 2;
            if(array[mid] < number){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }
        return right;
    }
    static int binarySearchUpper(int[] array, int number, int right){
        int left = 0;
        while(left < right){
            int mid = (left + right) / 2;
            if(array[mid] <= number){
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
        int n = Integer.parseInt(st.nextToken());

        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        int[] d = new int[n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            d[i] = Integer.parseInt(st.nextToken());
        }

        int[] sumA = new int[n * n];
        int[] sumB = new int[n * n];

        int idx = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                sumA[idx] = a[i] + b[j];
                sumB[idx] = c[i] + d[j];
                idx++;
            }
        }

        Arrays.sort(sumA);
        Arrays.sort(sumB);

        long result = 0;
        int right = sumB.length;
        for(int i = 0; i < sumA.length; i++){
            int target = sumA[i] * -1;
            int lower = binarySearchLower(sumB, target, right);
            int upper = binarySearchUpper(sumB, target, right);
            if(upper > 0 && sumB[upper - 1] == target){
                result += (upper - lower);
            }
            right = upper;
        }
//        System.out.println(Arrays.toString(sumA));
//        System.out.println(Arrays.toString(sumB));
        System.out.println(result);


    }
}