public class Ship
{
    //Direction constants
    public static final int UNSET = -1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    
    //Instance constants
    private int row;
    private int col;
    private int length;
    private int direction;
    
    //Constructor - creates a ship and sets the length
    public Ship(int length)
    {
        //Must initialize the location and direction to UNSET
        this.row = UNSET;
        this.col = UNSET;
        this.direction = UNSET;
        this.length = length;
    }
    
    //Has the location been initialized?
    public boolean isLocationSet()
    {
        //If row or col is -1, location is not yet set
        return row != UNSET && col != UNSET;
    }
    
    //Has the direction been initialized?
    public boolean isDirectionSet()
    {
        return direction != UNSET;
    }
    
    //Sets the location of the ship
    public void setLocation(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
    
    //Sets the direction of the ship
    public void setDirection(int direction)
    {
        this.direction = direction;
    }
    
    //Getter for the row value
    public int getRow()
    {
        return row;
    }
    
    //Getter for the column value
    public int getCol()
    {
        return col;
    }
    
    //Getter for the length of the ship
    public int getLength() 
    {
        return length;
    }
    
    //Getter for the direction
    public int getDirection()
    {
        return direction;
    }
    
    //Helper method to get a string value from the direction
    private String directionToString()
    {
        if (direction == HORIZONTAL)
        {
            return "horizontal";
        }
        else if (direction == VERTICAL)
        {
            return "vertical)";
        }
        else
        {
            return "unset direction";
        }
    }
    
    //toString value for this ship
    public String toString()
    {
        return "Ship of length " + length + " at (" + row + ", " + col + ") " + directionToString();
    }
}
