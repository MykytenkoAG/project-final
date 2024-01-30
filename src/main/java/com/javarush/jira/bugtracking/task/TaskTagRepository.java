package com.javarush.jira.bugtracking.task;

import com.javarush.jira.common.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface TaskTagRepository extends BaseRepository<TaskTag> {

    @Query(value = "SELECT DISTINCT t FROM Task t JOIN TaskTag tt ON t.id=tt.taskTagPK.taskId WHERE tt.taskTagPK.tag IN (:taskTags)")
    List<Task> findAllTasksByTagNames(List<String> taskTags);

    @Query("SELECT tt FROM Task t JOIN TaskTag tt ON t.id=tt.taskTagPK.taskId WHERE t.id=:taskId")
    List<TaskTag> findAllTagsForTask(long taskId);

}
