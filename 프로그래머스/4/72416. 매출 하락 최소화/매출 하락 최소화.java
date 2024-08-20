import java.util.*;

class Solution {
    static List<List<Integer>> link;
    static int[] cost;
    static int[][] dp;
    static int INF = Integer.MAX_VALUE;

    static void dfs(int idx){
        dp[idx][0] = 0; //해당 노드가 참여X
        dp[idx][1] = cost[idx]; //해당 노드가 참여O
        
        boolean isForceSelect = true;
        int sub = INF; 
        for(int cidx : link.get(idx)){
            dfs(cidx);
            //서브트리의 루트가 참여하는 경우가 최적일 때
            if(dp[cidx][0] > dp[cidx][1]){ 
                dp[idx][0] += dp[cidx][1];
                dp[idx][1] += dp[cidx][1];
                
                //루트가 참여하게 되면 더 이상 서브값을 계산하지 않아도 됨
                isForceSelect = false;
            }
            //서브트리의 루트가 참여X 경우가 최적일 때
            else{
                dp[idx][0] += dp[cidx][0];
                dp[idx][1] += dp[cidx][0];
                
                //해당 노드의 값을 강제로 선택하는 대신 자식 노드 선택값(최적해)을 포기할 때 드는 최소 비용 계산
                sub = Math.min(sub, dp[cidx][1] - dp[cidx][0]); 
            }
                     
        }
        
        if(link.get(idx).size() != 0 && isForceSelect){
            dp[idx][0] += sub;
        }

    }
    
    public int solution(int[] sales, int[][] links) {
        
        link = new ArrayList();
        cost = new int[sales.length + 1];
        dp = new int[sales.length + 1][2];
        
        for(int i = 0; i < sales.length; i++){
            cost[i+1] = sales[i];
        }
        
        for(int i = 0; i <= sales.length; i++){
            link.add(new ArrayList());
        }
        
        for(int[] l : links){
            link.get(l[0]).add(l[1]);
        }
        
        dfs(1);
        
        // for(int[] line : dp){
        //     System.out.println(Arrays.toString(line));
        // }
        
        int answer = Math.min(dp[1][0], dp[1][1]);
        return answer;
    }
}