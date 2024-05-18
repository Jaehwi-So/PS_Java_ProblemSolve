import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        long c = sc.nextLong();
        if(b >= c){
            System.out.println(-1);
        }
        else{
            long result = (a / (c - b)) + 1;
            System.out.println(result);
        }
    }
}
