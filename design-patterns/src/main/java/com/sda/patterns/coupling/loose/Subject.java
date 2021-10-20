package com.sda.patterns.coupling.loose;

import com.sda.patterns.coupling.tight.Topic;

public class Subject {

    private Topic topic;

    public Subject(Topic topic) {
        this.topic = topic;
    }

    public void startReading() {
        topic.understand();
    }
}
