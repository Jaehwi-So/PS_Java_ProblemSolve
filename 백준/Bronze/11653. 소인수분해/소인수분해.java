import java.util.*;

class Main {
    static void calc(int x){
        if(x == 2){
            System.out.println(x);
            return;
        }
        for(int i = 2; i <= x; i++){
            if(x % i == 0){
                System.out.println(i);
                calc(x / i);
                break;
            }
        }


    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        calc(n);
    }
}
