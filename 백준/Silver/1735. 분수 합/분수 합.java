
import java.util.Scanner;

public class Main {

    static long gcd(long m, long n){
        while(true){
            if(n == 0){
                return m;
            }
            else if(m % n == 0){
                return n;
            }
            else{
                long temp = m % n;
                m = n;
                n = temp;
            }
        }
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long a_ = sc.nextLong();
        long b = sc.nextLong();
        long b_ = sc.nextLong();
        
        a *= b_;
        b *= a_;

        long top = a + b;
        long bottom = a_ * b_;

        long sgcp = gcd(top, bottom);

        top /= sgcp;
        bottom /= sgcp;

        System.out.println(top + " " + bottom);
        

    }
}

