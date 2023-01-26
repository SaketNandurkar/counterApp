package com.techstack.counter.service;

import com.techstack.counter.model.Counter;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CounterService {

    public List<Counter> getAllCounters();

    public Counter getCounter(String counterName);

    public Boolean addCounter(Counter counter);

    public Counter deleteCounter(String counterName);

    public Counter updateCounter(String counterName);

}
