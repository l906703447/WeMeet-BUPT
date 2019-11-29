package com.wemeet.wemeet.repository;

import com.wemeet.wemeet.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;

/**
 * @author xieziwei99
 * 2019-11-28
 */
public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmail(@NotNull String email);
}
