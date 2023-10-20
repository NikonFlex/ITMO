import java.util.Arrays;

public class MyIntList {
    private int INIT_SIZE = 2;
    private int[] array = new int[INIT_SIZE];
    private int last_index = 0;

    public void add(int item) {
        if(last_index == array.length - 1)
            resize(array.length * 2);
        array[last_index++] = item;
    }

    public int get(int index) {
        try {
            return array[index];
        } catch(ArrayIndexOutOfBoundsException e) {
            System.err.println("выход за границу массива" + e.getMessage());
            throw e;
        }
    }

    public int size() {
        return last_index;
    }

    public int[] toArrayCopy() {
        return Arrays.copyOf(array, array.length);
    }

    private void resize(int newLength) {
        array = Arrays.copyOf(array, newLength);
    }
}
