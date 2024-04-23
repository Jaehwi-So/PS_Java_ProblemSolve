import java.util.Scanner;

public class Main {
    static int[] button;
    static int n;
    static char[] chs;

    static int minV = Integer.MAX_VALUE;

    static void calc(int step){
        if(step == chs.length){
            String s = new String(chs);
            int result = Integer.parseInt(s);
            minV = Math.min(minV, Math.abs(n - result) + Integer.toString(result).length());


            for(int i = 1; i <= 9; i++){
                if(button[i] != -1) {
                    String k = button[i] + s;
                    result = Integer.parseInt(k);
                    minV = Math.min(minV, Math.abs(n - result) + Integer.toString(result).length());
                    break;
                }
            }

            if(chs.length > 1){
                s = s.substring(1, chs.length);
                result = Integer.parseInt(s);
                minV = Math.min(minV, Math.abs(n - result) + Integer.toString(result).length());
            }

        }
        else{
            char ch = chs[step];
            for(int i = 0; i <= 9; i++){
                if(button[i] != -1) {
                    chs[step] = Integer.toString(i).charAt(0);
                    calc(step + 1);
                    chs[step] = ch;
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int m = sc.nextInt();

        button = new int[10];
        for(int i = 0; i < 10; i++){
            button[i] = i;
        }

        for(int i = 0; i < m; i++){
            int num = sc.nextInt();
            button[num] = -1;
        }


        minV = Math.abs(n - 100);
        String str = Integer.toString(n);
        chs = str.toCharArray();
        calc(0);

        System.out.println(minV);
    }
}

