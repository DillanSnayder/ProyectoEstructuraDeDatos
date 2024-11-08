package co.edu.uniquindio.proyectoestructura.estructurasPropias.colas.actividad;

import co.edu.uniquindio.proyectoestructura.modelo.Actividad;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ColaActividad {
    private Queue<Actividad> actividades;
    private static final String RUTA_ARCHIVO_ACTIVIDADES = "src/main/resources/archivosTxt/actividades.txt";


    public ColaActividad() {
        actividades = new LinkedList<>();
        cargarActividadesDesdeArchivo(RUTA_ARCHIVO_ACTIVIDADES);
    }

    public void guardarActividad(Actividad actividad) {
        actividades.offer(actividad);
        System.out.println("Actividad guardada: " + actividad);
    }


    public boolean eliminarActividad(String nombre) {
        for (Actividad actividad : actividades) {
            if (actividad.getNombre().equals(nombre)) {
                actividades.remove(actividad);
                System.out.println("Actividad eliminada: " + actividad);
                return true;
            }
        }
        System.out.println("No se encontró la actividad con nombre: " + nombre);
        return false;
    }


    public boolean modificarActividad(String nombre, String nuevaDescripcion, boolean esObligatoria) {
        for (Actividad actividad : actividades) {
            if (actividad.getNombre().equals(nombre)) {
                actividad.setDescripcion(nuevaDescripcion);
                actividad.setEsObligatoria(esObligatoria);
                System.out.println("Actividad modificada: " + actividad);
                return true;
            }
        }
        System.out.println("No se encontró la actividad con nombre: " + nombre);
        return false;
    }



    /*
     *
     *
     *
     *
     * METODOS PARA GUARDAR EN TXT
     *
     *
     *
     * */


    public void agregarActividadAlArchivo(Queue<Actividad> actividades) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO_ACTIVIDADES, true))) {
            for (Actividad actividad : actividades) {

                writer.write(actividad.getNombre() + ";" + actividad.getDescripcion() + ";" + actividad.isEsObligatoria());
                writer.newLine();
            }
            System.out.println("Actividades guardadas en el archivo.");
        } catch (IOException e) {
            System.err.println("Error al guardar las actividades en el archivo: " + e.getMessage());
        }
    }

    public Actividad buscarActividadPorNombre(String nombreActividad) {
        for (Actividad actividad : actividades) {
            if (actividad.getNombre().equalsIgnoreCase(nombreActividad)) {
                return actividad;
            }
        }
        return null;
    }

    public Queue<Actividad> cargarActividadesDesdeArchivo(String rutaArchivo) {


        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(";");

                // Verificamos que la línea tenga los tres elementos necesarios
                if (partes.length == 3) {
                    String nombre = partes[0].trim();
                    String descripcion = partes[1].trim();
                    boolean isObligatoria = Boolean.parseBoolean(partes[2].trim());

                    // Creamos una nueva actividad con los datos de la línea
                    Actividad actividad = new Actividad(nombre, descripcion, isObligatoria,null);

                    // Agregamos la actividad a la cola
                    actividades.add(actividad);
                } else {
                    System.out.println("Formato incorrecto en línea: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return actividades;
    }
    public boolean modificarActividadEnArchivo(String nombre, String nuevaDescripcion, boolean esObligatoria) {
        File archivo = new File(RUTA_ARCHIVO_ACTIVIDADES);
        List<String> lineas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos[0].equals(nombre)) {
                    // Modificar la actividad con la nueva descripción y obligatoriedad
                    lineas.add(nombre + ";" + nuevaDescripcion + ";" + esObligatoria);
                } else {
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return false;
        }

        // Reescribir el archivo con la actividad modificada
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, false))) {
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
            System.out.println("Actividad modificada en el archivo.");
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarLineaDelArchivo(String identificador) {
        File archivo = new File(RUTA_ARCHIVO_ACTIVIDADES);
        List<String> lineas = new ArrayList<>();
        boolean eliminado = false;


        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {

                if (!linea.startsWith(identificador + ";")) {
                    lineas.add(linea);
                } else {
                    eliminado = true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return false;
        }


        if (eliminado) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, false))) {
                for (String linea : lineas) {
                    bw.write(linea);
                    bw.newLine();
                }
                System.out.println("Línea eliminada del archivo.");
                return true;
            } catch (IOException e) {
                System.err.println("Error al escribir en el archivo: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("No se encontró ninguna línea con el identificador: " + identificador);
            return false;
        }
    }
}


