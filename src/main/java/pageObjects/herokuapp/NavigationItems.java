package pageObjects.herokuapp;

public enum NavigationItems {
    DYNAMIC_LOADING("Dynamic Loading"),
    INFINITE_SCROLL("Infinite Scroll"),
    CONTEXT_MENU("Context Menu"),
    FRAMES("Frames"),
    DYNAMIC_CONTROLS("Dynamic Controls"),
    FILE_UPLOAD("File Upload"),
    FILE_DOWNLOAD("File Download"),
    ADD_REMOVE_ELEMENTS("Add/Remove Elements"),
    INPUTS("Inputs"),
    DROPDOWN_LIST("Dropdown"),
    DRAG_AND_DROP("Drag and Drop"),
    MULTIPLE_WINDOWS("Multiple Windows"),
    DATA_TABLES("Sortable Data Tables");

    private String item;

    NavigationItems(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }
}
