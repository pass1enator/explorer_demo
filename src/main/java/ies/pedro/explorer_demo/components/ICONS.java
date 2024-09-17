package ies.pedro.explorer_demo.components;

public enum ICONS {
    DIRECTORY("bi-folder2"),
    FILE("bi-file"),
    TRASH("bi-trash"),
    UPLOAD("bi-arrow-bar-up"),
    DOC("file-microsoft-word"),
    PDF("file-adobe-acrobat"),
    TXT("bi-file-earmark-text"),
    DEFAULT("bi-file-earmark"),
    IMAGE("antf-picture");
    private String name;
    ICONS(String name) {
        this.name=name;
    }
    public String getName() {
        return this.name;
    }

}
