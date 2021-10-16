package org.monki.taskmanager.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum State {
    PLANNED,
    WORK_IN_PROGRESS,
    DONE,
    CANCELLED;

    public static Set<State> getAllowedStates(State currentState) {
        if (currentState == null) {
            throw new IllegalArgumentException("Current state cannot be null");
        }
        Set<State> allowedStates = new HashSet<>();
        switch (currentState) {
            case PLANNED: {
                allowedStates.add(WORK_IN_PROGRESS);
                break;
            }
            case WORK_IN_PROGRESS: {
                allowedStates.add(DONE);
                allowedStates.add(CANCELLED);
                break;
            }
            default:
                break;
        }
        return Collections.unmodifiableSet(allowedStates);
    }
}
