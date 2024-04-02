import java.util.*;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        System.out.println((n * (n-1)) / 2);
        System.out.println(2);
    }
                           // 7 ->
                           //6 5 4 3 2 1 = 21 = 7
                           // 7 6 5 4 3 2 1 = 28 = 8
}