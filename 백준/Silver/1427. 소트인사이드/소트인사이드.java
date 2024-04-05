import java.util.*;

class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long number = sc.nextLong();
        char[] chs = Long.toString(number).toCharArray();
        Arrays.sort(chs);
        StringBuilder sb = new StringBuilder();
        for(int i = chs.length - 1; i >= 0; i--){
            sb.append(chs[i]);
        }
        System.out.println(sb);
    }
}