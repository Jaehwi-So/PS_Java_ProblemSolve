import java.util.*;

class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        List xs = new ArrayList<Integer>();
        List ys = new ArrayList<Integer>();
        for(int i = 0; i < 3; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            if(xs.contains(x)){
                int index = xs.indexOf(x);
                xs.remove(index);
            }
            else{
                xs.add(x);
            }
            if(ys.contains(y)){
                int index = ys.indexOf(y);
                ys.remove(index);
            }
            else{
                ys.add(y);
            }
        }


        System.out.println(xs.get(0) + " " + ys.get(0));
    }
}