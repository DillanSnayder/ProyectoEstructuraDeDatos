package co.edu.uniquindio.proyectoestructura.controller;

import co.edu.uniquindio.proyectoestructura.estructurasPropias.cola.tarea.ColaTarea;
import co.edu.uniquindio.proyectoestructura.estructurasPropias.listaEnlazada.proceso.ListaEnlazadaProceso;
import co.edu.uniquindio.proyectoestructura.modelo.Actividad;
import co.edu.uniquindio.proyectoestructura.modelo.Tarea;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class AdminTareaController {

    ColaTarea colaTarea = new ColaTarea();
    AdminProcesoController adminProcesoController;
    ListaEnlazadaProceso listaEnlazadaProceso;

    public void guardar(Tarea tarea) {
        colaTarea.guardarTarea(tarea);
    }

    public void eliminar(String nombre) {
        colaTarea.eliminarTarea(nombre);
    }

    //se ingresa el nombre anterior pero con nueva descripcion
    public void modificar(String nombre, String descripcion, boolean obligatorio, int duracion) {
        colaTarea.modificarTarea(nombre, descripcion, obligatorio, duracion);
    }

    //METODOS AL TXT

    public void agregarTxt(List<Tarea> tareas, String archivo) {
        colaTarea.guardarListaEnArchivo(tareas, archivo);

    }

    public void modificarTxt(String nombre, String nuevaDescripcion, boolean esObligatoria, int duracion) {
        colaTarea.modificarTareasEnArchivo(nombre, nuevaDescripcion, esObligatoria, duracion);
    }

    public void eliminarTxt(String identificador) {
        colaTarea.eliminarLineaDelArchivo(identificador);
    }

    public Actividad[] colaAArreglo(Queue<Actividad> cola) {
        return colaTarea.colaAArreglo(cola);
    }

    public Tarea buscarTarea(String nombre) {
        return colaTarea.buscarTareaPorNombre(nombre);
    }

    public Queue<Tarea> cargarTareasDesdeArchivo(String ruta) {
        return colaTarea.cargarTareasDesdeArchivo(ruta);
    }
    public Tarea[]cargarTareaArchivo(String ruta){
        return colaTarea.cargarTareasArchivo(ruta);
    }

    public List<Tarea> generarTareasDesdeNombres(String nombresTareas) {
        List<Tarea> listaTareas = new ArrayList<>();
        String[] nombres = nombresTareas.split(","); // Dividir nombres de tareas por comas
        for (String nombre : nombres) {
            Tarea tarea = this.buscarTarea(nombre.trim()); // Buscar tarea por nombre
            if (tarea != null) {
                listaTareas.add(tarea);
            } else {
                System.out.println("Tarea no encontrada: " + nombre.trim());
            }
        }
        return listaTareas;
    }


    public void guardarTareaEnActividad(String nombreActividad, Tarea nuevaTarea){
        colaTarea.guardarTareaEnActividad(nombreActividad, nuevaTarea);
}
}
