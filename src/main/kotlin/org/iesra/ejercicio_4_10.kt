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

    fun mostrarInformacion(){
        println("JUGADOR$id, tu símbolo es: '$simbolo'")
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

    var tablero:Tablero = Tablero(3,3)
    val jugador1:Jugador = Jugador(1)
    val jugador2:Jugador = Jugador(2)
    var turnoActual: Jugador = jugador1
    var partidaEnCurso: Boolean = true

    fun iniciar(){
        // flujo del juego
    }

    fun cambiarTurno(){
        if (turnoActual == jugador1){
            turnoActual = jugador2
        } else{
            turnoActual = jugador1
        }
    }

    fun ponerFicha(fila:Int, columna:Int): Boolean{
        if (tablero.celdaVacia(fila, columna)){
            tablero.colocarFicha(fila, columna, turnoActual.simbolo)
            return true
        }else {
            return false
        }
    }

    fun hayGanador():Boolean{

        val simbolo = turnoActual.simbolo

        // comprobar columnas
        for (columna in 0 until tablero.columnas) {
            if ((0 until tablero.filas).all { fila -> tablero.tablero[fila][columna] == simbolo }) {
                return true
            }
        }

        // comprobar filas
        for (fila in 0 until tablero.filas) {
            if ((0 until tablero.columnas).all { col -> tablero.tablero[fila][col] == simbolo }) {
                return true
            }
        }

        // comprobar diagonal izq --> dcha
        if ((0 until tablero.filas).all { i -> tablero.tablero[i][i] == simbolo }) {
            return true
        }

        // comprobar diagonal dcha --> izq
        if ((0 until tablero.filas).all { i -> tablero.tablero[i][tablero.columnas - 1 - i] == simbolo }) {
            return true
        }

        // no hay ganador
        return false
    }

    fun Empate(): Boolean{
        if (!hayGanador() && tablero.tableroLLeno()){
            return true
        }else{
            return false
        }
    }


}

fun main(){
    val tablero = Tablero(3,3)
    tablero.mostrarTablero()
}