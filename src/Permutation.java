import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {

    public static void main(String[] args) {
        int count = Integer.parseInt(args[0]);
        String[] strings = StdIn.readString().split(" ");
        for (int i = 0; i < count; i++) {
            int index = StdRandom.uniform(count - i);
            System.out.println(strings[index]);
            strings[index] = strings[count - i - 1];
        }
    }

}