package homework;

import java.util.*;
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
        /*  Raw data:

        0 - Harry
        0 - Harry
        1 - Harry
        2 - Harry
        3 - Emily
        4 - Jack
        4 - Jack
        5 - Amelia
        5 - Amelia
        6 - Amelia
        7 - Amelia
        8 - Amelia

        **************************************************

        Duplicate filtered, grouped by name, sorted by name and id:

        Amelia:
        1 - Amelia (5)
        2 - Amelia (6)
        3 - Amelia (7)
        4 - Amelia (8)
        Emily:
        1 - Emily (3)
        Harry:
        1 - Harry (0)
        2 - Harry (1)
        3 - Harry (2)
        Jack:
        1 - Jack (4)
     */

    public static void main(String[] args) {
        System.out.println("Raw data:");
        System.out.println();

        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println();

        Arrays.stream(RAW_DATA)
                .filter(Objects::nonNull)
                .distinct()
                .sorted(Comparator.comparing(Person::getId))
                .collect(Collectors.groupingBy(Person::getName, Collectors.counting()))
                .forEach((key, value) -> System.out.println("Key: " + key + "\n" + "Value: " + value));

        System.out.println();
        System.out.println("Task 2: ");
        System.out.println(Arrays.toString(findNumbers(new int[]{3, 4, 2, 7}, 10)));

        /*Task3
            Реализовать функцию нечеткого поиска*/
        System.out.println();
        System.out.println("Task 3: ");
        fuzzySearch("car", "ca6$$#_rtwheel"); // true
        fuzzySearch("cwhl", "cartwheel"); // true
        fuzzySearch("cwhee", "cartwheel"); // true
        fuzzySearch("cartwheel", "cartwheel"); // true
        fuzzySearch("cwheeel", "cartwheel"); // false
        fuzzySearch("lw", "cartwheel"); // false


    }

    private static void fuzzySearch(String substring, String text) {
        int count = 0;
        int point = 0;

        for (int i = 0; i < substring.length(); i++) {
            for (int j = point; j < text.length(); j++) {
                if (substring.charAt(i) == text.charAt(j)) {
                    count++;
                    point = ++j;
                    break;
                }
            }
        }
        System.out.println(count == substring.length());
    }

    private static int[] findNumbers(int[] numbers, int sum) {
        int[] result = null;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == sum) {
                    result = new int[]{numbers[i], numbers[j]};
                    break;
                }
            }
            if (result != null) break;
        }
        return result;
    }
}
