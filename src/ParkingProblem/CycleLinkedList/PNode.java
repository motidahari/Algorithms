package ParkingProblem.CycleLinkedList;

public class PNode {
    private char data;
    private PNode perv, next;

    //constructor
    public PNode(char data, PNode perv, PNode next){
        this.data = data;
        this.next = next;
        this.perv = perv;
    }
    //toString
    public String toString(){
        return "" + this.data;
    }
    //****** Getter and Setter *******
    public char getData() {
        return data;
    }

    public void setData(char data) {
        this.data = data;
    }

    public PNode getPerv() {
        return perv;
    }

    public void setPerv(PNode perv) {
        this.perv = perv;
    }

    public PNode getNext() {
        return next;
    }

    public void setNext(PNode next) {
        this.next = next;
    }
}
