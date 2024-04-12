import java.util.Scanner;

/**
 * 1 : 1
 * 2 : 2
 * 3 : 3
 * 4 : 5
 * 5 : 8 00001 00100 10000 00111 10011 11001 11100 11111
 * 6 : 13  000000 000011 001100 110000 001111 100111 110011 111001 111100 111111
 * 7 :
 */
public class Main {
    static int calc(int n){
        int[] f = new int[n + 3];
        f[0] = 0;
        f[1] = 1;
        f[2] = 2;

        for(int i = 3; i <= n; i++){
            f[i] = (f[i - 1] + f[i - 2]) % 15746;
        }

        return f[n];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(calc(n));

    }
}
