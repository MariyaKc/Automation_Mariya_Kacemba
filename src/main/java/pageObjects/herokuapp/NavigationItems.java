package pageObjects.herokuapp;

public enum NavigationItems {
    DYNAMIC_LOADING("Dynamic Loading"),
    INFINITE_SCROLL("Infinite Scroll"),
    CONTEXT_MENU("Context Menu"),
    FRAMES("Frames"),
    DYNAMIC_CONTROLS("Dynamic Controls"),
    FILE_UPLOAD("File Upload"),
    FILE_DOWNLOAD("File Download");

    private String item;

    NavigationItems(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }
}
