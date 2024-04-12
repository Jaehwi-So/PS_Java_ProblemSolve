import java.util.Scanner;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static void getSequence(int[] array, int step, int n, int m){
        if(step == m){
            print(array);
            return;
        }
        for(int i = 1; i <= n; i++){
            array[step] = i;
            getSequence(array, step + 1, n, m);
            array[step] = 0;
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
        getSequence(array, 0, n, m);
        System.out.println(sb);
    }
}
