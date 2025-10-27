import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static Map<Object, Integer> countOfElements(ArrayList array) {

        Map<Object, Integer> countOfEl = new HashMap<>();
        for (Object num : array) {

            if (countOfEl.containsKey(num)) {
                int currentCount = (int) countOfEl.get(num);
                countOfEl.put(num, currentCount + 1);
            } else {
                countOfEl.put(num, 1);
            }
        }
        return countOfEl;
    }


    public static void main(String[] args) {

        ArrayList array = new ArrayList();
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(1);
        array.add(1);

        Map<Object, Integer> countOfEl = countOfElements(array);

        System.out.println(countOfEl);
    }
}
