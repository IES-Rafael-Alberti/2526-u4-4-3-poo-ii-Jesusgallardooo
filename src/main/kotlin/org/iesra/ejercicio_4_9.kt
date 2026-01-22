package org.iesra

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


/**
 * Clase Estado
 *
 * @constructor Crea un estado para las tareas
 */
enum class Estado{
    PENDIENTE,
    REALIZADA
}


/**
 * Clase Tarea
 * @property id
 * @property descripcion
 * @constructor Crea una tarea con un id, una descripción y un estado (PENDIENTE por defecto)
 */
class Tarea(val id: Int, var descripcion: String) {
    var estado: Estado = Estado.PENDIENTE
    var fechaRealizacion: String? = null

    /** Marcar realizada */
    fun marcarRealizada() {
        estado = Estado.REALIZADA
        val fechaHoraActual = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        fechaRealizacion = fechaHoraActual.format(formatter)
    }

    /**
     * To string
     *
     * @return
     */
    override fun toString(): String {

        if (estado==Estado.PENDIENTE) {
            return "Tarea(id=$id, descripcion='$descripcion', estado=PENDIENTE)"
        }else {
            return "Tarea(id=$id, descripcion='$descripcion', estado=REALIZADA, fecha=$fechaRealizacion)"
        }
    }

}

/**
 * Clase Lista de tareas
 *
 * @constructor Crea una lista de tareas para almacenarlas y tratar con ellas.
 */
class ListaDeTareas {
    private val tareas: MutableList<Tarea> = mutableListOf()
    private var siguienteId: Int = 1

    /**
     * Agregar tarea
     *
     * @param descripcion
     */
    fun agregarTarea(descripcion: String) {
        val tarea = Tarea(siguienteId, descripcion)
        tareas.add(tarea)
        println("Tarea agregada: $tarea")
        siguienteId++
    }

    /**
     * Eliminar tarea
     *
     * @param id
     */
    fun eliminarTarea(id: Int) {
        val tarea = tareas.find { it.id == id }
        if (tarea != null) {
            tareas.remove(tarea)
            println("Tarea eliminada: $tarea")
        } else {
            println("No se encontró tarea con id $id")
        }
    }

    /**
     * Cambiar estado
     *
     * @param id
     */
    fun cambiarEstado(id: Int) {
        val tarea = tareas.find { it.id == id }
        if (tarea != null) {
            if (tarea.estado == Estado.PENDIENTE) {
                tarea.marcarRealizada()
                println("Tarea marcada como realizada: $tarea")
            } else {
                println("La tarea ya estaba realizada: $tarea")
            }
        } else {
            println("No se encontró tarea con id $id")
        }
    }

    /** Listar tareas */
    fun listarTareas() {
        if (tareas.isEmpty()) {
            println("No hay tareas en la lista")
            return
        }
        println("Todas las tareas:")
        tareas.forEach { println(it) }
    }

    /** Listar tareas pendientes */
    fun listarTareasPendientes() {
        val pendientes = tareas.filter { it.estado == Estado.PENDIENTE }
        if (pendientes.isEmpty()) {
            println("No hay tareas pendientes")
            return
        }
        println("Tareas pendientes:")
        pendientes.forEach { println(it) }
    }

    /** Listar tareas realizadas */
    fun listarTareasRealizadas() {
        val realizadas = tareas.filter { it.estado == Estado.REALIZADA }
        if (realizadas.isEmpty()) {
            println("No hay tareas realizadas")
            return
        }
        println("Tareas realizadas:")
        realizadas.forEach { println(it) }
    }
}

fun main() {
    val lista = ListaDeTareas()
    var continuar: Boolean

    do {
        println("\n<<< Menú de Tareas >>>")
        println("1. Agregar tarea")
        println("2. Eliminar tarea")
        println("3. Cambiar estado de tarea")
        println("4. Listar todas las tareas")
        println("5. Listar tareas pendientes")
        println("6. Listar tareas realizadas")
        println("7. Salir")
        print("Seleccione una opción: ")

        val opcion = readLine()?.toIntOrNull()
        continuar = true

        when (opcion) {
            1 -> {
                print("Ingrese descripción de la tarea: ")
                val desc = readLine() ?: ""
                lista.agregarTarea(desc)
            }
            2 -> {
                print("Ingrese ID de la tarea a eliminar: ")
                val id = readLine()?.toIntOrNull()
                if (id != null) lista.eliminarTarea(id)
                else println("ID inválido")
            }
            3 -> {
                print("Ingrese ID de la tarea a marcar como realizada: ")
                val id = readLine()?.toIntOrNull()
                if (id != null) lista.cambiarEstado(id)
                else println("ID inválido")
            }
            4 -> lista.listarTareas()
            5 -> lista.listarTareasPendientes()
            6 -> lista.listarTareasRealizadas()
            7 -> {
                println("Fin del programa...")
                continuar = false
            }
            else -> println("Opción inválida, intente de nuevo")
        }
    } while (continuar)
}
