import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.*;

@ExtendWith(EasyMockExtension.class)
public class FriendshipsMongoTest {
    
    @TestSubject
    private FriendshipsMongo friendshipsMongo = new FriendshipsMongo();
    
    @Mock(type = MockType.NICE)
    private FriendsCollection friendsCollection;
    
    @Test
    public void mockingWorksAsExpected() {
        Person joe = new Person("Joe");
        expect(friendsCollection.findByName("Joe")).andReturn(joe);
        replay(friendsCollection);
        
        assertThat(friendsCollection.findByName("Joe")).isEqualTo(joe);
    }
    
    @Test
    public void alexDoesNotHaveFriends() {
        assertThat(friendshipsMongo.getFriendsList("Alex")).isEmpty();
    }
    
    @Test
    public void joeHas5Friends() {
        List<String> expected = Arrays.asList("Karol", "Dawid", "Maciej", "Tomek", "Adam");
        Person joe = createMock(Person.class);
        expect(friendsCollection.findByName("Joe")).andReturn(joe);
        expect(joe.getFriends()).andReturn(expected);
        replay(friendsCollection);
        replay(joe);
        
        assertThat(friendshipsMongo.getFriendsList("Joe")).hasSize(5)
                .containsOnly("Karol", "Dawid", "Maciej", "Tomek", "Adam");
    }
    
    @Test
    public void joeWithAlexAreNotFriends() {
        Person joe = createMock(Person.class);
        expect(friendsCollection.findByName("Joe")).andReturn(joe);
        expect(joe.getFriends()).andReturn(new ArrayList<>());
        replay(friendsCollection);
        replay(joe);
        
        assertThat(friendshipsMongo.areFriends("Joe", "Alex")).isFalse();
    }
    
    @Test
    public void joeWithAlexAreFriends() {
        Person joe = createMock(Person.class);
        expect(friendsCollection.findByName("Joe")).andReturn(joe);
        expect(joe.getFriends()).andReturn(Collections.singletonList("Alex"));
        replay(friendsCollection);
        replay(joe);
        
        assertThat(friendshipsMongo.areFriends("Joe", "Alex")).isTrue();
    }
    
    @Test
    public void nameOfJoeIsJoe() {
        Person joe = createMock(Person.class);
        expect(joe.getName()).andReturn("Joe");
        replay(joe);
        
        assertThat(joe.getName()).isEqualTo("Joe");
    }
    
    @Test
    public void settingNameWithEmptyStringThrowsException() {
        Person joe = createMock(Person.class);
        joe.setName("");
        expectLastCall().andThrow(new RuntimeException());
    }
    
}