package com.javarush.jira.bugtracking.task;

import com.javarush.jira.bugtracking.Handlers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskTagService {

    private final TaskTagRepository repository;
    private final Handlers.TaskExtHandler handler;

    public List<Long> getAllTasksForTagNames(List<String> tags) {
        List<Long> tasks = repository.findAllTasksByTagNames(tags);
        return tasks;
    }

    public List<String> getAllTagsForTask(long id) {
        Task task = handler.getRepository().getExisted(id);
        List<String> taskTags = repository.findAllTagsForTask(id);
        return taskTags;
    }

    public void updateTagsForTask(long id, List<String> taskTags){
        Task task = handler.getRepository().getExisted(id);
        List<TaskTag> oldTaskTags = repository.findAllTaskTagsForTask(id);
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
