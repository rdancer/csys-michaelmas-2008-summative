/**
 * Track Class is a class to store information about music tracks: title, artist, date, length (in time), rating, 
 * size and the guest Artist - if applicable-
 */

import java.util.*;
public class Album
{
    private String albumName;
    private String albumType;
    private Artist artist;
    private ArrayList<Track> tracks;
 
    /**
     * Constructor for objects of class Artist
     */
    public Album()
    {
        tracks = new ArrayList<Track>();         
    }
    public Album(String aName)
        albumName = albumName; 
        tracks = new ArrayList<Track>();         
    }
    public Album(String aName, String aType, Artist a )
    {
        albumName = aName;
        albumType=aType;
        artist = a;
        tracks = new ArrayList<Track>(); 
    }  
    
    public void setAlbumName(String n) 
   {
       albumName = n;
    }
    
    public String getAlbumName()
    {
        return albumName;
    }
    
   public void setAlbumType(String t) 
   {
       albumType = t;
    }
    
    public String getAlbumType()
    {
        return albumType;
    }
    
    public void addTrack(Track t)
    {
        tracks.add(t);
    }
    
     public void setArtist(Artist a) 
   {
       artist = a;
    }
    
    public Artist getArtist()
    {
        return artist;
    }
    /**
     * getTracksLength methos will calculate the whole length for all tracks
     * 
     * @return  length   the summation of all tracks lengths
     */
    public double getTracksLength()
    {
        double length = 0.0;
        for (Track t:tracks)
        length = length + t.getLength();
        return length;
    }
    
    
    /**
     * getTracksSize methos will calculate the whole size for all tracks
     * 
     * @return  size  the summation of tracks sizes
     */
    public double getTracksSize()
    {
        double size = 0.0;
        for (Track t:tracks)
            size = size + t.getSize();        
        return size;
    }
        
    /**
     * getTracksRating methos will calculate the average ratings for all tracks
     * 
     * @return  average of ratings
     */
    public double getAvgRating()
    {    
        int rating = 0;
        for (Track t: tracks)
        rating = rating + t.getRating();
        return rating/tracks.size();
    }
     
}
