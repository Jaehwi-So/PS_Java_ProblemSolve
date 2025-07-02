import java.io.*;
import java.util.*;
class Chess{
    int index;
    int row;
    int col;
    int direct;

    public Chess(int index, int row, int col, int direct){
        this.index = index;
        this.row = row;
        this.col = col;
        this.direct = direct;
    }
}

public class Main {
    static int n;
    static int k;
    static int[][] board;
    static Chess[] chesses;
    static Stack<Integer>[][] matrix;

    static int[] dy = {0, 0, 0, -1, 1};
    static int[] dx = {0, 1, -1, 0, 0};

    static boolean move(int index){

//        for(int i = 1; i <= k; i++){
//            System.out.println(chesses[i].row + " " + chesses[i].col + " (" + chesses[i].direct + ") " + matrix[chesses[i].row][chesses[i].col]);
//        }
//        System.out.println("Move " + index);

        int row = chesses[index].row;
        int col = chesses[index].col;
        int d = chesses[index].direct;
        Stack<Integer> stack = matrix[row][col];

        int nrow = row + dy[d];
        int ncol = col + dx[d];
        if(nrow < 1 || ncol < 1 || nrow > n || ncol > n || board[nrow][ncol] == 2){
            nrow = row - dy[d];
            ncol = col - dx[d];
            if(chesses[index].direct < 3){
                chesses[index].direct = chesses[index].direct == 1 ? 2 : 1;
            }
            else{
                chesses[index].direct = chesses[index].direct == 3 ? 4 : 3;
            }
            if(nrow < 1 || ncol < 1 || nrow > n || ncol > n || board[nrow][ncol] == 2){
                return false;
            }
        }

        List<Integer> temp = new ArrayList<>();
        while(!stack.isEmpty()){
            int current = stack.pop();
            temp.add(current);
            if(current == index){
                break;
            }
        }

        boolean reverse = board[nrow][ncol] == 1 ? true : false;

        Stack<Integer> nextStack = matrix[nrow][ncol];
        if(reverse){
            for(int i = 0; i < temp.size(); i++){
                nextStack.push(temp.get(i));
                chesses[temp.get(i)].row = nrow;
                chesses[temp.get(i)].col = ncol;
            }
        }
        else{
            for(int i = temp.size() - 1; i >= 0; i--){
                nextStack.push(temp.get(i));
                chesses[temp.get(i)].row = nrow;
                chesses[temp.get(i)].col = ncol;
            }
        }




        if(nextStack.size() >= 4){
            return true;
        }
        return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];
        matrix = new Stack[n+1][n+1];
        chesses = new Chess[k+1];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                matrix[i][j] = new Stack<>();
            }
        }

        for(int i = 1; i <= k; i++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int direct = Integer.parseInt(st.nextToken());
            chesses[i] = new Chess(i, row, col, direct);
            matrix[row][col].push(i);
        }


        int turn = 0;
        while(true){
            boolean exit = false;
            for(int i = 1; i <= k; i++){
                exit = move(i);
                if(exit){
                    break;
                }
            }
            turn++;
            if(exit || turn > 1000){
                break;
            }
        }

        if(turn > 1000) turn = -1;
        System.out.println(turn);


    }
}