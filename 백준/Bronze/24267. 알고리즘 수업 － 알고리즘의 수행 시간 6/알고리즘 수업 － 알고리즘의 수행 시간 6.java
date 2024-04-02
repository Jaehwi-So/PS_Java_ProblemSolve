import java.util.*;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        System.out.println((n * (n-1) * (n-2)) / 6);
        System.out.println(3);
    }

    // n * n - 1 * n - 2
    //     6
    // 1~n-2 * (2~n)~(n-1) * (3~n+1)~(n)
    // n = 5  1 2 3, 
    // 2 3 4  / 3 4 / 4 
    // 3 4 5 4 5 5 / 4 5 5 / 5
    // n = 5 -> 10
    // 7 6 5
}