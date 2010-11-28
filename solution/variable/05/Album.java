/**
 * Track Class is a class to store information about music tracks: title, artist, date, length (in time), rating, 
 * size and the guest Artist - if applicable-
 */

import java.util.*;
public class Album
{
    private String albumName /* #1 */;
    private String albumType /* #2 */;
    private Artist artist /* #3 */;
    private ArrayList<Track> tracks /* #4 */;
 
    /**
     * Constructor for objects of class Artist
     */
    public Album()
    {
        tracks /* #4 */ = new ArrayList<Track>();         
    }
    public Album(String aName /* #5 */)
    {
	// This seems to should have read ... = aName --Jan Minář
        albumName /* #1 */ = albumName /* #1 */;
        tracks /* #4 */ = new ArrayList<Track>();         
    }
    public Album(String aName /* #6 */, String aType /* #7 */, Artist a /* #8 */)
    {
        albumName /* #1 */ = aName /* #6 */;
        albumType /* #2 */=aType /* #7 */;
        artist /* #3 */ = a /* #8 */;
        tracks /* #4 */ = new ArrayList<Track>(); 
    }  
    
    public void setAlbumName(String n /* #9 */) 
   {
       albumName /* #1 */ = n /* #9 */;
    }
    
    public String getAlbumName()
    {
        return albumName /* #1 */;
    }
    
   public void setAlbumType(String t /* #10 */) 
   {
       albumType /* #2 */ = t /* #10 */;
    }
    
    public String getAlbumType()
    {
        return albumType /* #2 */;
    }
    
    public void addTrack(Track t /* #11 */)
    {
        tracks /* #4 */.add(t /* #11 */);
    }
    
     public void setArtist(Artist a /* #12 */) 
   {
       artist /* #3 */ = a /* #12 */;
    }
    
    public Artist getArtist()
    {
        return artist /* #3 */;
    }
    /**
     * getTracksLength methos will calculate the whole length for all tracks
     * 
     * @return  length   the summation of all tracks lengths
     */
    public double getTracksLength()
    {
        double length /* #13 */ = 0.0;
        for (Track t /* #14 */ :tracks /* #4 */)
        length /* #13 */ = length /* #13 */ + t /* #14 */ .getLength();
        return length /* #13 */;
    }
    
    
    /**
     * getTracksSize methos will calculate the whole size for all tracks
     * 
     * @return  size  the summation of tracks sizes
     */
    public double getTracksSize()
    {
        double size /* #15 */ = 0.0;
        for (Track t /* #16 */ :tracks /* #4 */)
            size /* #15 */ = size /* #15 */ + t /* #16 */ .getSize();        
        return size /* #15 */;
    }
        
    /**
     * getTracksRating methos will calculate the average ratings for all tracks
     * 
     * @return  average of ratings
     */
    public double getAvgRating()
    {    
        int rating /* #16 */ = 0;
        for (Track t /* #17 */ : tracks /* #4 */)
        rating /* #16 */ = rating /* #16 */ + t /* #17 */ .getRating();
        return rating /* #16 */ /tracks /* #4 */ .size();
    }
     
}

// Total: 17 variables
