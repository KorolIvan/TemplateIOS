package korol.ivan.helpers;

/**
 * @author by Ivan Korol on 6/20/2017.
 */
public class ToastMessages {

    private String screenName;
    private String toastName;
    private String language;

    public ToastMessages(String screenName, String toastName, String language) {
        this.screenName = screenName;
        this.toastName = toastName;
        this.language = language;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getToastName() {
        return toastName;
    }

    public void setToastName(String toastName) {
        this.toastName = toastName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
