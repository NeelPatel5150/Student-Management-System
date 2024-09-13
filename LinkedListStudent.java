import java.util.*;;
class LinkedListStudent {
    class Node {
        Student data;
        Node next;

        Node(Student data) {
            this.data = data;
            this.next = null;
        }
    }

    Node first = null;

    void addFirst(Student data) {
        Node n = new Node(data);

        if (first == null) {
            first = n;
        }

        else {
            n.next = first;
            first = n;
        }
    }

    public void addLast(Student data) {
        Node n = new Node(data);

        if (first == null) {
            first = n;
        }

        else {
            Node temp = first;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = n;
        }
    }

     public Iterable<Student> getAllStudents() {
        LinkedList<Student> list = new LinkedList<>();

        Node temp = first;
        while (temp != null) {
            list.add(temp.data);
            temp = temp.next;
        }
        return list;
    }
}