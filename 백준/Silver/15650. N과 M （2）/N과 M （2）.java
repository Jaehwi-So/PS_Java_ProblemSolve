import java.util.Scanner;

public class Main {

    static StringBuilder sb = new StringBuilder();

    static void insert(int[] array, int index, int n){
        for(int i = 0; i < index - 1; i++){
            if(array[i] >= array[index - 1]){
                return;
            }
        }
        if(index == array.length){
            print(array);
        }
        else{
            for(int i = 1; i <= n; i++){
                array[index] = i;
                insert(array, index + 1, n);
                array[index] = 0;
            }
        }
    }

    static void print(int[] array){
        for(int i : array){
            sb.append(i + " ");
        }
        sb.append("\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] array = new int[m];

        insert(array, 0, n);
        System.out.println(sb);
    }
}
