import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder s = new StringBuilder(sc.nextLine());
        StringBuilder t = new StringBuilder(sc.nextLine());

        while(s.length() < t.length()){
            if(t.charAt(t.length() - 1) == 'A'){
                t.setLength(t.length() - 1);
            }
            else if(t.charAt(t.length() - 1) == 'B'){
                t.setLength(t.length() - 1);
                t.reverse();
            }
            else{
                break;
            }
        }

        int result = s.toString().equals(t.toString()) ? 1 : 0;
        System.out.println(result);
    }
}
