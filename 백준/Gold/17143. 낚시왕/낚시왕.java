import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Shark{

    int velocity;
    int direct; //1, 2, 3, 4 -> 위, 아래, 오른쪽, 왼쪽
    int weight;
    int row;
    int col;

    public Shark(int velocity, int direct, int weight, int row, int col){
        this.velocity = velocity;
        this.direct = direct;
        this.weight = weight;
        this.row = row;
        this.col = col;
    }

    public void move(){
        row += (Main.dy[direct] * velocity);
        col += (Main.dx[direct] * velocity);

        // 5 (9 -> 1, 8 -> 0, 7 -> 1, 6 -> 2, 5 -> 3, 4 -> 4 , 3 -> 3)
        if(row < 0){
            row = Math.abs(row);
            direct = 2;
        }
        if(row >= Main.r){
            int k = row % (Main.r - 1);
            int p = row / (Main.r - 1);
            if(p % 2 == 0){
                row = k;
            }
            else{
                row = (Main.r - 1) - k;
                direct = direct == 1 ? 2 : 1;
            }
        }

        if(col < 0){
            col = Math.abs(col);
            direct = 3;
        }
        if(col >= Main.c){
            int k = col % (Main.c - 1);
            int p = col / (Main.c - 1);
            if(p % 2 == 0){
                col = k;
            }
            else{
                col = (Main.c - 1) - k;
                direct = direct == 3 ? 4 : 3;
            }
        }
    }
}
public class Main {
    static int[] dy = {0, -1, 1, 0, 0};
    static int[] dx = {0, 0, 0, 1, -1};
    static int r;
    static int c;
    static int m;
    static Shark[][] array;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        array = new Shark[r][c];
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            array[row][col] = new Shark(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), row, col);
        }

        int pos = 0;
        int result = 0;
        while(pos < c){

//            System.out.println();
//            for(int i = 0; i < r; i++){
//                for(int j = 0; j < c; j++){
//                    if(array[i][j] != null){
//                        System.out.print(array[i][j].weight + " ");
//                    }
//                    else{
//                        System.out.print(0 + " ");
//                    }
//                }
//                System.out.println();
//            }


            for(int i = 0; i < r; i++){
                if(array[i][pos] != null){
                    result += array[i][pos].weight;
                    array[i][pos] = null;
                    break;
                }
            }

            Stack<Shark> stack = new Stack<>();
            for(int i = 0; i < r; i++){
                for(int j = 0; j < c; j++){
                    Shark shark = array[i][j];
                    if(shark != null){
                        shark.move();
                        stack.push(new Shark(shark.velocity, shark.direct, shark.weight, shark.row, shark.col));
                        array[i][j] = null;
                    }
                }
            }

            while(!stack.isEmpty()){
                Shark shark = stack.pop();
                if(array[shark.row][shark.col] != null){
                    if(array[shark.row][shark.col].weight < shark.weight){
                        array[shark.row][shark.col] = shark;
                    }
                }
                else{
                    array[shark.row][shark.col] = shark;
                }
            }
            pos++;
        }
        System.out.println(result);
    }
}
