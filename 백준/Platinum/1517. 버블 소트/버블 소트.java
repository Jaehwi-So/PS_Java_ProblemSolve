import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static long result = 0;
    static void merge(int arr[], int left, int mid, int right){

        int[] left_arr = Arrays.copyOfRange(arr, left, mid + 1);
        int[] right_arr = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0;  //왼쪽 배열 검사 인덱스
        int j = 0;  //오른쪽 배열 검사 인덱스
        int k = left;  //합병중인 배열의 현재 인덱스
        int cnt = left_arr.length;


        while (i < left_arr.length && j < right_arr.length) {
            if (left_arr[i] <= right_arr[j]) {
                arr[k++] = left_arr[i++];
                cnt--;
            }
            else {
                arr[k++] = right_arr[j++];
                result += cnt;
            }
        }

        while (i < left_arr.length) {
            arr[k++] = left_arr[i++];

        }

        while (j < right_arr.length) {
            arr[k++] = right_arr[j++];
        }

    }

    static void mergeSort(int[] arr, int left, int right){
        //left와 right로 분할 가능하다면
        if(left < right){
            int mid = (right + left) / 2;   //mid
            //mid를 기준으로 두 파트로 분할
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);   //분할한 결과 합병 (분할 정복)
        }
    }
    static void sort(int arr[]){
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] array = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        sort(array);
        System.out.println(result);

    }
}
