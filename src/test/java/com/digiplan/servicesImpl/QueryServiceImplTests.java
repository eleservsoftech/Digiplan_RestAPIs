package com.digiplan.servicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.digiplan.entities.Query;
import com.digiplan.repositories.QueryRepository;

@SpringBootTest
public class QueryServiceImplTests {

    @InjectMocks
    private QueryServiceImpl queryServiceImpl;

    @Mock
    private QueryRepository queryRepository;

    @Test
    public void test_getQuery() {
        Query query = new Query("1", "Karan", 9999999999L, "karankumar12@gmail.com", "demo");
        Optional<Query> retrievedData = Optional.of(new Query("1", "Suraj", 5679996579L, "surajr12@gmail.com", "demo"));
        String queryId = "1";
        when(queryRepository.findById(queryId)).thenReturn(retrievedData);
        if (retrievedData.isPresent())
            when(queryRepository.getById(queryId)).thenReturn(query);
        assertEquals(queryId, queryServiceImpl.getQuery(queryId).getQueryId());
    }

    @Test
    public void test_getAllQueries() {
        List<Query> query = new ArrayList<>();
        query.add(new Query("1", "Karan", 9999999999L, "karankumar12@gmail.com", "demo"));
        query.add(new Query("2", "Karan", 9999999999L, "karankumar12@gmail.com", "demo"));
        query.add(new Query("3", "Karan", 9999999999L, "karankumar12@gmail.com", "demo"));
        when(queryRepository.findAll()).thenReturn(query);
        assertEquals(3, queryServiceImpl.getAllQueries().size());
    }

    @Test
    public void test_addQuery() {
        Query query = new Query("1", "Karan", 9999999999L, "karankumar12@gmail.com", "demo");
        when(queryRepository.saveAndFlush(query)).thenReturn(query);
        assertEquals(query, queryServiceImpl.addQuery(query));
    }

    @Test
    public void test_updateQuery() {
        Query query = new Query("1", "Karan", 9999999999L, "karankumar12@gmail.com", "demo");
        Optional<Query> retrievedData = Optional.of(new Query("1", "Suraj", 5679996579L, "surajr12@gmail.com", "demo"));
        String queryId = "1";
        when(queryRepository.findById(queryId)).thenReturn(retrievedData);
        if (retrievedData.isPresent())
            when(queryRepository.saveAndFlush(query)).thenReturn(query);
        assertEquals(query, queryServiceImpl.updateQuery(queryId, query));
    }

    @Test
    public void test_deleteQuery() {
        String queryId = "1";
        queryServiceImpl.deleteQuery(queryId);
        verify(queryRepository, times(1)).deleteById(queryId);
    }

}
