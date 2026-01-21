package org.iesra

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

enum class Estado{
    PENDIENTE,
    REALIZADA
}

class Tarea(val id: Int, var descripcion: String) {
    var estado: Estado = Estado.PENDIENTE
    var fechaRealizacion: String? = null

    fun marcarRealizada() {
        estado = Estado.REALIZADA
        val fechaHoraActual = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        fechaRealizacion = fechaHoraActual.format(formatter)
    }

    override fun toString(): String {
        return if (estado == Estado.PENDIENTE) {
            "Tarea(id=$id, descripcion='$descripcion', estado=PENDIENTE)"
        } else {
            "Tarea(id=$id, descripcion='$descripcion', estado=REALIZADA, fecha=$fechaRealizacion)"
        }
    }
}

class ListaDeTareas {
    private val tareas: MutableList<Tarea> = mutableListOf()
    private var siguienteId: Int = 1

    fun agregarTarea(descripcion: String) {
        val tarea = Tarea(siguienteId, descripcion)
        tareas.add(tarea)
        println("Tarea agregada: $tarea")
        siguienteId++
    }

    fun eliminarTarea(id: Int) {
        val tarea = tareas.find { it.id == id }
        if (tarea != null) {
            tareas.remove(tarea)
            println("Tarea eliminada: $tarea")
        } else {
            println("No se encontró tarea con id $id")
        }
    }

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

    fun listarTareas() {
        if (tareas.isEmpty()) {
            println("No hay tareas en la lista")
            return
        }
        println("Todas las tareas:")
        tareas.forEach { println(it) }
    }

    fun listarTareasPendientes() {
        val pendientes = tareas.filter { it.estado == Estado.PENDIENTE }
        if (pendientes.isEmpty()) {
            println("No hay tareas pendientes")
            return
        }
        println("Tareas pendientes:")
        pendientes.forEach { println(it) }
    }

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
                println("Saliendo del programa...")
                continuar = false
            }
            else -> println("Opción inválida, intente de nuevo")
        }
    } while (continuar)
}
