//Hamza Hussein (501168617)
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/*
* This class manages, stores, and plays audio content such as songs, podcasts and audiobooks.
*/
public class Library
{
   private ArrayList<Song>             songs;
   private ArrayList<AudioBook>    audiobooks;
   private ArrayList<Playlist>     playlists;
  
 //private ArrayList<Podcast>  podcasts;
  
   // Public methods in this class set errorMesg string
   // Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
   // In assignment 2 we will replace this with Java Exceptions
   String errorMsg = "";
  
   public String getErrorMessage()
   {
       return errorMsg;
   }
   public ArrayList<Song> getSongs(){
    return songs;
   }
   public Library()
   {
       songs           = new ArrayList<Song>();
       audiobooks  = new ArrayList<AudioBook>(); ;
       playlists   = new ArrayList<Playlist>();
     //podcasts        = new ArrayList<Podcast>(); ;
   }
   /*
    * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
    * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
    * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
    * to determine which list it belongs to above
    *
    * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
    * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
    * See the video
    */
   public void download(ArrayList<AudioContent> use) {
    // takes in audiocontent and checks whether its downloaded or not
    // if it is downloaded it prints already downloaded and if not it downloads and print added to library
       for (int i = 0; i < use.size(); i++){
           AudioContent audio = use.get(i);
           if (audio.getType().equals(Song.TYPENAME)){
               boolean check = false;
               for (int j = 0; j < songs.size(); j++){
                   if (audio.equals(songs.get(j))){
                       check = true;
                       System.out.println("SONG " + audio.getTitle() + " already downloaded");
                       break;
                   }
               }
               if (check == false){
                   songs.add((Song) audio);
                   System.out.println("SONG " + audio.getTitle() + " Added to library");
               }
           }
           if (audio.getType().equals(AudioBook.TYPENAME)){
               boolean check = false;
               for (int x = 0; x < audiobooks.size(); x++)
               {
                   if (audio.equals(audiobooks.get(x)))
                   {
                       check = true;
                       System.out.println("AUDIOBOOK " + audio.getTitle() + " already downloaded");
                       break;
                   }
               }
               if (check == false){
                    audiobooks.add((AudioBook) audio);
                    System.out.println("AUDIOBOOK " + audio.getTitle() + " Added to library");
                }
            }
        }
   }
// same as above method without printing out song added to library when its downloaded
   public void downloadNew(ArrayList<AudioContent> use) {
    for (int i = 0; i < use.size(); i++){
        AudioContent audio = use.get(i);
        if (audio.getType().equals(Song.TYPENAME)){
            boolean check = false;
            for (int j = 0; j < songs.size(); j++){
                if (audio.equals(songs.get(j))){
                    check = true;
                    System.out.println("SONG " + audio.getTitle() + " already downloaded");
                    break;
                }
            }
            if (check == false){
                songs.add((Song) audio);
                //System.out.println("SONG " + audio.getTitle() + " Added to library");
            }
        }
        if (audio.getType().equals(AudioBook.TYPENAME)){
            boolean check = false;
            for (int x = 0; x < audiobooks.size(); x++)
            {
                if (audio.equals(audiobooks.get(x)))
                {
                    check = true;
                    System.out.println("AUDIOBOOK " + audio.getTitle() + " already downloaded");
                    break;
                }
            }
            if (check == false){
                 audiobooks.add((AudioBook) audio);
                 System.out.println("AUDIOBOOK " + audio.getTitle() + " Added to library");
             }
         }
     }
}
   // Print Information (printInfo()) about all songs in the array list
   public void listAllSongs()
   {
       for (int i = 0; i < songs.size(); i++)
       {
           int index = i + 1;
           System.out.print("" + index + ". ");
           songs.get(i).printInfo();
           System.out.println();  
       }
   }
  
   // Print Information (printInfo()) about all audiobooks in the array list
   public void listAllAudioBooks()
   {
       for (int i = 0; i < audiobooks.size(); i++)
       {
           int index = i + 1;
           System.out.print("" + index + ". ");
           audiobooks.get(i).printInfo();
           System.out.println();  
       }      
   }
  
 // Print Information (printInfo()) about all podcasts in the array list
   public void listAllPodcasts()
   {
      
   }
  
 // Print the name of all playlists in the playlists array list
   // First print the index number as in listAllSongs() above
   public void listAllPlaylists()
   {
      
       for (int i = 0; i < playlists.size(); i++)
       {
           int index = i + 1;
           System.out.print("" + index + ". ");
           System.out.println(playlists.get(i).getTitle());   
       }
      
   }
  
 // Print the name of all artists.
   public void listAllArtists()
   {
       // First create a new (empty) array list of string
       // Go through the songs array list and add the artist name to the new arraylist only if it is
       // not already there. Once the artist arrayl ist is complete, print the artists names
      
       ArrayList<String> artists = new ArrayList<String>();
       for (int i = 0; i<songs.size(); i++){
           if(!(artists.contains(songs.get(i).getArtist())))
               artists.add(songs.get(i).getArtist());
      
       }
       for (int i = 0; i<artists.size(); i++){
           System.out.println((i+1) +". "+ artists.get(i));
       }
      
   }


   // Delete a song from the library (i.e. the songs list) -
   // also go through all playlists and remove it from any playlist as well if it is part of the playlist
   public boolean deleteSong(int index)
   {
       if (index-1 < 0 || index-1 >= songs.size()){
           throw new AudioContentNotFoundException("Song Not Found");
       }
       for (int i = 0; i<playlists.size(); i++){
           for (int j = 0; j<playlists.get(i).getContent().size(); j++){  
               if (playlists.get(i).getContent().get(j).getType().equalsIgnoreCase("SONG")){
                   if ((playlists.get(i).getContent().get(j).equals(songs.get(index-1)))){
                       playlists.get(i).deleteContent(j+1);
                   }
               }
       }
       }
       if (index<=songs.size() && songs.size() > 0 && index>=0){
           songs.remove(index-1);
           return true;
       }
       return false;
   }
  
 //Sort songs in library by year
   public void sortSongsByYear()
   {
       // Use Collections.sort()
       Collections.sort(songs, new SongYearComparator());
   }
 // Write a class SongYearComparator that implements
   // the Comparator interface and compare two songs based on year
   private class SongYearComparator implements Comparator<Song>
   {
       public int compare(Song a, Song b)
       {
           return Integer.compare(a.getYear(),b.getYear());


       }
   }


   // Sort songs by length
   public void sortSongsByLength()
   {
    // Use Collections.sort()
       Collections.sort(songs, new SongLengthComparator());
   }
 // Write a class SongLengthComparator that implements
   // the Comparator interface and compare two songs based on length
   private class SongLengthComparator implements Comparator<Song>
   {
       public int compare(Song a, Song b)
       {
           return Integer.compare(a.getLength(),b.getLength());


       }
   }


   // Sort songs by title
   public void sortSongsByName()
   {
     // Use Collections.sort()
       // class Song should implement the Comparable interface
       // see class Song code
       Collections.sort(songs, new SongNameComparator());
   }
   private class SongNameComparator implements Comparator<Song>
   {
       public int compare(Song a, Song b)
       {
           return a.compareTo(b);


       }
   }
  
  
   /*
    * Play Content
    */
  
   // Play song from songs list
   public void playSong(int index)
   {
       if (index < 1 || index > songs.size())
       {
           throw new AudioContentNotFoundException("Song Not Found");
       }
       songs.get(index-1).play();
       //return true;
   }
  
   // Play podcast from list (specify season and episode)
   // Bonus
   public void playPodcast(int index, int season, int episode)
   {
       throw new AudioContentNotFoundException("Podcast Not Found");
   }
  
   // Print the episode titles of a specified season
   // Bonus
   public void printPodcastEpisodes(int index, int season)
   {
       throw new AudioContentNotFoundException("Season Not Found");
   }
  
   // Play a chapter of an audio book from list of audiobooks
   public void playAudioBook(int index, int chapter)
   {
       if (index < 1 || index > songs.size())
       {
           throw new AudioContentNotFoundException("Audiobook Not Found");
       }
       audiobooks.get(index-1).selectChapter(chapter);
       audiobooks.get(index-1).play();
       //return true;
   }
  
   // Print the chapter titles (Table Of Contents) of an audiobook
   // see class AudioBook
   public void printAudioBookTOC(int index)
   {
       if (index < 1 || index > songs.size())
       {
           throw new AudioContentNotFoundException("Audiobook Not Found");
       }
       //System.out.println("Audio Book Number: " + index);
       audiobooks.get(index-1).printTOC();
       //return true;
   }
  
 /*
  * Playlist Related Methods
  */
  
   // Make a new playlist and add to playlists array list
   // Make sure a playlist with the same title doesn't already exist
   public void makePlaylist(String title)
   {
       Playlist use = new Playlist(title);
       for (int i = 0; i< playlists.size(); i++){
           if (playlists.get(i).equals(use)){
               throw new AudioContentNotFoundException("Playlist Name Already Exists");
       }
       }
       playlists.add(use);
   }
  
   // Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
   public void printPlaylist(String title)
   {
       for (int i = 0; i< playlists.size(); i++){
           if (playlists.get(i).getTitle().equalsIgnoreCase(title)){
               playlists.get(i).printContents();
               return;
           }
       }
       throw new AudioContentNotFoundException("Playlist Not Found");
   }
  
   // Play all content in a playlist
   public void playPlaylist(String playlistTitle)
   {
       for (int i = 0; i< playlists.size(); i++){
           if (playlists.get(i).getTitle().equals(playlistTitle)){
               playlists.get(i).playAll();
               return;
       }
   }
       throw new AudioContentNotFoundException("Playlist Not Found.");
   }
  
   // Play a specific song/audiobook in a playlist
   public void playPlaylist(String playlistTitle, int indexInPL)
   {
    for (int i = 0; i< playlists.size(); i++){
    if (!(indexInPL >= 1 && indexInPL <= playlists.get(i).getContent().size()))
    {
        throw new AudioContentNotFoundException("Playlist Index Out of Bounds");
    }
}
       for (int i = 0; i< playlists.size(); i++){
           if (indexInPL >= 1 && indexInPL <= playlists.get(i).getContent().size()){
               if (playlists.get(i).getTitle().equals(playlistTitle)){
                   playlists.get(i).play(indexInPL);
                   return;
           }
       }
   }
       throw new AudioContentNotFoundException("Song/AudioBook not found in Playlist. ");
   }
  
   // Add a song/audiobook/podcast from library lists at top to a playlist
   // Use the type parameter and compare to Song.TYPENAME etc
   // to determine which array list it comes from then use the given index
   // for that list
   public void addContentToPlaylist(String type, int index, String playlistTitle)
   {
       Playlist playlist = new Playlist(playlistTitle);
       AudioContent use = null;
       
       if(playlists.contains(playlist))
       {
           if (type.equalsIgnoreCase("SONG"))
           {
               if (index < 1 || index > songs.size())
               {
                   throw new AudioContentNotFoundException("Song Not Found.");
               }
               use = songs.get(index-1);
               playlists.get(playlists.indexOf(playlist)).addContent(use);
           }
           else if (type.equalsIgnoreCase("AUDIOBOOK"))
           {
               if (index < 1 || index > audiobooks.size())
               {
                   throw new AudioContentNotFoundException("AudioBook Not Found.");
               }
               use = audiobooks.get(index-1);
               playlists.get(playlists.indexOf(playlist)).addContent(use);
           }
       }
       else{
       throw new AudioContentNotFoundException("\n PlayList Not Found.");
       }
   }


 // Delete a song/audiobook/podcast from a playlist with the given title
   // Make sure the given index of the song/audiobook/podcast in the playlist is valid
   public void delContentFromPlaylist(int index, String title)
   {
        for (int i = 0; i< playlists.size(); i++){
            if (playlists.get(i).getTitle().equalsIgnoreCase(title)){
                if (index < 1 || index-1 > playlists.size()){
                    throw new AudioContentNotFoundException("Out of Index Bounds");
                }
                else{
                    playlists.get(i).deleteContent(index);
                    return;
                }
            }
        } 
       throw new AudioContentNotFoundException("Playlist Not Found");
   }
}
class AudioContentNotFoundException extends RuntimeException{
    public AudioContentNotFoundException(){}
    public AudioContentNotFoundException(String message){
        super(message);
    }
}





