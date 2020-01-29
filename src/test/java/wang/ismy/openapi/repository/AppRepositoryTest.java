package wang.ismy.openapi.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AppRepositoryTest {

    @Autowired
    AppRepository appRepository;

    @Test
    public void find(){
        assertEquals(1,appRepository.findAll().size());
    }
}