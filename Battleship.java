public class Battleship extends ConsoleProgram
{
    public void run()
    {
        System.out.println("Welcome to Battleship!");
        
        Player human = new Player();
        Player computer = new Player();
        
        //Sets limit to how many tries the user gets to sink all ships
        //Ammo counter
        int torpedoes = 40;
        
        //Setup phase
        System.out.println("\n..........User Setup..........\n");
        setupHumanShips(human);
        
        //Clears screen after user is done with setup
        clearScreen();
        
        System.out.println("\n..........Computer Setup..........");
        computer.setupComputerShips();
        System.out.println("Computer has placed its ships!");
        
        //Pauses the game for a second
        waitForEnter();
        
        //Game loop
        while (true)
        {
            //Clears screen for fresh turn
            clearScreen();
            
            //Checking ammo
            System.out.println("Torpedoes Remaining: " + torpedoes);
            if (torpedoes == 0)
            {
                System.out.println("GAME OVER!!! You ran out of ammo");
                System.out.println("Computer wins by deafault");
                break;
            }
            
            
            //Human turn
            System.out.println("\nYour turn!");
            human.getOpponentGrid().printStatus();
            askForGuess(human, computer);
            
            //Decrease ammo by one
            torpedoes--;
            
            if (computer.hasLost())
            {
                System.out.println("You win!!! You sank all the computer's ships!");
                break;
            }
            
            //Pauses here so user can see before it clears
            waitForEnter();
            clearScreen();
            
            //Computer turn
            System.out.println("\nComputer's Turn");
            computerGuess(computer, human);
            
            if (human.hasLost())
            {
                System.out.println("You Lose!!! The computer sank all your ships!");
                break;
            }
            
            //Pause here so user sees what computer did
            waitForEnter();
        }
    }
    
    //Robust user setup part
    private void setupHumanShips(Player p)
    {
        Ship[] ships = p.getShips();
        for (Ship s : ships)
        {
            while (true)
            {
                //Clear the screen to ensure a fresh view
                clearScreen();
                
                p.getMyGrid().printShips();
                System.out.println("\nPlacing ship with length " + s.getLength() + "\n");
                
                int row = readRow();
                int col = readCol();
                int dir = readDir();
                
                //Check if valid before placement
                if (p.getMyGrid().canPlaceShip(row, col, s.getLength(), dir))
                {
                    p.chooseShipLocation(s, row, col, dir);
                    break; //Success and moving on to next ship
                }
                else
                {
                    System.out.println("Error - Can't place a ship there (either overlaps or is out of bounds) - Try Again");
                    //Pause so the user can see the error message
                    waitForEnter();
                }
            }
        }
        
        //One final screen clear and final board viewing
        clearScreen();
        System.out.println("Final Board Setup: \n");
        
        //Print final board
        p.getMyGrid().printShips();
        //Pause so user can see their ship layout
        waitForEnter();
    }
    
    //Helper for Human Guess
    private void askForGuess(Player guesser, Player opponent)
    {
        while (true)
        {
            int row = readRow();
            int col = readCol();
            
            if (!guesser.getOpponentGrid().alreadyGuessed(row, col))
            {
                boolean hit = opponent.recordOpponentGuess(row, col);
                guesser.markGuessOnOpponentGrid(row, col, hit);
                
                if (hit)
                {
                    System.out.println("It's a hit!!!");
                }
                else
                {
                    System.out.println("It's a miss!!!");
                }
                return;
            }
            else
            {
                System.out.println("You already guessed that spot - try again");
            }
        }
    }
    
    //Helper for computer guess
    private void computerGuess(Player computer, Player human)
    {
        int row;
        int col;
        
        //Keep picking random spots until we find one we haven't guessed yet
        do
        {
            row = Randomizer.nextInt(0, 9);
            col = Randomizer.nextInt(0, 9);
        }
        while (computer.getOpponentGrid().alreadyGuessed(row, col));
        
        boolean hit = human.recordOpponentGuess(row, col);
        computer.markGuessOnOpponentGrid(row, col, hit);
        
        //Print the result for the user to see
        char rowChar = (char)('A' + row);
        System.out.println("Computer guessed " + rowChar + (col + 1));
        if (hit)
        {
            System.out.println("The computer HIT your ship!!!");
        }
        else
        {
            System.out.println("The computer missed!!!");
        }
    }
    
    //Input helpers
    private int readRow()
    {
        while (true)
        {
            String input = readLine("Row (A-J): ");
            if (input.length() > 0)
            {
                //Convert the lteer to a number using ASCII math
                //'A' is 65 - if the user types 'C' then it's 67-65 = 2
                //Therefore Row C becomes index 2
                char c = input.toUpperCase().charAt(0);
                if (c >= 'A' && c <= 'J')
                {
                    return c - 'A';
                }
            }
            System.out.println("Invalid Row");
        }
    }
    
    private int readCol()
    {
        while (true)
        {
            int val = readInt("Column (1-10): ");
            if (val >= 1 && val <= 10)
            {
                return val - 1;
            }
            System.out.println("Invalid Column");
        }
    }
    
    private int readDir()
    {
        while (true)
        {
            String input = readLine("Direction (H/V)");
            if (input.equalsIgnoreCase("H"))
            {
                return Ship.HORIZONTAL;
            }
            if (input.equalsIgnoreCase("V"))
            {
                return Ship.VERTICAL;
            }
            System.out.println("Invalid Direction (enter H or V)");
        }
    }
    
    //Scrolls the screen to help clear up user view
    private void clearScreen()
    {
        for (int i = 0; i < 70; i++)
        {
            System.out.println();
        }
    }
    
    //Stops the game for a second so the user an see the last thing that happened
    private void waitForEnter()
    {
        readLine("Press ENTER to continue...");
    }
}
