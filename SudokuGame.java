public class SudokuGame
{
	public static void main(String[] args)
	{
		SudokuBoard gameBoard = new SudokuBoard("data1.sdk");
		System.out.println(gameBoard);
		if(gameBoard.isValid()){
			System.out.println("VALID");
		}
	}
}
