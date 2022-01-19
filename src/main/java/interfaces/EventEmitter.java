package interfaces;

import java.util.LinkedList;

public abstract class EventEmitter<Event> {
    protected final LinkedList<EventListener<Event>> eventListeners = new LinkedList<>();

    public void addListener(EventListener<Event> listener) {
        eventListeners.add(listener);
    }

    public void removeListener(EventListener<Event> listener) {
        eventListeners.remove(listener);
    }

    public void emit(Event event) {
        for (EventListener<Event> listener : eventListeners) {
            listener.onEvent(event);
        }
    }
}
