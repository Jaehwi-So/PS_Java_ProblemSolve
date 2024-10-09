import java.util.*;

class Solution {
    static int n;
    static int m;
    static int k;
    static int padding;
    static int[][][] matrix;
    
    public boolean solution(int[][] key, int[][] lock) {
        this.n = lock.length;
        this.m = key.length;
        this.padding = (n - 1);
        this.k = m + (padding * 2);
        this.matrix = new int[4][k][k];
        // for(int[][] mat : matrix){
        //     for(int[] line : mat){
        //         Arrays.fill(line, -1);
        //     }
        // }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < m; j++){
                matrix[0][i+padding][j+padding] = key[i][j];
            }
        }
        
        boolean over = true;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(lock[i][j] == 0){
                    over = false;
                    break;
                } 
            }
        }
        if(over){
            return true;
        }
                
        for(int l = 1; l < 4; l++){
            int[][] temp = new int[k][k];
            for(int j = 0; j < k; j++){
                for(int i = k-1; i >= 0; i--){
                    int p = Math.abs(i - (k-1));
                    temp[j][p] = matrix[l-1][i][j];
                }
            }
            matrix[l] = temp;
        }
        
        for(int[][] array : matrix){
            for(int[] line : array){
                System.out.println(Arrays.toString(line));
            }
            System.out.println();
        }
        
        
        boolean success = false;
        for(int l = 0; l < 4; l++){
            for(int i = 0; i < k - padding; i++){
                for(int j = 0; j < k - padding; j++){
                    
                    boolean flag = true;
                    for(int a = 0; a < n; a++){
                        for(int b = 0; b < n; b++){
                            int x = i+a;
                            int y = j+b;
                            if(lock[a][b] == 0){
                                if(matrix[l][x][y] != 1){
                                    flag = false;
                                    break;
                                }
                            }
                            else if(lock[a][b] == 1){
                                if(matrix[l][x][y] == 1){
                                    flag = false;
                                    break;
                                }
                            }
                            
                        }
                    }
                    if(flag == true){
                        success = true;
                        System.out.println(l + " " + i + " " + j);
                        break;
                    }                    
                }
                
                if(success == true) break;
            }         
            if(success == true) break;        
        }
        
        return success;
    }
}
