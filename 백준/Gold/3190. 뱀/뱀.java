import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Point{
    int row;
    int col;

    public Point(int row, int col){
        this.row = row;
        this.col = col;
    }
}

public class Main {
    static int board[][];
    static final int[] LEFT = new int[]{0, -1};
    static final int[] RIGHT = new int[]{0, 1};
    static final int[] UP = new int[]{-1, 0};
    static final int[] DOWN = new int[]{1, 0};

    static int[] direction = RIGHT;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            board[row-1][col-1] = 2;
        }
        board[0][0] = 0;

        st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int[] times = new int[l + 1];
        char[] directions = new char[l + 1];
        int[][] commands = new int[l + 1][2];

        times[0] = 0;
        directions[0] = 'R';
        commands[0] = RIGHT;

        for(int i = 1; i <= l; i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            char d = st.nextToken().charAt(0);
            times[i] = t;
            if(d == 'L'){
                if(directions[i-1] == 'L'){
                    directions[i] = 'D';
                    commands[i] = DOWN;
                }
                else if(directions[i-1] == 'R'){
                    directions[i] = 'U';
                    commands[i] = UP;
                }
                else if(directions[i-1] == 'U'){
                    directions[i] = 'L';
                    commands[i] = LEFT;
                }
                else if(directions[i-1] == 'D'){
                    directions[i] = 'R';
                    commands[i] = RIGHT;
                }
            }
            else{
                if(directions[i-1] == 'L'){
                    directions[i] = 'U';
                    commands[i] = UP;
                }
                else if(directions[i-1] == 'R'){
                    directions[i] = 'D';
                    commands[i] = DOWN;
                }
                else if(directions[i-1] == 'U'){
                    directions[i] = 'R';
                    commands[i] = RIGHT;
                }
                else if(directions[i-1] == 'D'){
                    directions[i] = 'L';
                    commands[i] = LEFT;
                }
            }
        }

        int time = 0;
        Deque<Point> deque = new ArrayDeque<>();
        deque.add(new Point(0, 0));
        board[0][0] = 1;
        int eventTime = 1;

        while(true){

            Point current = deque.peekLast();
            Point newPoint = new Point(current.row + direction[0], current.col + direction[1]);

            time++;
//            System.out.println("Time : " + time);

            if(newPoint.row < 0 || newPoint.col < 0){
                break;
            }
            if(newPoint.row >= n || newPoint.col >= n){
                break;
            }

            if(board[newPoint.row][newPoint.col] == 0){
                Point remove = deque.removeFirst();
                board[remove.row][remove.col] = 0;
            }
            if(board[newPoint.row][newPoint.col] == 1){
                break;
            }

            board[newPoint.row][newPoint.col] = 1;
            deque.addLast(newPoint);


//            for(int[] line : board){
//                System.out.println(Arrays.toString(line));
//            }
//            System.out.println();


            if(eventTime <= l && time == times[eventTime]){
//                System.out.println("Turn : " + directions[eventTime]);
                direction = commands[eventTime];
                eventTime++;
            }

        }

        System.out.println(time);


    }
}
