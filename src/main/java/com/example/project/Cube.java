package com.example.project;

	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.ArrayList;
	

	
	public class Cube {
		

		static String[][][] cube = {
				{
						{ "0r", "1r", "2r" },
						{ "3r", "4r", "5r" },
						{ "6r", "7r", "8r" },
				},
	
				{
						{ "0b", "1b", "2b" },
						{ "3b", "4b", "5b" },
						{ "6b", "7b", "8b" },
				},
	
				{
						{ "0o", "1o", "2o" },
						{ "3o", "4o", "5o" },
						{ "6o", "7o", "8o" },
				},
	
				{
						{ "0g", "1g", "2g" },
						{ "3g", "4g", "5g" },
						{ "6g", "7g", "8g" },
				},
	
				{
						{ "0y", "1y", "2y" },
						{ "3y", "4y", "5y" },
						{ "6y", "7y", "8y" },
				},
	
				{
						{ "0w", "1w", "2w" },
						{ "3w", "4w", "5w" },
						{ "6w", "7w", "8w" },
				},
		};
	
		static ArrayList<String> solve = new ArrayList<>();
	
		
		static void show(String[][][] cube) {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					for (int k = 0; k < 3; k++) {
						System.out.print(cube[i][j][k] + " ");
					}
					System.out.println();
				}
				System.out.println();
			}
		};
	
		
		static void rotate(String[][][] cube, boolean clockwise, int face) {
			String[][][] temp = new String[6][3][3];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					temp[face][i][j] = cube[face][i][j];
				}
			}
	
			if (clockwise) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (cube[face][i][j] == cube[face][i][2]) {
							temp[face][i][j] = cube[face][j - 2][i];
						} else if (cube[face][i][j] == cube[face][i][1]) {
							temp[face][i][j] = cube[face][j][i];
						} else if (cube[face][i][j] == cube[face][i][0]) {
							temp[face][i][j] = cube[face][j + 2][i];
						}
					}
				}
				
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						cube[face][i][j] = temp[face][i][j];
					}
				}
			} else if (!clockwise) {
	
				for (int ccl = 0; ccl < 3; ccl++) {
													
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if (cube[i][j] == cube[i][2]) {
								temp[face][i][j] = cube[face][j - 2][i];
							} else if (cube[face][i][j] == cube[face][i][1]) {
								temp[face][i][j] = cube[face][j][i];
							} else if (cube[face][i][j] == cube[face][i][0]) {
								temp[face][i][j] = cube[face][j + 2][i];
							}
						}
					}
					
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							cube[face][i][j] = temp[face][i][j];
						}
					}
				}
			}
	
		}
	
		
		static void rotateEdge(String[][][] cube, int color, int row, int col, int row2, int col2) {
			String[][][] temp = new String[4][3][3];
			switch (color) {
				case 0:
					int j = 3; 
					for (int i = 0; i < 3; i++) { 
						j--;
						temp[0][i][col] = cube[1][i][col];
						temp[1][i][col] = cube[4][i][col];
						temp[2][i][col] = cube[5][i][col];
						temp[3][i][col] = cube[3][j][col2];
						cube[1][i][col] = temp[2][i][col];
						cube[4][i][col] = temp[0][i][col];
						cube[5][i][col] = temp[3][i][col];
						cube[3][j][col2] = temp[1][i][col];
	
					}
					break;
				case 1:
					for (int i = 0; i < 3; i++) {
						temp[0][i][col] = cube[2][i][col];
						temp[1][row][i] = cube[4][row][i];
						temp[2][i][col2] = cube[0][i][col2];
						temp[3][row2][i] = cube[5][row2][i];
						cube[2][i][col] = temp[3][row2][i];
						cube[4][row][i] = temp[0][i][col];
						cube[0][i][col2] = temp[1][row][i];
						cube[5][row2][i] = temp[2][i][col2];
					}
					break;
				case 2:
					j = 3; 
					for (int i = 0; i < 3; i++) { 
						j--;
						temp[0][i][col] = cube[1][i][col];
						temp[1][i][col] = cube[4][i][col];
						temp[2][i][col] = cube[5][i][col];
						temp[3][i][col] = cube[3][j][col2];
						cube[1][i][col] = temp[1][i][col];
						cube[4][i][col] = temp[3][i][col];
						cube[5][i][col] = temp[0][i][col];
						cube[3][j][col2] = temp[2][i][col];
	
					}
					break;
				case 3:
	
					for (int i = 0; i < 3; i++) { 
						temp[0][i][col] = cube[2][i][col];
						temp[1][row][i] = cube[4][row][i];
						temp[2][i][col2] = cube[0][i][col2];
						temp[3][row2][i] = cube[5][row2][i];
						cube[2][i][col] = temp[3][row2][i];
						cube[4][row][i] = temp[0][i][col];
						cube[0][i][col2] = temp[1][row][i];
						cube[5][row2][i] = temp[2][i][col2];
					}
	
					break;
				case 4:
				for (int i = 0; i < 3; i++) { 
					temp[0][row][i] = cube[0][row][i];
					temp[1][row][i] = cube[1][row][i];
					temp[2][row][i] = cube[2][row][i];
					temp[3][row][i] = cube[3][row][i];
					cube[0][row][i] = temp[3][row][i];
					cube[1][row][i] = temp[0][row][i];
					cube[2][row][i] = temp[1][row][i];
					cube[3][row][i] = temp[2][row][i];
				}
					
					break;
				case 5:
				for (int i = 0; i < 3; i++) { 
					temp[0][row][i] = cube[0][row][i];
					temp[1][row][i] = cube[1][row][i];
					temp[2][row][i] = cube[2][row][i];
					temp[3][row][i] = cube[3][row][i];
					cube[0][row][i] = temp[3][row][i];
					cube[1][row][i] = temp[0][row][i];
					cube[2][row][i] = temp[1][row][i];
					cube[3][row][i] = temp[2][row][i];
				}
					break;
			}
	
		}
	
		
		public static void main(String[] args)
				throws IOException {
	
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
			show(cube);
	
			boolean argsCheck = false;
			int argsRunIndex = 0;
	
			if (args.length > 0) {
				argsCheck = true;
			}
			System.out.println(
					"Enter (u, u1, d, d1, r, r1, l, l1, f, f1, b, b1) to rotate a side or \"current\" to see current cube  or \"stop\" to quit");
			boolean keepGoing = true;
			while (keepGoing) {
				String input;
				if (!argsCheck) {
					input = reader.readLine();
				} else {
					if (argsRunIndex == args.length) {
						argsCheck = false;
						input = "stop";
					} else {
						input = args[argsRunIndex];
						argsRunIndex++;
					}
				}
	
				switch (input) {
					case "u":
						rotate(cube, true, 4);
						rotateEdge(cube, 4, 0, 0, 0, 0);
						solve.add("u'");
						break;
					case "u1":
						rotate(cube, false, 4);
						rotateEdge(cube, 4, 0, 0, 0, 0);
						rotateEdge(cube, 4, 0, 0, 0, 0);
						rotateEdge(cube, 4, 0, 0, 0, 0);
						solve.add("u");
						break;
					case "d":
						rotate(cube, true, 5);
						rotateEdge(cube, 4, 2, 0, 0, 0);
						rotateEdge(cube, 4, 2, 0, 0, 0);
						rotateEdge(cube, 4, 2, 0, 0, 0);
						solve.add("d'");
	
						break;
					case "d1":
						rotate(cube, false, 5);
						rotateEdge(cube, 4, 2, 0, 0, 0);
						solve.add("d");
						break;
					case "r":
						rotate(cube, true, 0);
						rotateEdge(cube, 0, 0, 2, 0, 0);
						solve.add("r'");
						break;
					case "r1":
						rotate(cube, false, 0);
						rotateEdge(cube, 0, 0, 2, 0, 0);
						rotateEdge(cube, 0, 0, 2, 0, 0);
						rotateEdge(cube, 0, 0, 2, 0, 0);
						solve.add("r");
						break;
					case "l":
						rotate(cube, true, 2);
						rotateEdge(cube, 2, 0, 0, 0, 2);
						solve.add("l'");
						break;
					case "l1":
						rotate(cube, false, 2);
						rotateEdge(cube, 2, 0, 0, 0, 2);
						rotateEdge(cube, 2, 0, 0, 0, 2);
						rotateEdge(cube, 2, 0, 0, 0, 2);
						solve.add("l");
						break;
					case "f":
						rotate(cube, true, 1);
						rotateEdge(cube, 1, 2, 2, 0, 0);
						solve.add("f'");
						break;
					case "f1":
						rotate(cube, false, 1);
						rotateEdge(cube, 1, 2, 2, 0, 0);
						rotateEdge(cube, 1, 2, 2, 0, 0);
						rotateEdge(cube, 1, 2, 2, 0, 0);
						solve.add("f");
						break;
					case "b":
						rotate(cube, true, 3);
						rotateEdge(cube, 3, 0, 0, 2, 2);
						rotateEdge(cube, 3, 0, 0, 2, 2);
						rotateEdge(cube, 3, 0, 0, 2, 2);
						solve.add("b'");
						break;
					case "b1":
						rotate(cube, false, 3);
						rotateEdge(cube, 3, 0, 0, 2, 2);
						solve.add("b");
						break;
					case "stop":
						keepGoing = false;
						break;
					case "current":
						show(cube);
						break;
					default:
						System.out.println("Unrecognized input");
						break;
	
				}
	
			}
			show(cube);
		}

		
	
	}