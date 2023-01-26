package com.techstack.counter.service.impl;

import com.techstack.counter.model.Counter;
import com.techstack.counter.service.CounterService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CounterServiceImpl implements CounterService {

    private List<Counter> counters = createList();
    @Override
    public List<Counter> getAllCounters() {
        return counters;
    }

    @Override
    public Counter getCounter(String counterName) {
        return counters.stream().filter(counter -> counterName.equals(counter.getCounterName()))
                .findAny().orElse(null);
    }

    @Override
    public Boolean addCounter(Counter counterToAdd) {
        String existingConName = counterToAdd.getCounterName();
        long con = counters.stream().filter(counter -> existingConName.equals(counter.getCounterName()))
                .count();
        if(con==0) {
            counters.add(counterToAdd);
            return true;
        }
        return false;
    }

    @Override
    public Counter deleteCounter(String counterName) {
        Counter counterToRemove = null;
        /*for (Counter con : counters) {
            if (con.getCounterName().equals(counterName)) {
                counters.remove(con);
                counter = con;
                break;
            }
        }*/
        counterToRemove = counters.stream().filter(counter -> counterName.equals(counter.getCounterName()))
                .findAny().orElse(null);
        counters.remove(counterToRemove);
        return counterToRemove;
    }

    @Override
    public Counter updateCounter(String counterName) {
        Counter con = counters.stream().filter(counter -> counterName.equals(counter.getCounterName()))
                .findAny().orElse(null);
        if(con!=null) {
            con.setCounterValue(con.getCounterValue() + 1);
        }
        return con;
    }

    private static List<Counter> createList() {
        List<Counter> tempCounter = new ArrayList<>();
        return tempCounter;
    }
}
