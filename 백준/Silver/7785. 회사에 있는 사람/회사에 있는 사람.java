import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        Set<String> set = new HashSet<String>();
        for(int i = 0; i < n; i++){
            String name = sc.next();
            String status = sc.next();
            sc.nextLine();
            if(status.equals("enter")){
                set.add(name);
            }
            else{
                set.remove(name);
            }
        }

        List<String> list = new ArrayList<>(set);
        list.sort(Comparator.reverseOrder());

        for(String s : list){
            System.out.println(s);
        }


    }
}