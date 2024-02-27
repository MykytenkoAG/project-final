package com.javarush.jira.bugtracking.task;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "task_tag")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskTag {

    @EmbeddedId
    private TaskTagPK taskTagPK;

}
