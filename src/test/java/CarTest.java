import org.easymock.MockType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    
    private Car car;
    
    @BeforeEach
    public void setUp() {
        car = mock(MockType.NICE, Car.class);
    }
    
    @Test
    public void testInstanceCar() {
        assertThat(car).isInstanceOf(Car.class);
    }
    
    @Test
    public void testDefaultBehaviourNeedsFuel() {
        assertThat(car.needsFuel()).isFalse();
    }
    
    @Test
    public void testDefaultBehaviourTemperature() {
        assertThat(car.getEngineTemperature()).isCloseTo(0.0, offset(0.01));
    }
    
    @Test
    public void testDefaultBehaviourGetName() {
        assertThat(car.getName()).isNull();
    }
    
    @Test
    public void testDefaultBehaviourGetProductionYear() {
        assertThat(car.getProductionYear()).isEqualTo(0);
    }
    
    @Test
    public void testStubbingMock() {
        expect(car.needsFuel()).andReturn(true);
        replay(car);
        
        assertThat(car.needsFuel()).isTrue();
    }
    
    @Test
    public void testException() {
        expect(car.needsFuel()).andThrow(new RuntimeException());
        replay(car);
        
        assertThatThrownBy(() -> car.needsFuel()).isInstanceOf(RuntimeException.class);
    }
    
    @Test
    public void testGetNameMock() {
        expect(car.getName()).andReturn("Test");
        replay(car);
        
        assertThat(car.getName()).isEqualTo("Test");
        
        verify(car);
    }
    
    @Test
    public void testGetProductionYearMock() {
        expect(car.getProductionYear()).andReturn(1923);
        replay(car);
        
        assertThat(car.getProductionYear()).isEqualTo(1923);
        
        verify(car);
    }
    
    @Test
    public void testGetProductionYearException() {
        expect(car.getProductionYear()).andThrow(new RuntimeException());
        replay(car);
        
        assertThatThrownBy(() -> car.getProductionYear()).isInstanceOf(RuntimeException.class);
    }
    
    @AfterEach
    public void tearDown() {
        car = null;
    }
    
}