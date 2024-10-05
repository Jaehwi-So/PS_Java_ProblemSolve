import java.util.*;

class Solution {
    static Map<String, Integer> map = new HashMap();
    
    static void init(){
        map.put("java", 0);
        map.put("python", 1);
        map.put("cpp", 2);
        map.put("backend", 0);
        map.put("frontend", 1);
        map.put("junior", 0);
        map.put("senior", 1);
        map.put("chicken", 0);
        map.put("pizza", 1);
    }
    
    static int binarySearch(int score, List<Integer> list){
        int left = 0;
        int right = list.size();
        while(left < right){
            int mid = (left + right) / 2;
            if(list.get(mid) < score){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }
        return right;
    }
    

    public int[] solution(String[] info, String[] query) {
            
        List<Integer>[][][][] list = new ArrayList[3][2][2][2];
        int[] size = {3, 2, 2, 2};
        init();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 2; j++){
                for(int k = 0; k < 2; k++){
                    for(int l = 0; l < 2; l++){
                        list[i][j][k][l] = new ArrayList();
                    }
                }
            }
        }
        
        for(int k = 0; k < info.length; k++){
            String[] s = info[k].split(" ");    
            int[] idx = new int[5];
            for(int i = 0; i < 4; i++){
                idx[i] = map.get(s[i]);
            }
            idx[4] = Integer.parseInt(s[4]);

            
            list[idx[0]][idx[1]][idx[2]][idx[3]].add(idx[4]);
            
        }
        
        
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 2; j++){
                for(int k = 0; k < 2; k++){
                    for(int l = 0; l < 2; l++){
                        Collections.sort(list[i][j][k][l]);
                    }
                }
            }
        }
        
        int[] answer = new int[query.length];
        for(int k = 0; k < query.length; k++){
            String[] str = query[k].split(" and ");
            String[] s = new String[5];
            for(int i = 0; i < 3; i++){
                s[i] = str[i];
            }
            String[] ss = str[3].split(" ");
            s[3] = ss[0];
            s[4] = ss[1];
            
            List<Integer>[] idxs = new ArrayList[4];
            
            for(int i = 0; i < 4; i++){
                idxs[i] = new ArrayList();
                if(s[i].equals("-")){
                    for(int j = 0; j < size[i]; j++){
                        idxs[i].add(j);
                    }
                }
                else{
                    idxs[i].add(map.get(s[i]));
                }
            }
            

            
            int result = 0;
            for(int i1 : idxs[0]){
                for(int i2 : idxs[1]){
                    for(int i3 : idxs[2]){
                        for(int i4 : idxs[3]){
                            List current = list[i1][i2][i3][i4];
                            int length = current.size();
                            int c = length - binarySearch(Integer.parseInt(s[4]), current);
                            result += c;
                        }
                    }
                }
            }
            answer[k] = result;
        }
        

        return answer;
    }
}