import java.util.*;
import java.io.*;

public class SudokuBoard
{
	private int[][] board; // 0 for empty spaces

	public SudokuBoard(String fileName)
	{
		board = new int[9][9];
		loadFile(fileName);
	}

	private void loadFile(String fileName)
	{
		try
		{
			Scanner input = new Scanner(new File(fileName));
			for (int r = 0; r < board.length; r++)
			{
				String line = input.nextLine();
				for (int c = 0; c < board[r].length; c++)
				{
					int spot = 0;
					if (line.charAt(c) != '.')
					{
						spot = line.charAt(c) - '0';
					}
					board[r][c] = spot;
				}
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error: File not found");
		}
	}

	public boolean isValid(){
		boolean valid = false;

		for(int r = 0; r < board.length; r++){
			for(int c = 0; c < board[r].length; c++){
				if(board[r][c] >= 0 && board[r][c] <= 9){
					valid = true;
				} else {
					return false;
				}
			}
		}

		for(int r = 0; r < 9; r++){
			// System.out.println("Checking R: " + r);
			if(!checkRowValid(r)){
				return false;
			}
		}

		for(int c = 0; c < 9; c++){
			// System.out.println("Checking R: " + r);
			if(!checkColValid(c)){
				return false;
			}
		}
		
		for(int s = 0; s < 9; s++){
			// System.out.println("Checking R: " + r);
			if(!checkMiniValid(s)){
				return false;
			}
		}


		return valid;
	}

	private boolean checkRowValid(int index){
		List<Integer> original = new ArrayList<Integer>();
		Set<Integer> shortened = new HashSet<>();

		for (int c = 0; c < 9; c++){
			if(board[index][c] != 0){
				original.add(board[index][c]);
				shortened.add(board[index][c]);
			}
		}

		// System.out.print(original.toString() + shortened.toString()); 

		return original.size() == shortened.size();
	}

	private boolean checkColValid(int index){
		List<Integer> original = new ArrayList<Integer>();
		Set<Integer> shortened = new HashSet<>();

		for (int r = 0; r < 9; r++){
			if(board[r][index] != 0){
				original.add(board[r][index]);
				shortened.add(board[r][index]);
			}
		}

		// System.out.print(original.toString() + shortened.toString()); 

		return original.size() == shortened.size();
	}

	private boolean checkMiniValid(int spot){
		List<Integer> original = new ArrayList<Integer>();
		Set<Integer> shortened = new HashSet<>();
		int[][] miniSquare = miniSquare(spot);

		for(int r = 0; r < 3; r++){
			for(int c = 0; c < 3; c++){
				if(miniSquare[r][c] != 0){
					original.add(miniSquare[r][c]);
					shortened.add(miniSquare[r][c]);
				}
			}
		}

		return original.size() == shortened.size();
	}

	private int[][] miniSquare(int spot){
		int[][] mini = new int[3][3];
		for(int r = 0; r < 3; r++){
			for(int c = 0; c < 3; c++){
				mini[r][c] = board[(spot) / 3 * 3 + r][(spot) % 3 * 3 + c];
			}
		}
		return mini;
	}


	public String toString()
	{
		// System.out.println(board.toString());
		String result = "";
		for (int r = 0; r < board.length; r++)
		{
			for (int c = 0; c < board[r].length; c++)
			{
				// Space at the start of each row
				if (c == 0)
				{
					result += " ";
				}

				if (board[r][c] == 0)
				{
					result += "-";
				}
				else
				{
					result += board[r][c];
				}

				// Prevent a '|' at the end of the row
				if (c < board[r].length - 1)
				{
					if ((c + 1) % 3 == 0)
					{
						result += " | ";
					}
					else
					{
						result += "   ";
					}
				}
			}
			result += "\n";

			// Prevent divider after last row
			if (r < board.length - 1)
			{
				if ((r + 1) % 3 == 0)
				{
					result += "–––––––––––|–––––––––––|––––––––––\n";
				}
			}
		}
		return result;
	}

	// public String toString(){
	// 	String result = "";
	// 	for(int r = 0; r < board.length; r++){
	// 		for(int c = 0; c < board[r].length; c++){
	// 			result += board[r][c] + ", ";
	// 		}
	// 	}
	// 	return result;
	// }
}
