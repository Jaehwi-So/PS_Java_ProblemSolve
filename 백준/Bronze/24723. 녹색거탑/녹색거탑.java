import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int number = 1;
        for(int i = 1; i <= n; i++){
            number *= 2;
        }
        System.out.println(number);
    }
}