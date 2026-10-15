public class SudokuGame
{
	public static void main(String[] args)
	{
		SudokuBoard gameBoard = new SudokuBoard("valid-complete.sdk");
		System.out.println(gameBoard);
		if(gameBoard.isValid()){
			System.out.println("VALID");
		}

		if(gameBoard.isSolved()){
			System.out.println("SOLVED");
		}
	}
}
