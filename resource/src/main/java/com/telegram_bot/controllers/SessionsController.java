package com.telegram_bot.controllers;

import com.telegram_bot.entity.Sessions;
import com.telegram_bot.repository.SessionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Greg on 10/12/16.
 */
@RestController
@RequestMapping("/sessions")
public class SessionsController {

    @Autowired
    private SessionsRepository sessionsRepository;

    public SessionsController(SessionsRepository sessionsRepository) {
        this.sessionsRepository = sessionsRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Sessions> findAllSessions(){
        return sessionsRepository.findAll();
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Sessions> addSession(@RequestBody Sessions sessions){
        sessionsRepository.save(sessions);
        return sessionsRepository.findAll();
    }

    @RequestMapping(value = "/delete/{idsession}", method = RequestMethod.POST)
    public List<Sessions> removeSession(@PathVariable("idsession") int idsession){
        sessionsRepository.delete(idsession);
        return sessionsRepository.findAll();
    }

    @RequestMapping(value = "/update/{idsession}", method = RequestMethod.PATCH,consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Sessions> updateSession(@PathVariable("idsession") int idsession, @RequestBody Sessions sessions){
        Sessions update = sessionsRepository.findOne(idsession);
        update.setIdSession(sessions.getIdSession());
        update.setIdCinema(sessions.getIdCinema());
        update.setIdMovie(sessions.getIdMovie());
        update.setPrice(sessions.getPrice());
        update.setTime(sessions.getTime());
        update.setIdFormat(sessions.getIdFormat());
        sessionsRepository.save(update);
        return sessionsRepository.findAll();
    }

}
