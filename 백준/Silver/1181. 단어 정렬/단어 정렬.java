import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        Set set = new HashSet<String>();
        for(int i = 0; i < n; i++){
            set.add(sc.nextLine());
        }

        List<String> list = new ArrayList<>(set);

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() == o2.length()){
                    return o1.compareTo(o2);
                }
                return o1.length() - o2.length();
            }
        });


        for(String s : list){
            System.out.println(s);
        }
    }
}