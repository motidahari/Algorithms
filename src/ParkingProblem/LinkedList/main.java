package ParkingProblem.LinkedList;

import static ParkingProblem.LinkedList.ParkingProblem.parkingProblemWithLinearPart;

public class main {

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.add(8);
        ll.add(8);
        ll.add(8);
        ll.add(8);
        ll.add(8);
        ll.add(8);
        ll.add(8);
        ll.add(8);
        ll.add(8);
        ll.add(8);
        ll.add(8);
        System.out.println(parkingProblemWithLinearPart(ll));
    }
}
