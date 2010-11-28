/**
 * Library Class represent a music library. Inside this class you can create a library, adding tracks and/or albums to the library,
 * creating a random list of tracks from the library with a minimum rate specified by the user
 */

import java.util.ArrayList;
import java.util.Random;

public class Library
{
    private ArrayList<Track> tracks /* #1 */;
    private ArrayList<Album> albums /* #2 */;
    
    /**
     * Constructor for objects of class Library
     */
    public Library()
    {
        tracks /* #1 */ = new ArrayList<Track>();
        albums /* #2 */ = new ArrayList<Album>();
    }
    
   public void setArrayTrack(ArrayList<Track> ts /* #3 */)
   {
       tracks /* #1 */ = ts /* #3 */;
    }
    
      public void setArrayAlbum(ArrayList<Album> albs /* #4 */)
   {
       albums /* #2 */ = albs /* #4 */;
    }
    /**
     * addAlbumTracksToTrackArrayList() method used to add all tracks available at all Albums to the track list
     */
     public void addAlbumTracksToTrackArrayList()
    {         
        for(Album a /* #4 */ :albums /* #2 */)
        {
            ArrayList<Track> temp /* #5 */ = new ArrayList<Track>();
            for (Track t /* #6 */ :temp /* #5 */)
                tracks /* #1 */.add(t /* #6 */);
        }
    
    }
   
    
    /**
     * RandomRatingList() method used to create a random list of tracks from the library with a minimum rating
     * @param  rating     the minimum rating  
     * @return  ArrayList of random tracks with the minimum rating  
     */  
    public ArrayList<Track> randomRatingList(int rating /* #7 */)
    {
        Random random /* #8 */ = new Random();
          
        ArrayList<Track> tempTrackList /* #9 */ = new ArrayList<Track>();
        ArrayList<Track> randomTrackList /* #10 */ = new ArrayList<Track>();
        
        for(Track t /* #11 */ : tracks /* #1 */)
        {
            if (t /* #11 */ .getRating() >= rating /* #7 */)
            {
                tempTrackList /* #9 */ .add(t /* #11 */);
            }
        }
        
        int i /* #12 */ = random /* #8 */.nextInt();
        
        
        while ((i /* #12 */ >=tempTrackList /* #9 */ .size()) || (i /* #12 */ <0))
        {
            
            i /* #12 */ = random /* #8 */ .nextInt();
        }
         
        for (int x /* #13 */ = 0; x /* #13 */ < i /* #12 */; x /* #13 */ ++)
        {
              randomTrackList /* #10 */ .get(random /* #8 */ .nextInt(tempTrackList /* #9 */ .size()));
        }
        
        return randomTrackList /* #10 */;
    }
}

// Total: 13 variables
