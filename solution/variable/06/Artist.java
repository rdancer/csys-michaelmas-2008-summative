/**
 * Class Artist: a Java class to store information about Artists, including their names and memberships.  
 *
 */

import java.util.*;

public class Artist
{      
    private String name /* #1 */;
    private ArrayList<Artist> members /* #2 */; 
    private boolean isSoloist /* #3 */;

    /**
     * Constructor for objects of class Artist
     */
    public Artist()
    {
        name /* #1 */ = "";
        members /* #2 */ = new ArrayList<Artist>();
        isSoloist /* #3 */ = true;
    }
    
    public void setName(String n /* #4 */)
    {
       name /* #1 */ = n /* #4 */;
    }
    
    public String getName()
    {
        return name /* #1 */;
    }
      
     /**
     * storeMemebr methos will add the members - in case the artist is band - to the arraylist
     * 
     * @param  n   the name of the Band's member
     * @return     the ArrayList containing all Band memebrs.
     */
    public void storeMember(String n /* #5 */)
    {
        Artist member /* #6 */ = new Artist();
        member /* #6 */ .setName(n /* #5 */);
        member /* #6 */ .setIsSoloist(false); //Becasue method works only for Band members
        members /* #2 */.add(member /* #6 */);
    }
    
    public void setIsSoloist(boolean s /* #7 */)
    {
        isSoloist /* #3 */ = s /* #7 */;
    }
    
    public boolean getIsSoloist()
    {
        return isSoloist /* #3 */;
    }
    
    public String toString()
    {
        String str /* #8 */ = "Name : " + name /* #1 */ + "Is Soloist : " + isSoloist /* #3 */;
        if (isSoloist /* #3 */ == false)
        {
            str /* #8 */ += "Members :";
            for (Artist m /* #9 */ :members /* #2 */)
                str /* #8 */ += "\n" + m /* #9 */ .getName();
        }
        return str /* #8 */;
    }  
}

// Total: 9 variables
