package com.techstack.counter.controller;

import com.techstack.counter.model.Counter;
import com.techstack.counter.service.CounterService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CounterController {

    @Autowired
    CounterService counterService;

    @RequestMapping(value = "/counters", method = RequestMethod.GET, produces = "application/json")
    public List<Counter> getAllCounters() {
        return counterService.getAllCounters();
    }

    @RequestMapping(value = "/{counterName}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Counter getCounter(@RequestParam String counterName){
        return counterService.getCounter(counterName);
    }

    @DeleteMapping(path = { "/{counterName}" })
    public ResponseEntity delete(@RequestParam String counterName) {
        if(counterService.deleteCounter(counterName)!=null) {
            return ResponseEntity.status(HttpStatus.OK).body("Data has been deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Data not found");
    }

    @PostMapping(path = { "/counters" })
    public ResponseEntity create(@RequestBody Counter counterToAdd) {
        if(counterService.addCounter(counterToAdd)){
            return ResponseEntity.ok().body("Counter Created");
        }else{
            return ResponseEntity.badRequest().body("Counter is present already ");
        }
    }

    @PutMapping(path = {"/{counterName}"})
    public ResponseEntity update(@RequestParam String counterName) {
        if(counterService.updateCounter(counterName)!=null){
            return ResponseEntity.status(HttpStatus.OK).body("Counter has been incremented");
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Data not found");
        }
    }

}
