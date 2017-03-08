package com.telegram_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.telegram_bot.entity.Users;

/**
 * Created by Greg on 9/10/16.
 */
@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

//    @Autowired
//    @Qualifier("spring.datasource_users")
//
//    /**
//     * NOT USED
//     * @param state
//     * @param idusers
//     */
//    @Modifying(clearAutomatically = true)
//    @Transactional
//    @Query("update UsersEntity u set u.state = ?1 where u.idusers = ?2")
//    void setStateFor(int state, int idusers);

}
