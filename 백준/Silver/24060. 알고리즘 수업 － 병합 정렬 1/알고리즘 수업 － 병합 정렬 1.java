import java.util.Scanner;

public class Main {

    public static int saveNumber = -1;
    public static int count = 0;
    public static int saveCount = 7;
    public static void merge(int[] array, int left, int mid, int right){
        int[] tmp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int t = 0;

        while(i <= mid && j <= right){
            if(array[i] <= array[j]){
                tmp[t++] = array[i++];
            }
            else{
                tmp[t++] = array[j++];
            }
        }

        while(i <= mid){
            tmp[t++] = array[i++];
        }

        while(j <= right){
            tmp[t++] = array[j++];
        }

        i = left;
        t = 0;
        while(i <= right){
            array[i++] = tmp[t++];
//            print(array, i - 1);
            count++;
            if(count == saveCount){
                saveNumber = array[i - 1];
            }
        }
    }

    public static void mergeSort(int[] array, int left, int right){
        if(left < right){
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    public static void print(int[] array, int k){
        for(int i : array){
            System.out.print(i + " ");
        }
        System.out.print(" / Save : " + array[k]);
        System.out.println();
    }

    public static void sort(int[] array){
        mergeSort(array, 0, array.length - 1);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        saveCount = sc.nextInt();
        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = sc.nextInt();
        }

        sort(array);
        System.out.println(saveNumber);

    }
}
