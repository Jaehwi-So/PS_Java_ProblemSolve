import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();

        Set<String> set = new HashSet<>();
        for(int i = 0; i < n; i++){
            set.add(sc.nextLine());
        }

        List<String> intersect = new ArrayList<>();
        for(int i = 0; i < m; i++){
            String s = sc.nextLine();
            if(set.contains(s)){
                intersect.add(s);
            }
        }
        Collections.sort(intersect);
        StringBuilder sb = new StringBuilder();
        sb.append(intersect.size()).append("\n");
        for(String s : intersect){
            sb.append(s).append("\n");
        }
        System.out.println(sb);

    }
}
