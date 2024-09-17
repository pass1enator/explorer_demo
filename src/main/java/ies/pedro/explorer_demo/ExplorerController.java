package ies.pedro.explorer_demo;

import ies.pedro.explorer_demo.components.ViewItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.util.Callback;
import org.controlsfx.control.GridCell;
import org.controlsfx.control.GridView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;


public class ExplorerController {
    @FXML
    public TextField ruta;
    @FXML
    public GridView grid;
    @FXML
    public Button cargar;
    @FXML
    public Button subir;
    @FXML
    public Button borrar;
    @FXML
    public Button reload;
    @FXML
       private Label welcomeText;
    private ObservableList<File> elementos;
    private ContextMenu contextMenu;
    private ContextMenu contextMenuGrid;
    private FileSystem fs;
    private ItemGridCell current = null;

    private Alert a = new Alert(Alert.AlertType.ERROR, " Error al abrir el directorio");

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void initialize() {

        this.configContextMenu();
        elementos = FXCollections.observableArrayList();

        this.grid.setCellFactory(
                (Callback<GridView<File>, GridCell<File>>) gridView -> {
                    var itemgridcell = new ItemGridCell();
                    itemgridcell.setCM(contextMenu);
                    itemgridcell.setFS(this.fs);
                    //cuando se hace dobleclick en un directorio
                    itemgridcell.setOndoubleclick(path -> {
                        if (path.toFile().isDirectory()) {
                            elementos.clear();
                            this.fs.setActual_path(path);
                            elementos.addAll(this.fs.getFiles());
                            this.ruta.setText(this.fs.getActual_path().toAbsolutePath().toString());
                        }
                    });
                    itemgridcell.setOnclick(path -> {
                        if (this.current != null) {
                            this.current.vi.setUnselect();
                        }
                        this.current = itemgridcell;
                        this.current.vi.setSelected();
                    });
                    return itemgridcell;
                }
        );
        this.grid.setItems(this.elementos);
        this.grid.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY && this.fs.getCache() != null) {
                this.contextMenuGrid.show(this.grid.getScene().getWindow(), mouseEvent.getScreenX(), mouseEvent.getScreenY());

            }

        });

        this.cargar.setOnMouseClicked(e -> {
            if (!this.ruta.getText().isEmpty()) {
                if (this.fs == null) {
                    this.fs = new FileSystem();
                }
                this.fs.setActual_path(Paths.get(this.ruta.getText()));
                this.reload();

            }
        });
        this.subir.setOnMouseClicked(e -> {
            this.fs.update();
            this.reload();

            this.ruta.setText(this.fs.getActual_path().toString());
        });
        this.borrar.setOnMouseClicked(e -> {
            try {
                this.fs.delete(this.current.getItem().getAbsolutePath());
                this.reload();
            } catch (IOException ex) {
                a.setContentText("Error al borrar el fichero");
                a.show();
            }
        });
        this.reload.setOnMouseClicked(mouseEvent -> {
            this.reload();
        });
    }

    private void reload() {
        if (this.elementos != null) {
            this.elementos.clear();
            this.elementos.addAll(this.fs.getFiles());
        }
    }

    private void configContextMenu() {
        contextMenu = new ContextMenu();

        MenuItem item1 = new MenuItem("Copìar");

        MenuItem item2 = new MenuItem("Cortar");


        contextMenu.getItems().addAll(item1, item2);
        contextMenuGrid = new ContextMenu();
        MenuItem item3 = new MenuItem("Pegar");
        item3.setOnAction(actionEvent -> {
            if (this.fs.getCache() != null) {

                try {
                    if (this.fs.getState() == FileSystem.COPY_OR_CUT.COPY) {
                        this.fs.copy();
                    } else if (this.fs.getState() == FileSystem.COPY_OR_CUT.CUT) {
                        this.fs.move();
                    }
                } catch (IOException e) {
                   a.setContentText("Error al copiar/cortar el fichero");
                   a.show();
                }

            }
        });
        contextMenuGrid.getItems().addAll(item3);
    }

    // Clase personalizada para cada celda del GridView
    public static class ItemGridCell extends GridCell<File> {
        private ViewItem vi;
        private ContextMenu cm;
        private FileSystem fs;

        public ItemGridCell() {
            this.vi = new ViewItem();

        }

        public void setCM(ContextMenu cm) {
            this.cm = cm;
        }

        public void setFS(FileSystem fs) {
            this.fs = fs;
        }

        public void setOndoubleclick(Consumer<Path> ondoubleclick) {
            this.vi.setOnDoubleClick(ondoubleclick);
        }

        public void setOnclick(Consumer<Path> onclick) {
            this.vi.setOnClick(onclick);
        }

        @Override
        protected void updateItem(File item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null); // Si no hay elemento, no mostramos nada
            } else {
                this.vi.setItem(item);
                this.vi.setTextLabel(item.getName());
                this.vi.setCm(cm);
                this.vi.loadIcon();
                this.vi.setOncut(file -> {
                    fs.setCache(file, FileSystem.COPY_OR_CUT.CUT);
                });
                this.vi.setOncopy(file -> {
                    fs.setCache(file, FileSystem.COPY_OR_CUT.COPY);
                });
                this.vi.setOnpaste(file -> {
                    if (fs.getState() != null) {
                        try {

                            if (fs.getState() == FileSystem.COPY_OR_CUT.CUT) {
                                fs.move();
                            } else
                                fs.copy();

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

                setGraphic(this.vi);
            }
        }


    }
}