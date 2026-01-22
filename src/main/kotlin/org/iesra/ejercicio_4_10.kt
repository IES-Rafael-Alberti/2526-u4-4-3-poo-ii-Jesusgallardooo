package org.iesra

// Tres en raya POO

/**
 * Jugador
 * @property id
 * @constructor Crea un jugador con su id y su símbolo.
 */
class Jugador(val id: Int) {

    val simbolo:String

    init {
        require(id in 1..2) { "id 1 o 2, no +2 jugadores..." }
        if (id == 1 ){
            simbolo = "X"
        }else {
            simbolo = "O"
        }
    }

}


class Tablero(val filas:Int, val columnas:Int){

    val valorInicial = " "
    val tablero = MutableList(filas){
                    MutableList(columnas){
                        valorInicial
                    }
    }

    fun mostrarTablero(){
        val separador = "+${"-".repeat(3)}".repeat(columnas) + "+"

        for (fila in tablero) {
            println(separador)
            for (celda in fila) {
                print("| ${celda.padEnd(1)} ")
            }
            println("|")
        }
        println(separador)
    }

    fun celdaVacia(fila:Int, columna:Int): Boolean{
        if (tablero[fila][columna] == valorInicial){
            return true
        } else {
            return false
        }
    }

    fun tableroLLeno(): Boolean{
        for (fila in tablero){
            for (celda in fila){
                if (celda == valorInicial){
                    return false
                }
            }
        }
        return true
    }

    fun colocarFicha(fila:Int, columna:Int, ficha:String): Boolean{
        if (tablero[fila][columna] == valorInicial){
            tablero[fila][columna] = ficha
            return true
        } else {
            return false
        }
    }

    fun limpiarTablero(){
        for (i in tablero.indices){
            for (j in tablero[i].indices){
                tablero[i][j] = valorInicial
            }
        }
    }

}


class Juego(){

}

fun main(){
    val tablero = Tablero(3,3)
    tablero.tablero[0][0] = "X"
    tablero.tablero[0][1] = "O"
    tablero.tablero[0][2] = "C"
    tablero.tablero[1][0] = "F"
    tablero.tablero[1][2] = "C"
    tablero.tablero[2][0] = "D"
    tablero.tablero[2][1] = "F"
    tablero.tablero[2][2] = "O"

    if (tablero.colocarFicha(1, 1, "X")) {
        println("Ficha colocada correctamente")
    } else {
        println("Posición ya ocupada, intenta otra posición")
    }

    println(tablero.tableroLLeno())
    println(tablero.celdaVacia(2,2))
    tablero.mostrarTablero()
    tablero.limpiarTablero()
    tablero.mostrarTablero()

}