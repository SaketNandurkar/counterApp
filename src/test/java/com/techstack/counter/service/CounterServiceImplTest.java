package com.techstack.counter.service;

import com.techstack.counter.model.Counter;
import com.techstack.counter.service.impl.CounterServiceImpl;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CounterServiceImplTest {

    @InjectMocks
    CounterService counterService = new CounterServiceImpl();

    @Mock
    Optional optional;

    @Mock
    Predicate predicate;

    @Mock
    Counter counter;

    String counterName = "test";

    @Test
    public void testGetAllCounters(){
        assertNotNull(counterService.getAllCounters());
    }

    // As there is no data to get (as we are not using any database), asserting to null.
    @Test
    public void testGetCounter(){
        assertEquals(counterService.getCounter(counterName), null);
    }

    // Adding a counter by creating it explicitly
    @Test
    public void testAddCounter(){
        Counter counter = new Counter();
        counter.setCounterName("test");
        counter.setCounterValue(0);
        assertNotNull(counterService.addCounter(counter));
    }

    // As there is no data to delete (as we are not using any database), asserting to null.
    @Test
    public void testDeleteCounter(){
        assertEquals(counterService.deleteCounter(counterName), null);
    }

    // As there is no data to update (as we are not using any database), asserting to null.
    @Test
    public void testUpdateCounter(){
        Counter counter1 = Mockito.mock(Counter.class);
        doNothing().when(counter1).setCounterValue(anyInt());
        when(counter1.getCounterValue()).thenReturn(anyInt());
        assertEquals(counterService.updateCounter(counterName), null);
    }

}
