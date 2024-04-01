import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true){

            short[] sides = new short[3];

            for(int i = 0; i < 3; i++){
                sides[i] = sc.nextShort();
            }

            Arrays.sort(sides);

            if(sides[0] == 0 && sides[2] == 0){
                return;
            }

            if(sides[0] + sides[1] <= sides[2]){
                System.out.println("Invalid");
            }
            else{
                if(sides[0] == sides[1] && sides[0] == sides[2]){
                    System.out.println("Equilateral");
                }
                else if(sides[0] == sides[1] || sides[0] == sides[2] || sides[1] == sides[2]){
                    System.out.println("Isosceles");
                }
                else{
                    System.out.println("Scalene");
                }
            }

        }
    }
}
