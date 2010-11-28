/**
 * @Track Class is a class to store information about music tracks: title, artist, date, length (in time), rating, 
 * size and the guest Artist - if applicable-
 *
 */

import javax.swing.*;

public class Track
{
    private String title;
    private Artist artist;
    private String date;
    private double length;
    private int rating;
    private String location;
    private double size;
    private Artist guestArtist;
    private int playCount;
    
    /**
     * Constructors for objects of class Track
     */
    public Track()
    {
    }
    /**
     * Constructors for objects of class Track
     */
     public Track(String t, Artist a,String d, double l, int r, String loc, double s, Artist g )
    {
     title=t;
     artist = a;
     date=d;
     length=l;
     rating=r;
     location=loc;    
     size=s; 
     guestArtist = g;
     playCount = 0;
    }
    
    /**
     * Accessor and Mutator methods
     */
    public void setTitle(String t)
    {
        title = t;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setArtist(Artist a)
    {
        artist = a;
    }
    
    public Artist getArtist()
    {
        return artist;
    }
    
     public void setDate(String d)
    {
        date = d;
    }
    
      public String getDate()
    {
        return date;
    }

    public void setLength(double l)
    {
        length = l;
    }
    public double getLength()
    {
        return length;
    }
    
    public void setSize(double s)
    {
        size = s;
    }
    public double getSize()
    {
        return size;
    }
    
    public void setRating(int r)
    {
        rating = r;
    }
     public int getRating()
    {
        return rating;
    }
    
     public void setLocation(String l)
    {
        location = l;
    }
    
     public String getLocation()
    {
        return location;
    }
    
     public void setGuestArtist(Artist g)
    {
        guestArtist = g;
    }
    
     public Artist getGuestArtist()
    {
        return guestArtist;
    }
    
    /**
     * addPlayCount method used to count the number of tracks being played
     * 
     */
    public void addPlayCount()
    {
        playCount++;
    }
    
    /**
     * getPlayCount method used to return the number of tracks being played
     * 
     * @return  will save the result for the variable playCount
     */   
    public int getPlayCount()
    {
        return playCount;
    }
}