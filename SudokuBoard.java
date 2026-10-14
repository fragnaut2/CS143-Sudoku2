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

		return valid;
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
