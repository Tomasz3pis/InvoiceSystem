package pl.futurecollars.invoice.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class IdGenerator {

    private AtomicLong currentId = new AtomicLong(1L);

    public synchronized Long getId() {
        return currentId.getAndIncrement();
    }
}
