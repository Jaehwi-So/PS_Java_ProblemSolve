import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        for(int i = 0; i < n ; i++){
            set.add(sc.nextInt());
        }
        Set<Integer> differ = new HashSet();
        for(int i = 0; i < m; i++){
            int k = sc.nextInt();
            if(set.contains(k)){
                set.remove(k);
            }
            else{
                differ.add(k);
            }
        }
        System.out.println(set.size() + differ.size());
    }

}