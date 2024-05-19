import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


class Dice{
    int[] numbers = {0, 0, 0, 0, 0, 0, 0};
    int[] points = {0, 1, 2, 3, 4, 5, 6};

    void move(int command){
        switch (command){
            case 1 :
                right();
                break;
            case 2 :
                left();
                break;
            case 3 :
                up();
                break;
            case 4 :
                down();
                break;
            default:
                break;
        }
    }
    //오른쪽
    void swap(int a, int b){
        int temp = points[a];
        points[a] = points[b];
        points[b] = temp;
    }
    void right(){
        swap(1, 3);
        swap(6, 4);
        swap(3, 4);
    }

    //왼쪽
    void left(){
        swap(1, 4);
        swap(6, 3);
        swap(3, 4);
    }

    //위쪽
    void up(){
        swap(1, 2);
        swap(6, 5);
        swap(2, 5);
    }


    //아래쪽
    void down(){
        swap(1, 5);
        swap(6, 2);
        swap(2, 5);
    }

}
class Point{
    int row;
    int col;

    public Point(int row, int col){
        this.row = row;
        this.col = col;
    }
}

public class Main {
    static int[][] map;

    static int[][] GO = {{0, 0}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}}; //오른쪽, 왼쪽, 위쪽, 아래쪽


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        int c = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        Point current = new Point(c, r);

        int k = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] commands = new int[k];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++){
            commands[i] = Integer.parseInt(st.nextToken());
        }
        Dice dice = new Dice();
        int top = 1;
        int bottom = 6;
        StringBuilder sb = new StringBuilder();
        for(int command : commands){
            int row = current.row + GO[command][0];
            int col = current.col + GO[command][1];
            if((row < 0 || row >= n) || (col < 0 || col >= m)){
                continue;
            }
            current.row = row;
            current.col = col;
            dice.move(command);

            if(map[row][col] == 0){
                map[row][col] = dice.numbers[dice.points[top]];
            }
            else{
                dice.numbers[dice.points[top]] = map[row][col];
                map[row][col] = 0;
            }

            sb.append(dice.numbers[dice.points[bottom]]).append("\n");
        }

        System.out.println(sb);
    }
}