package com.wemeet.wemeet.repository;

import com.wemeet.wemeet.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xieziwei99
 * 2019-11-28
 */
public interface UserRepo extends JpaRepository<User, Long> {

}
