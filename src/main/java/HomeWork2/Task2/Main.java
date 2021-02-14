package HomeWork2.Task2;

public class Main {

    public static void main(String[] args) {

        MyArrayList<Integer> mal = new MyArrayList<>();
        mal.add(5);
        mal.add(2);
        mal.add(1);
        mal.add(3);
        System.out.println(mal);

        mal.add(4, 9);
        System.out.println(mal);

        mal.remove(9);
        System.out.println(mal);

        System.out.println(mal.indexOf(5));
        System.out.println(mal.indexOf(9));
    }
}
