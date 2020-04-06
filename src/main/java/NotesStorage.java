import java.util.List;

public interface NotesStorage {
    
    void add(Note note);
    
    boolean isEmptyList();
    
    List<Note> getAllNotesOf(String name);
    
    void clear();
    
}