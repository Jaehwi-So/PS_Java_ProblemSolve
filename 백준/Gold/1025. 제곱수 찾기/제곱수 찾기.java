
import java.util.*;

/**
 * 문제
 * N행 M열의 표 A가 있고, 표의 각 칸에는 숫자가 하나씩 적혀있다.
 *
 * 연두는 서로 다른 1개 이상의 칸을 선택하려고 하는데, 행의 번호가 선택한 순서대로 등차수열을 이루고 있어야 하고, 열의 번호도 선택한 순서대로 등차수열을 이루고 있어야 한다. 이렇게 선택한 칸에 적힌 수를 순서대로 이어붙이면 정수를 하나 만들 수 있다.
 *
 * 연두가 만들 수 있는 정수 중에서 가장 큰 완전 제곱수를 구해보자. 완전 제곱수란 어떤 정수를 제곱한 수이다.
 *
 * 입력
 * 첫째 줄에 N, M이 주어진다. 둘째 줄부터 N개의 줄에는 표에 적힌 숫자가 1번 행부터 N번 행까지 순서대로 한 줄에 한 행씩 주어진다. 한 행에 적힌 숫자는 1번 열부터 M번 열까지 순서대로 주어지고, 공백없이 모두 붙여져 있다.
 *
 * 출력
 * 첫째 줄에 연두가 만들 수 있는 가장 큰 완전 제곱수를 출력한다. 만약, 완전 제곱수를 만들 수 없는 경우에는 -1을 출력한다.
 *
 * 제한
 * 1 ≤ N, M ≤ 9
 * 표에 적힌 숫자는 0보다 크거나 같고, 9보다 작거나 같다.
 */
public class Main {
    public static int max = -1;
    public static int x = 0;
    public static int y = 0;
    public static char[][] array;

    public static Set<List<Integer>> xSet = new HashSet<>();
    public static Set<List<Integer>> ySet = new HashSet<>();

    public static int arrayMax;


    static boolean isMode(int number){
        double result = Math.sqrt(number);
        if(Math.ceil(result) == (int)result){
            return true;
        }
        else{
            return false;
        }
    }

    static void compareMax(String s){
        int number = Integer.parseInt(s);
//        System.out.println(s);
        if(isMode(number)){
            if(number > max){
                max = number;
            }
        }
    }

    static List<Integer> reverseList(List<Integer> list){
        List<Integer> tmp = new ArrayList<>();
        for(int i = list.size() - 1; i >= 0; i--){
            tmp.add(list.get(i));
        }
        return tmp;
    }

    static void diagExecute(int l, int k, int startX, int startY){
        int xIdx = startX;
        int yIdx = startY;
        String str = "";
        while(xIdx >= 0 && xIdx < x && yIdx >= 0 && yIdx < y){
            str += array[yIdx][xIdx];
            xIdx += l;
            yIdx += k;
        }
        compareMax(str);
    }

    static String reverse(String s){
        char[] str = s.toCharArray();
        char newer[] = new char[str.length];
        for(int i = 0; i < str.length; i++){
            newer[str.length - 1 - i] = str[i];
        }
        return new String(newer);
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        y = sc.nextInt();
        x = sc.nextInt();
        array = new char[y][x];
        for(int i = 0; i < y; i++){
            String s = sc.next();
            array[i] = s.toCharArray();
        }

        arrayMax = x < y ? y : x;


        List<Integer> tmp;

        for(int k = 0; k < x; k++){
            tmp = new ArrayList<>();
            for(int i = 0; i < arrayMax; i++){
                tmp.add(k);
            }
            xSet.add(tmp);
        }

        for(int k = 0; k < y; k++){
            tmp = new ArrayList<>();
            for(int i = 0; i < arrayMax; i++){
                tmp.add(k);
            }
            ySet.add(tmp);
        }


        for(int l = 1; l < x; l++){ //증가시킬 인덱스
            for(int i = 0; i < x; i++){ //시작할 인덱스
                List<Integer> list = new ArrayList<>();
                for(int k = i; k < x; k +=l){ //등차 수열
                    list.add(k);
                }
                xSet.add(list);
            }
            for(int i = x - 1; i >= 0; i--){ //시작할 인덱스
                List<Integer> list = new ArrayList<>();
                for(int k = i; k >= 0; k -=l){ //등차 수열
                    list.add(k);
                }
                xSet.add(list);
            }

        }




        for(int l = 1; l < y; l++){
            for(int i = 0; i < y; i++){
                List<Integer> list = new ArrayList<>();
                for(int k = i; k < y; k +=l){
                    list.add(k);
                }
                ySet.add(list);
            }
            for(int i = y - 1; i >= 0; i--){ //시작할 인덱스
                List<Integer> list = new ArrayList<>();
                for(int k = i; k >= 0; k -=l){ //등차 수열
                    list.add(k);
                }
                ySet.add(list);
            }
        }

//        for(List<Integer> x : xSet){
//            System.out.println(x.toString());
//        }
//
//        System.out.println();
//
//        for(List<Integer> y : ySet){
//            System.out.println(y.toString());
//        }

        for(List<Integer> x : xSet){
            for(List<Integer> y : ySet){
                int max = x.size() > y.size() ? y.size() : x.size();
                String str = "";
                for(int i = 0; i < max; i++) {
                    str += array[y.get(i)][x.get(i)];
                    compareMax(str);
                }
            }
        }
        System.out.println(max);
    }
}