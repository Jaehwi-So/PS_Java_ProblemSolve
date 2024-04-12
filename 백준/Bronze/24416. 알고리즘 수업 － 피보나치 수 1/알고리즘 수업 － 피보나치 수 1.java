import java.util.Scanner;

public class Main {

    static int count = 0;

    static int fib_d(int n){
        int[] f = new int[n + 1];
        f[1] = 1;
        f[2] = 1;

        for(int i = 3; i <= n; i++){
            f[i] = f[i-1] + f[i-2];
            count++;
        }
        return f[n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(fib_d(n) + " " + count);

    }
}