package ParkingProblem.CycleLinkedList;


public class main {

    public static DoubleCycleLinkedList buildLinkedList() {
        DoubleCycleLinkedList DCLL = new DoubleCycleLinkedList();
        DCLL.add('V');
        DCLL.add('B');
        DCLL.add('M');
        DCLL.add('Z');
        DCLL.add('S');
        DCLL.add('A');
        DCLL.add('X');
        DCLL.add('P');
        DCLL.add('V');
        DCLL.add('T');
        System.out.println("DCLL : " + DCLL.toString());
        return DCLL;
    }

    public static void main(String[] args) {
        parkingProblem parking = new parkingProblem();
        DoubleCycleLinkedList list = buildLinkedList();
        System.out.println();

        System.out.println("number of cars = " + parking.calcCars(list));
        System.out.println("DCLL after mark: " + list.toString());
        //**************************************************
        System.out.println();
        System.out.println("number of cars with pointers = " + parking.calcCarsPointers(list));
        System.out.println("DCLL after mark: " + list.toString());
    }
}