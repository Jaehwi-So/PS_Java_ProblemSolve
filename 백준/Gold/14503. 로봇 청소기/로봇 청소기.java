import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Point{
    int row;
    int col;
    public Point(int row, int col){
        this.row = row;
        this.col = col;
    }
}
public class Main {
    static int[][] array;
    static int n;
    static int m;
    static int status;  //0(위), 1(왼쪽), 2(아래), 3(오른쪽) 방향
    static int[] dx = {0, -1, 0, 1}; //위 -> 왼쪽 -> 아래 -> 오른쪽
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Point current = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        status = Integer.parseInt(st.nextToken());
        if(status == 1){
            status = 3;
        }
        else if(status == 3){
            status = 1;
        }
        array = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            int cr = current.row;
            int cc = current.col;
            if(array[cr][cc] == 0){
                array[cr][cc] = 2;
            }
            boolean isDirty = false;
            for(int i = status + 1; i < status + 5; i++){ //0 -> 1 2 3 4
                int direction = i % 4;
                int row = cr + dy[direction];
                int col = cc + dx[direction];
                if(array[row][col] == 0){
                    isDirty = true;
                    current.row = row;
                    current.col = col;
                    status = direction;
                    break;
                }
            }
            if(!isDirty){
                int d = (status + 2) % 4;
                int row = cr + dy[d];
                int col = cc + dx[d];
                if(array[row][col] == 1){
                    break;
                }
                else{
                    current.row = row;
                    current.col = col;
                }
            }

//            for(int[] line : array){
//                System.out.println(Arrays.toString(line));
//            }
//            System.out.println("=======");
        }



        int count = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(array[i][j] == 2){
                    count++;
                }
            }
        }
        System.out.println(count);

    }
}
