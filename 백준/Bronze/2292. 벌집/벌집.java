import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        int step = 1;
        n -= 1;
        while(n >= 1){
            n -= (6 * step);
            step++;
        }
        System.out.println(step);

    }
}