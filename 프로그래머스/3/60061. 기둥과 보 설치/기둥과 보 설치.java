import java.util.*;
class Solution {
    static int n;
    static int[][] column;
    static int[][] bow;
    
    static boolean availableColumn(int row, int col){
        // 최하단이거나, 아래에 기둥 존재
        if(row == 0 || column[row-1][col] == 0){
            return true;
        }          
        // 좌측에 보 존재
        else if(col-1 >= 0 && bow[row][col-1] == 1){
            return true;
        }
        // 동일위치에 보 존재
        else if(bow[row][col] == 1){
            return true;
        }
        return false;
    }
    static boolean availableBow(int row, int col){
        // 왼쪽 아래 기둥 존재
        if(row - 1 >= 0 && column[row-1][col] == 0){
            return true;
        }
        // 오른쪽 아래 기둥 존재
        else if(row - 1 >= 0 && col + 1 < n && column[row-1][col+1] == 0){
            return true;
        }
        // 왼쪽, 오른쪽 보 존재
        else if(col + 1 < n && col - 1 >= 0 && bow[row][col-1] == 1 && bow[row][col+1] == 1){
            return true;
        }
        return false;
    }
   
    public int[][] solution(int n, int[][] build_frame) {
        n++;
        this.n = n;
        column = new int[this.n][this.n];
        for(int[] line : column){
            Arrays.fill(line, -1);
        }
        bow = new int[this.n][this.n];
        for(int[] line : bow){
            Arrays.fill(line, -1);
        }
        
        for(int[] frame : build_frame){
            boolean add = frame[3] == 1 ? true : false;
            int row = frame[1];
            int col = frame[0];
            if(add){
                if(frame[2] == 0 && availableColumn(row, col)){
                    column[row][col] = 0;
                }
                else if(frame[2] == 1 && availableBow(row, col)){
                    bow[row][col] = 1;
                }
            }
            else{
                if(frame[2] == 0){
                    column[row][col] = -1;
                }
                else{
                    bow[row][col] = -1;
                }

                int startR = row - 1;
                int endR = row + 1;
                int startC = col - 1;
                int endC = col + 1;
                if(startR < 0) startR = 0;
                if(startC < 0) startC = 0;
                if(endR >= this.n) endR = this.n - 1;
                if(endC >= this.n) endC = this.n - 1;
                boolean rollback = false;
                for(int i = startR; i <= endR; i++){
                    for(int j = startC; j <= endC; j++){
                        if(column[i][j] == 0 && !availableColumn(i, j)){
                            rollback = true;
                            break;
                        }
                        else if(bow[i][j] == 1 && !availableBow(i, j)){
                             rollback = true;
                            break;
                        }
                    }
                }
                if(rollback){
                    if(frame[2] == 0){
                        column[row][col] = 0;
                    }
                    else{
                        bow[row][col] = 1;
                    }
                }
            }           
        }
        
        List<int[]> result = new ArrayList();
        for(int i = 0; i < this.n; i++){
            for(int j = 0; j < this.n; j++){
                if(column[i][j] != -1){
                    result.add(new int[]{j, i, 0});
                }
                if(bow[i][j] != -1){
                    result.add(new int[]{j, i, 1});
                }
            }
        }
        
        Collections.sort(result, new Comparator<int[]>(){
            public int compare(int[] a1, int[] a2){
                if(a1[0] == a2[0]){
                    if(a1[1] == a2[1]){
                        return a1[2] - a2[2];
                    }
                    return a1[1] - a2[1];
                }
                return a1[0] - a2[0];
            }
        });
        
        int[][] answer = new int[result.size()][3];
        for(int i = 0; i < answer.length; i++){
            answer[i] = result.get(i);
        }
            
        return answer;
    }
}