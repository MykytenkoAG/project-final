package com.javarush.jira.bugtracking.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskTagService {

    private final TaskTagRepository repository;

    public List<Task> getAllTasksForTagNames(List<String> tags) {
        List<Task> tasks = repository.findAllTasksByTagNames(tags);
        return tasks;
    }

    public List<TaskTag> getAllTagsForTask(long id) {
        List<TaskTag> taskTags = repository.findAllTagsForTask(id);
        return taskTags;
    }

    public void updateTagsForTask(long id, List<String> taskTags){
        List<TaskTag> oldTaskTags = repository.findAllTagsForTask(id);
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
