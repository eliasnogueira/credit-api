/*
 * MIT License
 *
 * Copyright (c) 2023 Elias Nogueira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.eliasnogueira.credit.service;

import com.eliasnogueira.credit.entity.EventType;
import com.eliasnogueira.credit.entity.EventStore;
import com.eliasnogueira.credit.entity.Restriction;
import com.eliasnogueira.credit.repository.EventRepository;
import java.util.Optional;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("eventService")
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Async
    public void addEvent(String cpf, EventType eventType) {
        EventStore eventStore = null;

        try {
            eventStore = new EventStore();
            eventStore.setCpf(cpf);
            eventStore.setEventType(eventType);

            Thread.sleep(7000); // intentionally add to add a delay to log the event

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        eventRepository.save(eventStore);
    }

    public Optional<EventStore> findByCpf(String cpf) {
        return eventRepository.findByCpf(cpf);
    }
}
