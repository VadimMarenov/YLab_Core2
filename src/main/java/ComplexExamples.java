import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ComplexExamples {

    static class Person {
        final int id;

        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }
    }

    private static Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
    };

    public static void main(String[] args) {
        // TASK 1

        Map<String, Integer> persons = Arrays.stream(RAW_DATA)
                .distinct()
                .map(Person::getName)
                .collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum));

        for (Map.Entry entry : persons.entrySet()) {
            System.out.println("Key: " + entry.getKey());
            System.out.println("Value: " + entry.getValue());
        }

        // TASK 2
        int[] arr = {3, 4, 2, 7};
        List<Integer> resultOfTask2 = getPair(arr, 10);
        System.out.println(resultOfTask2);

        // TASK 3
        fuzzySearch("car", "ca6$$#_rtwheel"); // true
        fuzzySearch("cwhl", "cartwheel"); // true
        fuzzySearch("cwhee", "cartwheel"); // true
        fuzzySearch("cartwheel", "cartwheel"); // true
        fuzzySearch("cwheeel", "cartwheel"); // false
        fuzzySearch("lw", "cartwheel"); // false

    }

    public static List<Integer> getPair(int[] array, int sum) {
        List<Integer> result = new ArrayList<>();
        Arrays.sort(array);
        int first = 0;
        int last = array.length - 1;
        while (first < last) {
            int s = array[first] + array[last];
            if (s == sum) {
                result.add(array[first]);
                result.add(array[last]);
                break;
            } else {
                if (s < sum) first++;
                else last--;
            }
        }
        return result;
    }

    public static boolean fuzzySearch(String first, String second) {
        int countMatches = 0;
        int curIndex = 0;
        char[] firstArr = first.toCharArray();
        char[] secondArr = second.toCharArray();
        for (int i = 0; i < firstArr.length; i++) {
            for (int j = curIndex; j < secondArr.length; j++) {
                if (firstArr[i] == secondArr[j]) {
                    countMatches++;
                    curIndex = j + 1;
                }
            }
        }
        System.out.println(countMatches == firstArr.length);
        return countMatches == firstArr.length;

    }

        /*
        Task1
            Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени

            Что должно получиться
                Key: Amelia
                Value:4
                Key: Emily
                Value:1
                Key: Harry
                Value:3
                Key: Jack
                Value:1
         */

        /*
        Task2

            [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
         */


        /*
        Task3
            Реализовать функцию нечеткого поиска
            
                    fuzzySearch("car", "ca6$$#_rtwheel"); // true
                    fuzzySearch("cwhl", "cartwheel"); // true
                    fuzzySearch("cwhee", "cartwheel"); // true
                    fuzzySearch("cartwheel", "cartwheel"); // true
                    fuzzySearch("cwheeel", "cartwheel"); // false
                    fuzzySearch("lw", "cartwheel"); // false
         */


}