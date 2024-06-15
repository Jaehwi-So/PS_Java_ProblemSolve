import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n; //땅의 크기
    static int m; //나무의 개수
    static int k; //몇 년 이상

    static int[][] ingredient;
    static int[][] array;
    static PriorityQueue<Integer>[][] trees;

    static int[] dx = {0, 0, -1, 1, 1, 1, -1, -1};
    static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};

    static void status(){
        System.out.println("INGRED");
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("TREES");
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                System.out.print(trees[i][j].size() + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        ingredient = new int[n+1][n+1];
        array = new int[n+1][n+1];
        trees = new PriorityQueue[n+1][n+1];
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                ingredient[i][j] = Integer.parseInt(st.nextToken());
                trees[i][j] = new PriorityQueue();
                array[i][j] = 5;
            }
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            trees[r][c].offer(age);
        }



        Queue<Integer> temp = new LinkedList<>();
        while(k > 0){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    int dead = 0;
                    while(!trees[i][j].isEmpty()){
                        int age = trees[i][j].poll();
                        if(age <= array[i][j]){
                            array[i][j] -= age;
                            age++;
                            if(age % 5 == 0){
                                for(int k = 0; k < 8; k++){
                                    int row = i + dy[k];
                                    int col = j + dx[k];
                                    if(row >= 1 && row <= n && col >= 1 && col <= n){
                                        if(row < i || row == i && col < j){
                                            trees[row][col].offer(1);
                                        }
                                        else{
                                            trees[row][col].offer(0);
                                        }

                                    }
                                }
                            }
                            temp.offer(age);
                        }
                        else{
                            dead += (age / 2);
                        }
                    }
                    while(!temp.isEmpty()){
                        trees[i][j].offer(temp.poll());
                    }
                    array[i][j] += (dead + ingredient[i][j]);
                }
            }
            k--;
        }


        int count = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                count += trees[i][j].size();
            }
        }
        System.out.println(count);

    }
}