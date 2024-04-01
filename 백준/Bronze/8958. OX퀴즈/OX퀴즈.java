import java.util.*;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < n; i++){
            String s = sc.nextLine();
            char[] chs = s.toCharArray();
            int step = 1;
            int result = 0;
            for(char ch : chs){
                if(ch == 'O'){
                    result += step;
                    step++;
                }
                else{
                    step = 1;
                }
            }
            System.out.println(result);
        }
    }
}