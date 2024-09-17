package ies.pedro.explorer_demo.components;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;
import java.nio.file.Path;
import java.util.function.Consumer;

public class ViewItem extends VBox {
    private File item;
    private Label label;


    private FontIcon icon;

    private Consumer<Path> onClick;
    private Consumer<Path> onDoubleClick;

    private Consumer<File> oncopy;
    private Consumer<File> oncut;
    private Consumer<File> onpaste;
    private ContextMenu cm;
    private ContextMenu ctm;

    public ViewItem() {
        super();
        this.item = null;
        this.label = new Label();


    }

    public void setCm(ContextMenu cm) {
        this.cm = cm;
    }
    public void setOncopy(Consumer<File> oncopy) {
        this.oncopy=oncopy;
    }
    public void setOncut(Consumer<File> oncut) {
        this.oncut = oncut;
    }
    public void setOnpaste(Consumer<File> onpaste) {
        this.onpaste = onpaste;
    }
    public void setOnDoubleClick(Consumer<Path> onDoubleClick) {
        this.onDoubleClick = onDoubleClick;
    }

    private void init() {
        this.label = new Label(item.getName());
        this.label.setOnMouseClicked(mouseEvent -> {
            if (this.onClick != null) {
                this.onClick.accept(item.toPath());
            }
        });
        this.icon = new FontIcon();
        this.getChildren().add(this.label);
    }

    private String getIcon(String filename) {

        String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        //para los directorios
        if(extension.equals(filename) || !filename.contains(".") ) {
            extension="";
        }

        String vuelta = "";
        switch (extension) {
            case "pdf":
                vuelta = ICONS.PDF.getName();
                break;
            case "doc":
                vuelta = ICONS.DOC.getName();
                break;
            case "":
                vuelta = ICONS.DIRECTORY.getName();
                break;
            case "txt":
                vuelta = ICONS.FILE.getName();
            case "png":
            case "jpg":
            case "jpeg":
            case "gif":
            case "bmp":
                vuelta = ICONS.IMAGE.getName();
                break;
            default:
                vuelta = ICONS.DEFAULT.getName();
        }

        return vuelta;
    }

    public File getItem() {
        return item;
    }

    public void setItem(File item) {

        this.item = item;
    }

    public Label getLabel() {
        return label;
    }

    public void setTextLabel(String label) {
        this.getChildren().remove(this.label);
        this.label = new Label(label);
        this.getChildren().add(this.label);

    }

    public FontIcon getIcon() {
        return icon;
    }

    public void setIcon(FontIcon icon) {
        this.icon = icon;
    }

    public void loadIcon() {

        if (this.icon != null) {
            this.getChildren().remove(this.icon);
        }
        this.icon = new FontIcon();
        this.icon.setIconLiteral(this.getIcon(this.item.getName()));
        this.icon.setIconSize(64);
        this.icon.setIconColor(Color.GREEN);
        this.icon.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                if (mouseEvent.getClickCount() == 2) {
                    this.onDoubleClick.accept(item.toPath());
                } else {
                    if (this.onClick != null) {
                        this.onClick.accept(item.toPath());
                    }
                }
            } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                if (this.cm != null) {
                    cm.getItems().get(0).setOnAction(actionEvent -> {
                        if(this.oncopy!=null)
                            this.oncopy.accept(this.item);
                    });
                    cm.getItems().get(1).setOnAction(actionEvent -> {
                        if(this.oncut!=null)
                            this.oncut.accept(this.item);
                    });


                    cm.show(this.getScene().getWindow(), mouseEvent.getScreenX(), mouseEvent.getScreenY());
                    mouseEvent.consume();
                }
            }

        });

        this.setOnMouseClicked(mouseEvent -> {
            if (this.onClick != null) {
                this.onClick.accept(item.toPath());
            }
        });
        this.getChildren().addFirst(this.icon);
    }

    public Consumer<Path> getOnClick() {
        return onClick;
    }

    public void setOnClick(Consumer<Path> onClick) {
        this.onClick = onClick;
    }

    public ContextMenu getCtm() {
        return ctm;
    }

    public void setCtm(ContextMenu ctm) {
        this.ctm = ctm;
    }

    public void setUnselect() {
        this.setStyle("");
    }

    public void setSelected() {
        this.setStyle("-fx-background-color: #6565cc;");
    }

}
