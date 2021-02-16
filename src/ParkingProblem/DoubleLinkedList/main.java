package ParkingProblem.DoubleLinkedList;


public class main {

    public static void main(String[] args) {
        DoubleLinkedList cl = new DoubleLinkedList();
        cl.add(5);
        cl.add(6);
        cl.add(7);
        cl.add(8);
        cl.add(9);
        cl.add(10);
        cl.add(11);
        cl.add(12);
//        System.out.println(cl);
        cl.createCircle(1);
        System.out.println(ParkingProblem.parkingProblemWithLinearPart(cl));
    }
}
