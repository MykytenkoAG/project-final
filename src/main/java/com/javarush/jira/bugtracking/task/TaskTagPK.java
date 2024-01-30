package com.javarush.jira.bugtracking.task;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskTagPK implements Serializable {

        @Column(name = "task_id")
        private long taskId;

        @Column(name = "tag")
        private String tag;

}
