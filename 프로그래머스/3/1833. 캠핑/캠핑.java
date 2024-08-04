import java.util.*;

class Solution {
    public int solution(int n, int[][] data) {
        Set<Integer> xPoints = new HashSet<>();
        Set<Integer> yPoints = new HashSet<>();
        
        for(int i = 0; i < n; i++){
            xPoints.add(data[i][0]);
            yPoints.add(data[i][1]);
        }
        
        List<Integer> xList = new ArrayList(xPoints);
        List<Integer> yList = new ArrayList(yPoints);
        Collections.sort(xList);
        Collections.sort(yList);
        
        int[][] dp = new int[n+1][n+1];
        
        // 좌표 압축
        for(int i = 0; i < n; i++){
            int x = xList.indexOf(data[i][0]) + 1;
            int y = yList.indexOf(data[i][1]) + 1;
            data[i][0] = x;
            data[i][1] = y;
            dp[x][y] = 1; // 쐐기 존재하는 곳을 누적합 배열을 1로 설정
        }
        
        // 2차원 누적합 = (0,0) ~ (i, j)까지의 쐐기의 개수
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                dp[i][j] = dp[i][j] + dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1];
            }
        }
        

        int count = 0;
        for(int i = 0; i < n - 1; i++){
            for(int j = i + 1; j < n; j++){
                if(data[i][0] != data[j][0] && data[i][1] != data[j][1]){ // 직사각형인 경우
                    int minX = Math.min(data[i][0], data[j][0]);
                    int minY = Math.min(data[i][1], data[j][1]);
                    int maxX = Math.max(data[i][0], data[j][0]);
                    int maxY = Math.max(data[i][1], data[j][1]);
                    
                    
                    //dp[a][b] ~ dp[i][j] 사이에 쐐기가 존재하는지의 여부 확인
                    //dp[i-1][j-1] - dp[a][j-1] - dp[i-1][b] + dp[a][b] > 0
                    if(minX >= maxX - 1 || minY >= maxY - 1){
                        count++;
                    }
                    else{
                        int res = dp[maxX - 1][maxY - 1] - dp[maxX - 1][minY] - dp[minX][maxY - 1] + dp[minX][minY];
                        if(res == 0){
                            count++;
                        }
                    }
                }
            }
        }
        
        // for(int[] line : dp){
        //     System.out.println(Arrays.toString(line));
        // }
        
        return count;
        
    }
}