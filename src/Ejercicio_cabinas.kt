import kotlin.random.Random

// Clase para representar una llamada
class Llamada(val tipo: String, val duracion: Int) {
    fun costo(): Int {
        if (tipo == "LOCAL") {
            return 50 * duracion
        } else if (tipo == "LARGA_DISTANCIA") {
            return 350 * duracion
        } else if (tipo == "CELULAR") {
            return 150 * duracion
        } else {
            println("Tipo de llamada inválido")
            return 0
        }
    }
// Muestreo del tipo de llamada, duración y costeo
    fun obtenerInfo(): String {
        return "Tipo: $tipo, Duración: $duracion minutos, Costo: ${costo()} pesos"
    }
}

// Clase para una cabina
class Cabina(val id: Int) {
    var numeroDeLlamadas = 0
    var duracionTotal = 0
    var costoTotal = 0
    val historialLlamadas = mutableListOf<Llamada>()

    fun registrarLlamada(llamada: Llamada) {
        val llamadasActuales = Random.nextInt(1, 11)
        numeroDeLlamadas += llamadasActuales
        duracionTotal += llamada.duracion
        costoTotal += llamada.costo()
        historialLlamadas.add(llamada)
    }
//reinicia todos los datos de las 3 cabinas
    fun reiniciar() {
        numeroDeLlamadas = 0
        duracionTotal = 0
        costoTotal = 0
        historialLlamadas.clear() //limpia los datos que tenian las llamadas anteriores
    }

    fun obtenerInfoCabina(): String {
        var detalleLlamadas = ""
        for (llamada in historialLlamadas) {
            detalleLlamadas += llamada.obtenerInfo() + "\n"
        }
        return "Cabina $id:\n" +
                "Número de llamadas: $numeroDeLlamadas\n" +
                "Duración total de llamadas: $duracionTotal minutos\n" +
                "Costo total de llamadas: $costoTotal pesos\n" +
                "Historial de llamadas:\n$detalleLlamadas"
    }
}

// Clase principal para controlar las cabinas
class ControlGastosTelefonicos {
    val cabina1 = Cabina(1)
    val cabina2 = Cabina(2)
    val cabina3 = Cabina(3)

    fun registrarLlamada(cabinaId: Int, tipo: String, duracion: Int) {
        val llamada = Llamada(tipo, duracion)
        if (cabinaId == 1) {
            cabina1.registrarLlamada(llamada)
        } else if (cabinaId == 2) {
            cabina2.registrarLlamada(llamada)
        } else if (cabinaId == 3) {
            cabina3.registrarLlamada(llamada)
        } else {
            println("ID de cabina inválido")
        }
    }

    fun mostrarInfoCabina(cabinaId: Int) {
        if (cabinaId == 1) {
            println(cabina1.obtenerInfoCabina())
        } else if (cabinaId == 2) {
            println(cabina2.obtenerInfoCabina())
        } else if (cabinaId == 3) {
            println(cabina3.obtenerInfoCabina())
        } else {
            println("ID de cabina inválido")
        }
    }

    fun mostrarConsolidadoTotal() {
        var totalLlamadas = 0
        var totalMinutos = 0
        var totalCosto = 0

        totalLlamadas += cabina1.numeroDeLlamadas
        totalLlamadas += cabina2.numeroDeLlamadas
        totalLlamadas += cabina3.numeroDeLlamadas

        totalMinutos += cabina1.duracionTotal
        totalMinutos += cabina2.duracionTotal
        totalMinutos += cabina3.duracionTotal

        totalCosto += cabina1.costoTotal
        totalCosto += cabina2.costoTotal
        totalCosto += cabina3.costoTotal

        val costoPromedioPorMinuto = if (totalMinutos > 0) totalCosto / totalMinutos else 0

        println("Consolidado Total:\n" +
                "Número total de llamadas: $totalLlamadas\n" +
                "Duración total de llamadas: $totalMinutos minutos\n" +
                "Costo total de llamadas: $totalCosto pesos\n" +
                "Costo promedio por minuto: $costoPromedioPorMinuto pesos/minuto\n")
    }

    fun reiniciarCabina(cabinaId: Int) {
        if (cabinaId == 1) {
            cabina1.reiniciar()
        } else if (cabinaId == 2) {
            cabina2.reiniciar()
        } else if (cabinaId == 3) {
            cabina3.reiniciar()
        } else {
            println("ID de cabina inválido")
        }
    }
}

// Pruebas unitarias
fun main() {
    val controlador = ControlGastosTelefonicos()

    controlador.registrarLlamada(1, "LOCAL", 10)
    controlador.registrarLlamada(2, "CELULAR", 5)
    controlador.registrarLlamada(3, "LARGA_DISTANCIA", 2)

    controlador.mostrarInfoCabina(1)
    controlador.mostrarInfoCabina(2)
    controlador.mostrarInfoCabina(3)

    controlador.mostrarConsolidadoTotal()

    controlador.reiniciarCabina(1)
    controlador.reiniciarCabina(2)
    controlador.reiniciarCabina(3)

    controlador.mostrarInfoCabina(1)
    controlador.mostrarInfoCabina(2)
    controlador.mostrarInfoCabina(3)
}


