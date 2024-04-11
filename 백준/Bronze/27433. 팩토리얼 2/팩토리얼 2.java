import java.util.*;
class Main{
    static long factorial(int k){
        if(k <= 1){
            return 1;
        }
        return factorial(k - 1) * (long)k;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(factorial(n));
    }
    
}