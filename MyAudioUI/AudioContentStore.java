//Hamza Hussein (501168617)
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class AudioContentStore{
   private ArrayList<AudioContent> contents;
   private HashMap<String, Integer> titleUse = new HashMap<>();
   private HashMap<String, ArrayList<Integer>> artistUse = new HashMap<>();
   private HashMap<String, ArrayList<Integer>> genreUse = new HashMap<>();
   public AudioContent getContent(int index)
   {
       if (index < 1 || index > contents.size())
       {
           return null;
       }
       return contents.get(index-1);
   }
   public void listAll()
   {
       for (int i = 0; i < contents.size(); i++)
       {
           int index = i + 1;
           System.out.print("" + index + ". ");
           contents.get(i).printInfo();
           System.out.println();
       }
   }
   public AudioContentStore(){
       contents = createAudioContents();
   }
   public ArrayList<AudioContent> getContents()
   {
    return contents;
   }
   private ArrayList<AudioContent> createAudioContents(){
      
       ArrayList<AudioContent> audioContents = new ArrayList<>();


       try{
           // Data file to read through Scanner
           File data = new File("store.txt");
           Scanner  scanner= new Scanner(data);


           while (scanner.hasNextLine()){
               String audiotype = scanner.nextLine();


               if (audiotype.equals(Song.TYPENAME)){
                   System.out.println("Loading " + audiotype);


                   // Get all the properties of a song
                   String id = scanner.nextLine();
                   String title = scanner.nextLine();
                   int year = Integer.valueOf(scanner.nextLine());
                   int length = Integer.valueOf(scanner.nextLine()); 
                   String artist = scanner.nextLine();
                   String composer = scanner.nextLine();
                   String genre = scanner.nextLine();


                   int lyricLines = Integer.valueOf(scanner.nextLine());
                   String lyrics = "";


                   // Store all the lyrics into a string
                   for (int i = 0; i < lyricLines; i++){
                       lyrics += scanner.nextLine() + "\n";
                   }
                   // Add to title to index hashmap
                   titleUse.put(title.toLowerCase(), audioContents.size());
                   // Add to artist to index hashmap
                   if (artistUse.containsKey(artist.toLowerCase())){
                       artistUse.get(artist.toLowerCase()).add(audioContents.size());
                   }
                   else{
                       ArrayList<Integer> indexes = new ArrayList<>();
                       indexes.add(audioContents.size());
                       artistUse.put(artist.toLowerCase(), indexes);
                   }


                   // Add to genre to index hashmap
                   if (genreUse.containsKey(genre.toLowerCase())){
                       genreUse.get(genre.toLowerCase()).add(audioContents.size());
                   }
                   else{
                       ArrayList<Integer> indexes = new ArrayList<>();
                       indexes.add(audioContents.size());
                       genreUse.put(genre.toLowerCase(), indexes);
                   }


                   audioContents.add(new Song(title, year, id, Song.TYPENAME, "", length, artist, composer, Song.Genre.valueOf(genre), lyrics));
               }
               else if (audiotype.equals(AudioBook.TYPENAME)){
                   System.out.println("Loading " + audiotype);


                   // Get all properties of an audiobook
                   String id = scanner.nextLine();
                   String title = scanner.nextLine();
                   int year = Integer.valueOf(scanner.nextLine());
                   int length = Integer.valueOf(scanner.nextLine()); 
                   String author = scanner.nextLine();
                   String narrator = scanner.nextLine();
                   ArrayList<String> chapterTitles = new ArrayList<>();
                   ArrayList<String> chapters = new ArrayList<>();


                   int lines_chapter_titles = Integer.valueOf(scanner.nextLine());


                   // Store chapter titles
                   for (int i = 0; i < lines_chapter_titles; i++){
                       chapterTitles.add(scanner.nextLine());
                   }


                   int lines_of_chapters = Integer.valueOf(scanner.nextLine());


                   // Store chapters
                   for (int i = 0; i < lines_of_chapters; i++){
                       chapters.add(scanner.nextLine());
                   }
                   titleUse.put(title.toLowerCase(), audioContents.size());


                   // Add to author to index hashmap
                   if (artistUse.containsKey(author.toLowerCase())){
                       artistUse.get(author.toLowerCase()).add(audioContents.size());
                   }
                   else{
                       ArrayList<Integer> indexes = new ArrayList<>();
                       indexes.add(audioContents.size());
                       artistUse.put(author.toLowerCase(), indexes);
                   }


                   audioContents.add(new AudioBook(title, year, id, AudioBook.TYPENAME, "", length, author, narrator, chapterTitles, chapters));
               }
           }


           scanner.close();
       }
       catch(FileNotFoundException error){
           System.out.println(error.getMessage());
       }
       return audioContents;
   }
  
   // Returns all audiocontent given a title
   public void search_title(String title){
    // checks if song has same title as one entered
       Integer index = titleUse.get(title.toLowerCase());
       if (index == null){
           throw new AudioContentNotFoundException("No matches for " + title);
       }

       System.out.print((index.intValue() + 1) + ". ");
       contents.get(index.intValue()).printInfo();
   }

   // Returns all audiocontent given an artist
   public ArrayList<AudioContent> search_artist(String artist){
    // checks if song is made by same artist as one entered
       ArrayList<Integer> indexes = artistUse.get(artist.toLowerCase());
       if (indexes == null){
           throw new AudioContentNotFoundException("No matches for " + artist);
       }

       ArrayList<AudioContent> audiocontents = new ArrayList<>();

       for (int i = 0; i < indexes.size(); i++){
           audiocontents.add(contents.get(indexes.get(i)));
       }
       return audiocontents;
   }

   // Returns all audiocontent given a genre
   public ArrayList<AudioContent> search_genre(String genre){
    // checks if song has same genre as one entered and returns a arraylist with songs of same genre
       ArrayList<Integer> indexes = genreUse.get(genre.toLowerCase());
       if (indexes == null){
           throw new AudioContentNotFoundException("No matches for " + genre);
       }
       ArrayList<AudioContent> audiocontents = new ArrayList<>();
       for (int i = 0; i < indexes.size(); i++){
           audiocontents.add(contents.get(indexes.get(i)));
       }
       return audiocontents;
   }


   public ArrayList<AudioContent> search_audiocontent(String text){
       // Audio contents to be returned to be downloaded
        // checks if song has same partial text as one entered and returns a arraylist with songs or books with that partial text

       ArrayList<AudioContent> audiocontents = new ArrayList<>();


       // Loop through all audio content in the store
       for (int i = 0; i < this.contents.size(); i++){
           AudioContent content = contents.get(i);
           // Get the audio content's properties (title & audiofile shared by song and audiobook)
           String title = content.getTitle().toLowerCase();
           String audiofile = content.getAudioFile().toLowerCase();
           // If current content is a song
           if (content.getType().equals(Song.TYPENAME)){
               Song song = (Song) content;
               // Get the song's properties (artist, composer, and lyrics)
               String artist = song.getArtist().toLowerCase();
               String composer = song.getComposer().toLowerCase();
               String lyrics = song.getLyrics().toLowerCase();

               if (artist.contains(text) || composer.contains(text) || lyrics.contains(text) || title.contains(text) || audiofile.contains(text)){
                   audiocontents.add(content);
               }
           }
           else if (content.getType().equals(AudioBook.TYPENAME)){
               AudioBook audiobook = (AudioBook) content;

               // Get the audiobook's properties
               String author = audiobook.getAuthor().toLowerCase();
               String narrator = audiobook.getNarrator().toLowerCase();
               ArrayList<String> chapterTitles = audiobook.getChapterTitles();
               ArrayList<String> chapters = audiobook.getChapters();

               // Check if text matches a chapter title
               for (int j = 0; j < chapterTitles.size(); j++){
                   if (chapterTitles.get(j).toLowerCase().contains(text)){
                       audiocontents.add(content);
                   }
               }
               // Check if chapters contain text
               for (int j = 0; j < chapters.size(); j++){
                   if (chapters.get(j).toLowerCase().contains(text)){
                       audiocontents.add(content);
                   }
               }
               // Check if properties contain text
               if (author.contains(text) || narrator.contains(text) || title.contains(text) || audiofile.contains(text)){
                   audiocontents.add(content);
               }
           }
       }
       return audiocontents;
   }
}


