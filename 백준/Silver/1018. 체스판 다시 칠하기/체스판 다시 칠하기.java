import java.util.*;

class Main {
    static char[][] makeBoard(int width, int height, int whiteFirst){
        char[][] board = new char[height][width];

        char color;

        for(int i = 0; i < height; i++){
            if(i % 2 == 0 + whiteFirst){
                color = 'B';
            }
            else{
                color = 'W';
            }
            for(int j = 0; j < width; j++){
                board[i][j] = color;
                color = color == 'W' ? 'B' : 'W';
            }
        }

        return board;

    }



    static int caseCk(char[][] board1, char[][] board2){

        int result = Integer.MAX_VALUE;

        for(int k = 0; k < board1.length - 7; k++){
            for(int l = 0; l < board1[k].length - 7; l++){
                int count = 0;
                for(int i = k; i < k + 8; i++){
                    for(int j = l; j < l + 8; j++){
                        if(board1[i][j] != board2[i][j]){
                            count++;
                        }
                    }
                }
                if(count < result){
                    result = count;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int height = sc.nextInt();
        int width = sc.nextInt();
        char[][] board = new char[height][width];

        sc.nextLine();
        for(int i = 0; i < height; i++){
            String s = sc.nextLine();
            board[i] = s.toCharArray();
        }



        char[][] whiteBoard = makeBoard(width, height, 1);
        char[][] blackBoard = makeBoard(width, height, 0);

        int blackCnt = caseCk(board, blackBoard);
        int whiteCnt = caseCk(board, whiteBoard);

        if(blackCnt < whiteCnt){
            System.out.println(blackCnt);
        }
        else{
            System.out.println(whiteCnt);
        }

    }
}
