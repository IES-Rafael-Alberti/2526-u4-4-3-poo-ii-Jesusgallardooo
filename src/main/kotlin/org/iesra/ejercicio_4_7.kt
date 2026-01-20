package org.iesra


/**
 * Clase Persona
 * @param dni dni de la persona
 * @constructor Crea una persona con DNI
 */

class Persona(val dni: String) {

    val cuentas: Array<Cuenta?> = arrayOfNulls(3)

    fun anadirCuenta(cuenta: Cuenta): Boolean {
        for (i in cuentas.indices) {
            if (cuentas[i] == null) {
                cuentas[i] = cuenta
                return true
            }
        }
        return false
    }

    fun cuentaPorNumero(numero: Int): Cuenta? {
        return cuentas.firstOrNull { it?.numeroDeCuenta == numero }
    }

    override fun toString(): String {
        val cuentasStr = cuentas.filterNotNull().joinToString { it.toString() }
        return "Persona(dni='$dni', cuentas=[$cuentasStr])"
    }
}

/**
 * Clase Cuenta
 * @param numeroDeCuenta
 * @param saldo
 * @constructor Crea una Cuenta con número de cuenta y un saldo
 */
class Cuenta(val numeroDeCuenta:Int, var saldo:Double) {

    companion object{

        fun es_Morosa(persona: Persona):Boolean{
            for (cuenta in persona.cuentas){
                if (cuenta != null && cuenta.saldo < 0){
                    return true
                }
            }
            return false
        }

        fun transferencia(dePersona: Persona, aPersona: Persona, numeroCuentaOrigen:Int, numeroCuentaDestino:Int, cantidad:Double):Boolean {

            val cuentaOrigen = dePersona.cuentaPorNumero(numeroCuentaOrigen)
            val cuentaDestino = aPersona.cuentaPorNumero(numeroCuentaDestino)

            if (cuentaOrigen == null || cuentaDestino == null){
                return false
            }

            if (cuentaOrigen.saldo < cantidad){
                return false
            }

            cuentaOrigen.realizar_pago(cantidad)
            cuentaDestino.recibir_abono(cantidad)
            return true
        }
    }

    /**
     * Retorna el saldo
     */
    fun consultar_saldo():Double{
        return saldo
    }

    /**
     * Recibir_abono
     * @param abono
     * Realiza un abono al saldo de la cuenta
     */
    fun recibir_abono(abono:Double){
        saldo += abono
    }

    /**
     * Realizar_pago
     * @param
     * Realiza un pago y se lo resta a la cuenta
     */
    fun realizar_pago(pago:Double){
        saldo -= pago
    }

    /**
     * To string
     * @return datos de la cuenta
     */
    override fun toString(): String {
        return "Cuenta($numeroDeCuenta, saldo=$saldo)"
    }
}

fun main() {

    val persona: Persona = Persona("12345678A")

    val cuenta1: Cuenta = Cuenta(1, 0.0)
    val cuenta2: Cuenta = Cuenta(2,700.0)

    persona.anadirCuenta(cuenta1)
    persona.anadirCuenta(cuenta2)

    cuenta1.recibir_abono(1100.0)
    cuenta2.realizar_pago(750.0)

    println("Persona morosa: ${Cuenta.es_Morosa(persona)}")

    Cuenta.transferencia(persona, persona, cuenta1.numeroDeCuenta, cuenta2.numeroDeCuenta, 1000.0)
    println(persona)
}