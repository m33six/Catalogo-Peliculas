package cpjlaboratoriofinal;

import java.util.Scanner;
import mx.com.gm.peliculas.negocio.*;

public class CPJLaboratorioFinal {
    public static void main(String[] args) {
        Scanner consola = new Scanner(System.in);
        int opcion = -1;
        String nombreArchivo;
        ICatalogoPeliculas catalogo = new CatalogoPeliculas();
        
        while(opcion !=0){
            System.out.println("Elige una opcion:"
                + "\n1.Iniciar catalogo de peliculas"
                + "\n2.Agregar pelicula"
                + "\n3.Listar peliculas"
                + "\n4.Buscar pelicula"
                + "\n0.Salir");
            opcion = Integer.parseInt(consola.nextLine());
            
            switch(opcion){
                case 1 -> catalogo.iniciarArchivo();
                case 2 -> {
                    System.out.println("Introduce el nombre de la pelicula");
                    var nombrePelicula = consola.nextLine();
                    catalogo.agregarPelicula(nombrePelicula);
                }
                case 3 -> catalogo.listarPeliculas();
                case 4 -> {
                    System.out.println("Introduce la pelicula que quieras buscar");
                    var buscar = consola.nextLine();
                    catalogo.buscarPelicula(buscar);
                }
                case 0 -> System.out.println("Hasta pronto!");
                default -> System.out.println("Opcion no reconocida");
            }
        }
    }
}
