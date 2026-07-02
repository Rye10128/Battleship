public class Grid
{
    private Location[][] grid;
    
    //Constants for number of rows and columns
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;
    
    //Creates a new Grid / initializes each location in the grid to be a new Location object
    public Grid()
    {
        grid = new Location[NUM_ROWS][NUM_COLS];
        
        //Loop through every row and column to initialize the objects
        for (int row = 0; row < NUM_ROWS; row++)
        {
            for (int col = 0; col < NUM_COLS; col++)
            {
                grid[row][col] = new Location();
            }
        }
    }
    
    //Mark a hit in this location by calling the markHit method on the Location object
    public void markHit(int row, int col)
    {
        grid[row][col].markHit();
    }
    
    public void markMiss(int row, int col)
    {
        grid[row][col].markMiss();
    }
    
    public void setStatus(int row, int col, int status)
    {
        grid[row][col].setStatus(status);
    }
    
    //Gets the status of this location on the grid
    public int getStatus(int row, int col)
    {
        return grid[row][col].getStatus();
    }
    
    //Returns whether or not this Location has already been guessed
    public boolean alreadyGuessed(int row, int col)
    {
        //If not unguessed, then it has been guessed
        return !grid[row][col].isUnguessed();
    }
    
    //Sets whether or not there is a ship at this location to the val
    public void setShip(int row, int col, boolean val)
    {
        grid[row][col].setShip(val);
    }
    
    //Returns whether or not there is a ship there
    public boolean hasShip(int row, int col)
    {
        return grid[row][col].hasShip();
    }
    
    //Gets the Location object at this row and column position
    public Location get(int row, int col)
    {
        return grid[row][col];
    }
    
    //Returns the number of rows in the grid
    public int numRows()
    {
        return NUM_ROWS;
    }
    
    //Returns the number of columns in the grid
    public int numCols()
    {
        return NUM_COLS;
    }
    
    //Check if a ship can be placed at this location without going off the board
    public boolean canPlaceShip(int row, int col, int length, int direction)
    {
        //Check bounds
        if (direction == Ship.HORIZONTAL)
        {
            if (col + length > NUM_COLS)
            {
                return false;
            }
        }
        else
        {
            if (row + length > NUM_ROWS)
            {
                return false;
            }
        }
        for (int i = 0; i < length; i++)
        {
            if (direction == Ship.HORIZONTAL)
            {
                if (grid[row][col + i].hasShip())
                {
                    return false;
                }
            }
            else
            {
                if (grid[row + i][col].hasShip())
                {
                    return false;
                }
            }
        }
        
        //If passed all checks --> safe to continue
        return true;
    }
    public void addShip(Ship s) 
    {
        int row = s.getRow();
        int col = s.getCol();
        int length = s.getLength();
        int direction = s.getDirection();
        
        //Debug help
        //System.out.println("DEBUG: Adding ship. Row: " + row + " Col: " + col + " Dir: " + direction + " Len: " + length);
        
        //Loop through the length of the ship
        for (int i = 0; i < length; i++)
        {
            if (direction == Ship.HORIZONTAL)
            {
                //Horizontal: row stays the same / column increases
                setShip(row, col + i, true);
            }
            else if (direction == Ship.VERTICAL)
            {
                //Vertical: Column stays same / row increases
                setShip(row + i, col, true);
            }
        }
    }
    //Prints the grid status
    public void printStatus()
    {
        System.out.println(" 1 2 3 4 5 6 7 8 9 10");
        
        for (int row = 0; row < NUM_ROWS; row++) 
        {
            //Prints the letter for the row
            char rowLetter = (char) ('A' + row);
            System.out.print(rowLetter + " ");
            
            for (int col = 0; col < NUM_COLS; col++)
            {
                if (grid[row][col].checkHit())
                {
                    System.out.print("X ");
                }
                else if (grid[row][col].checkMiss())
                {
                    System.out.print("O ");
                }
                else 
                {
                    System.out.print("- ");
                }
            }
            
            //Move to the next line after finishing a row
            System.out.println();
        }
    }
    
    //Prints the grid whether there is a ship at each location
    public void printShips()
    {
        System.out.println(" 1 2 3 4 5 6 7 8 9 10");
        
        for (int row = 0; row < NUM_ROWS; row++)
        {
            char rowLetter = (char) ('A' + row);
            System.out.print(rowLetter + " ");
            
            for (int col = 0; col < NUM_COLS; col++)
            {
                if (grid[row][col].hasShip())
                {
                    System.out.print("X ");
                }
                else
                {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }
}
