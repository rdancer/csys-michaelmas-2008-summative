 /**
 * This is the main class; the user runs the program from here. In addition, this code contains sample of reading from keyboard 
 * and loading from file (parts of the extention). This class contains many methods other than the main method. Inside the
 * main method the user creates instances by calling the methods. 
 */
import java.io.*;
import java.util.*;
public class Main
{
/**
 * This is the main class; the user runs the program from here. In addition, this code contains sample of reading from keyboard 
 * and loading from file (parts of the extention). This class contains many methods other than the main method. Inside the
 * main method the user creates instances by calling the methods. 
 */
 
    public static void main(String []args /* #1 */) throws IOException
    {   // Creating ArrayLists to save created instances fo Artist, Track and Album 
        ArrayList<Artist> artists /* #2 */ = new ArrayList<Artist>();
        Artist artist /* #3 */ = new Artist();
        ArrayList<Track> tracks /* #4 */ = new ArrayList<Track>();
        Track track /* #5 */ = new Track();
        ArrayList<Album> albums /* #6 */ = new ArrayList<Album>();
        Album album /* #7 */ = new Album();
        
        //Reading from the keyboard what is the Required 
        BufferedReader buffr /* #8 */ = new BufferedReader(new InputStreamReader(System.in));        
        System.out.println("Please Enter: \n1 for using keyboard input \n2 for loading from a file" ); 
        String str /* #9 */ = buffr /* #8 */.readLine();
                
        // Keyboard Input
        if (Integer.parseInt(str /* #9 */) == 1)
        { 
            // An instance from Artist by calling addArtist() method
            artist /* #3 */ = addArtist();
            artists /* #2 */ .add(artist /* #3 */);
            
            // An instance from Track by  calling addTrack(Artist artist /* #3 */) method
            track /* #5 */ = addTrack(artist /* #3 */);
            tracks /* #4 */.add(track /* #5 */);
            
            // An instance from Album by  calling addAlbum(Artist artist, ArrayList<Track> tracks) method
            album /* #7 */ = addAlbum(artist /* #3 */, tracks /* #4 */);
            albums /* #6 */.add(album /* #7 */);
        
            // An instance from Library by  calling AddLibrary(ArrayList<Track> tracks, ArrayList<Album> albums) method
            Library library /* #10 */ = new Library();
            library /* #10 */ = addLibrary(tracks /* #4 */, albums /* #6 */);
        }
        
        //Sample of loading from a file 
          else if (Integer.parseInt(str /* #9 */) == 2)
        {
            BufferedReader reader /* #11 */ = new BufferedReader(new FileReader("MusicLib.txt"));
            String line /* #12 */ = reader /* #11 */.readLine();
                  //Reading Datafile
           while (line /* #12 */ != null)
           {        
                   artist /* #3 */.setIsSoloist(Boolean.parseBoolean(reader /* #11 */.readLine()));                
                   artist /* #3 */.setName(reader /* #11 */.readLine());
                   track /* #5 */.setTitle(reader /* #11 */.readLine());
                   track /* #5 */.setArtist(artist /* #3 */);
                   track /* #5 */.setDate(reader /* #11 */.readLine());
                   track /* #5 */.setLength(Double.parseDouble(reader /* #11 */.readLine()));
                   track /* #5 */.setRating(Integer.parseInt(reader /* #11 */.readLine()));
                   track /* #5 */.setLocation(reader /* #11 */.readLine());
                   track /* #5 */.setSize(Double.parseDouble(reader /* #11 */.readLine()));
                   line /* #12 */ = reader /* #11 */.readLine();
                 
                   System.out.println(artist /* #3 */.getName());
                   System.out.println(track /* #5 */.getTitle());
           }  
           reader /* #11 */.close();
        }            
    }
    
    /**
     * addArtist method used to create an instance from Artist class
     * 
     * @return  will return the created object
     */
    public static Artist addArtist()throws IOException
    {
        BufferedReader buffr /* #13 */ = new BufferedReader(new InputStreamReader(System.in));        
        String str /* #14 */ = "";
          
            Artist artist /* #15 */ = new Artist();                                   
            //Determine whether Solo or Band
            System.out.println("Is this artist Soloist? please enter 1 if YES, and 2 if NO");
            String solo /* #16 */ = buffr /* #13 */.readLine();
            if (Integer.parseInt(solo /* #16 */) == 1)
            {
                artist /* #15 */.setIsSoloist(true);
                System.out.println("Enter the name of the Soloist Artist");
                str /* #14 */ = buffr /* #13 */.readLine();
                artist /* #15 */.setName(str /* #14 */);
            }
            else if (Integer.parseInt(solo /* #16 */) == 2)                  
            {      
                artist /* #15 */.setIsSoloist(false);
                System.out.println("Enter Band Name");
                str /* #14 */ = buffr /* #13 */.readLine();
                artist /* #15 */.setName(str /* #14 */);     
                String m /* #17 */ = "1";
                while (Integer.parseInt(m /* #17 */) == 1)
                {
                    System.out.println("Enter member Name");
                    str /* #14 */ = buffr /* #13 */.readLine();
                    artist /* #15 */.storeMember(str /* #14 */);
                
                    System.out.println("Do you want to add another member? enter 1 if YES, and 2 if NO");
                    m /* #17 */ = buffr /* #13 */.readLine();
                }
                
            }
            return artist /* #15 */;
        }
        
    /**
     * addTrack method used to create an instance from Track class
     * 
     * @return  will return the created object
     */
            public static Track addTrack(Artist artist /* #18 */) throws IOException
            {
                BufferedReader buffr /* #19 */ = new BufferedReader(new InputStreamReader(System.in));        
                String str /* #20 */ = "";
                
                Track track /* #21 */ = new Track();
                System.out.println("Enter Track Title");
                str /* #20 */ = buffr /* #19 */.readLine();
                track /* #21 */.setTitle(str /* #20 */);
            
                track /* #21 */.setArtist(artist /* #18 */);
            
                System.out.println("Enter Track Date");
                str /* #20 */ = buffr /* #19 */.readLine();
                track /* #21 */.setDate(str /* #20 */);
            
                System.out.println("Enter Track Length");
                str /* #20 */ = buffr /* #19 */.readLine();
                track /* #21 */.setLength(Double.parseDouble(str /* #20 */));
            
                System.out.println("Enter Track Rating");
                str /* #20 */ = buffr /* #19 */.readLine();
                track /* #21 */.setRating(Integer.parseInt(str /* #20 */));
            
                System.out.println("Enter Track location");
                str /* #20 */ = buffr /* #19 */.readLine();
                track /* #21 */.setLocation(str /* #20 */);
            
                System.out.println("Enter Track Size");
                str /* #20 */ = buffr /* #19 */.readLine();
                track /* #21 */.setSize(Double.parseDouble(str /* #20 */));
                        
                track /* #21 */.setGuestArtist(addArtist());
                
                return track /* #21 */;
            }
            
       
    /**
     * addAlbum method used to create an instance from Album class
     * 
     * @return  will return the created object
     */     
            public static Album addAlbum(Artist artist /* #22 */, ArrayList<Track> tracks /* #23 */) throws IOException
            {
                BufferedReader buffr /* #24 */ = new BufferedReader(new InputStreamReader(System.in));        
                String str /* #25 */ = "";
                
                Album album /* #26 */ = new Album();
                System.out.println("Enter Album Name ");
                str /* #25 */ = buffr /* #24 */.readLine();
                album /* #26 */.setAlbumName(str /* #25 */);
                            
                System.out.println("Enter Album Type ");
                str /* #25 */ = buffr /* #24 */.readLine();
                album /* #26 */.setAlbumType(str /* #25 */);
                
                for (Track t /* #26 */:tracks /* #23 */)
                {
                    album /* #26 */.addTrack(t /* #26 */);
                }
                
                album /* #26 */.setArtist(artist /* #22 */);
                
                return album /* #26 */;
            }         
       
    /**
     * addLibrary method used to create an instance from Library class
     * 
     * @return  will return the created object
     */     
             public static Library addLibrary(ArrayList<Track> t /* #27 */, ArrayList<Album> a /* #28 */) throws IOException
             {
                 Library library /* #29 */ = new Library();
                 library /* #29 */.setArrayTrack(t /* #27 */);
                 library /* #29 */.setArrayAlbum(a /* #28 */);
                 
                 return library /* #29 */;
             }   
        }    

// Total: 29 variables
