package gr.qa.pages.herokuapp.enums;

public enum HerokuTestPagesEnum {

    AB_TESTING("A/B Test Control", "https://the-internet.herokuapp.com/abtest"),
    ADD_REMOVE_ELEMENTS("Add/Remove Elements", "https://the-internet.herokuapp.com/add_remove_elements/"),
    BASIC_AUTH("Basic Auth", "https://the-internet.herokuapp.com/basic_auth"),
    BROKEN_IMAGES("Broken Images", "https://the-internet.herokuapp.com/broken_images"),
    CHALLENGING_DOM("Challenging DOM", "https://the-internet.herokuapp.com/challenging_dom"),
    CHECKBOXES("Checkboxes", "https://the-internet.herokuapp.com/checkboxes"),
    CONTEXT_MENU("Context Menu", "https://the-internet.herokuapp.com/context_menu"),
    DIGEST_AUTHENTICATION("Digest Authentication (user and pass: admin)", "https://the-internet.herokuapp.com/digest_auth"),
    DISAPPEARING_ELEMENTS ("Disappearing Elements", "https://the-internet.herokuapp.com/disappearing_elements"),
    DRAG_AND_DROP("Drag and Drop", "https://the-internet.herokuapp.com/drag_and_drop"),
    DROPDOWN("Dropdown List", "https://the-internet.herokuapp.com/dropdown"),
    DYNAMIC_CONTENT("Dynamic Content", "https://the-internet.herokuapp.com/dynamic_content"),
    DYNAMIC_CONTROLS("Dynamic Controls", "https://the-internet.herokuapp.com/dynamic_controls"),
    DYNAMIC_LOADING("Dynamically Loaded Page Elements", "https://the-internet.herokuapp.com/dynamic_loading"),
    ENTRY_AD("Entry Ad", "https://the-internet.herokuapp.com/entry_ad"),
    EXIT_INTENT("Exit Intent", "https://the-internet.herokuapp.com/exit_intent"),
    FILE_DOWNLOAD("File Downloader", "https://the-internet.herokuapp.com/download"),
    FILE_UPLOAD("File Uploader", "https://the-internet.herokuapp.com/upload"),
    FLOATING_MENU("Floating Menu", "https://the-internet.herokuapp.com/floating_menu"),
    FORGOT_PASSWORD("Forgot Password", "https://the-internet.herokuapp.com/forgot_password"),
    FORM_AUTHENTICATION("Login Page", "https://the-internet.herokuapp.com/login"),
    FRAMES("Frames", "https://the-internet.herokuapp.com/frames"),
    GEOLOCATION("Geolocation", "https://the-internet.herokuapp.com/geolocation"),
    HORIZONTAL_SLIDER("Horizontal Slider", "https://the-internet.herokuapp.com/horizontal_slider"),
    HOVERS("Hovers", "https://the-internet.herokuapp.com/hovers"),
    INFINITE_SCROLL("Infinite Scroll", "https://the-internet.herokuapp.com/infinite_scroll"),
    INPUTS("Inputs", "https://the-internet.herokuapp.com/inputs"),
    JQUERY_UI_MENUS("JQueryUI - Menu", "https://the-internet.herokuapp.com/jqueryui/menu"),
    JAVASCRIPT_ALERTS("JavaScript Alerts", "https://the-internet.herokuapp.com/javascript_alerts"),
    JAVASCRIPT_ON_LOAD_EVENT_ERROR("-", "https://the-internet.herokuapp.com/javascript_error"),
    KEY_PRESSES("Key Presses", "https://the-internet.herokuapp.com/key_presses"),
    LARGE_AND_DEEP_DOM("Large & Deep DOM", "https://the-internet.herokuapp.com/large"),
    MULTIPLE_WINDOWS("Opening a new window", "https://the-internet.herokuapp.com/windows"),
    NESTED_FRAMES("-", "https://the-internet.herokuapp.com/nested_frames"),
    NOTIFICATION_MESSAGES("Notification Message", "https://the-internet.herokuapp.com/notification_message"),
    REDIRECT_LINK("Redirection", "https://the-internet.herokuapp.com/redirector"),
    SECURE_FILE_DOWNLOAD("Secure File Downloader", "https://the-internet.herokuapp.com/download_secure"),
    SHADOW_DOM("Simple template", "https://the-internet.herokuapp.com/download_secure"),
    SHIFTING_CONTENT("Shifting Content", "https://the-internet.herokuapp.com/shifting_content"),
    SLOW_RESOURCES("Slow Resources", "https://the-internet.herokuapp.com/slow"),
    SORTABLE_DATA_TABLES("Data Tables", "https://the-internet.herokuapp.com/tables"),
    STATUS_CODE("Status Codes", "https://the-internet.herokuapp.com/status_codes"),
    TYPOS("Typos", "https://the-internet.herokuapp.com/typos"),
    WYSIWYG_EDITOR("An iFrame containing the TinyMCE WYSIWYG Editor", "https://the-internet.herokuapp.com/tinymce"),
    ;

    private final String title;
    private final String url;

    HerokuTestPagesEnum(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
