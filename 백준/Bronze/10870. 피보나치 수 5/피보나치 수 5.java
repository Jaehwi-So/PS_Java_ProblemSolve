import java.util.Scanner;

public class Main {
    static int fib(int k){
        if(k <= 1){
            return k;
        }
        return fib(k-1) + fib(k-2);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(fib(n));

    }
}
