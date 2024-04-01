import java.util.*;

class Main {
    static String calc(int a, int b){
        if(b % a == 0){
            return "factor";
        }
        else if(a % b == 0){
            return "multiple";
        }
        else{
            return "neither";
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            int a = sc.nextInt();
            int b = sc.nextInt();
            if(a == 0 && b == 0){
                break;
            }
            System.out.println(calc(a, b));
        }
    }
}