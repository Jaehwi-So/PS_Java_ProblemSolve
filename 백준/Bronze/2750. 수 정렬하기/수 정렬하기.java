import java.util.*;

class Main {

    // Insertion Sort
    static void sort(int[] array){
        for(int i = 1; i < array.length; i++){
            int key = i;
            for(int j = key - 1; j >= 0; j--){
                if(array[key] < array[j]){
                    int temp = array[key];
                    array[key] = array[j];
                    array[j] = temp;
                    key = j;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] numbers = new int[n];
        for(int i = 0; i < n; i ++){
            numbers[i] = sc.nextInt();
        }
        sort(numbers);
//        Arrays.sort(numbers);
        for(int number : numbers){
            System.out.println(number);
        }
    }
}
