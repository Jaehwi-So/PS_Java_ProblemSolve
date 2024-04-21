import java.util.Scanner;

public class Main {
    static long a;
    static long c;

    static long pow(long b){
        if(b == 1){
            return a % c;
        }
        else{
            long k = b / 2;
            long p = pow(k);
            if(b % 2 == 0){
                return (p * p) % c;
            }
            else{
                return ((p * p) % c) * a % c;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        a = sc.nextLong();
        long b = sc.nextLong();
        c = sc.nextLong();


        System.out.println(pow(b));

    }
}

