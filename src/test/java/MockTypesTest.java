import org.easymock.MockType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

public class MockTypesTest {
    
    @Test
    public void defaultTest() {
        List<String> list = mock(List.class);
        expect(list.size()).andReturn(1);
        expect(list.add("a")).andReturn(true);
        expect(list.get(0)).andReturn("a");
        replay(list);
        
        list.add("a");
        
        assertEquals("a", list.get(0));
        assertEquals(1, list.size());
        
        verify(list);
    }
    
    @Test
    public void strictTest() {
        List<String> list = mock(MockType.STRICT, List.class);
        expect(list.add("a")).andReturn(true);
        expect(list.size()).andReturn(1);
        expect(list.get(0)).andReturn("a");
        replay(list);
        
        list.add("a");
        
        assertEquals(1, list.size());
        assertEquals("a", list.get(0));
        
        verify(list);
    }
    
    @Test
    public void niceTest() {
        List<String> list = mock(MockType.NICE, List.class);
        expect(list.add("a")).andReturn(true);
        expect(list.size()).andReturn(1);
        expect(list.get(0)).andReturn("a");
        replay(list);
        
        list.add("a");
        boolean b = list.add("b");
        String c = list.get(3);
        
        assertEquals(1, list.size());
        assertEquals("a", list.get(0));
        assertFalse(b);
        assertNull(c);
        
        verify(list);
    }
    
}
