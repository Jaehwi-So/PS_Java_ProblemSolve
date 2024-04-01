import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        long v = sc.nextLong();

        v = v - a;
        long date = (long) (1 + Math.ceil((double)v / (a - b)));
        System.out.println(date);
    }
}