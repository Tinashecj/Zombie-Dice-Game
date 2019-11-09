/* 	Tinashe Changunda 2984770
	I recommend using Lucida Console font, font size between 12-16, white text on black background */
import java.util.*;
class Assignment_02{ 
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n");
		welcomeDisplay();
		System.out.println("\n\n\tEat Brains. Don\'t get shotgunned.\n\tThis game includes these rules, 13 dice, and a virtual cup to hold them. Two or more can play.\n\tThe first player is the one who won the last game, or the one who can say \"Braaaaains!\" with the most feeling.\n\n\tOn your turn, you will select three dice from the cup at random. Each one is a human victim. The red dice are the toughest. Green dice are the easiest, and yellow are medium tough.\n\tThe dice have three symbols:\n\tBrains - you ate your victim\'s brain. The die will be set aside.\n\tShotgun - he fought back! The die will be set aside.\n\tFootprints - your victim escaped. This die will be re-rolled if you choose to continue your turn.\n\tIf you roll three shotguns, your turn is over. Otherwise, you can choose to stop and score, or continue.\n\tIf you decide to stop, you will score 1 point for each Brain you have, and all the dice will be returned to the cup for the next player\'s turn.\n\tIf you choose to keep going, all your footprints will be used again and you will pick dice randomly from the cup until you have three dice again.\n\tAfter you take new dice you can\'t decide to stop... you have to roll.\n\tIf at any points you have atleast three Shotguns rolled, your turn is over and you lose your Brains for that turn.\n\n\tPlay until someone reaches more that 13 Brains. Then finish the round. If a tie occurs, another round will be played until there is one winner.\n\n");
		System.out.print("How Many Players Would Like To Play? ");
		int numOfPlayers = kb.nextInt();
		while (numOfPlayers < 2){  // Incase they input 1 or less players this will force them to re enter an appropriate amount
			System.out.print("Need at least 2 players. How Many Players Would Like To Play? ");
			numOfPlayers = kb.nextInt();	
		}
		kb.nextLine(); // clear buffer
		String[] players = new String[numOfPlayers];
		int[] playersScores = new int[numOfPlayers];
		for (int i = 0; i < numOfPlayers; i++){
			System.out.print("Enter the Name of Player " + (i + 1) + ": ");
			players[i] = kb.nextLine();
			playersScores[i] = 0;
		}
		int turn = 0;
		int highestScore = 0, highestScoreOccurance = 0; // will be used to determine if there is a tie and who will be going to sudden death
		while (playersScores[turn] <= 13){
			String[] greenDi = {"Footprints", "Footprints", "  Shotgun ", "  Brains  ", "  Brains  ", "  Brains  "};
			String[] yellowDi = {"Footprints", "Footprints", "  Shotgun ", "  Shotgun ", "  Brains  ", "  Brains  "};
			String[] redDi = {"Footprints", "Footprints", "  Shotgun ", "  Shotgun ", "  Shotgun ", "  Brains  "};
			int[] inventory = {1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3}; //represents dice in the cup, red is 1, yellow is 2, green is 3
			int[] thisTurn = {0, 0, 0, 0, 0, 1}; //shotguns, brains, footsteps1, footsteps2, footsteps3, rollNumber
			int roll1, roll2, roll3;
			int shotguns = 0, brains = 0, footsteps1 = 0, footsteps2 = 0, footsteps3 = 0; // if a footprint is rolled, that die will change footsteps1, 2, or 3 to a one so it will not choose another die for the next roll 
			roll1 = pickDi1(inventory); //picks die 1
			roll2 = pickDi2(inventory); //picks die 2
			roll3 = pickDi3(inventory); //picks die 3
			printDisplay1(greenDi, yellowDi, redDi, roll1, shotguns, brains, footsteps1, thisTurn, players, playersScores, numOfPlayers, turn); //displays what die 1 was rolled as well as die color
			printDisplay2(greenDi, yellowDi, redDi, roll2, shotguns, brains, footsteps2, thisTurn); //displays what die 2 was rolled as well as die color
			printDisplay3(greenDi, yellowDi, redDi, roll3, shotguns, brains, footsteps3, thisTurn); //displays what die 3 was rolled as well as die color
			shotguns = thisTurn[0]; //takes value from the array
			brains = thisTurn[1]; //takes value from the array
			footsteps1 = thisTurn[2]; //takes value from the array
			footsteps2 = thisTurn[3]; //takes value from the array
			footsteps3 = thisTurn[4]; //takes value from the array
			int redLeft = 0, yellowLeft = 0, greenLeft = 0;
			for (int i = 0; i < 13; i++){  // keeps track of how many of each color die remains
				if (inventory[i] == 1){
					redLeft++;
				}
				else if (inventory[i] == 2){
					yellowLeft++;
				}
				else if (inventory[i] == 3){
					greenLeft++;
				}
			}
			System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tShotguns: " + shotguns);
			System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tBrains: " + brains);
			System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tRed Dice Remaining: " + redLeft);
			System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tYellow Dice Remaining: " + yellowLeft);
			System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tGreen Dice Remaining: " + greenLeft);
			if (shotguns < 3){	// roll again branch
				String contPlay = "";
				String play = "play";
				String pass = "pass";
				System.out.print("\nWould You Like To Roll Again? Lower-case \"play\" to Roll Again or \"pass\" to End Turn: ");
				contPlay = kb.nextLine();
				while (contPlay.equals(play) != true && contPlay.equals(pass) != true){ //checks for invalid response
					System.out.print("\nInvalid Response. Would You Like To Roll Again? Lower-case \"play\" to Roll Again or \"pass\" to End Turn: ");
					contPlay = kb.nextLine();
				}
				if (contPlay.equals(play) != true){ // end turn if they pass
					System.out.println("\nEnd of turn");
					playersScores[turn] = playersScores[turn] + brains;
					turn++;
					if (turn == (numOfPlayers)) { //rolls back to first players turn
						turn = 0;
					}
					System.out.println("\n" + players[turn] + "'s Turn is Next. Press Enter to Continue.");
					kb.nextLine();
				}
				while (contPlay.equals(play) == true){ //continues turn and rolls again until the player passes
					if (thisTurn[2] != 1){	//keeps previous die if last roll was footprints
						roll1 = pickDi1(inventory);
					}
					if (thisTurn[3] != 1){	//keeps previous die if last roll was footprints
						roll2 = pickDi2(inventory);
					}
					if (thisTurn[4] != 1){	//keeps previous die if last roll was footprints
						roll3 = pickDi3(inventory);
					}
					if (roll1 != 4){	//makes sure there are remaining dice
						printDisplay1(greenDi, yellowDi, redDi, roll1, shotguns, brains, footsteps1, thisTurn, players, playersScores, numOfPlayers, turn);
					}
					if (roll2 != 4){	//makes sure there are remaining dice	
						printDisplay2(greenDi, yellowDi, redDi, roll2, shotguns, brains, footsteps2, thisTurn);
					}
					if (roll3 != 4){	//makes sure there are remaining dice
						printDisplay3(greenDi, yellowDi, redDi, roll3, shotguns, brains, footsteps3, thisTurn);
					}
					if (roll1 == 4 && roll2 == 4 && roll3 == 4){ //if you run out of dice your turn is over
					System.out.println("\nEnd of turn, No More Dice.");
					playersScores[turn] = playersScores[turn] + brains;
					turn++;
					if (turn == (numOfPlayers)) { // in case you need to roll back to the first players turn
						turn = 0;
					}
					System.out.println("\n" + players[turn] + "'s Turn is Next. Press Enter to Continue.");
					kb.nextLine();
					break;
					}
					shotguns = thisTurn[0];
					brains = thisTurn[1];
					footsteps1 = thisTurn[2];
					footsteps2 = thisTurn[3];
					footsteps3 = thisTurn[4];
					redLeft = 0; 
					yellowLeft = 0; 
					greenLeft = 0;
					for (int i = 0; i < 13; i++){ // count dice left again
						if (inventory[i] == 1){
							redLeft++;
						}
						else if (inventory[i] == 2){
							yellowLeft++;
						}
						else if (inventory[i] == 3){
							greenLeft++;
						}
					}
					System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tShotguns: " + shotguns);
					System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tBrains: " + brains);
					System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \t \t \tRed Dice Remaining: " + redLeft);
					System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tYellow Dice Remaining: " + yellowLeft);
					System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \tGreen Dice Remaining: " + greenLeft);
					if (shotguns >= 3){ //if you roll 3 or more total shotguns at any point
						System.out.println("\nYou Were Shot 3 Times, You lose Your Score This Turn.");
						brains = 0;
						playersScores[turn] = playersScores[turn] + brains;
						turn++;
						if (turn == (numOfPlayers)) { //rolls back to player one
							turn = 0;
						}
						System.out.println("\n" + players[turn] + "'s Turn is Next. Press Enter to Continue.");
						kb.nextLine();
						break;
					}
					System.out.print("\nWould You Like To Roll Again? Lower-case \"play\" to Roll Again or \"pass\" to End Turn: ");
					contPlay = kb.nextLine();
					while (contPlay.equals(play) != true && contPlay.equals(pass) != true){ //ensures appropriate response 
						System.out.print("\nInvalid Response. Would You Like To Roll Again? Lower-case \"play\" to Roll Again or \"pass\" to End Turn: ");
						contPlay = kb.nextLine();
					}	
					if (contPlay.equals(play) != true){ // ends turn
						System.out.println("\nEnd of turn");
						playersScores[turn] = playersScores[turn] + brains; // adds brains to total score
						turn++;
						if (turn == (numOfPlayers)) { // rolls back to player one
							turn = 0;
						}
						System.out.println("\n" + players[turn] + "'s Turn is Next. Press Enter to Continue.");
						kb.nextLine();
					}
				}
			}
			else if (shotguns >= 3){ // Ends turn
				System.out.println("\nYou Were Shot 3 Times, You lose Your Score This Turn.");
				brains = 0; // Lose all your brains
				playersScores[turn] = playersScores[turn] + brains; 
				turn++;
				if (turn == (numOfPlayers - 1)) { //rolls back to player one
					turn = 0;
				}
				System.out.println("\n" + players[turn] + "'s Turn is Next. Press Enter to Continue.");
				kb.nextLine();
			}
			if (turn == (numOfPlayers)) { //rolls back to player one
				turn = 0;
			}
		}
		highestScore = 0;
		highestScoreOccurance = 0;
		for (int i = 0; i < numOfPlayers; i++){ // looks for highest score after last turn
			if (playersScores[i] > highestScore){
				highestScore = playersScores[i];
			}
		}
		for (int i = 0; i < numOfPlayers; i++){ // checks for ties in first place after last turn 
			if (playersScores[i] == highestScore){
				highestScoreOccurance++;
			}
		}
		if (highestScoreOccurance == 1){ // if there is no ties, declares winner 
			for (int i = 0; i < numOfPlayers; i++){ // find the winner's array location
				int winner;
				if (playersScores[i] == highestScore){
					winner = i;
					System.out.println("\t\t\t\t\t\t\t___________.__              __      __.__                             .___       ");
					System.out.println("\t\t\t\t\t\t\t\\__    ___/|  |__   ____   /  \\    /  \\__| ____   ____   ___________  |   | ______");
					System.out.println("\t\t\t\t\t\t\t  |    |   |  |  \\_/ __ \\  \\   \\/\\/   /  |/    \\ /    \\_/ __ \\_  __ \\ |   |/  ___/");
					System.out.println("\t\t\t\t\t\t\t  |    |   |   Y  \\  ___/   \\        /|  |   |  \\   |  \\  ___/|  | \\/ |   |\\___ \\");
					System.out.println("\t\t\t\t\t\t\t  |____|   |___|  /\\___  >   \\__/\\  / |__|___|  /___|  /\\___  >__|    |___/____  >");
					System.out.println("\t\t\t\t\t\t\t                \\/     \\/         \\/          \\/     \\/     \\/                 \\/ ");
					System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t" + players[winner] + "!\n\n\n");
				}
			}
		}
		else{ //sends to sudden death
			String[] suddenDeathYellowDi = {"Footprints", "Footprints", "  Shotgun ", "  Shotgun ", "  Brains  ", "  Brains  "};
			suddenDeath(highestScoreOccurance, numOfPlayers, playersScores, highestScore, players, suddenDeathYellowDi);
		}
	}
	public static void suddenDeath(int highestScoreOccurance, int numOfPlayers,int[] playersScores, int highestScore, String[] players, String[] suddenDeathYellowDi){
		int tieCount = 0;
		Scanner kb = new Scanner(System.in);
		String[] suddenDeathPlayers = new String[highestScoreOccurance]; // new player array
		int[] suddenDeathPlayersTally = new int[highestScoreOccurance]; 
		for (int i = 0; i < suddenDeathPlayersTally.length; i++){
			suddenDeathPlayersTally[i] = 0;
		}
		for (int i = 0; i < numOfPlayers; i++){ // determines who tied and brings them into the new array
			if (playersScores[i] == highestScore){
				suddenDeathPlayers[tieCount] = players[i];
				tieCount++;
			}
			if (tieCount == highestScoreOccurance){
				break;
			}
		}
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nWelcome to Sudden Death. The Remaining Players will Roll Yellow Dice Until Only One Person Rolls a Brain. This Player will be Crowned Victorious!");
		int brainCount = 0; // gets you into the while loop
		int suddenDeathRoundCounter = 1;
		while (brainCount != 1){
			int winnerID = -1;
			System.out.println("\t\t\t\t\t\t  _________         .___  .___              ________                 __  .__    ");
			System.out.println("\t\t\t\t\t\t /   _____/__ __  __| _/__| _/____   ____   \\______ \\   ____ _____ _/  |_|  |__ ");
			System.out.println("\t\t\t\t\t\t \\_____  \\|  |  \\/ __ |/ __ |/ __ \\ /    \\   |    |  \\_/ __ \\\\__  \\\\   __\\  |  \\");
			System.out.println("\t\t\t\t\t\t /        \\  |  / /_/ / /_/ \\  ___/|   |  \\  |    `   \\  ___/ / __ \\|  | |   Y  \\");
			System.out.println("\t\t\t\t\t\t/_______  /____/\\____ \\____ |\\___  >___|  / /_______  /\\___  >____  /__| |___|  /");
			System.out.println("\t\t\t\t\t\t        \\/           \\/    \\/    \\/     \\/          \\/     \\/     \\/          \\/");
			System.out.println("\n\t\t\t\t\t\t\t\t\t\t Round " + suddenDeathRoundCounter + "\n\n\n");
			for (int i = 0; i < suddenDeathPlayers.length; i++){ // allows each player to roll one at a time 
				System.out.print(suddenDeathPlayers[i] + "\'s Turn, Press Enter to Roll the Die!");
				kb.nextLine();
				int randomNum = suddenDeathRoll(suddenDeathYellowDi, suddenDeathPlayersTally); //rolls die
				if(randomNum == 4 || randomNum == 5){ // checks if they rolled a brain
					suddenDeathPlayersTally[i] = 1;
				}
			}
			for (int i = 0; i < suddenDeathPlayers.length; i++){ //counts how many players rolled brains 
				if (suddenDeathPlayersTally[i] == 1){
					brainCount++;
				}		
			}
			if (brainCount == 1){ // if only one player rolled a brain, declare winner
				for (int i = 0; i < suddenDeathPlayers.length; i++){
					if (suddenDeathPlayersTally[i] == 1){
						winnerID = i;
						break;
					}
				}
				System.out.println("\t\t\t\t\t\t\t___________.__              __      __.__                             .___       ");
				System.out.println("\t\t\t\t\t\t\t\\__    ___/|  |__   ____   /  \\    /  \\__| ____   ____   ___________  |   | ______");
				System.out.println("\t\t\t\t\t\t\t  |    |   |  |  \\_/ __ \\  \\   \\/\\/   /  |/    \\ /    \\_/ __ \\_  __ \\ |   |/  ___/");
				System.out.println("\t\t\t\t\t\t\t  |    |   |   Y  \\  ___/   \\        /|  |   |  \\   |  \\  ___/|  | \\/ |   |\\___ \\");
				System.out.println("\t\t\t\t\t\t\t  |____|   |___|  /\\___  >   \\__/\\  / |__|___|  /___|  /\\___  >__|    |___/____  >");
				System.out.println("\t\t\t\t\t\t\t                \\/     \\/         \\/          \\/     \\/     \\/                 \\/ ");
				System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t" + suddenDeathPlayers[winnerID] + "!\n\n\n");
				break;
			}
			else { // if either nobody or multiple players rolled brains it resets the tallies and brain counter to start the next round
				for (int i = 0; i < suddenDeathPlayersTally.length; i++){
					suddenDeathPlayersTally[i] = 0;
				}
				suddenDeathRoundCounter++;
				brainCount = 0;
			}
		}
		
	}
	public static int suddenDeathRoll(String[] suddenDeathYellowDi, int[] suddenDeathPlayersTally){
		Random r = new Random();
		String[] suddenDeathDie = new String [6];
		suddenDeathDie = suddenDeathYellowDi;
		int randomNum;
		System.out.println("\t\t\t\t\t\t\t\t\t     ______________ ");
		System.out.println("\t\t\t\t\t\t\t\t\t    |              |");
		System.out.println("\t\t\t\t\t\t\t\t\t    |              |");
		System.out.println("\t\t\t\t\t\t\t\t\t    |              |");
		System.out.print("\t\t\t\t\t\t\t\t\t    ");
		randomNum = r.nextInt(6);		
		System.out.print("|  " + suddenDeathDie[randomNum] + "  |");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t    |              |");
		System.out.println("\t\t\t\t\t\t\t\t\t    |              |");
		System.out.println("\t\t\t\t\t\t\t\t\t    |______________|");
		System.out.println("\n");
		return randomNum; //returns the randomly generated number
		
	}
	public static int pickDi1(int[] inventory){
		Random r = new Random();
		int r1 = r.nextInt(13);
		int roll1 = inventory[r1];
		int zeroCount = 0;
		while (roll1 == 0){ //re-picks die incase the die selected has already been picked this turn 
			r1 = r.nextInt(13);
			roll1 = inventory[r1];
			for (int i = 0; i < 13; i++){ //checks how many dice has been selected so far
				if (inventory[i] == 0){
					zeroCount++;
				}
			}
			if (zeroCount == 13){ //if there are no remaining dice 
				zeroCount = 0;
				roll1 = 4;
				return roll1;
			}
		}
		zeroCount = 0;
		inventory[r1] = 0;
		return roll1;
	}
	public static int pickDi2(int[] inventory){
		Random r = new Random();
		int r2 = r.nextInt(13);
		int roll2 = inventory[r2];
		int zeroCount = 0;
		while (roll2 == 0){ //re-picks die incase the die selected has already been picked this turn 
			r2 = r.nextInt(13);
			roll2 = inventory[r2];
			for (int i = 0; i < 13; i++){ //checks how many dice has been selected so far
				if (inventory[i] == 0){
					zeroCount++;
				}
			}
			if (zeroCount == 13){ //if there are no remaining dice 
				zeroCount = 0;
				roll2 = 4;
				return roll2;
			}
		}
		zeroCount = 0;
		inventory[r2] = 0;
		return roll2;
	}
	public static int pickDi3(int[] inventory){
		Random r = new Random();
		int r3 = r.nextInt(13);
		int roll3 = inventory[r3];
		int zeroCount = 0;
		while (roll3 == 0){ //re-picks die incase the die selected has already been picked this turn
			r3 = r.nextInt(13);
			roll3 = inventory[r3];
			for (int i = 0; i < 13; i++){ //checks how many dice has been selected so far
				if (inventory[i] == 0){
					zeroCount++;
				}
			}
			if (zeroCount == 13){ //if there are no remaining dice 
				zeroCount = 0;
				roll3 = 4;
				return roll3;
			}
		}
		zeroCount = 0;
		inventory[r3] = 0;
		return roll3;
	}
	public static void printDisplay1(String[] greenDi, String[] yellowDi, String[] redDi, int roll1, int shotguns, int brains, int footsteps1, int[] thisTurn, String[] players, int[] playersScores, int numOfPlayers, int turn){
		Random r = new Random();
		brains = 0;
		shotguns = 0;
		String[] diRoll1 = new String [6];
		String diRoll1Color = ""; 
		String diRoll2Color = ""; 
		String diRoll3Color = "";
		String greenColor = "Green Die ";
		String yellowColor = "Yellow Die";
		String redColor = "Red Die   ";
		if (roll1 == 1){ //Sets the string that corresponds with the die picked
			diRoll1 = redDi;
			diRoll1Color = redColor;
		}
		else if (roll1 == 2){ //Sets the string that corresponds with the die picked
			diRoll1 = yellowDi;
			diRoll1Color = yellowColor;
		}
		else if (roll1 == 3){ //Sets the string that corresponds with the die picked
			diRoll1 = greenDi;
			diRoll1Color = greenColor;
		}
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				System.out.println("\t   _____  .__       .__                  .__      _________             __    __    __________                              _________                                          ");
		System.out.println("\t  /     \\ |__| ____ |  |__ _____    ____ |  |    /   _____/ ____  _____/  |__/  |_  \\______   \\_____  ______   ___________  \\_   ___ \\  ____   _____ ___________    ____ ___.__.");
		System.out.println("\t /  \\ /  \\|  |/ ___\\|  |  \\\\__  \\ _/ __ \\|  |    \\_____  \\_/ ___\\/  _ \\   __\\   __\\  |     ___/\\__  \\ \\____ \\_/ __ \\_  __ \\ /    \\  \\/ /  _ \\ /     \\\\____ \\__  \\  /    <   |  |");
		System.out.println("\t/    Y    \\  \\  \\___|   Y  \\/ __ \\\\  ___/|  |__  /        \\  \\__(  <_> )  |  |  |    |    |     / __ \\|  |_> >  ___/|  | \\/ \\     \\___(  <_> )  Y Y  \\  |_> > __ \\|   |  \\___  |");
		System.out.println("\t\\____|__  /__|\\___  >___|  (____  /\\___  >____/ /_______  /\\___  >____/|__|  |__|    |____|    (____  /   __/ \\___  >__|     \\______  /\\____/|__|_|  /   __(____  /___|  / ____|");
		System.out.println("\t        \\/        \\/     \\/     \\/     \\/               \\/     \\/                                   \\/|__|        \\/                \\/             \\/|__|       \\/     \\/\\/    ");
		System.out.println();
		for (int i = 0; i < numOfPlayers; i++){ //display scores
			System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t " + players[i] + ": " + playersScores[i]);
		}
		System.out.println("\n" + players[turn] + "'s Turn, Roll Number " + thisTurn[5]);
		int j1;
		System.out.println("\n\n\t\t\t\t\t\t  #######  ####   #     #  #####   ###  ######   ####   ###    ####  ######\t\t                       .....");
		System.out.println("\t\t\t\t\t\t       #  #    #  ##   ##  #    #   #   #        #   #   #    #      #     \t\t                      C C  /");
		System.out.println("\t\t\t\t\t\t      #   #    #  # # # #  #    #   #   #        #    #  #   #       #     \t\t                     /<   /");
		System.out.println("\t\t\t\t\t\t     #    #    #  #  #  #  #####    #   ######   #    #  #   #       ######\t\t      ___ __________/_#__=o");
		System.out.println("\t\t\t\t\t\t    #     #    #  #     #  #    #   #   #        #    #  #   #       #     \t\t     /(- /(\\_\\________   \\ ");
		System.out.println("\t\t\t\t\t\t   #      #    #  #     #  #    #   #   #        #   #   #    #      #     \t\t     \\ ) \\ )_      \\o     \\");
		System.out.println("\t\t\t\t\t\t  #######  ####   #     #  #####   ###  ######   ###    ###    ####  ######\t\t     /|\\ /|\\       |'     |");
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   |     _|");
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   /o   __\\");
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  / '     |");
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t / /      |");
		System.out.println("\t\t\t\t\t\t\t\t\t ______________ \t\t\t\t\t\t\t\t        /_/\\______|");
		System.out.println("\t\t\t\t\t\t\t\t\t|              |\t\t\t\t\t\t\t\t       (   _(    <");
		System.out.println("\t\t\t\t\t\t\t\t\t|              |\t\t\t\t\t\t\t\t\t\\    \\    \\   ");
		System.out.println("\t\t\t\t\t\t\t\t\t|              |\t\t\t\t\t\t\t\t\t \\    \\    |  ");
		System.out.print("\t\t\t\t\t\t\t\t\t");
		j1 = r.nextInt(6);		
		System.out.print("|  " + diRoll1[j1] + "  |\t" + diRoll1Color + "\t\t\t\t\t\t  \\____\\____\\ ");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t|              |\t\t\t\t\t\t\t\t\t  ____\\_\\__\\_\\");
		System.out.println("\t\t\t\t\t\t\t\t\t|              |\t\t\t\t\t\t\t\t\t/`   /`     o\\");
		System.out.println("\t\t\t\t\t\t\t\t\t|______________|\t\t\t\t\t\t\t\t\t|___ |_______|..");
		System.out.println("\n");
		int rollNumber = thisTurn[5];
		rollNumber++;
		thisTurn[5] = rollNumber;
		if (roll1 == 1){ // if  red die		
			if (j1 == 2 || j1 == 3 || j1 == 4){
				shotguns++;
			}
			else if (j1 == 5){
				brains++;
			}
		}
		else if (roll1 == 2){	//if yellow die	
			if (j1 == 2 || j1 == 3){
				shotguns++;
			}
			else if (j1 == 4 || j1 == 5){
				brains++;
			}
		}
		else if (roll1 == 3){	 // if green die	
			if (j1 == 2){
				shotguns++;
			}
			else if (j1 == 3 || j1 == 4 || j1 == 5){
				brains++;
			}
		}
		if (j1 == 0 || j1 == 1){ // if a footstep is rolled change tally to one so a new die won't be selected next turn
			footsteps1 = 1;
		}
		else {
			footsteps1 = 0;
		}
		thisTurn[0] = thisTurn[0] + shotguns;
		thisTurn[1] = thisTurn[1] + brains;
		thisTurn[2] = footsteps1;
	}
	public static void printDisplay2(String[] greenDi, String[] yellowDi, String[] redDi, int roll2, int shotguns, int brains, int footsteps2, int[] thisTurn){
		Random r = new Random();
		brains = 0;
		shotguns = 0;
		String[] diRoll2 = new String [6];
		String diRoll1Color = ""; 
		String diRoll2Color = ""; 
		String diRoll3Color = "";
		String greenColor = "Green Die";
		String yellowColor = "Yellow Die";
		String redColor = "Red Die";
		if (roll2 == 1){ //Sets the string that corresponds with the die picked
			diRoll2 = redDi;
			diRoll2Color = redColor;
		}
		else if (roll2 == 2){ //Sets the string that corresponds with the die picked
			diRoll2 = yellowDi;
			diRoll2Color = yellowColor;
		}
		else if (roll2 == 3){ //Sets the string that corresponds with the die picked
			diRoll2 = greenDi;
			diRoll2Color = greenColor;
		}
		int j2;
		System.out.println("\t\t\t\t\t\t\t\t\t ______________ ");
		System.out.println("\t\t\t\t\t\t\t\t\t|              |");
		System.out.println("\t\t\t\t\t\t\t\t\t|              |");
		System.out.println("\t\t\t\t\t\t\t\t\t|              |");
		System.out.print("\t\t\t\t\t\t\t\t\t");
		j2 = r.nextInt(6);		
		System.out.print("|  " + diRoll2[j2] + "  |\t" + diRoll2Color);
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t|              |");
		System.out.println("\t\t\t\t\t\t\t\t\t|              |");
		System.out.println("\t\t\t\t\t\t\t\t\t|______________|");
		System.out.println("\n");
		if (roll2 == 1){	//if red die	
			if (j2 == 2 || j2 == 3 || j2 == 4){
				shotguns++;
			}
			else if (j2 == 5){
				brains++;
			}
		}
		else if (roll2 == 2){	//if yellow die	
			if (j2 == 2 || j2 == 3){
				shotguns++;
			}
			else if (j2 == 4 || j2 == 5){
				brains++;
			}
		}
		else if (roll2 == 3){	//if green die	
			if (j2 == 2){
				shotguns++;
			}
			else if (j2 == 3 || j2 == 4 || j2 == 5){
				brains++;
			}
		}
		if (j2 == 0 || j2 == 1){ // if a footstep is rolled change tally to one so a new die won't be selected next turn
			footsteps2 = 1;
		}
		else {
			footsteps2 = 0;
		}
		thisTurn[0] = thisTurn[0] + shotguns;
		thisTurn[1] = thisTurn[1] + brains;
		thisTurn[3] = footsteps2;
	}
	public static void printDisplay3(String[] greenDi, String[] yellowDi, String[] redDi, int roll3, int shotguns, int brains, int footsteps3, int[] thisTurn){
		Random r = new Random();
		brains = 0;
		shotguns = 0;
		String[] diRoll3 = new String [6];
		String diRoll1Color = ""; 
		String diRoll2Color = ""; 
		String diRoll3Color = "";
		String greenColor = "Green Die";
		String yellowColor = "Yellow Die";
		String redColor = "Red Die";
		if (roll3 == 1){ //Sets the string that corresponds with the die picked
			diRoll3 = redDi;
			diRoll3Color = redColor;
		}
		else if (roll3 == 2){ //Sets the string that corresponds with the die picked
			diRoll3 = yellowDi;
			diRoll3Color = yellowColor;
		}
		else if (roll3 == 3){ //Sets the string that corresponds with the die picked
			diRoll3 = greenDi;
			diRoll3Color = greenColor;
		}
		int j3;
		System.out.println("\t\t\t\t\t\t\t\t\t ______________ ");
		System.out.println("\t\t\t\t\t\t\t\t\t|              |");
		System.out.println("\t\t\t\t\t\t\t\t\t|              |");
		System.out.println("\t\t\t\t\t\t\t\t\t|              |");
		System.out.print("\t\t\t\t\t\t\t\t\t");
		j3 = r.nextInt(6);		
		System.out.print("|  " + diRoll3[j3] + "  |\t" + diRoll3Color);
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t|              |");
		System.out.println("\t\t\t\t\t\t\t\t\t|              |");
		System.out.println("\t\t\t\t\t\t\t\t\t|______________|");
		System.out.println("\n");
		if (roll3 == 1){		//if red die
			if (j3 == 2 || j3 == 3 || j3 == 4){
				shotguns++;
			}
			else if (j3 == 5){
				brains++;
			}
		}
		else if (roll3 == 2){	//if yellow die	
			if (j3 == 2 || j3 == 3){
				shotguns++;
			}
			else if (j3 == 4 || j3 == 5){
				brains++;
			}
		}
		else if (roll3 == 3){	//if green die	
			if (j3 == 2){
				shotguns++;
			}
			else if (j3 == 3 || j3 == 4 || j3 == 5){
				brains++;
			}
		}
		if (j3 == 0 || j3 == 1){ // if a footstep is rolled change tally to one so a new die won't be selected next turn
			footsteps3 = 1;
		}
		else {
			footsteps3 = 0;
		}
		thisTurn[0] = thisTurn[0] + shotguns;
		thisTurn[1] = thisTurn[1] + brains;
		thisTurn[4] = footsteps3;
	}
	public static void welcomeDisplay(){
		System.out.println("\t   _____  .__       .__                  .__      _________             __    __    __________                              _________                                          ");
		System.out.println("\t  /     \\ |__| ____ |  |__ _____    ____ |  |    /   _____/ ____  _____/  |__/  |_  \\______   \\_____  ______   ___________  \\_   ___ \\  ____   _____ ___________    ____ ___.__.");
		System.out.println("\t /  \\ /  \\|  |/ ___\\|  |  \\\\__  \\ _/ __ \\|  |    \\_____  \\_/ ___\\/  _ \\   __\\   __\\  |     ___/\\__  \\ \\____ \\_/ __ \\_  __ \\ /    \\  \\/ /  _ \\ /     \\\\____ \\__  \\  /    <   |  |");
		System.out.println("\t/    Y    \\  \\  \\___|   Y  \\/ __ \\\\  ___/|  |__  /        \\  \\__(  <_> )  |  |  |    |    |     / __ \\|  |_> >  ___/|  | \\/ \\     \\___(  <_> )  Y Y  \\  |_> > __ \\|   |  \\___  |");
		System.out.println("\t\\____|__  /__|\\___  >___|  (____  /\\___  >____/ /_______  /\\___  >____/|__|  |__|    |____|    (____  /   __/ \\___  >__|     \\______  /\\____/|__|_|  /   __(____  /___|  / ____|");
		System.out.println("\t        \\/        \\/     \\/     \\/     \\/               \\/     \\/                                   \\/|__|        \\/                \\/             \\/|__|       \\/     \\/\\/    ");
		System.out.println("\t\t\t\t     ____  __.       .__                  _____  .__                        ____    ___________.__               ");
		System.out.println("\t\t\t\t    |    |/ _|___.__.|  |   ____         /  _  \\ |  |   ____ ___  ___      /  _ \\   \\__    ___/|__| _____   ____ ");
		System.out.println("\t\t\t\t    |      < <   |  ||  | _/ __ \\       /  /_\\  \\|  | _/ __ \\\\  \\/  /      >  _ </\\   |    |   |  |/     \\ /  _ \\");
		System.out.println("\t\t\t\t    |    |  \\ \\___  ||  |_\\  ___/      /    |    \\  |_\\  ___/ >    <      /  <_\\ \\/   |    |   |  |  Y Y  (  <_> )");
		System.out.println("\t\t\t\t    |____|__ \\/ ____||____/\\___  > /\\  \\____|__  /____/\\___  >__/\\_ \\ /\\  \\_____\\ \\   |____|   |__|__|_|  /\\____/ ");
		System.out.println("\t\t\t\t            \\/\\/               \\/  )/          \\/          \\/      \\/ )/         \\/                     \\/       ");
		System.out.println("\n\n\n\t\t                                                                       :::!~!!!!!:.");
		System.out.println("\t\t  #######  ####   #     #  #####   ###  ######                     .xUHWH!! !!?M88WHX:.");
		System.out.println("\t\t       #  #    #  ##   ##  #    #   #   #                        .X*#M@$!!  !X!M$$$$$$WWx:.");
		System.out.println("\t\t      #   #    #  # # # #  #    #   #   #                       :!!!!!!?H! :!$!$$$$$$$$$$8X:");
		System.out.println("\t\t     #    #    #  #  #  #  #####    #   ######                 !!~  ~:~!! :~!$!#$$$$$$$$$$8X:");
		System.out.println("\t\t    #     #    #  #     #  #    #   #   #                     :!~::!H!<   ~.U$X!?R$$$$$$$$MM!");
		System.out.println("\t\t   #      #    #  #     #  #    #   #   #                     ~!~!!!!~~ .:XW$$$U!!?$$$$$$RMM!");
		System.out.println("\t\t  #######  ####   #     #  #####   ###  ######                  !:~~~ .:!M\"T#$$$$WX??#MRRMMM!\t\t ,______________________________________");
		System.out.println("\t\t                                                                ~?WuxiW*`   `\"#$$$$8!!!!??!!!\t\t|_________________,----------._ [____]  \"\"-,__  __....-----=====");
		System.out.println("\t\t                                                              :X- M$$$$       `\"T#$T~!8$WUXU~\t\t               (_(||||||||||||)___________/   \"\"                |");
		System.out.println("\t\t\t   ####   ###    ####  ######                        :%`  ~#$$$m:        ~!~ ?$$$$$$\t\t                  `----------'        [ ))\"-,                   |");
		System.out.println("\t\t\t   #   #   #    #      #                           :!`.-   ~T$$$$8xx.  .xWW- ~\"\"##*\"\t\t                                       \"\"    `,  _,--....___    |");
		System.out.println("\t\t\t   #    #  #   #       #                         -~~:<` !    ~?T#$$@@W@*?$$      /`\t\t                                               `/           \"\"\"\"");
		System.out.println("\t\t\t   #    #  #   #       ######                 !!! .!~~ !!     .:XUW$W!~ `\"~:    :");
		System.out.println("\t\t\t   #    #  #   #       #                      .:x%`!!  !H:   !WM$$$$Ti.: .!WUn+!`\t\t\t _______________________________________________");
		System.out.println("\t\t\t   #   #   #    #      #                      !!`:X~ .: ?H.!u \"$$$B$$$!W:U!T$$M~ \t\t\t|\t\t\t\t\t\t|");	
		System.out.println("\t\t\t   ###    ###    ####  ######                  :X@!.-~   ?@WTWo(\"*$$$W$TH$! `    \t\t\t|\t\tWarning: Violence\t\t|");
		System.out.println("\t\t                                                      #X$?!-~    : ?$$$B$Wu(\"**$RM!      \t\t\t|\t\t\t\t\t\t|");
		System.out.println("\t\t                                                      #~~ !     :   ~$$$$$B$$en:``       \t\t\t|_______________________________________________|");
		System.out.println("\t\t                                                      #Wx.~    :     ~\"##*$$$$M~\n\n\n");
	}
} 