package com.telegram_bot.repository;

import com.telegram_bot.entity.Sessions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Greg on 9/10/16.
 */
@Repository
public interface SessionsRepository extends JpaRepository<Sessions,Integer> {

}
