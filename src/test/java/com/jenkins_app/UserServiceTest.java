package com.jenkins_app;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private Repo userRepository;
    @Test
    public void shouldReturnUserById() {
        Exemple user = new Exemple();
        user.id=1;

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Exemple result = userRepository.findById(1).get();
        System.out.print("test");
        assertEquals(1, result.id);
        verify(userRepository).findById(1);
    }
}*/
