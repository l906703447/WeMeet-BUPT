package com.wemeet.wemeet.repository;

import com.wemeet.wemeet.entity.CatcherBugKey;
import com.wemeet.wemeet.entity.CatcherBugRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xieziwei99
 * 2020-02-23
 */
public interface CatcherBugRecordRepo extends JpaRepository<CatcherBugRecord, CatcherBugKey> {

}
