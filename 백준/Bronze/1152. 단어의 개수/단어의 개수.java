import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        s = s.trim();
        if(s.length() == 0){
            System.out.println(0);
        }
        else{
            String[] list = s.split(" ");
            System.out.println(list.length);
        }
    }
}
