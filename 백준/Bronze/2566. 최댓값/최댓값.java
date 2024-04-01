import java.util.*;

class Main {
    static int max = -100;
    static int max_x = 0;
    static int max_y = 0;

    static boolean calc(int x){
        if(x > max){
            return true;
        }
        else{
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] array = new int[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                array[i][j] = sc.nextInt();
            }
            sc.nextLine();
        }

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                int k = array[i][j];
                boolean isMax = calc(k);
                if(isMax == true){
                    max = k;
                    max_y = i + 1;
                    max_x = j + 1;
                }
            }
        }

        System.out.println(max);
        System.out.printf("%d %d", max_y, max_x);

    }
}