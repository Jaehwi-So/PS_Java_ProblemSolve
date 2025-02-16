import java.io.*;
import java.util.*;

class Box{
    char[] color; //위, 아래, 앞, 뒤, 왼쪽, 오른쪽 (UDFBLR)

    void rotate(int command){
        if(command == 0) rotateFBLRLeft(); // 위 반시계방향, 아래 시계방향
        else if(command == 1) rotateFBLRRight(); // 위 시계방향, 아래 반시계방향
        else if(command == 2) rotateUDLRLeft(); // 뒤 반시계방향, 앞 시계방향
        else if(command == 3) rotateUDLRRight(); // 뒤 시계방향, 앞 반시계방향
        else if(command == 4) rotateUDFBLeft(); // 왼쪽 반시계방향, 오른쪽 시계방향
        else if(command == 5) rotateUDFBRight(); //왼쪽 시계방향, 오른쪽 반시계방향
    }

    public Box(Box b){
        this.color = Arrays.copyOf(b.color, 6);
    }
    public Box(){
        this.color = new char[]{'w', 'y', 'r', 'o', 'g', 'b'};
    }

    void rotateFBLRLeft(){ // 윗면기준 시계반대 (앞, 뒤, 왼, 오른)
        char[] temp = Arrays.copyOf(color, 6);
        temp[2] = color[4]; //앞 = 왼
        temp[3] = color[5]; //뒤 = 오른
        temp[4] = color[3]; //왼 = 뒤
        temp[5] = color[2]; //오른 = 앞
        color = temp;
    }

    void rotateFBLRRight(){ // 윗면기준 시계방향 (앞, 뒤, 왼, 오른)
        char[] temp = Arrays.copyOf(color, 6);
        temp[2] = color[5]; //앞 = 오른
        temp[3] = color[4]; //뒤 = 왼
        temp[4] = color[2]; //왼 = 앞
        temp[5] = color[3]; //오른 = 뒤
        color = temp;
    }

    void rotateUDLRLeft(){ // 뒷면기준 시계반대 (위, 아래, 왼, 오른)
        char[] temp = Arrays.copyOf(color, 6);
        temp[0] = color[4]; //위 = 왼
        temp[1] = color[5]; //아래 = 오른
        temp[4] = color[1]; //왼 = 아래
        temp[5] = color[0]; //오른 = 위
        color = temp;
    }

    void rotateUDLRRight(){ // 뒷면기준 시계 (위, 아래, 왼, 오른)
        char[] temp = Arrays.copyOf(color, 6);
        temp[0] = color[5]; //위 = 오른
        temp[1] = color[4]; //아래 = 왼
        temp[4] = color[0]; //왼 = 위
        temp[5] = color[1]; //오른 = 아래
        color = temp;
    }




    void rotateUDFBLeft(){ // 왼쪽면기준 시계반대 (위, 아래, 앞, 뒤)
        char[] temp = Arrays.copyOf(color, 6);
        temp[0] = color[2]; //위 = 앞
        temp[1] = color[3]; //아래 = 뒤
        temp[2] = color[1]; //앞 = 아래
        temp[3] = color[0]; //뒤 = 위
        color = temp;
    }

    void rotateUDFBRight(){ // 왼쪽면기준 시계방향 (위, 아래, 앞, 뒤)
        char[] temp = Arrays.copyOf(color, 6);
        temp[0] = color[3]; //위 = 뒤
        temp[1] = color[2]; //아래 = 앞
        temp[2] = color[0]; //앞 = 위
        temp[3] = color[1]; //뒤 = 어래
        color = temp;
    }
}



public class Main {
    static int n = 3;
    static int m;
    static Box[][][] cubes; //위, 뒤, 왼

    static Box[][] rotateMatrix(Box[][] boxes, int command, boolean clock){
        Box[][] temp = new Box[n][n];
        int index = 0;
        if(clock){ //시계
            for(int col = 0; col < n; col++){
                for(int row = n-1; row >= 0; row--){
                    int tr = index / n;
                    int tc = index % n;
                    temp[tr][tc] = boxes[row][col];
                    temp[tr][tc].rotate(command);
                    index++;
                }
            }
        }
        else{   //반시계
            for(int col = n-1; col >= 0; col--){
                for(int row = 0; row < n; row++){
                    int tr = index / n;
                    int tc = index % n;
                    temp[tr][tc] = boxes[row][col];
                    temp[tr][tc].rotate(command);
                    index++;
                }
            }
        }
        return temp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int test = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        for(int t = 0; t < test; t++){
            cubes = new Box[n][n][n];

            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    for(int k = 0; k < n; k++){
                        cubes[i][j][k] = new Box();
                    }
                }
            }

            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());

            String[] commands = new String[m];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < m; i++){
                commands[i] = st.nextToken();
            }
            for(String command : commands){
                char place = command.charAt(0);
                char direct = command.charAt(1);
                Box[][] boxes = new Box[n][n];
                boolean clock = direct == '+' ? true : false;

                // 옆쪽
                if(place == 'L' || place == 'R'){
                    int k = place == 'L' ? 0 : n-1;
                    for(int i = 0; i < n; i++){ //z
                        for(int j = 0; j < n; j++){ //y
                            boxes[i][j] = new Box(cubes[i][j][k]);
                        }
                    }
                    int c = 4; //왼쪽 반시계, 오른쪽 시계
                    if(place == 'L' && direct == '+' || place == 'R' && direct == '-') c = 5; //왼쪽 시계, 오른쪽 반시계
                    if(place == 'R') clock = !clock;
                    boxes = rotateMatrix(boxes, c, clock);

                    for(int i = 0; i < n; i++){
                        for(int j = 0; j < n; j++){
                            cubes[i][j][k] = boxes[i][j];
                        }
                    }
                }

                // 앞뒤쪽
                else if(place == 'B' || place == 'F'){
                    int k = place == 'B' ? 0 : n-1;
                    for(int i = 0; i < n; i++){ //z
                        for(int j = 0; j < n; j++){ //x
                            boxes[i][j] = new Box(cubes[i][k][j]);
                        }
                    }
                    int c = 2; //뒤 반시계, 앞 시계
                    if(place == 'B' && direct == '+' || place == 'F' && direct == '-') c = 3; //앞 시계, 뒤 반시계
                    if(place == 'B') clock = !clock;
                    boxes = rotateMatrix(boxes, c, clock);
                    for(int i = 0; i < n; i++){
                        for(int j = 0; j < n; j++){
                            cubes[i][k][j] = boxes[i][j];
                        }
                    }
                }

                // 위쪽
                else if(place == 'U' || place == 'D'){
                    int k = place == 'U' ? 0 : n-1;
                    for(int i = 0; i < n; i++){
                        for(int j = 0; j < n; j++){
                            boxes[i][j] = new Box(cubes[k][i][j]);
                        }
                    }
                    int c = 0; //위 반시계, 아래 시계
                    if(place == 'U' && direct == '+' || place == 'D' && direct == '-') c = 1; //위 시계, 아래 반시계
                    if(place == 'D') clock = !clock;
                    boxes = rotateMatrix(boxes, c, clock);
                    for(int i = 0; i < n; i++){
                        for(int j = 0; j < n; j++){
                            cubes[k][i][j] = boxes[i][j];
                        }
                    }
                }

            }


//            sb.append("\nTop\n");
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    sb.append(cubes[0][i][j].color[0]);
                }
                sb.append("\n");
            }
//            sb.append("\nLeft\n");
//            for(int i = 0; i < n; i++){
//                for(int j = 0; j < n; j++){
//                    sb.append(cubes[i][j][0].color[4]);
//                }
//                sb.append("\n");
//            }
//            sb.append("\nFront\n");
//            for(int i = 0; i < n; i++){
//                for(int j = 0; j < n; j++){
//                    sb.append(cubes[i][n-1][j].color[2]);
//                }
//                sb.append("\n");
//            }
//            sb.append("\n");
        }

        System.out.println(sb);

    }
}