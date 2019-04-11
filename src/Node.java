
public class Node {
	int value;
	int offset;
	int left;
	int right;
	Node()
	{
		
	}
	void print()
	{
		System.out.println("Value: "+this.value);
		System.out.println("Offset: "+this.offset);
		System.out.println("Left: "+this.left);
		System.out.println("Right: "+this.right);
	}
	Node(int value, int offset, int left, int right)
	{
		this.value = value;
		this.offset = offset;
		this.left = left;
		this.right = right;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getRight() {
		return right;
	}
	public void setRight(int right) {
		this.right = right;
	}
}
