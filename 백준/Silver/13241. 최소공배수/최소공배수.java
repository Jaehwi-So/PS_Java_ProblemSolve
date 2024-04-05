import java.util.Scanner;

public class Main {


    //15 12
    //12 3
    //3
//    static int gcd(int m, int n){
//        if(n == 0){
//            return m;
//        }
//        else if(m % n == 0){
//            return n;
//        }
//        else{
//            return gcd(n,m % n);
//        }
//    }

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
        long b = sc.nextLong();
        long max = Math.max(a, b);
        long min = Math.min(a, b);
        long gc = gcd(max, min);


        long lcm = 0;
        if(gc == 1){
            lcm = max * min;
        }
        else{
            lcm = (min / gc) * max;
        }

        // 21 28 = (gcd)7 * 12
        // 36 48 = (gcd)12 * 12

        System.out.println(lcm);

    }
}
