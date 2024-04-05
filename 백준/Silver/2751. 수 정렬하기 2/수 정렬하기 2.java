import java.util.*;
import java.io.*;

class Main {

    // O(N^2)의 정렬 알고리즘 사용 시 = 100만 * 100만 = 1조개의 연산 -> 보통 1초의 시간제한은 1억 아래개의 연산을 가정하고 있어서 불가능
    // O(nlogn)의 정렬 알고리즘 사용 시 = 100만 * 6 = 600만개의 연산

    static void merge(int arr[], int left, int mid, int right){

        int[] left_arr = Arrays.copyOfRange(arr, left, mid + 1);
        int[] right_arr = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0;  //왼쪽 배열 검사 인덱스
        int j = 0;  //오른쪽 배열 검사 인덱스
        int k = left;  //합병중인 배열의 현재 인덱스


        while (i < left_arr.length && j < right_arr.length) {
            if (left_arr[i] <= right_arr[j]) {
                arr[k++] = left_arr[i++];
            }
            else {
                arr[k++] = right_arr[j++];
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
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];
        for(int i = 0; i < n; i++){
            numbers[i] = Integer.parseInt(br.readLine());
        }
        sort(numbers);
        for(int number : numbers){
            sb.append(number).append("\n");
        }
        System.out.println(sb);
    }
}
