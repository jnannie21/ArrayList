import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>(Arrays.asList(13, 0));
        java.util.Iterator<Integer> it = list.iterator();
        list.removeAll(Arrays.asList(null, 11));
        it.next();
    }
}
