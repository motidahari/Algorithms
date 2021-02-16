package MinPrice.aaaaa;
import java.util.ArrayList;
import java.util.Vector;


public class BestPath {
	static int run = 0;
	public static void main(String[] args) {
		Node[][] mat = {
				{new Node(1, 2), new Node(1,1), new Node(1,3), new Node(0,1)},
				{new Node(2,3), new Node(5,1), new Node(6,3), new Node(0,1)},
				{new Node(2,4), new Node(7,1), new Node(2,3), new Node(0,1)},
				{new Node(2,0), new Node(1,0), new Node(1,0), new Node(0,0)}
		};
		Node[][] mat2 = {
				{new Node(1, 5), new Node(4, 1), new Node(0, 6), new Node(4, 2)},
				{new Node(4, 7), new Node(2, 5), new Node(0, 3), new Node(1, 5)},
				{new Node(1, 0), new Node(2, 0), new Node(0, 0), new Node(6, 3)},
				{new Node(1, 1), new Node(5, 2), new Node(1, 1), new Node(1, 1)}
		};
		BestPath bp = new BestPath(mat);
		System.out.println("price = " + bp.price);
		System.out.println("numOfPaths = " + bp.numOfPaths);
		System.out.println(bp.path(mat) + " ||| => " + bp.getOneCheapestPath());
		System.out.println(bp.path(mat) + " ||| => " + bp.getOneCheapestPathRec());
		System.out.println("isOnBestPath (1, 1) = " + bp.isOnBestPath(1, 1));
		System.out.println("isOnBestPath (1, 2) = " + bp.isOnBestPath(1, 2));
		System.out.println("isOnBestPath = " + bp.isOnBestPath(new Node[] {new Node(1,1),new Node(1,2), new Node(1,3)}));
		System.out.println("isOnBestPath = " + bp.isOnBestPath(new Node[] {new Node(1,1),new Node(3,1), new Node(3,3)}));

	}

	int price, numOfPaths;
	private Node [][]mat;

	public BestPath(Node[][] mat){
		this.mat = mat;
		price = 0;
		numOfPaths = 0;
		getBestPrice();
	}
	// build matrix
	private void getBestPrice(){
		// n rows, m columns
		int n = mat.length, m = mat[0].length;
		mat[0][0].price = 0;
		for (int i=1; i<n; i++){// first column
			mat[i][0].price = mat[i-1][0].y+  mat[i-1][0].price;
			mat[i][0].numOfPaths = 1;
		}
		for (int j=1; j<m; j++){// first row
			mat[0][j].price = mat[0][j-1].price +  mat[0][j-1].x;
			mat[0][j].numOfPaths = 1;
		}
		for (int i=1; i<n; i++){
			for (int j=1; j<m; j++){
				int x = mat[i-1][j].price+mat[i-1][j].y;
				int y = mat[i][j-1].price+mat[i][j-1].x;
				mat[i][j].price = x<=y ? x : y;
				if (x<y){
					//mat[i][j].price = x;
					mat[i][j].numOfPaths = mat[i-1][j].numOfPaths;
				}
				else if (x>y) {
					//mat[i][j].price = y;
					mat[i][j].numOfPaths = mat[i][j-1].numOfPaths;
				}
				else{//x==y
					mat[i][j].numOfPaths = mat[i][j-1].numOfPaths+mat[i-1][j].numOfPaths;
				}
			}
		}
		numOfPaths = mat[n-1][m-1].numOfPaths;
		price = mat[n-1][m-1].price;
	}
	// Print matrix of prices
	public void printPrices(){
		System.out.println("\nmatrix of prices in right direction\n");
		for (int i=0; i<mat.length; i++){
			for (int j=0; j<mat[0].length; j++){
				System.out.print(mat[i][j].price+" ");
			}
			System.out.println();
		}
	}
	// Print matrix of paths number
	public void printNumberOfPaths(){
		System.out.println("\nthe matrix of numbers of the cheapest paths \n");
		for (int i=0; i<mat.length; i++){
			for (int j=0; j<mat[0].length; j++){
				System.out.print(mat[i][j].numOfPaths+" ");
			}
			System.out.println();
		}
	}

	String getOneCheapestPathRec(){
		return getOneCheapestPathRec(mat.length-1,mat[0].length-1);
	}

	private String getOneCheapestPathRec(int i, int j) {
		if(i == 0 && j == 0) return "";
		if(i > 0 && j == 0) return getOneCheapestPathRec(i-1,0) + "1";
		if(i == 0 && j > 0) return getOneCheapestPathRec(0,j-1) + "0";
		if(mat[i][j].price == mat[i-1][j].price + mat[i-1][j].y) {
			return getOneCheapestPathRec(i-1,j) + "1";
		}
		else {
			return getOneCheapestPathRec(i,j-1) + "0";
		}
	}

	String getOneCheapestPath(){
		String ans = "";
		int i = mat.length-1, j = mat[0].length-1;
		while(i>0 && j>0){
			int a = mat[i-1][j].price+mat[i-1][j].y;
			int b = mat[i][j-1].price+mat[i][j-1].x;
			if (a < b){
				ans = "1" + ans;
				i--;
			}
			else{//a>b
				ans = "0" + ans;
				j--;
			}
		}
		while (j>0){
			ans = "0" + ans;
			j--;
		}
		while (i>0){
			ans = "1" + ans;
			i--;
		}
		return ans;
	}

	/**
	 * complexity: O((n+m)choose(n))
	 */
	Vector<String> getAllCheapestPathRec(){
		Vector<String> ans = new Vector<String>();
		getAllCheapestPathRec("",mat.length-1,mat[0].length-1,ans);
		return ans;
	}

	private void getAllCheapestPathRec(String temp,int i, int j, Vector<String> ans) {
		if(i == 0 && j == 0) {
			ans.add(temp);
			return;
		}
		else if(i > 0 && j == 0) {
			getAllCheapestPathRec("1"+temp,i-1,0,ans);
		}
		else if(i == 0 && j > 0) {
			getAllCheapestPathRec("0"+temp,0,j-1,ans);
		}
		else {
			int a = mat[i-1][j].price+mat[i-1][j].y;
			int b = mat[i][j-1].price+mat[i][j-1].x;
			if(a < b) {
				getAllCheapestPathRec("1"+temp,i-1,j,ans);
			}
			else if(b < a) {
				getAllCheapestPathRec("0"+temp,i,j-1,ans);
			}
			else {
				getAllCheapestPathRec("1"+temp,i-1,j,ans);
				getAllCheapestPathRec("0"+temp,i,j-1,ans);
			}
		}
	}

	/**
	 * O(1)
	 */
	public boolean isOnBestPath(int i,int j) {
		return (mat[i][j].price + bestPathFromTo(i,j,mat.length-1,mat[0].length-1)) == price;
	}

	/**
	 * O(n*m)
	 */
	private int bestPathFromTo(int p1,int q1,int p2,int q2) {
		// Assuming p2>=p1 and q2>=q1
		Node[][] temp = new Node[q2-q1+1][p2-p1+1];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				temp[i][j] = new Node(0,0);
			}
		}
		for (int i=1; i<q2-q1+1; i++){// first column
			temp[i][0].price = mat[i-1+q1][p1].y + temp[i-1][0].price;
		}
		for (int j=1; j<p2-p1+1; j++){// first row
			temp[0][j].price = temp[0][j-1].price +  mat[q1][j-1+p1].x;
		}
		for (int i=1; i<q2-q1+1; i++){
			for (int j=1; j<p2-p1+1; j++){
				int x = temp[i-1][j].price+mat[i-1+q1][j+p1].y;
				int y = temp[i][j-1].price+mat[i+q1][j-1+p1].x;
				temp[i][j].price = x<=y ? x : y;
			}
		}
		return temp[q2-q1][p2-p1].price;
	}

	/**
	 * O(n*m*k) k = |p|
	 */
	public boolean isOnBestPath(Node[] p) {
		sort(p);
		int sum = mat[p[0].y][p[0].x].price;
		for (int i = 1; i < p.length; i++) {
			if(p[i].y < p[i-1].y) return false;
			sum += bestPathFromTo(p[i-1].x,p[i-1].y,p[i].x,p[i].y);
		}
		sum += bestPathFromTo(p[p.length-1].x,p[p.length-1].y,mat[0].length-1,mat.length-1);
		return sum == price;
	}

	private void sort(Node[] p) {
		ArrayList<Node>[] freqy = new ArrayList[mat.length];
		for (int i = 0; i < freqy.length; i++) {
			freqy[i] = new ArrayList<Node>();
		}
		Node[] temp = new Node[p.length];
		for (int i = 0; i < p.length; i++) {
			freqy[p[i].y].add(p[i]);
		}
		int k = 0;
		for (int i = 0; i < freqy.length; i++) {
			for (int j = 0; j < freqy[i].size(); j++) {
				temp[k++] = freqy[i].get(j);
			}
		}
		ArrayList<Node>[] freqx = new ArrayList[mat[0].length];
		for (int i = 0; i < freqx.length; i++) {
			freqx[i] = new ArrayList<Node>();
		}
		Node[] temp2 = new Node[p.length];
		for (int i = 0; i < temp.length; i++) {
			freqx[temp[i].x].add(temp[i]);
		}
		k = 0;
		for (int i = 0; i < freqx.length; i++) {
			for (int j = 0; j < freqx[i].size(); j++) {
				temp2[k++] = freqx[i].get(j);
			}
		}
		for (int i = 0; i < temp2.length; i++) {
			p[i] = temp2[i];
		}
	}
	/**
	 * O(P) : P = the length of the path
	 **/
	private static String path(Node[][] mat) {
		int i = mat.length - 1, j = mat[0].length - 1;
		String ans = "";
		while (i != 0 && j != 0) {
			if (mat[i - 1][j].price + mat[i - 1][j].y < mat[i][j - 1].price + mat[i][j - 1].x) {
				ans = checkRun(ans,"down");
				i--;
			}else {
				ans = checkRun(ans,"right");
				j--;
			}
			run++;
		}
		while (i != 0) {
			ans = "down -> " + ans;
			i--;
		}
		while (j != 0) {
			ans = "right -> " + ans;
			j--;
		}
		return ans;
	}

	private static String checkRun(String ans, String str) {
		if (run == 0) {
			ans = str + " " + ans;
		} else {
			ans = str + " -> " + ans;
		}
		return ans;
	}
}
class Node {
	int x,y,price,numOfPaths;

	public Node(int x,int y) {
		this.x = x;
		this.y = y;
		price = 0;
		numOfPaths = 1;
	}

	@Override
	public String toString() {
		return "("+x+","+y+")"+":"+price + "";
	}
}
