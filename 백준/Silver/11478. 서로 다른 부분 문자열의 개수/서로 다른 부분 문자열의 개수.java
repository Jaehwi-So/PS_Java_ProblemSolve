import java.util.*;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] chs = sc.nextLine().toCharArray();
        StringBuilder sb = new StringBuilder();

        Set<String> set = new HashSet();
        for(int i = 0; i < chs.length; i++){
            sb.append(chs[i]);
            set.add(sb.toString());
            for(int j = i + 1; j < chs.length; j++){
                sb.append(chs[j]);
                set.add(sb.toString());
            }
            sb.setLength(0);
        }

        System.out.println(set.size());

    }
}
