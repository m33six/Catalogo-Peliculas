package mx.com.gm.peliculas.datos;

import java.util.List;
import mx.com.gm.peliculas.domain.pelicula;
import mx.com.gm.peliculas.excepciones.*;

public interface IAccesoDatos {
    public static final int MAX_PELICULAS = 30;
    
    public boolean existe(String nombreArchivo) throws AccesoDatosEx;
    
    public List<pelicula> listar(String nombre) throws LecturaDatosEx;
    
    public void escribir(pelicula pelicula, String nombreArchivo, boolean anexar) throws EscrituraDatosEx;
    
    public String buscar(String nombreArchivo, String buscar) throws LecturaDatosEx;
    
    public void crear(String nombreArchivo) throws AccesoDatosEx;
    
    public void borrar(String nombreArchivo) throws AccesoDatosEx;
}
