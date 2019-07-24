package com.xzw.goldbug2.repository;

import com.xzw.goldbug2.entity.BugProperty;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author xieziwei99
 * 2019-07-13
 */
public interface BugPropertyRepo extends JpaRepository<BugProperty, Long> {

    // Between - findByStartDateBetween … where x.startDate between ?1 and ?2
    List<BugProperty> findByStartLongitudeBetweenAndStartLatitudeBetween
            (@NotNull Double startLongitude, @NotNull Double startLongitude2,
             @NotNull Double startLatitude, @NotNull Double startLatitude2);
}
