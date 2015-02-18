

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe implements ActionListener	{
	final String VERSION = "2.0";
	
	JFrame window = new JFrame("Tic-Tac-Toe " + VERSION);
	
	JMenuBar mnuMain = new JMenuBar();
	JMenuItem 	mnuNewGame = new JMenuItem("New Game"),	
	mnuInstruction = new JMenuItem("Instructions"),
	mnuExit = new JMenuItem("Exit"), 
	mnuAbout = new JMenuItem("About");
	
	JButton btn1v1 = new JButton("Player vs Player"),
	
	btn1vCPU = new JButton("Player vs Computer"),
	btnQuit = new JButton("Quit"),
	btnSetName = new JButton("Set Player Names"),
	btnContinue = new JButton("Continue..."),
	btnTryAgain = new JButton("Try Again?");
	
	JButton btnEmpty[] = new JButton[10];
	
	JPanel 
	
	pnlNewGame = new JPanel(),
	pnlMenu = new JPanel(),
	pnlMain = new JPanel(),
    pnlTop = new JPanel(),
    pnlBottom = new JPanel(),
	pnlQuitNTryAgain = new JPanel(),
	pnlPlayingField = new JPanel();
				
	JLabel 
	
	lblTitle = new JLabel("Tic-Tac-Toe"),
	lblTurn = new JLabel(),
	lblStatus = new JLabel("", JLabel.CENTER),
	lblMode = new JLabel("", JLabel.LEFT);
	JTextArea txtMessage = new JTextArea();
	
	final int winCombo[][] = new int[][]	{
		{1, 2, 3}, 			{1, 4, 7}, 		{1, 5, 9},
		{4, 5, 6}, 			{2, 5, 8}, 		{3, 5, 7},
		{7, 8, 9}, 			{3, 6, 9}

	};
	
	final int X = 535, Y = 342,
		mainColorR = 190, mainColorG = 50, mainColorB = 50,
		btnColorR = 70, btnColorG = 70, btnColorB = 70;
	Color clrBtnWonColor = new Color(190, 190, 190);
	
	int 	turn = 1,
			player1Won = 0, player2Won = 0,
			wonNumber1 = 1, wonNumber2 = 1, wonNumber3 = 1,
			option;
	
	boolean inGame = false,
			CPUGame = false,
			win = false;
	
			String 	message,
				Player1 = "Player 1", Player2 = "Player 2",
				tempPlayer2 = "Player 2";
	

	public TicTacToe()	{	
	
		window.setSize(X, Y);
		window.setLocation(350, 260);
		
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pnlMenu.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlTop.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlNewGame.setBackground(new Color(mainColorR - 50, mainColorG - 50, mainColorB- 50));
		pnlMenu.setBackground(new Color((mainColorR - 50), (mainColorG - 50), (mainColorB- 50)));
		pnlMain.setBackground(new Color(mainColorR, mainColorG, mainColorB));
		pnlTop.setBackground(new Color(mainColorR, mainColorG, mainColorB));
		pnlBottom.setBackground(new Color(mainColorR, mainColorG, mainColorB));
		pnlQuitNTryAgain.setLayout(new GridLayout(1, 2, 2, 2));
		pnlQuitNTryAgain.add(btnTryAgain);
		pnlQuitNTryAgain.add(btnQuit);
		mnuMain.add(mnuNewGame);
		mnuMain.add(mnuInstruction);
		mnuMain.add(mnuAbout);
		mnuMain.add(mnuExit);
		pnlNewGame.setLayout(new GridLayout(4, 1, 2, 10));
		pnlNewGame.add(btnContinue);
		pnlNewGame.add(btn1v1);
		pnlNewGame.add(btn1vCPU);
		pnlNewGame.add(btnSetName);
		btnTryAgain.setEnabled(false);
		btnContinue.setEnabled(false);
		txtMessage.setBackground(new Color(mainColorR-30, mainColorG-30, mainColorB-30));
		txtMessage.setForeground(Color.white);
		txtMessage.setEditable(false);
		mnuNewGame.addActionListener(this);
		mnuExit.addActionListener(this);
		mnuInstruction.addActionListener(this);
		mnuAbout.addActionListener(this);
		btn1v1.addActionListener(this);
		btn1vCPU.addActionListener(this);
		btnQuit.addActionListener(this);
		btnSetName.addActionListener(this);
		btnContinue.addActionListener(this);
		btnTryAgain.addActionListener(this);
		pnlPlayingField.setLayout(new GridLayout(3, 3, 2, 2));
		pnlPlayingField.setBackground(Color.black);
		
		for(int i=1; i<=9; i++)	{
			btnEmpty[i] = new JButton();
			btnEmpty[i].setBackground(new Color(btnColorR, btnColorG, btnColorB));
			btnEmpty[i].addActionListener(this);
			pnlPlayingField.add(btnEmpty[i]);
		}

		lblMode.setForeground(Color.white);
		pnlMenu.add(lblMode);
		pnlMenu.add(mnuMain);
		pnlMain.add(lblTitle);
		window.add(pnlMenu, BorderLayout.NORTH);
		window.add(pnlMain, BorderLayout.CENTER);
		window.setVisible(true);
	}
	
	
	public static void main(String[] args)	{
		new TicTacToe();
	
	}




	public void showGame()	{	
		clearPanelSouth();
		pnlMain.setLayout(new BorderLayout());
		pnlTop.setLayout(new BorderLayout());
		pnlBottom.setLayout(new BorderLayout());
		pnlTop.add(pnlPlayingField);
		pnlBottom.add(lblTurn, BorderLayout.WEST);
		pnlBottom.add(lblStatus, BorderLayout.CENTER);
		pnlBottom.add(pnlQuitNTryAgain, BorderLayout.EAST);
		pnlMain.add(pnlTop, BorderLayout.CENTER);
		pnlMain.add(pnlBottom, BorderLayout.SOUTH);
		pnlPlayingField.requestFocus();
		inGame = true;
		checkTurn();
		checkWinStatus();

	}

	public void newGame()	{	
		
		btnEmpty[wonNumber1].setBackground(new Color(btnColorR, btnColorG, btnColorB));
		btnEmpty[wonNumber2].setBackground(new Color(btnColorR, btnColorG, btnColorB));
		btnEmpty[wonNumber3].setBackground(new Color(btnColorR, btnColorG, btnColorB));
		for(int i=1; i<10; i++)	{
			btnEmpty[i].setText("");
			btnEmpty[i].setEnabled(true);
		
		}
		
		turn = 1;
		win = false;
		showGame();
	}

	public void quit()	{
		inGame = false;
		lblMode.setText("");
		btnContinue.setEnabled(false);
		clearPanelSouth();
		setDefaultLayout();
		pnlTop.add(pnlNewGame);
		pnlMain.add(pnlTop);
	}

	public void checkWin()	{	
		
		for(int i=0; i<8; i++)	{
			if(
				!btnEmpty[winCombo[i][0]].getText().equals("") &&
				btnEmpty[winCombo[i][0]].getText().equals(btnEmpty[winCombo[i][1]].getText()) &&								
				btnEmpty[winCombo[i][1]].getText().equals(btnEmpty[winCombo[i][2]].getText()))	{
				win = true;
				wonNumber1 = winCombo[i][0];
				wonNumber2 = winCombo[i][1];
				wonNumber3 = winCombo[i][2];
		
				btnEmpty[wonNumber1].setBackground(clrBtnWonColor);
				btnEmpty[wonNumber2].setBackground(clrBtnWonColor);
				btnEmpty[wonNumber3].setBackground(clrBtnWonColor);
				break;
			}
		}
		
		if(win || (!win && turn>9))	{
			if(win)	{
				if(btnEmpty[wonNumber1].getText().equals("X"))	{
					message = Player1 + " has won";
					player1Won++;
				}
				else	{
					
					message = Player2 + " has won";
					player2Won++;
				}
		}	else if(!win && turn>9)
				
			message = "Both players have tied!\nBetter luck next time.";
			showMessage(message);
			
			for(int i=1; i<=9; i++)	{
				btnEmpty[i].setEnabled(false);
			}
			btnTryAgain.setEnabled(true);
			checkWinStatus();
		} else
			checkTurn();
	}

	public void AI()	{
		
		int computerButton;
		
		if(turn <= 9)	{
			turn++;
			computerButton = CPU.doMove(
				btnEmpty[1], btnEmpty[2], btnEmpty[3],
				btnEmpty[4], btnEmpty[5], btnEmpty[6],
				btnEmpty[7], btnEmpty[8], btnEmpty[9]);
			if(computerButton == 0)
				Random();
			else {
				btnEmpty[computerButton].setText("O");
				btnEmpty[computerButton].setEnabled(false);
			}
			checkWin();
		}
	}

	
	public void Random()	{
		int random;
		if(turn <= 9)	{
			random = 0;
			
			while(random == 0)	{
				random = (int)(Math.random() * 10);
			}
			
			if(CPU.doRandomMove(btnEmpty[random]))	{
				btnEmpty[random].setText("O");
				btnEmpty[random].setEnabled(false);
			} else {
				Random();
			}
		}
	}

	public void checkTurn()	{
		String whoTurn;
		
		if(!(turn % 2 == 0))	{
			whoTurn = Player1 + " [X]";
		}	else	{
			whoTurn = Player2 + " [O]";
		}
		lblTurn.setText("Turn: " + whoTurn);
	}

	public void askUserForPlayerNames()	{
		
		
		String temp;
		boolean tempIsValid = false;
		temp = getInput("Enter player 1 name:", Player1);
		if(temp == null)	{}
		else if(temp.equals(""))
			showMessage("Invalid Name!");
		else if(temp.equals(Player2))	{
			option = askMessage("Player 1 name matches Player 2's\nDo you want to continue?", "Name Match", JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION)
				tempIsValid = true;
		} else if(temp != null)	{
			tempIsValid = true;
		}
		
		if(tempIsValid)	{
			Player1 = temp;
			tempIsValid = false;
		}
		
		temp = getInput("Enter player 2 name:", Player2);
		if(temp == null)	{/*Do Nothing*/}
		else if(temp.equals(""))
			showMessage("Invalid Name!");
		else if(temp.equals(Player1))	{
			option = askMessage("Player 2 name matches Player 1's\nDo you want to continue?", "Name Match", JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION)
				tempIsValid = true;
		} else if(temp != null)	{
			tempIsValid = true;
		}
		if(tempIsValid)	{
			Player2 = temp;
			tempPlayer2 = temp;
			tempIsValid = false;
		}
	}

	public void setDefaultLayout()	{
		pnlMain.setLayout(new GridLayout(2, 1, 2, 5));
		pnlTop.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
	}

	public void checkWinStatus()	{
		lblStatus.setText(Player1 + ": " + player1Won + " | " + Player2 + ": " + player2Won);	
	}

	public int askMessage(String msg, String tle, int op)	{
		return JOptionPane.showConfirmDialog(null, msg, tle, op);
	}

	public String getInput(String msg, String setText)	{
		return JOptionPane.showInputDialog(null, msg, setText);
	}

	public void showMessage(String msg)	{
		JOptionPane.showMessageDialog(null, msg);
	}

	public void clearPanelSouth()	{	
		
		pnlMain.remove(lblTitle);
		pnlMain.remove(pnlTop);
		pnlMain.remove(pnlBottom);
		pnlTop.remove(pnlNewGame);
		pnlTop.remove(txtMessage);
		pnlTop.remove(pnlPlayingField);
		pnlBottom.remove(lblTurn);
		pnlBottom.remove(pnlQuitNTryAgain);
	}

	
	public void actionPerformed(ActionEvent click)	{
		Object source = click.getSource();
		
		for(int i=1; i<=9; i++)	{
			
			if(source == btnEmpty[i] && turn <	10)	{
				if(!(turn % 2 == 0))
					btnEmpty[i].setText("X");
				else
					btnEmpty[i].setText("O");
				btnEmpty[i].setEnabled(false);
				pnlPlayingField.requestFocus();
				turn++;
				checkWin();
				if(CPUGame && win == false)
					AI();
			}
		}
		
		if(source == mnuNewGame || source == mnuInstruction || source == mnuAbout)	{
			clearPanelSouth();
			setDefaultLayout();
			
			if(source == mnuNewGame)	{
				pnlTop.add(pnlNewGame);
			}	
			
			else if(source == mnuInstruction || source == mnuAbout)	{
				
				if(source == mnuInstruction)	{
					message = 	"Instructions:\n\n" +
									"Your goal is to be the first player to get 3 X's or O's in a\n" +
									"row. (horizontally, diagonally, or vertically)\n" +
									Player1 + ": X\n" +
									Player2 + ": O\n";
				
				} else	{
					message = 	"About:\n\n" +
									"Title: Tic-Tac-Toe\n" +
									"Creator: Blmaster\n" +
									"Version: " + VERSION + "\n";
				}
				txtMessage.setText(message);
				pnlTop.add(txtMessage);
			}	
			pnlMain.add(pnlTop);
		}
		
		else if(source == btn1v1 || source == btn1vCPU)	{
			
			if(inGame)	{
				option = askMessage("If you start a new game," +
					"your current game will be lost..." + "\n" +
					"Are you sure you want to continue?", 
					"Quit Game?" ,JOptionPane.YES_NO_OPTION
				);
				
				if(option == JOptionPane.YES_OPTION)
					inGame = false;
			}
			
			if(!inGame)	{
				btnContinue.setEnabled(true);
				if(source == btn1v1)	{
					Player2 = tempPlayer2;
					player1Won = 0;
					player2Won = 0;
					lblMode.setText("1 v 1");
					CPUGame = false;	
					newGame();
					
				} else	{
					
					Player2 = "Computer";
					player1Won = 0;
					player2Won = 0;
					lblMode.setText("1 v CPU");
					CPUGame = true;
					newGame();
				}
			}
		}
		
		else if(source == btnContinue)	{
			checkTurn();
			showGame();
		}
		
		else if(source == btnSetName)	{
			askUserForPlayerNames();
		}
		
		else if(source == mnuExit)	{
			option = askMessage("Are you sure you want to exit?", "Exit Game", JOptionPane.YES_NO_OPTION);
			
			if(option == JOptionPane.YES_OPTION)
				System.exit(0);
		}
		
		else if(source == btnTryAgain)	{
			newGame();
			btnTryAgain.setEnabled(false);
		}
		
		else if(source == btnQuit)	{
			quit();
		}
		pnlMain.setVisible(false);
		pnlMain.setVisible(true);
	}
}

