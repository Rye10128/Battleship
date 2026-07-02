public class Player
{
    private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    
    private Ship[] ships;
    private Grid myGrid;
    private Grid opponentGrid;
    
    //Constructor
    public Player()
    {
        //Initialize the grids
        myGrid = new Grid();
        opponentGrid = new Grid();
        
        //Intialize the ships array to hold 5 ships
        ships = new Ship[SHIP_LENGTHS.length];
        
        //Loop through the lengths and create the Ship objects
        for (int i = 0; i < SHIP_LENGTHS.length; i++)
        {
            //Create a new ship of the specific length and add it to the list
            ships[i] = new Ship(SHIP_LENGTHS[i]);
        }
    }
    //Helper method to set a ship's location and add it to the grid
    public void chooseShipLocation(Ship s, int row, int col, int direction)
    {
        s.setLocation(row, col);
        s.setDirection(direction);
        myGrid.addShip(s);
    }
    
    //Getter for the list of ships (needed for the tester)
    public Ship[] getShips()
    {
        return ships;
    }
    
    //Getter for the player's grid (needed for the tester)
    public Grid getMyGrid()
    {
        return myGrid;
    }
    
    //Getter for the opponent's grid
    public Grid getOpponentGrid()
    {
        return opponentGrid;
    }
    
    //Record an opponent's guess agaist this player
    public boolean recordOpponentGuess(int row, int col)
    {
        if (myGrid.hasShip(row, col))
        {
            myGrid.markHit(row, col);
            return true;
        }
        else
        {
            myGrid.markMiss(row, col);
            return false;
        }
    }
    
    //Record this player's guess on their tracking field
    public void markGuessOnOpponentGrid(int row, int col, boolean wasHit)
    {
        if (wasHit)
        {
            opponentGrid.markHit(row, col);
        }
        else
        {
            opponentGrid.markMiss(row, col);
        }
    }
    
    public void setupComputerShips()
    {
        for (Ship s : ships)
        {
            boolean placed = false;
            while (!placed)
            {
                int row = Randomizer.nextInt(0, 9);
                int col = Randomizer.nextInt(0, 9);
                
                boolean isHorizontal = Randomizer.nextBoolean();
                int dir;
                if (isHorizontal)
                {
                    dir = Ship.HORIZONTAL;
                }
                else
                {
                    dir = Ship.VERTICAL;
                }

                //Use the new safety check
                if (myGrid.canPlaceShip(row, col, s.getLength(), dir))
                {
                    chooseShipLocation(s, row, col, dir);
                    placed = true;
                }
            }
        }
    }
    
    //Check if the player has lost (all ships hit)
    public boolean hasLost()
    {
        //Check every location on the grid
        for (int row = 0; row < myGrid.numRows(); row++)
        {
            for (int col = 0; col < myGrid.numCols(); col++)
            {
                //If there is a ship here AND it hasn't been hit yet, we havn't lost!
                if (myGrid.hasShip(row, col) && !myGrid.get(row, col).checkHit())
                {
                    //Found a surviving ship therefore game is not over yet
                    return false;
                }
            }
        }
        //If checked all spots and found no surviving ship parts, we lost
        return true;
    }
}
