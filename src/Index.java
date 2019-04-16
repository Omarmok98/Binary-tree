
import java.io.IOException;
import java.io.RandomAccessFile;

public class Index {

	final int RECORD_SIZE = 16;

	static Node readNode(RandomAccessFile f) throws IOException {
		Node n = new Node();

		n.setValue(f.readInt());
		n.setOffset(f.readInt());
		n.setLeft(f.readInt());
		n.setRight(f.readInt());

		return n;
	}

	static void writeNode(RandomAccessFile f, int value, int offset, int left, int right) throws IOException {

		f.writeInt(value);
		f.writeInt(offset);
		f.writeInt(left);
		f.writeInt(right);
	}

	static void writeNode(RandomAccessFile f, Node n) throws IOException {

		f.writeInt(n.getValue());
		f.writeInt(n.getOffset());
		f.writeInt(n.getLeft());
		f.writeInt(n.getRight());
	}

	static void CreateRecordsFile(String filename, int numberOfRecords) throws IOException {

		RandomAccessFile index = new RandomAccessFile(filename, "rw");

		for (int i = 0; i < numberOfRecords; i++) {
			if (i == numberOfRecords - 1) {
				writeNode(index, -1, 0, 0, 0);

			} else {
				writeNode(index, i + 1, 0, 0, 0);
			}

		}
		index.close();
	}

	private static int addRecord(String filename, int key, int byteOffset) throws IOException {
		RandomAccessFile index = new RandomAccessFile(filename, "rw");
		// Node input = new Node(key, byteOffset, -1, -1);
		index.seek(0);
		int counter = index.readInt();
		if (counter != -1) {
			index.seek(counter * 16);
			int check = index.readInt();
			if (check == -1) {
				index.seek(0);
				index.seek(counter * 16);
				writeNode(index, key, byteOffset, -1, -1);
				index.seek(0);
				counter = -1;
				index.writeInt(counter);
			} else {

				index.seek(0);
				index.seek(counter * 16);
				writeNode(index, key, byteOffset, -1, -1);
				counter++;
				index.seek(0);
				index.writeInt(counter);
			}

			System.out.println(counter);
			if (counter == -1) {
				long len = index.length();
				index.seek(len);
				long eof = index.getFilePointer();
				counter = (int) eof / 16;
				System.out.println(counter);
			}
			index.close();
			return counter;
		} else {
			index.close();
			return -1;
		}
	}

	static int updateTree(String filename, int inputIndex, int key, int byteOffset) throws IOException {
		RandomAccessFile index = new RandomAccessFile(filename, "rw");
		Node input = new Node(key, byteOffset, -1, -1);
		int counter = index.readInt();
		index.seek(0);
		int rootOffset = 16;
		index.seek(rootOffset);
		int pointer = index.readInt();
		System.out.println(counter);
		index.seek(16);
		while (true) {
			Node temp = readNode(index);
			if (temp.getValue() > input.getValue()) {

				if (temp.getLeft() != -1) {

					pointer = temp.getLeft() * 16;
					index.seek(0);
					index.seek(pointer);
				} else {
					index.seek(index.getFilePointer() - 16);
					temp.setLeft(inputIndex - 1);
					writeNode(index, temp);
					break;
				}
			} else if (temp.getValue() < input.getValue()) {

				if (temp.getRight() != -1) {

					pointer = temp.getRight() * 16;
					index.seek(0);
					index.seek(pointer);
				} else {
					index.seek(index.getFilePointer() - 16);
					temp.setRight(inputIndex - 1);
					writeNode(index, temp);
					break;
				}
			}

		}

		index.close();
		return 0;
	}

	static int InsertNewRecordAtIndex(String filename, int key, int byteOffset) throws IOException {

		int counter = addRecord(filename, key, byteOffset);
		System.out.println("counter: " + counter);
		if (counter != -1) {
			if (counter == 2) // add root
			{
				System.out.println("Root added");
				return 0;
			}
			updateTree(filename, counter, key, byteOffset);
			System.out.println("added");
			return 0;
		} else {
			// updateTree(filename,counter, key, byteOffset);
			System.out.println("full");
			return -1;
		}

	}

	static int SearchRecordInIndex(String filename, int key) throws IOException {
		RandomAccessFile index = new RandomAccessFile(filename, "r");
		index.seek(16);

		while (true) {
			Node temp = readNode(index);
			if (temp.getValue() > key) {
				if (temp.getLeft() != -1) {
					index.seek(0);
					index.seek(temp.getLeft() * 16);

				} else {
					return -1;

				}
			} else if (temp.getValue() < key) {
				if (temp.getRight() != -1) {
					index.seek(0);
					index.seek(temp.getRight() * 16);
				} else {
					return -1;
				}
			} else {
				return temp.getOffset();
			}
		}

	}

	static void TraverseBinarySearchTreeInOrder(String filename) throws IOException {
		RandomAccessFile index = new RandomAccessFile(filename, "r");
		Node cur = new Node();
		inOrder(index,cur,16);

		index.close();
	}
	static void inOrder(RandomAccessFile f,Node cur,int offset) throws IOException
	{
		
		f.seek(offset);
		cur=readNode(f);
		
		if(cur.getLeft() == 0 || cur.getLeft() == -1) {
			System.out.println(cur.getValue());
			return;
		}
		inOrder(f,cur,cur.getLeft()*16);
		System.out.println(cur.getValue());
		if(cur.getRight() == 0 || cur.getRight() == -1) {
			return;
		}
		inOrder(f,cur,cur.getRight()*16);
	}

	static void DisplayIndexFileContent(String filename) throws IOException {

		RandomAccessFile index = new RandomAccessFile(filename, "r");
		long len = index.length();
		index.seek(len);
		long eof = index.getFilePointer();
		index.seek(0);
		StringBuilder output = new StringBuilder();
		while (index.getFilePointer() != eof) {
			output.append('|');
			output.append(index.readInt());
			output.append('|');
			output.append(index.readInt());
			output.append('|');
			output.append(index.readInt());
			output.append('|');
			output.append(index.readInt());
			output.append('|');
			System.out.println(output);
			output.setLength(0);
		}
		index.close();
	}
}
