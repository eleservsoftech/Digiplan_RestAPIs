package com.digiplan.servicesImpl;

import com.digiplan.repositories.UserRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceImplTests {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

  /*  @Test
    public void test_getUser() {

        User user = new User("drsjavali", "render123#", "Bengaluru", "Sonal", "Javali", "sonaljavali@gmail.com",
                9901500892L, "Doctor", "chaitanya", "50", "Clove Dental", "113", "4th Cross", "Karnataka", "560043",
                "13.0105675", "77.6483568", "No", "No", "113, 4th Cross", 1, "");
        Optional<User> retrievedData = Optional.of(new User("skvyu", "render123#", "Delhi", "Karan", "Singh",
                "sonaljavali@gmail.com", 9901500892L, "Doctor", "chaitanya", "50", "Clove Dental", "113", "6th Cross",
                "up", "90043", "93.0105675", "97.6483568", "No", "No", "113, 4th Cross", 1, ""));
        Integer id = 1;
        when(userRepository.findById(id)).thenReturn(retrievedData);
        if (retrievedData.isPresent())
            when(userRepository.getById(id)).thenReturn(user);
        assertEquals(id, userServiceImpl.getUser(id).getId());
    }

    @Test
    public void test_getAllUsers() {
        List<User> user = new ArrayList<>();
        user.add(new User("drsjavali", "render123#", "Bengaluru", "Sonal", "Javali", "sonaljavali@gmail.com",
                9901500892L, "Doctor", "chaitanya", "50", "Clove Dental", "113", "4th Cross", "Karnataka", "560043",
                "13.0105675", "77.6483568", "No", "No", "113, 4th Cross", 1, ""));
        user.add(new User("drsjavali", "render123#", "Bengaluru", "Sonal", "Javali", "sonaljavali@gmail.com",
                9901500892L, "Doctor", "chaitanya", "50", "Clove Dental", "113", "4th Cross", "Karnataka", "560043",
                "13.0105675", "77.6483568", "No", "No", "113, 4th Cross", 1, ""));
        user.add(new User("drsjavali", "render123#", "Bengaluru", "Sonal", "Javali", "sonaljavali@gmail.com",
                9901500892L, "Doctor", "chaitanya", "50", "Clove Dental", "113", "4th Cross", "Karnataka", "560043",
                "13.0105675", "77.6483568", "No", "No", "113, 4th Cross", 1, ""));
        when(userRepository.findAll()).thenReturn(user);
        assertEquals(3, userServiceImpl.getAllUsers().size());
    }

    @Test
    public void test_addUser() {
        User user = new User("drsjavali", "render123#", "Bengaluru", "Sonal", "Javali", "sonaljavali@gmail.com",
                9901500892L, "Doctor", "chaitanya", "50", "Clove Dental", "113", "4th Cross", "Karnataka", "560043",
                "13.0105675", "77.6483568", "No", "No", "113, 4th Cross", 1, "");
        when(userRepository.saveAndFlush(user)).thenReturn(user);
        assertEquals(user, userServiceImpl.addUser(user));
    }

    @Test
    public void test_updateUser() {
        User user = new User("drsjavali", "render123#", "Bengaluru", "Sonal", "Javali", "sonaljavali@gmail.com",
                9901500892L, "Doctor", "chaitanya", "50", "Clove Dental", "113", "4th Cross", "Karnataka", "560043",
                "13.0105675", "77.6483568", "No", "No", "113, 4th Cross", 1, "");
        Optional<User> retrievedData = Optional.of(new User("skvyu", "render123#", "Delhi", "Karan", "Singh",
                "sonaljavali@gmail.com", 9901500892L, "Doctor", "chaitanya", "50", "Clove Dental", "113", "6th Cross",
                "up", "90043", "93.0105675", "97.6483568", "No", "No", "113, 4th Cross", 1, ""));
        Integer id = 1;
        when(userRepository.findById(id)).thenReturn(retrievedData);
        if (retrievedData.isPresent())
            when(userRepository.saveAndFlush(user)).thenReturn(user);
        assertEquals(user, userServiceImpl.updateUser(id, user));
    }

    @Test
    public void test_deleteUser() {
        Integer id = 1;
        userServiceImpl.deleteUser(id);
        verify(userRepository, times(1)).deleteById(id);
    } */

}
