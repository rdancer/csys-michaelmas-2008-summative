/**
 * Class Artist: a Java class to store information about Artists, including their names and memberships.  
 *
 */

import java.util.*;

public class Artist
{      
    private String name;
    private ArrayList<Artist> members; 
    private boolean isSoloist;

    /**
     * Constructor for objects of class Artist
     */
    public Artist()
    {
        name = "";
        members = new ArrayList<Artist>();
        isSoloist = true;
    }
    
    public void setName(String n)
    {
       name = n;
    }
    
    public String getName()
    {
        return name;
    }
      
     /**
     * storeMemebr methos will add the members - in case the artist is band - to the arraylist
     * 
     * @param  n   the name of the Band's member
     * @return     the ArrayList containing all Band memebrs.
     */
    public void storeMember(String n)
    {
        Artist member = new Artist();
        member.setName(n);
        member.setIsSoloist(false); //Becasue method works only for Band members
        members.add(member);
    }
    
    public void setIsSoloist(boolean s)
    {
        isSoloist = s;
    }
    
    public boolean getIsSoloist()
    {
        return isSoloist;
    }
    
    public String toString()
    {
        String str= "Name : " + name + "Is Soloist : " + isSoloist;
        if (isSoloist == false)
        {
            str+= "Members :";
            for (Artist m:members)
                str+= "\n" + m.getName();
        }
        return str;
    }  
}
