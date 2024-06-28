package com.laqf.Litelalura.principal;

import com.laqf.Litelalura.model.*;
import com.laqf.Litelalura.service.ConsumoAPI;
import com.laqf.Litelalura.service.ConvierteDatos;
import com.laqf.Litelalura.service.IAutorService;
import com.laqf.Litelalura.service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
@Component
public class Principal {
    private Scanner sc = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos convierte = new ConvierteDatos();
    @Autowired
    private ILibroService libroService;
    @Autowired
    private IAutorService autorService;
    public Principal(ILibroService libroService, IAutorService autorService) {
        this.libroService = libroService;
        this.autorService = autorService;
    }

    public void mostrarMenu() {
        while (true) {
            var json = consumoAPI.obtenerDatos(URL_BASE);
            var datos = convierte.convertir(json, Datos.class);
            System.out.println(datos.toString());
            System.out.println("***************************");
            System.out.println("Elija una de las siguientes opciones:");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Listar libros registrados");
            System.out.println("3. Listar autores registrados");
            System.out.println("4. Listar autores vivos en un determinado año");
            System.out.println("5. Listar libros por idioma");
            System.out.println("6. Salir");
            System.out.println("***************************");

            int opcion;
            do {
                System.out.print("Ingrese la opción deseada: ");
                try {
                    opcion = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingrese un número válido.");
                }
            } while (true);

            switch (opcion) {
                case 1:
                    buscarYRegistrarLibro(datos);
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresPorAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }


    private void buscarYRegistrarLibro(Datos datos) {
        System.out.println("Escriba el nombre del libro que desea buscar:");
        var tituloLibro = sc.nextLine().replace(" ", "+");

        Optional<DatosLibros> libro = datos.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();

        if (libro.isPresent()) {
            System.out.println(libro.get().toString());
            libroService.registrarLibro(libro.get());
        } else {
            System.out.println("No se encontró el libro");
        }
    }

    private void listarLibros() {
        System.out.println("Listando libros...");
        List<LibroDTO> libros = libroService.listarLibros();
        System.out.println(libros);
    }

    private void listarAutores() {
        System.out.println("Listando Autores...");
        List<AutorDTO> autores = autorService.buscarAutores();
        System.out.println(autores);
    }

    private void listarAutoresPorAnio() {
        System.out.println("Ingrese el año apartir el cual desea ver los autores vivos");
        int anio;
        do {
            System.out.print("Ingrese la opción deseada: ");
            try {
                anio = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        } while (true);

        List<AutorDTO> autores = autorService.buscarAutoresPorAnio(anio);
        if(autores.isEmpty()){
            System.out.println("No hay autores vivos apartir de este año");
        } else{
            System.out.println(autores);
        }

    }

    private void listarLibrosPorIdioma() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el número correspondiente al idioma:");
        System.out.println("1. Inglés");
        System.out.println("2. Francés");
        System.out.println("3. Español");
        System.out.println("4. Alemán");

        int opcion = scanner.nextInt();
        Idioma idiomaSeleccionado = null;

        switch (opcion) {
            case 1:
                idiomaSeleccionado = Idioma.en;
                break;
            case 2:
                idiomaSeleccionado = Idioma.fr;
                break;
            case 3:
                idiomaSeleccionado = Idioma.es;
                break;
            case 4:
                idiomaSeleccionado = Idioma.de;
                break;
            default:
                System.out.println("Opción no válida.");
        }

        if (idiomaSeleccionado != null) {
            List<LibroDTO> libros = libroService.mostrarLibrosPorIdioma(idiomaSeleccionado);

            if(libros.isEmpty()){
                System.out.println("No se encontraron libros en este idioma");
            } else {
                System.out.println(libros);
            }
        }
    }

}
