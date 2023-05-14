package net.nutchi.splatoonmc.model;

public class SidebarObject {
    private final String display;
    private final String objectName;
    private final String entry;

    public SidebarObject(String display, String objectName, String entry) {
        this.display = display;
        this.objectName = objectName;
        this.entry = entry;
    }

    public String getDisplay() {
        return display;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getEntry() {
        return entry;
    }
}
