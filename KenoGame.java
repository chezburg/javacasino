import java.util.*;

public class KenoGame {
    public static void main(String[] args) {
        int[] kenoNumbers = new int[20];
        ArrayList<Integer> p = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            while (true){
            kenoNumbers[i] = random.nextInt(80) + 1;
            if (p.contains(kenoNumbers[i])){
                continue;
            }
            else{
                p.add(kenoNumbers[i]);
                break;
            }
        }
            System.out.println(kenoNumbers[i]);

        }
    }
}
