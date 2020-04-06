import org.easymock.MockType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;
import static org.easymock.EasyMock.*;

public class NotesServiceTest {
    
    private NotesService notesService;
    
    private NotesStorage notesStorage;
    
    @BeforeEach
    public void setUp() {
        notesStorage = mock(MockType.NICE, NotesStorage.class);
        notesService = NotesServiceImpl.createWith(notesStorage);
    }
    
    @Test
    public void shouldNotCalculateAverageOfNullStudent() {
        assertThatThrownBy(() -> notesService.averageOf(null)).isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    public void shouldNotCalculateAverageOfEmptyStudent() {
        assertThatThrownBy(() -> notesService.averageOf("")).isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    public void shouldCalculateAverageOfStudentWithoutNotes() {
        expect(notesStorage.getAllNotesOf("Roman")).andReturn(new ArrayList<>());
        replay(notesStorage);
        
        assertThat(notesService.averageOf("Roman")).isCloseTo(0.0f, offset(0.01f));
        
        verify(notesStorage);
    }
    
    @Test
    public void shouldCalculateAverage() {
        expect(notesStorage.getAllNotesOf("Roman")).andReturn(Arrays.asList(Note.of("a", 3.0f), Note.of("b", 2.0f)));
        replay(notesStorage);
        
        assertThat(notesService.averageOf("Roman")).isCloseTo(2.5f, offset(0.01f));
        
        verify(notesStorage);
    }
    
    @Test
    public void shouldClearList() {
        replay(notesStorage);
        notesService.clear();
        verify(notesStorage);
    }
    
    @Test
    public void shouldAddItemToList() {
        replay(notesStorage);
        notesService.add(Note.of("asdf", 2.0f));
        verify(notesStorage);
    }
    
    @AfterEach
    public void tearDown() {
        notesStorage = null;
        notesService = null;
    }
    
}