package mx.com.gm.peliculas.datos;

import java.io.*;
import java.util.*;
import mx.com.gm.peliculas.domain.pelicula;
import mx.com.gm.peliculas.excepciones.*;

public class AccesoDatosImpl implements IAccesoDatos {
//implementacion

    @Override
    public boolean existe(String nombreArchivo) throws AccesoDatosEx {
        File archivo = new File(nombreArchivo);
        return archivo.exists();
    }

    @Override
    public List<pelicula> listar(String nombreArchivo) throws LecturaDatosEx {
        File archivo = new File(nombreArchivo);
        List<pelicula> peliculas = new ArrayList();
        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = entrada.readLine();
            while (linea != null) {
                pelicula pelicula = new pelicula(linea);
                peliculas.add(pelicula);
                linea = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEx("Excepcion alistar peliculas: " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEx("Excepcion alistar peliculas: " + ex.getMessage());
        }
        return peliculas;
    }

    @Override
    public void escribir(pelicula pelicula, String nombreArchivo, boolean anexar) throws EscrituraDatosEx {
        var archivo = new File(nombreArchivo);
        try {
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(pelicula.toString());
            salida.close();
            System.out.println("Se ha escrito informacion al archivo: " + pelicula);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new EscrituraDatosEx("Excepcion al escribir peliculas: " + ex.getMessage());
        }

    }

    @Override
    public String buscar(String nombreArchivo, String buscar) throws LecturaDatosEx {
        var archivo = new File(nombreArchivo);
        String resultado;
        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            String linea;
            linea = entrada.readLine();
            var indice = 1;
            while (linea != null) {
                if (buscar != null && buscar.equalsIgnoreCase(linea)) {
                    resultado = "Pelicula " + linea + " encontrada en el indice " + indice;
                    break;
                }
                linea = entrada.readLine();
                indice++;
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEx("Excepcion al buscar peliculas: " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEx("Excepcion al buscar peliculas: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public void crear(String nombreArchivo) throws AccesoDatosEx {
        var archivo = new File(nombreArchivo);
        try {
            var salida = new PrintWriter(new FileWriter(archivo));
            salida.close();
            System.out.println("Se ha creado el archivo");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new AccesoDatosEx("Excepcion al crear peliculas: " + ex.getMessage());
        }
    }

    @Override
    public void borrar(String nombreArchivo) {
        var archivo = new File(nombreArchivo);
        if (archivo.exists()) {
            archivo.delete();
        }
        System.out.println("Se ha eliminado correctamente");
    }
}