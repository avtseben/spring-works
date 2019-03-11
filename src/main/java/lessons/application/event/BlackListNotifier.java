package lessons.application.event;

import org.springframework.context.ApplicationListener;

/**
 * Custom notifier
 */
public class BlackListNotifier implements ApplicationListener<BlackListEvent> {

    private String notificationAddress;

    public void setNotificationAddress(String notificationAddress) {
        this.notificationAddress = notificationAddress;
    }

    public void onApplicationEvent(BlackListEvent event) {
        // уведомляются заинтересованные участники в notificationAddress...
    }
}
