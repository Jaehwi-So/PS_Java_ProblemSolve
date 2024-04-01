import java.util.*;

class Main {
    static int calc(int x){
        if(x == 1){
            return 3;
        }
        else{
            return (calc(x - 1) * 2) - 1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = calc(n);
        int result = (int) Math.pow(x, 2);
        System.out.println(result);
    }
}