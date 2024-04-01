import java.util.*;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int max = -1;
        int maxIndex = 0;
        for(int i = 0; i < 9; i++){
            int x = sc.nextInt();
            if(x > max){
                max = x;
                maxIndex = i + 1;
            }
        }
        System.out.println(max);
        System.out.println(maxIndex);
    }
}