import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            System.out.println(list.iterator().next());
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        list.add(13);
        Iterator it = list.iterator();
        System.out.println(it.next());
        try {
            System.out.println(it.next());
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        it.remove();
        System.out.println(list);
        it.remove();
    }
}
