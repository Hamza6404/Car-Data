//Hamza Hussein (501168617)
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
   public static void main(String[] args)
   {
       // Simulation of audio content in an online store
       // The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
       AudioContentStore store = new AudioContentStore();
      
       // Create my music mylibrary
       Library mylibrary = new Library();


       Scanner scanner = new Scanner(System.in);
       System.out.print(">");


       // Process keyboard actions
       while (scanner.hasNextLine())
       {
           String action = scanner.nextLine();
        try{
            if (action == null || action.equals(""))
            {
               System.out.print("\n>");
               continue;
            }
            else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
               return;
          
            else if (action.equalsIgnoreCase("STORE"))  // List all songs
            {
               store.listAll();
            }
           else if (action.equalsIgnoreCase("SONGS"))  // List all songs
           {
               mylibrary.listAllSongs();
           }
           else if (action.equalsIgnoreCase("BOOKS"))  // List all songs
           {
               mylibrary.listAllAudioBooks();
           }
           else if (action.equalsIgnoreCase("PODCASTS"))   // List all songs
           {
               mylibrary.listAllPodcasts();
           }
           else if (action.equalsIgnoreCase("ARTISTS"))    // List all songs
           {
               mylibrary.listAllArtists();
           }
           else if (action.equalsIgnoreCase("PLAYLISTS"))  // List all play lists
           {
               mylibrary.listAllPlaylists();
           }
           // Download audiocontent (song/audiobook/podcast) from the store
           // Specify the index of the content
           else if (action.equalsIgnoreCase("DOWNLOAD"))
           {
                   System.out.print("From Store Content #: ");
                   int nextNum = scanner.nextInt();
               System.out.print("To Store Content #: ");
                   int nextNum2 = scanner.nextInt();
                   ArrayList<AudioContent> use = new ArrayList<>();
                    if((nextNum<1 || nextNum>store.getContents().size()) || (nextNum2<1 || nextNum2>store.getContents().size()))
                        throw new AudioContentNotFoundException("Out of Index Bounds"); // checks if indexes are valid
                   for (int i = nextNum; i < nextNum2+1; i++)
                   {
                       use.add(store.getContent(i));
                   }
                   mylibrary.download(use);         
           }
           // Get the *library* index (index of a song based on the songs list)
           // of a song from the keyboard and play the song
           else if (action.equalsIgnoreCase("PLAYSONG"))
           {
               // Print error message if the song doesn't exist in the library
               System.out.print("Song Number: ");
               int index = scanner.nextInt();  // takes in index
               mylibrary.playSong(index); // plays song
            
           }
           // Print the table of contents (TOC) of an audiobook that
           // has been downloaded to the library. Get the desired book index
           // from the keyboard - the index is based on the list of books in the library
           else if (action.equalsIgnoreCase("BOOKTOC"))
           {
           // Print error message if the book doesn't exist in the library
           System.out.print("Audio Book Number: ");
           int index = scanner.nextInt(); 
           mylibrary.printAudioBookTOC(index);
           }
           // Similar to playsong above except for audio book
           // In addition to the book index, read the chapter
           // number from the keyboard - see class Library
           else if (action.equalsIgnoreCase("PLAYBOOK"))
           {
               System.out.print("Audio Book Number: ");
               int index = scanner.nextInt(); 
               System.out.print("Chapter: ");
               int chapter = scanner.nextInt();
               mylibrary.playAudioBook(index,chapter);           
           }
           // Print the episode titles for the given season of the given podcast
           // In addition to the podcast index from the list of podcasts,
           // read the season number from the keyboard
           // see class Library for the method to call
           else if (action.equalsIgnoreCase("PODTOC"))
           {
              
           }
           // Similar to playsong above except for podcast
           // In addition to the podcast index from the list of podcasts,
           // read the season number and the episode number from the keyboard
           // see class Library for the method to call
           else if (action.equalsIgnoreCase("PLAYPOD"))
           {
              
           }
           // Specify a playlist title (string)
           // Play all the audio content (songs, audiobooks, podcasts) of the playlist
           // see class Library for the method to call
           else if (action.equalsIgnoreCase("PLAYALLPL"))
           {
               System.out.print("Playlist Title: ");
               String title = scanner.next();
               mylibrary.playPlaylist(title);
           }
           // Specify a playlist title (string)
           // Read the index of a song/audiobook/podcast in the playist from the keyboard
           // Play all the audio content
           // see class Library for the method to call
           else if (action.equalsIgnoreCase("PLAYPL"))
           {
               System.out.print("Playlist Title: ");
               String title = scanner.next();
               System.out.print("Content Number: ");
               int index = scanner.nextInt();
               mylibrary.playPlaylist(title,index);              
           }
           // Delete a song from the list of songs in mylibrary and any play lists it belongs to
           // Read a song index from the keyboard
           // see class Library for the method to call
           else if (action.equalsIgnoreCase("DELSONG"))
           {
               System.out.print("Library Song #: ");
               int index = scanner.nextInt(); 
               mylibrary.deleteSong(index);
               if (!mylibrary.deleteSong(index))
                   System.out.println(mylibrary.getErrorMessage());
           }
           // Read a title string from the keyboard and make a playlist
           // see class Library for the method to call
           else if (action.equalsIgnoreCase("MAKEPL"))
           {
               System.out.print("Playlist Title: ");
               String title = scanner.next(); 
               mylibrary.makePlaylist(title);              
           }
           // Print the content information (songs, audiobooks, podcasts) in the playlist
           // Read a playlist title string from the keyboard
         // see class Library for the method to call
           else if (action.equalsIgnoreCase("PRINTPL"))    // print playlist content
           {
               System.out.print("Playlist Title: ");
               String title = scanner.next(); 
               mylibrary.printPlaylist(title);          
           }
           // Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
           // Read the playlist title, the type of content ("song" "audiobook" "podcast")
           // and the index of the content (based on song list, audiobook list etc) from the keyboard
         // see class Library for the method to call
           else if (action.equalsIgnoreCase("ADDTOPL"))
           {
               System.out.print("Playlist Title: ");
               String title = scanner.next();
               System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
               String type = scanner.next();
               System.out.print("Library Content #: ");
               int index = scanner.nextInt();
               mylibrary.addContentToPlaylist(type,index, title);
           }
           // Delete content from play list based on index from the playlist
           // Read the playlist title string and the playlist index
         // see class Library for the method to call
           else if (action.equalsIgnoreCase("DELFROMPL"))
           {
               System.out.print("Playlist Title: ");
               String title = scanner.next();
               System.out.print("Playlist Content #: ");
               int index = scanner.nextInt();
               mylibrary.delContentFromPlaylist(index, title);
           }
          
           else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
           {
               mylibrary.sortSongsByYear();
           }
           else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
           {
               mylibrary.sortSongsByName();
           }
           else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
           {
               mylibrary.sortSongsByLength();
           }
           else if (action.equalsIgnoreCase("SEARCH"))
           {
               System.out.print("Title: ");
               String title = scanner.nextLine(); // takes in title
               store.search_title(title); // searchs for title in store content
           }
           else if (action.equalsIgnoreCase("SEARCHA"))
           { 
            // This takes in input and then checks for songs written by the artist inputted
            // A arraylist is made with all content that is checked with the inputted artist
                System.out.print("Artist: ");
                String name = scanner.nextLine();
                ArrayList<AudioContent> use = store.search_artist(name);
                ArrayList<AudioContent> check = store.getContents();
                for (int i = 0; i < check.size(); i++){
                    for (int j = 0; j<use.size(); j++){
                        if (check.get(i).getType().equals(Song.TYPENAME)){
                            if (use.get(j).equals(check.get(i))){
                                System.out.print((i+1) + ". ");
                                use.get(j).printInfo();
                                System.out.println();
                            }
                        }
                    }
           
                }
           }    
           else if (action.equalsIgnoreCase("SEARCHG"))
           {
            // This takes in input and then checks for songs by the genre inputted
            // A arraylist is made with all content that is checked with the inputted genre
                System.out.print("[POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
                String genre = scanner.nextLine();
                ArrayList<AudioContent> use = store.search_genre(genre);
                ArrayList<AudioContent> check = store.getContents();
                for (int i = 0; i < check.size(); i++){
                   for (int j = 0; j<use.size(); j++){
                        if (check.get(i).getType().equals(Song.TYPENAME)){
                            if (use.get(j).equals(check.get(i))){
                                System.out.print((i+1) + ". ");
                                use.get(j).printInfo();
                                System.out.println();
                            }
                        }
                    }
               }
            }
           else if (action.equalsIgnoreCase("DOWNLOADA"))
           {
            // downloads song by artist inputted
                System.out.print("Artist Name: ");
                String artist = scanner.nextLine(); // takes in input
                ArrayList<AudioContent> use = store.search_artist(artist); // creates arraylist os songs by artist
                mylibrary.downloadNew(use); // downloads those songs
           }


           else if (action.equalsIgnoreCase("DOWNLOADG"))
           {
               System.out.print("Genre: ");
               String genre = scanner.nextLine(); // takes in input
               ArrayList<AudioContent> use = store.search_genre(genre); // creates arraylist with songs of genre
               mylibrary.downloadNew(use); // downloads songs
           }
           else if (action.equalsIgnoreCase("SEARCHP"))//Bonus
           {
            // searches for content with part of text
				System.out.print("SEARCH: ");
				String txt = scanner.nextLine(); // takes in text
				ArrayList<AudioContent> use = store.search_audiocontent(txt); // creates arraylist with audiocontent with this part text
				for (int i = 0; i < use.size(); i++){
					System.out.print((i+1) + ".");
					use.get(i).printInfo();
					System.out.println();
				}
			}
      
       }catch(AudioContentNotFoundException e1){
           System.out.println(e1.getMessage());
       }
           System.out.print("\n>");
       }
   }
}



