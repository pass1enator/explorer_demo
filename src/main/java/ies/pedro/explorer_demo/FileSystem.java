package ies.pedro.explorer_demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

public class FileSystem {


    public enum COPY_OR_CUT{
        COPY,
        CUT
    }

    public FileSystem() {

    }

    public COPY_OR_CUT getState() {
        return COPY_OR_CUT.CUT;
    }
    /**
     *   devuelve el path actual
     */

    public Path getActual_path() {
        return null;
    }

    /**
     * asigna el pat actual
     * @param actual_path
     */
    public void setActual_path(Path actual_path) {

    }

    /**
     * Asigna el path actual como cadena
     * @param actual_path
     */
    public void setActual_path(String actual_path) {

    }

    /**
     * indica el fichero que se encuentra el la cache y si ha sido para cortar o pergar
     * @param cache
     * @param c
     */
    public void setCache(File cache, COPY_OR_CUT c) {

    }
    public void setSelected(File selected) {

    }
    public File getCache(){
        return null;
    }

    /**
     * se borra la cache del archivo
     */
    private void unsetCache(){

    }
    public File getSelected() {
       return null;
    }

    /**
     * devuelve un listado de ficheros del path actual
     * @return
     */
    public File[] getFiles() {

        File[] files= new File[0];

        return files;
    }

    /**
     * devuelve el listado de los ficheros como cadens
     * @return
     */
    public String[] getFilesName() {
       return new String[0];
    }

    /**
     * Sube de nivel en el path actual
     */
    public void update() {

    }

    public void createDirectory(String name) throws IOException {

    }

    public void delete(String name) throws IOException {

    }

    public void copy() throws IOException {
   }
    public void move() throws IOException {
    }

}
