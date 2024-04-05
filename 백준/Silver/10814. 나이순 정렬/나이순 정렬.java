import java.util.*;

class Member implements Comparable<Member>{
    int age;
    String name;
    int order;

    public Member(int age, String name, int order){
        this.age = age;
        this.name = name;
        this.order = order;
    }

    @Override
    public int compareTo(Member m){
        if(this.age == m.age){
            return this.order - m.order;
        }
        return this.age - m.age;
    }

    @Override
    public String toString(){
        return this.age + " " + this.name;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        Member[] members = new Member[n];
        for(int i = 0; i < n; i++){
            int age = sc.nextInt();
            String name = sc.next();
            members[i] = new Member(age, name, i);
        }

        Arrays.sort(members);


        for(Member m : members){
            System.out.println(m);
        }
    }
}