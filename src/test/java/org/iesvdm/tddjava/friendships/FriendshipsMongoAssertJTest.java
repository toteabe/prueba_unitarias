package org.iesvdm.tddjava.friendships;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.*;


/**
 * Si falla en Run > Edit Configuration > añadir argjvm: --add-opens java.base/java.lang=ALL-UNNAMED

 @Mock creates a mock. @InjectMocks creates an instance of the class and injects the mocks that are created with the @Mock (or @Spy) annotations into this instance.

 Note you must use @RunWith(MockitoJUnitRunner.class) or Mockito.initMocks(this) to initialize these mocks and inject them (JUnit 4).

 With JUnit 5, you must use @ExtendWith(MockitoExtension.class).

 @RunWith(MockitoJUnitRunner.class) // JUnit 4
 // @ExtendWith(MockitoExtension.class) for JUnit 5
 public class SomeManagerTest {

 @InjectMocks
 private SomeManager someManager;

 @Mock
 private SomeDependency someDependency; // this will be injected into someManager

 // tests...

 }
 */
@ExtendWith(MockitoExtension.class)
public class FriendshipsMongoAssertJTest {

    @InjectMocks
    FriendshipsMongo friendships;

    @Mock
    FriendsCollection friends;

    @Test
    public void mockingWorksAsExpected() {
        Person joe = new Person("Joe");
        doReturn(joe).when(friends).findByName("Joe");
        assertThat(friends.findByName("Joe")).isEqualTo(joe);
    }

    @Test
    public void alexDoesNotHaveFriends() {
        assertThat(friendships.getFriendsList("Alex")).isEmpty();
    }

    @Test
    public void joeHas5Friends() {
        List<String> expected = Arrays.asList(
                new String[]{"Audrey", "Peter", "Michael", "Britney", "Paul"}
        );
        Person joe = spy(new Person("Joe"));
        doReturn(joe).when(friends).findByName("Joe");
        doReturn(expected).when(joe).getFriends();
        assertThat(friendships.getFriendsList("Joe"))
                .hasSize(5)
                .containsOnly("Audrey", "Peter", "Michael", "Britney", "Paul");
    }

}
