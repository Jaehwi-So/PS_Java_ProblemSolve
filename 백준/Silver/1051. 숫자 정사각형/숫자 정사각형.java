import java.util.Scanner;

/**
 * 문제
 * N×M크기의 직사각형이 있다. 각 칸에는 한 자리 숫자가 적혀 있다. 이 직사각형에서 꼭짓점에 쓰여 있는 수가 모두 같은 가장 큰 정사각형을 찾는 프로그램을 작성하시오. 이때, 정사각형은 행 또는 열에 평행해야 한다.
 *
 * 입력
 * 첫째 줄에 N과 M이 주어진다. N과 M은 50보다 작거나 같은 자연수이다. 둘째 줄부터 N개의 줄에 수가 주어진다.
 *
 * 출력
 * 첫째 줄에 정답 정사각형의 크기를 출력한다.
 *
 * 3 5
 * 42101
 * 22100
 * 22101
 *
 * -> 9
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int y = sc.nextInt();
        int x = sc.nextInt();
        char array[][] = new char[y][x];
        for(int k = 0; k < y; k++){
            String line = sc.next();
            char[] ch = line.toCharArray();
            array[k] = ch;
        }
        int max = y;    //5
        int etc = x;    //3
        boolean isConvert = false;
        if(y > x){
            max = x;    //3
            etc = y;    //5
            isConvert = true;
        }

        if(isConvert){
            char[][] convertArray = new char[max][etc];
            for(int i = 0; i < max; i++){
                for(int j = 0; j < etc; j++){
                    convertArray[i][j] = array[j][i];
                }
            }
            array = convertArray;
        }

        for(int k = max; k > 0; k--){
            for(int i = 0; i <= max - k; i++){
                for(int j = 0; j <= etc - k; j++){
                    if(array[i][j] == array[i + k - 1][j] && array[i][j] == array[i][j + k - 1]
                            && array[i][j] == array[i + k - 1][j + k - 1] && array[i + k - 1][j] == array[i][j + k - 1]
                            && array[i + k - 1][j] == array[i + k - 1][j + k - 1]
                            && array[i][j+ k - 1] == array[i + k - 1][j + k - 1]
                    ){
                        System.out.println(k * k);
                        return;
                    }
                }
            }
        }
        System.out.println(0);
    }
}
