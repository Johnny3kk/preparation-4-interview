package HomeWork2.Task1;

public class Main {

    public static void main(String[] args) {

        MyLinkedList<String> mll = new MyLinkedList<>();

        mll.insertFirst("Katia");
        mll.insertFirst("Petia");
        mll.insertFirst("Maria");

        System.out.println(mll);

        mll.insertLast("Bob");
        System.out.println(mll);
    }
}
