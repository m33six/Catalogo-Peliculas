package mx.com.gm.peliculas.negocio;

import mx.com.gm.peliculas.datos.AccesoDatosImpl;
import mx.com.gm.peliculas.datos.IAccesoDatos;
import mx.com.gm.peliculas.domain.pelicula;
import mx.com.gm.peliculas.excepciones.AccesoDatosEx;
import mx.com.gm.peliculas.excepciones.LecturaDatosEx;

public class CatalogoPeliculas implements ICatalogoPeliculas{
    
    private final IAccesoDatos datos;
    
    public CatalogoPeliculas(){
        this.datos = new AccesoDatosImpl();
    }
    
    @Override
    public void agregarPelicula(String nombrePelicula) {
        pelicula pelicula = new pelicula(nombrePelicula);
        boolean anexar = false;
        try {
            anexar = datos.existe(NOMBRE_RECURSO);
            datos.escribir(pelicula, NOMBRE_RECURSO, anexar);
        } catch (AccesoDatosEx ex) {
            ex.printStackTrace(System.out);
            System.out.println("Error de acceso a datos.");
        }
    }

    @Override
    public void listarPeliculas() {
        try {
            var peliculas = this.datos.listar(NOMBRE_RECURSO);
            for(var pelicula : peliculas){
                System.out.println("pelicula = " + pelicula);
            }
        } catch (AccesoDatosEx ex) {
            ex.printStackTrace(System.out);
            System.out.println("Error de acceso datos");
        }
        
    }

    @Override
    public void buscarPelicula(String buscar) {
        String resultado = null;
        try {
            resultado = this.datos.buscar(NOMBRE_RECURSO, buscar);
        } catch (LecturaDatosEx ex) {
            ex.printStackTrace(System.out);
            System.out.println("Error de acceso datos");
        }
        System.out.println("resultado = " + resultado);
    }

    @Override
    public void iniciarArchivo() {
        try {
            if(this.datos.existe(NOMBRE_RECURSO)){
                datos.borrar(NOMBRE_RECURSO);
                datos.crear(NOMBRE_RECURSO);
            }else{
                datos.crear(NOMBRE_RECURSO);
            }
        } catch (AccesoDatosEx ex) {
            ex.printStackTrace(System.out);
            System.out.println("Error al iniciar catalogo");
        }
    }
}