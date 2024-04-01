import java.util.Scanner;

class Main {
    static char parse(long i){
        if(i > 9){
            return (char)(i + 55);
        }
        else{
            return (char)(i + 48);
        }

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long k = sc.nextLong();
        int n = sc.nextInt();


        //18(10) -> 12(16)
        StringBuilder sb = new StringBuilder();
        while(k >= n){
            long r = k / n;
            sb.append(parse(k % n));
            k = r;
        }
        sb.append(parse(k));
        sb.reverse();
        System.out.println(sb.toString());


    }
}
