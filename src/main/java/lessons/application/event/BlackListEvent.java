package lessons.application.event;

import org.springframework.context.ApplicationEvent;

/**
 * Custom aapliction event
 */
public class BlackListEvent extends ApplicationEvent {
    private final String address;
    private final String test;

    public BlackListEvent(Object source, String address, String test) {
        super(source);
        this.address = address;
        this.test = test;
    }
}
