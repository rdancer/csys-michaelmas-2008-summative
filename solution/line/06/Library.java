/**
 * Library Class represent a music library. Inside this class you can create a library, adding tracks and/or albums to the library,
 * creating a random list of tracks from the library with a minimum rate specified by the user
 */

import java.util.ArrayList;
import java.util.Random;

public class Library
{
    private ArrayList<Track> tracks;
    private ArrayList<Album> albums;
    
    /**
     * Constructor for objects of class Library
     */
    public Library()
    {
        tracks = new ArrayList<Track>();
        albums = new ArrayList<Album>();
    }
    
   public void setArrayTrack(ArrayList<Track> ts)
   {
       tracks = ts;
    }
    
      public void setArrayAlbum(ArrayList<Album> albs)
   {
       albums = albs;
    }
    /**
     * addAlbumTracksToTrackArrayList() method used to add all tracks available at all Albums to the track list
     */
     public void addAlbumTracksToTrackArrayList()
    {         
        for(Album a:albums)
        {
            ArrayList<Track> temp = new ArrayList<Track>();
            for (Track t:temp)
                tracks.add(t);
        }
    
    }
   
    
    /**
     * RandomRatingList() method used to create a random list of tracks from the library with a minimum rating
     * @param  rating     the minimum rating  
     * @return  ArrayList of random tracks with the minimum rating  
     */  
    public ArrayList<Track> randomRatingList(int rating)
    {
        Random random = new Random();
          
        ArrayList<Track> tempTrackList = new ArrayList<Track>();
        ArrayList<Track> randomTrackList = new ArrayList<Track>();
        
        for(Track t: tracks)
        {
            if (t.getRating() >= rating)
            {
                tempTrackList.add(t);
            }
        }
        
        int i = random.nextInt();
        
        
        while ((i>=tempTrackList.size()) || (i<0))
        {
            
            i = random.nextInt();
        }
         
        for (int x = 0; x < i; x++)
        {
              randomTrackList.get(random.nextInt(tempTrackList.size()));
        }
        
        return randomTrackList;
    }
}
