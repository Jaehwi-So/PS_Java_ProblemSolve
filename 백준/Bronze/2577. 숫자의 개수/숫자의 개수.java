import java.util.*;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int k = 1;
        for(int i = 0; i < 3; i++){
            k *= sc.nextInt();
        }

        char[] chs = Integer.toString(k).toCharArray();

        Map map = new HashMap<Character, Integer>();
        for(int i = 0; i <= 9; i++){
            map.put(i, 0);
        }

        for(char c : chs){
            int key = Character.getNumericValue(c);
            int val = (int)map.get(key) + 1;
            map.put(key, val);
        }

        for(int i = 0; i <= 9; i++){
            System.out.println(map.get(i));
        }
    }
}