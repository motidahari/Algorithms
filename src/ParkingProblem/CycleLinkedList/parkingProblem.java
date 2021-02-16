package ParkingProblem.CycleLinkedList;


public class parkingProblem {
    private static DoubleCycleLinkedList parking = new DoubleCycleLinkedList();

    /**
     * O(n)
     * by pointers
     * */
    public static int calcCarsPointers(DoubleCycleLinkedList parking) {
        int result = 1;
        PNode node1 = parking.getHead().getNext();
        PNode head = parking.getHead();
        while (node1 != head) {
            node1 = node1.getNext();
            result++;
        }
        return result;
    }



    /**
     * O(n^2)
     * */
    public static int calcCars(DoubleCycleLinkedList parking) {
        PNode currPNode = parking.getHead().getNext();
        boolean flag = true;
        int counter = 1;
        char oldSing = 'V';
        char newSing = 'W';
        int steps = 0;

        while (flag) { // main loop start
            if (currPNode.getData() != oldSing) { // check if the current node has the same data as the head
                currPNode = currPNode.getNext();
                counter++;
            }else{
                currPNode.setData(newSing); // change nodes data
                steps = counter;
                while (steps > 0) { // go back to the head
                    currPNode = currPNode.getPerv();
                    steps--;
                }
                if (currPNode.getData() == newSing) { // if head's data is newSing its mean that we done a complete cycle
                    flag = false;
                } else { // start the loop again
                    counter = 1;
                    currPNode = parking.getHead().getNext();
                }
            }
        } // main loop end
        return counter;
    }


    @Override
    public String toString() {
        return parking.toString();
    }
}