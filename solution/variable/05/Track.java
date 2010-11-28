/**
 * @Track Class is a class to store information about music tracks: title, artist, date, length (in time), rating, 
 * size and the guest Artist - if applicable-
 *
 */

import javax.swing.*;

public class Track
{
    private String title /* #1 */;
    private Artist artist /* #2 */;
    private String date /* #3 */;
    private double length /* #4 */;
    private int rating /* #5 */;
    private String location /* #6 */;
    private double size /* #7 */;
    private Artist guestArtist /* #8 */;
    private int playCount /* #9 */;
    
    /**
     * Constructors for objects of class Track
     */
    public Track()
    {
    }
    /**
     * Constructors for objects of class Track
     */
     public Track(String t /* #10 */, Artist a /* #11 */,String d /* #12 */, double l /* #13 */, int r /* #14 */, String loc /* #15 */, double s /* #16 */, Artist g /* #17 */ )
    {
     title /* #1 */=t /* #10 */;
     artist /* #2 */ = a /* #11 */;
     date /* #3 */=d /* #12 */;
     length /* #4 */=l /* #13 */;
     rating /* #5 */=r /* #14 */;
     location /* #6 */=loc /* #15 */;    
     size /* #7 */=s /* #16 */; 
     guestArtist /* #8 */ = g /* #17 */;
     playCount /* #9 */ = 0;
    }
    
    /**
     * Accessor and Mutator methods
     */
    public void setTitle(String t /* #18 */)
    {
        title /* #1 */ = t /* #18 */;
    }
    
    public String getTitle()
    {
        return title /* #1 */;
    }
    
    public void setArtist(Artist a /* #19 */)
    {
        artist /* #2 */ = a /* #19 */;
    }
    
    public Artist getArtist()
    {
        return artist /* #2 */;
    }
    
     public void setDate(String d /* #20 */)
    {
        date /* #3 */ = d /* #20 */;
    }
    
      public String getDate()
    {
        return date /* #3 */;
    }

    public void setLength(double l /* #21 */)
    {
        length /* #4 */ = l /* #21 */;
    }
    public double getLength()
    {
        return length /* #4 */;
    }
    
    public void setSize(double s /* #22 */)
    {
        size /* #7 */ = s /* #22 */;
    }
    public double getSize()
    {
        return size /* #7 */;
    }
    
    public void setRating(int r /* #23 */)
    {
        rating /* #5 */ = r /* #23 */;
    }
     public int getRating()
    {
        return rating /* #5 */;
    }
    
     public void setLocation(String l /* #24 */)
    {
        location /* #6 */ = l /* #24 */;
    }
    
     public String getLocation()
    {
        return location /* #6 */;
    }
    
     public void setGuestArtist(Artist g /* #25 */)
    {
        guestArtist /* #8 */ = g /* #25 */;
    }
    
     public Artist getGuestArtist()
    {
        return guestArtist /* #8 */;
    }
    
    /**
     * addPlayCount method used to count the number of tracks being played
     * 
     */
    public void addPlayCount()
    {
        playCount /* #9 */++;
    }
    
    /**
     * getPlayCount method used to return the number of tracks being played
     * 
     * @return  will save the result for the variable playCount
     */   
    public int getPlayCount()
    {
        return playCount /* #9 */;
    }
}

// Total: 25 variables
