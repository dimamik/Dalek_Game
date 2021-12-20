package interfaces;

import java.util.LinkedList;

public abstract class EventEmitter<Event> {
    protected LinkedList<EventListener<Event>> eventListeners = new LinkedList<>();

    public abstract void addListener(EventListener<Event> listener);

    public abstract void removeListener(EventListener<Event> listener);

    public void emit(Event event) {
        for (EventListener<Event> listener : eventListeners) {
            listener.onEvent(event);
        }
    }
}
