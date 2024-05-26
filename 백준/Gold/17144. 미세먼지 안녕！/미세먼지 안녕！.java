import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point{
    int row;
    int col;
    int pval;
    public Point(int row, int col, int pval){
        this.row = row;
        this.col = col;
        this.pval = pval;
    }
}
public class Main {
    static int r;
    static int c;
    static int t;
    static int[][] matrix;

    static List<Point> pollutionList;

    static int[][] cleaner = new int[2][2];

    static int[] dUp = {-1, 0};
    static int[] dDown = {1, 0};
    static int[] dLeft = {0, -1};
    static int[] dRight = {0, 1};

    static int[] currentDist;
    static char currentDistChar = 'X';

    static void pollution(){
        for(Point p : pollutionList){
            int po = p.pval / 5;
            int[][] direction = {
                    {p.row - 1, p.col},
                    {p.row + 1, p.col},
                    {p.row, p.col - 1},
                    {p.row, p.col + 1}
            }; //왼쪽, 오른쪽, 위쪽, 아래쪽
            for(int[] d : direction){
                if((d[0] >= 0 && d[0] < r) && (d[1] >= 0 && d[1] < c) && (matrix[d[0]][d[1]] != -1)){
                    matrix[d[0]][d[1]] += po;
                    matrix[p.row][p.col] -= po;
                }
            }
        }
    }

    static void getPollutionPoint(){
        pollutionList = new ArrayList<>();
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(matrix[i][j] >= 5){
                    pollutionList.add(new Point(i, j, matrix[i][j]));
                }
            }
        }
    }

    static void print(){
        for(int[] line : matrix){
            System.out.println(Arrays.toString(line));
        }
        System.out.println();
    }

    static boolean changeDistance(boolean clock){
        if(clock == true){
            if(currentDistChar == 'U'){
                currentDist = dRight;
                currentDistChar = 'R';
                return false;
            }
            else if(currentDistChar == 'R'){
                currentDist = dDown;
                currentDistChar = 'D';
                return false;
            }
            else if(currentDistChar == 'D'){
                currentDist = dLeft;
                currentDistChar = 'L';
                return false;
            }
            else if(currentDistChar == 'L'){
                currentDist = dUp;
                currentDistChar = 'X';
                return true;
            }
            else{
                currentDist = dUp;
                currentDistChar = 'U';
                return false;
            }
        }
        else{
            if(currentDistChar == 'D'){
                currentDist = dRight;
                currentDistChar = 'R';
                return false;
            }
            else if(currentDistChar == 'R'){
                currentDist = dUp;
                currentDistChar = 'U';
                return false;
            }
            else if(currentDistChar == 'U'){
                currentDist = dLeft;
                currentDistChar = 'L';
                return false;
            }
            else if(currentDistChar == 'L'){
                currentDist = dDown;
                currentDistChar = 'X';
                return true;
            }
            else{
                currentDist = dDown;
                currentDistChar = 'D';
                return false;
            }
        }
    }
    static void move(boolean clock){

        int row = clock == true ? cleaner[0][0] - 1 : cleaner[1][0] + 1;
        int col = 0;
        int rowBound = clock == true ? row + 2 : row - 2;
        currentDistChar = 'X';
        changeDistance(clock);
//        System.out.println(row + " " + col);

        while(true){
            int crow = row + currentDist[0];
            int ccol = col + currentDist[1];
            if((crow < 0 || crow >= r) || (ccol < 0 || ccol >= c) || (crow == rowBound)){
                changeDistance(clock);
                crow = row + currentDist[0];
                ccol = col + currentDist[1];
            }
            if(matrix[crow][ccol] == -1){
                matrix[row][col] = 0;
                break;
            }

            matrix[row][col] = matrix[crow][ccol];
            row = crow;
            col = ccol;
//            System.out.println(row + " " + col);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        matrix = new int[r][c];

        int cidx = 0;
        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < c; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if(matrix[i][j] == -1){
                    cleaner[cidx++] = new int[]{i, j};
                }
            }
        }


        for(int i = 0; i < t; i++){
            getPollutionPoint();
            pollution();
//            print();
            move(true);
            move(false);
//            print();
        }

        int result = 0;
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(matrix[i][j] != -1) {
                    result += matrix[i][j];
                }
            }
        }

        System.out.println(result);


    }
}