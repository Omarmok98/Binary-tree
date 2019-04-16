
import java.io.IOException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws IOException{
		
		String FILENAME = "ds2.txt";
		//Index.CreateRecordsFile(FILENAME, 8);
		int choice;
		Scanner in  =  new Scanner(System.in);
		while(true)
		{
			System.out.println("1-Insert record.");
			System.out.println("2-Search record.");
			System.out.println("3-Traverse file");
			System.out.println("4-Display file");
			System.out.println("5-Exit.");
			choice = in.nextInt();
			if(choice == 1)
			{
				int key,offset;
				System.out.println("Please enter your Key: ");
				key = in.nextInt();
				System.out.println("Please enter your Offset: ");
				offset = in.nextInt();
				Index.InsertNewRecordAtIndex(FILENAME, key, offset);
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				
	
			}else if(choice == 2)
			{
				System.out.println("Please enter your Key: ");
				System.out.println(Index.SearchRecordInIndex(FILENAME, in.nextInt()));
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			}
			else if(choice == 3)
			{
				Index.TraverseBinarySearchTreeInOrder(FILENAME);
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			}
			else if(choice == 4)
			{
				Index.DisplayIndexFileContent(FILENAME);
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				
			}else if (choice == 5)
			{
				break;
			}else
			{
				continue;
			}
			
		}
		in.close();
		//Index.CreateRecordsFile(FILENAME, 8);
		//Index.DisplayIndexFileContent(FILENAME);
		
		/*Index.InsertNewRecordAtIndex(FILENAME, 5, 12);
		Index.InsertNewRecordAtIndex(FILENAME, 12, 24);
		Index.InsertNewRecordAtIndex(FILENAME, 3, 36);
		Index.InsertNewRecordAtIndex(FILENAME, 9, 48);
		Index.InsertNewRecordAtIndex(FILENAME, 8, 60);
		Index.InsertNewRecordAtIndex(FILENAME, 2, 72);
		Index.InsertNewRecordAtIndex(FILENAME, 4, 84);*/
		//Index.updateTree(FILENAME,6, 24);
		
		
		

	}

}
