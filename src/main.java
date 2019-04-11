
import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException{
		
		String FILENAME = "ds2.txt";
		//Index.CreateRecordsFile(FILENAME, 8);
		Index.DisplayIndexFileContent(FILENAME);
		
		
		System.out.println(Index.SearchRecordInIndex(FILENAME, 5));
		System.out.println(Index.SearchRecordInIndex(FILENAME, 12));
		System.out.println(Index.SearchRecordInIndex(FILENAME, 3));
		System.out.println(Index.SearchRecordInIndex(FILENAME, 9));
		System.out.println(Index.SearchRecordInIndex(FILENAME, 8));
		System.out.println(Index.SearchRecordInIndex(FILENAME, 2));
		System.out.println(Index.SearchRecordInIndex(FILENAME, 4));
		System.out.println(Index.SearchRecordInIndex(FILENAME, 82));
		/*Index.InsertNewRecordAtIndex(FILENAME, 5, 12);
		Index.InsertNewRecordAtIndex(FILENAME, 12, 24);
		Index.InsertNewRecordAtIndex(FILENAME, 3, 36);
		Index.InsertNewRecordAtIndex(FILENAME, 9, 48);
		Index.InsertNewRecordAtIndex(FILENAME, 8, 60);
		Index.InsertNewRecordAtIndex(FILENAME, 2, 72);
		Index.InsertNewRecordAtIndex(FILENAME, 4, 84);*/
		//Index.updateTree(FILENAME,6, 24);
		System.out.println("*****************");
		Index.DisplayIndexFileContent(FILENAME);
		
		
		

	}

}
