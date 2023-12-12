public class Question3 {
    
    public static <E extends Comparable<E>> int linearSearch(E[] list, E key) {

        for (int i = 0; i < list.length; i++) {
            if (list[i].compareTo(key) == 0) {
                System.out.print("Key found at index: ");
                return i;
            }
        }
        return -1; // key not found
    }

    public static void main(String[] args) {
        Integer[] list = { 3, 4, 5, 1, -3, -5, -1 };
        System.out.println(linearSearch(list, 2));
        System.out.println(linearSearch(list, 5));
    }
}
