public class Location
{
    //Status constants
    public static final int UNGUESSED = 0;
    public static final int HIT = 1;
    public static final int MISSED = 2;
    
    //Instance variables
    private boolean hasShip;
    private int status;
    
    //Location constructor
    public Location()
    {
        //By default, a new location has no ship and hasn't been guessed
        this.status = UNGUESSED;
        this.hasShip = false;
    }
    
    //Was this location a hit?
    public boolean checkHit()
    {
        return status == HIT;
    }
    
    //Was this location a miss?
    public boolean checkMiss()
    {
        return status == MISSED;
    }
    
    //Was this location unguessed?
    public boolean isUnguessed()
    {
        return status == UNGUESSED;
    }
    
    //Mark this location a hit
    public void markHit()
    {
        this.status = HIT;
    }
    
    //Mark this location a miss
    public void markMiss()
    {
        this.status = MISSED;
    }
    
    //Return whether or not this location has a ship
    public boolean hasShip()
    {
        return hasShip;
    }
    
    //Set the value of whether this location has a ship
    public void setShip(boolean val)
    {
        this.hasShip = val;
    }
    
    //Set the status of this location
    public void setStatus(int status)
    {
        this.status = status;
    }
    
    //Get the status of this location
    public int getStatus()
    {
        return status;
    }
}
