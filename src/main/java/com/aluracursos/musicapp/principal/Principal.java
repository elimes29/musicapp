package com.aluracursos.musicapp.principal;

import com.aluracursos.musicapp.modelo.Cancion;
import com.aluracursos.musicapp.modelo.Cantante;
import com.aluracursos.musicapp.modelo.Genero;
import com.aluracursos.musicapp.repository.CantanteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private CantanteRepository repositorio;

    public Principal(CantanteRepository repository) {
        this.repositorio = repository;
    }

    public void correApp(){


        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Registrar datos de cantantes
                    2 - Registrar datos de canciones
                    3 - Buscar canciones por cantantes
       
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    registrarCantante();
                    break;
                case 2:
                    registrarCancion();
                    break;
                case 3:
                    buscaCancionPorCantante();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }


    }

    private void registrarCantante() {
       //Pedidmos datos para crear cantante
        System.out.println("Ingrese el nombre del cantante");
        String nombre = teclado.nextLine();
        System.out.println("Ingrese el genero principal");
        String genero = teclado.nextLine();
        Genero enumGenero = Genero.generoTeclado(genero);

        //Creamos el cantante
        Cantante cantante = new Cantante();
        cantante.setNobre(nombre);
        cantante.setGenero(enumGenero);

        //Impactamos en Base de Datos
        repositorio.save(cantante);

    }
    private void registrarCancion() {
        //Pedidmos datos para crear cancion
        System.out.println("Ingrese el nombre de la canción");
        String nombre = teclado.nextLine();
        System.out.println("Ingrese duracion en seg");
        Integer duracion = teclado.nextInt();
        teclado.nextLine();

        muestraCantantes();
        System.out.println("Ingrese nombre del cantante de esta canción");
        String nombreCantante = teclado.nextLine();
        System.out.println("Cantante ingresado "+ nombreCantante);

        //buscamos cantante por nombre
        Optional<Cantante> cantanteRecibido = repositorio.getFirstCantanteByNombreContainsIgnoreCase(nombreCantante);

        if (cantanteRecibido.isPresent()){
            Cantante cantante = cantanteRecibido.get();
            Cancion cancion = new Cancion();
            cancion.setNombre(nombre);
            cancion.setDuracionEnSegundos(duracion);
            cancion.setCantante(cantante);
            List<Cancion> canciones = cantante.getCanciones();
            canciones.add(cancion);
            cantante.setCanciones(canciones);
            repositorio.save(cantante);
        }


    }

    private void muestraCantantes(){
        List<Cantante> cantantes = repositorio.findAll();
        if (cantantes != null) {
            cantantes.stream()
                    .map(Cantante::getNombre)
                    .forEach(System.out::println);
    }
}


    private void buscaCancionPorCantante() {
        muestraCantantes();
        System.out.println("Ingrese nombre del cantante a buscar sus canciones");
        String nombreCantante = teclado.nextLine();
        System.out.println("Cantante ingresado "+ nombreCantante);

        Optional<Cantante> cantanteRecibido = repositorio.getFirstCantanteByNombreContainsIgnoreCase(nombreCantante);

        if (cantanteRecibido.isPresent()){
            Cantante cantante = cantanteRecibido.get();
           //Mostramos canciones
            List<Cancion> canciones = cantante.getCanciones();
            System.out.println(canciones);
            canciones.stream()
                    .map(c -> "Cancion: " + c.getNombre() + " / la canta: " +  c.getCantante().getNombre())
                    .forEach(System.out::println);
        }else {
            System.out.println("No encontre cantante");
        }


    }


}
