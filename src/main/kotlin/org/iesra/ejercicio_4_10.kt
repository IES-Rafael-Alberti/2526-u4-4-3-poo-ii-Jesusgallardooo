package org.iesra

import javax.swing.plaf.TextUI

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

    fun comprobarCelda(fila:Int, columna:Int): String {
        return tablero[fila][columna]
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
    var turnoActual: Jugador = jugador2
    var partidaEnCurso: Boolean = true

    fun iniciar(){

        println("<<< TRES EN RAYA >>>")

        do {

            cambiarTurno()
            println("Turno del jugador: ${turnoActual.id} ('${turnoActual.simbolo}')")
            tablero.mostrarTablero()

            ponerFicha(pedirMovimiento(turnoActual, tablero))
            if (hayGanador() || tablero.tableroLLeno()){
                partidaEnCurso = false
            }

        }while(partidaEnCurso)

        tablero.mostrarTablero()

        if (hayGanador()){
            println("\nGANADOR --> JUGADOR${turnoActual.id} ('${turnoActual.simbolo}')")
        } else {
            println("\nEMPATE TÉCNICO")
        }

    }

    fun cambiarTurno(){
        if (turnoActual == jugador1){
            turnoActual = jugador2
        } else{
            turnoActual = jugador1
        }
    }

    fun pedirMovimiento(jugador: Jugador, tablero: Tablero): Pair<Int, Int> {

        var fila: Int = 0
        var columna: Int = 0
        var movimientoValido = false

        while (!movimientoValido) {
            print("Jugador ${jugador.id} (${jugador.simbolo}), ingresa fila (1-3): ")
            fila = readLine()?.toIntOrNull() ?: 0

            print("Jugador ${jugador.id} (${jugador.simbolo}), ingresa columna (1-3): ")
            columna = readLine()?.toIntOrNull() ?: 0

            if (fila in 1..3 && columna in 1..3) {
                if (tablero.comprobarCelda(fila - 1, columna - 1) == tablero.valorInicial) {
                    movimientoValido = true
                } else {
                    println("La celda ya está ocupada. Elige otra posición.")
                }
            } else {
                println("Valores inválidos, deben estar entre 1 y 3. Intenta de nuevo.")
            }
        }

        return Pair(fila - 1, columna - 1)
    }




    fun ponerFicha(posicion: Pair<Int, Int>): Boolean{

        var fila = posicion.first
        var columna = posicion.second

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

}

fun main(){
    val partida: Juego = Juego()
    partida.iniciar()
}