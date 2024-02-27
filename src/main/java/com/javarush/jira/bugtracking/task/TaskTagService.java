package com.javarush.jira.bugtracking.task;

import com.javarush.jira.common.error.DataConflictException;
import com.javarush.jira.login.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskTagService {

    private final TaskTagRepository repository;

    public List<Long> getAllTasksForTagNames(List<String> tags) {
        List<Long> tasks = repository.findAllTasksByTagNames(tags);
        return tasks;
    }

    public List<String> getAllTagsForTask(long id) {
        List<String> taskTags = repository.findAllTagsForTask(id);
        return taskTags;
    }

    public void updateTagsForTask(long id, List<String> taskTags){
        List<TaskTag> oldTaskTags = repository.findAllTaskTagsForTask(id);
        if (oldTaskTags.size() == 0) {
            throw new DataConflictException("Task " + id + " doesn't exist.");
        }
        for (TaskTag taskTag:
            oldTaskTags) {
            repository.delete(taskTag);
        }
        for (String taskTag:
             taskTags) {
            repository.saveAndFlush(new TaskTag(new TaskTagPK(id, taskTag)));
        }
    }

}
