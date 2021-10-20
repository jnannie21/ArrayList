
public class Main {
    public static void main(String[] args) {
        java.util.ArrayList<Cl> list = new java.util.ArrayList<>();
        list.add(new Cl(13));
        Object[] arr = list.toArray();

        list.get(0).i = 14;
        System.out.println(arr[0]);


    }

    static class Cl {
        public int i;

        Cl(int i) {
            this.i = i;
        }

        @Override
        public String toString() {
            return "Cl{" +
                    "i=" + i +
                    '}';
        }
    }
}
