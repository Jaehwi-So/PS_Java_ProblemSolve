import java.util.Scanner;

public class Main {
    static int factorial(int i){
        if(i <= 1){
            return 1;
        }
        return i * factorial(i - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int result = factorial(n) / (factorial(k) * factorial(n - k));
        System.out.println(result);

    }
}
