import org.easymock.MockType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

public class RambolTest {
    
    private Rambol rambol;
    
    @BeforeEach
    public void setUp() {
        rambol = mock(MockType.NICE, Rambol.class);
    }
    
    @Test
    public void testWithoutVerify() {
        expect(rambol.getA()).andReturn(111);
        expect(rambol.getB()).andReturn(222);
        replay(rambol);
        
        assertEquals(222, rambol.getB());
        // tutaj powinno byc 'verify' zeby sprawdzic czy wszystkie metody zostaly wywolane
    }
    
    @Test
    public void testWithVerify() {
        expect(rambol.getA()).andReturn(111);
        expect(rambol.getB()).andReturn(222);
        replay(rambol);
        
        assertEquals(222, rambol.getB());
        
        assertThrows(AssertionError.class, () -> verify(rambol));
        // test sie wywala, bo nie wywolywalismy wszyskich metod
    }
    
    @AfterEach
    public void tearDown() {
        rambol = null;
    }
    
}