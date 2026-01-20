package org.iesra


/**
 * Clase Libro
 * @property titulo
 * @property autor
 * @property nPaginas
 * @property calificacion
 * @constructor crea un libro con titulo, autor, nº de páginas y calificación
 */
class Libro(val titulo: String, val autor: String, val nPaginas: Int, val calificacion: Int){
    init {
        require(calificacion in 0..10) { "La calificación debe estar entre 0 y 10" }
    }

    override fun toString(): String {
        return "Título: $titulo, Autor: $autor, Páginas: $nPaginas, Calificación: $calificacion"
    }
}

/**
 * Clase ConjuntoLibros
 * @property capacidad
 * @constructor Crea un conjunto de libros con una capacidad máxima
 */
class ConjuntoLibros(private val capacidad: Int) {

    private val libros = ArrayList<Libro>()

    fun anadirLibro(libro: Libro): Boolean {
        if (libros.size >= capacidad) return false

        for (l in libros) {
            if (l.titulo == libro.titulo) return false
        }

        libros.add(libro)
        return true
    }

    fun eliminarPorTitulo(titulo: String): Boolean {
        for (i in libros.indices) {
            if (libros[i].titulo == titulo) {
                libros.removeAt(i)
                return true
            }
        }
        return false
    }

    fun eliminarPorAutor(autor: String): Boolean {
        var eliminado = false
        var i = 0
        while (i < libros.size) {
            if (libros[i].autor == autor) {
                libros.removeAt(i)
                eliminado = true
            } else {
                i++
            }
        }
        return eliminado
    }

    fun mostrarMayorYMenorCalificacion() {
        if (libros.isEmpty()) {
            println("No existen los libros")
            return
        }

        var max = libros[0]
        var min = libros[0]

        for (libro in libros) {
            if (libro.calificacion > max.calificacion) {
                max = libro
            }

            if (libro.calificacion < min.calificacion){
                min = libro
            }
        }

        println("Mayor calificación --> ${max.calificacion}")
        println("Menor calificación --> ${min.calificacion}")
    }

    fun mostrarConjunto() {
        if (libros.isEmpty()) {
            println("Conjunto vacío")
            return
        }

        for (libro in libros) {
            println(libro)
        }
    }
}

fun main(){

    val libro1 = Libro("El mago de york", "colombi", 348, 10)
    val libro2 = Libro("harry potter", "JK Daniel Felipe", 196, 7)
    val conjuntoDeLibros = ConjuntoLibros(3)

    conjuntoDeLibros.anadirLibro(libro1)
    conjuntoDeLibros.anadirLibro(libro2)

    conjuntoDeLibros.eliminarPorAutor("JK Daniel Felipe")
    conjuntoDeLibros.eliminarPorTitulo("El mago de york")

    val libro3 = Libro("No es lo mio", "johnpo", 2706, 10 )
    conjuntoDeLibros.anadirLibro(libro3)

    conjuntoDeLibros.mostrarConjunto()
}