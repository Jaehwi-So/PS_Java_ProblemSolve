import java.util.Scanner;

public class Main {
    static long count = -1;
    static int r;
    static int c;
    static long result;

    static boolean isPromising(int startX, int startY, int endX, int endY){
        if(r < startY || r > endY){
            return false;
        }
        else if(c < startX || c > endX){
            return false;
        }
        return true;
    }
    static boolean recursive(int startX, int startY, int endX, int endY){
        if(endX - startX == 0){
            count++;
            if(endY == r && endX == c){
                result = count;
                return true;
            }
        }
        else{
            if(isPromising(startX, startY, endX, endY)){
                int midX = (startX + endX) / 2;
                int midY = (startY + endY) / 2;

                if(recursive(startX, startY, midX, midY)){
                    return true;
                }
                if(recursive(midX + 1, startY, endX, midY)){
                    return true;
                }
                if(recursive(startX, midY + 1, midX, endY)){
                    return true;
                }
                if(recursive(midX + 1, midY + 1, endX, endY)){
                    return true;
                }
            }
            else{
                count += (endX - startX + 1) * (endY - startY + 1);
            }

        }
        return false;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();

        int size = (int)Math.pow(2, n);
        recursive(0, 0, size - 1, size - 1);
        System.out.println(result);
    }
}