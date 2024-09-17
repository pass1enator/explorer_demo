# Práctica 1.

## Sequeros Explorer.

A partir de la parte gráfica que se proporciona, completar los métodos de la clase
FileSystem de forma que se pueda interacturar como si de un explorador de archivos se tratara.

``` Java


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
```

Se ha de investigar y utilizar las librerías java.io, nio y nio2.

En el programa principal se tiene un atributo de tipo FileSystem, con el cual la interfaz gráfica interactura:

``` Java

public class ExplorerController {
    ....
    
    private FileSystem fs;
    private ItemGridCell current = null;
    ...

```
En el cuadro de texto se introduce la primera carpeta con la que trabajar.
Los botones superiores cargan la lista d e ficheros, sube de nivel, borra el fichero seleccionado y recarga el grid.

Además se tiene un menú contextual para copiar, cortar y pegar.

[!imagen](https://github.com/pass1enator/explorer_demo/blob/master/images/ejemplo.png)
