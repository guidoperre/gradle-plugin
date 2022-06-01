package com.gperre.jopit.android.gradle.base.publish

import org.gradle.api.Task
import org.gradle.api.tasks.TaskProvider

import java.text.SimpleDateFormat

abstract class PublishTask {

    public static final String TASK_GROUP = 'publishing'

    abstract TaskProvider<Task> register(Builder builder)

    protected static String getTimestamp() {
        def sdf = new SimpleDateFormat('yyyyMMddHHmmss')
        sdf.timeZone = TimeZone.getTimeZone('UTC')
        return sdf.format(new Date())
    }
}
