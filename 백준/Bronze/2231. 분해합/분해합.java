import java.util.*;

class Main {
    static int calc(int n){
        char[] chs = Integer.toString(n).toCharArray();
        int result = n;
        for(char ch : chs){
            result += Character.getNumericValue(ch);
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();

        int result = 0;
        for(int i = 0; i <= x; i++){
            int divsum = calc(i);
            if(divsum == x){
                result = i;
                break;
            }
        }
        System.out.println(result);


    }
}
