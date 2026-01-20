package org.iesra


/**
 * Clase Domicilio
 * @param calle calle del domicilio
 * @param numero numero del domicilio
 * @constructor Crea un domicilio con calle y numero
 */
data class Domicilio(val calle: String, val numero: Int) {

    fun dirCompleta(): String {
        return "$calle $numero"
    }

}

/**
 * Clase Cliente
 * @param nombre nombre del cliente
 * @param domicilio domicilio del cliente
 * @constructor Crea un cliente con nombre y domicilio
 */
data class Cliente(val nombre: String, val domicilio: Domicilio)

/**
 * Clase Compra
 * @param cliente cliente que realizo la compra
 * @param dia dia de la compra
 * @param monto monto de la compra
 * @constructor Crea una compra con cliente, dia y monto
 */
data class Compra(val cliente: Cliente, val dia: Int, val monto: Double)


/**
 * Clase RepositorioCompras
 * @constructor Crea un repositorio de compras
 */
class RepositorioCompras {

    private val compras: MutableList<Compra> = mutableListOf()

    /**
     * Agrega una compra al repositorio
     */
    fun agregarCompra(compra: Compra) {
        compras.add(compra)
    }

    /**
     * Retorna los domicilios únicos de los clientes
     * a los cuales se les debe enviar factura
     */
    fun domicilios(): Set<Domicilio> {
        return compras.map { it.cliente.domicilio }.toSet()
    }

}


fun main() {
    val d1 = Domicilio("Calle Las Flores", 355)
    val d2 = Domicilio("Mirasol", 218)
    val d3 = Domicilio("La Mancha", 761)

    val c1 = Cliente("Nuria Costa", d1)
    val c2 = Cliente("Jorge Russo", d2)
    val c3 = Cliente("Julián Rodríguez", d3)

    val repositorioDeCompras = RepositorioCompras()

    repositorioDeCompras.agregarCompra(Compra(c1, 5, 12780.78))
    repositorioDeCompras.agregarCompra(Compra(c2, 7, 699.0))
    repositorioDeCompras.agregarCompra(Compra(c1, 7, 532.90))
    repositorioDeCompras.agregarCompra(Compra(c3, 12, 5715.99))
    repositorioDeCompras.agregarCompra(Compra(c2, 15, 958.0))

    repositorioDeCompras.domicilios().forEach {
        println(it.dirCompleta())
    }
}
