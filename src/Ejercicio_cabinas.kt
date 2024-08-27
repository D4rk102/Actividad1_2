import kotlin.random.Random

// Clase para representar una llamada
class Llamada(val tipo: String, val duracion: Int) {
    // Función que calcula el costo de la llamada según el tipo
    fun costo(): Int {
        if (tipo == "LOCAL") {
            return 50 * duracion // Costo por minuto para llamadas locales
        } else if (tipo == "LARGA_DISTANCIA") {
            return 350 * duracion // Costo por minuto para llamadas de larga distancia
        } else if (tipo == "CELULAR") {
            return 150 * duracion // Costo por minuto para llamadas a celular
        } else {
            println("Tipo de llamada inválido") // Mensaje de error si el tipo no es válido
            return 0 // Costo 0 para tipo no válido
        }
    }

    // Función para obtener información de la llamada en formato de texto
    fun obtenerInfo(): String {
        return "Tipo: $tipo, Duración: $duracion minutos, Costo: ${costo()} pesos"
    }
}

// Clase para representar una cabina telefónica
class Cabina(val id: Int) {
    var numeroDeLlamadas = 0 // Contador de llamadas registradas en la cabina
    var duracionTotal = 0 // Suma de la duración de todas las llamadas
    var costoTotal = 0 // Suma del costo de todas las llamadas
    val historialLlamadas = mutableListOf<Llamada>() // Lista para guardar el historial de llamadas

    // Función para registrar una nueva llamada en la cabina
    fun registrarLlamada(llamada: Llamada) {
        val llamadasActuales = Random.nextInt(1, 6) // Número aleatorio de llamadas para simular diferentes escenarios
        numeroDeLlamadas += llamadasActuales // Incrementa el número de llamadas
        duracionTotal += llamada.duracion // Suma la duración de la nueva llamada a la duración total
        costoTotal += llamada.costo() // Suma el costo de la nueva llamada al costo total
        historialLlamadas.add(llamada) // Agrega la nueva llamada al historial de llamadas
    }

    // Función para reiniciar los contadores de la cabina a 0
    fun reiniciar() {
        numeroDeLlamadas = 0
        duracionTotal = 0
        costoTotal = 0
        historialLlamadas.clear() // Limpia el historial de llamadas
    }

    // Función para obtener la información completa de la cabina en formato de texto
    fun obtenerInfoCabina(): String {
        var detalleLlamadas = ""
        // Itera sobre el historial de llamadas y agrega la información de cada una al detalle de llamadas
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

// Clase principal para controlar las cabinas y su gestión
class ControlGastosTelefonicos {
    val cabinas = mutableListOf(Cabina(1)) // Inicia con una cabina por defecto

    // Función para agregar una nueva cabina al sistema
    fun agregarCabina() {
        val nuevaCabinaId = cabinas.size + 1 // El ID de la nueva cabina es el tamaño actual de la lista + 1
        cabinas.add(Cabina(nuevaCabinaId)) // Agrega la nueva cabina a la lista
        println("Cabina $nuevaCabinaId agregada.")
    }

    // Función para registrar una llamada en una cabina específica
    fun registrarLlamada(cabinaId: Int, tipo: String, duracion: Int) {
        // Comprueba que el ID de la cabina sea válido
        if (cabinaId <= cabinas.size && cabinaId > 0) {
            val llamada = Llamada(tipo, duracion) // Crea una nueva llamada con el tipo y duración proporcionados
            cabinas[cabinaId - 1].registrarLlamada(llamada) // Registra la llamada en la cabina especificada
        } else {
            println("ID de cabina inválido") // Mensaje de error si el ID no es válido
        }
    }

    // Función para mostrar la información de una cabina específica
    fun mostrarInfoCabina(cabinaId: Int) {
        // Comprueba que el ID de la cabina sea válido
        if (cabinaId <= cabinas.size && cabinaId > 0) {
            println(cabinas[cabinaId - 1].obtenerInfoCabina()) // Muestra la información de la cabina
        } else {
            println("ID de cabina inválido") // Mensaje de error si el ID no es válido
        }
    }

    // Función para mostrar un resumen total de todas las cabinas
    fun mostrarConsolidadoTotal() {
        var totalLlamadas = 0
        var totalMinutos = 0
        var totalCosto = 0

        // Suma los datos de todas las cabinas
        for (cabina in cabinas) {
            totalLlamadas += cabina.numeroDeLlamadas
            totalMinutos += cabina.duracionTotal
            totalCosto += cabina.costoTotal
        }

        // Calcula el costo promedio por minuto, asegurando que no haya división por cero
        val costoPromedioPorMinuto = if (totalMinutos > 0) totalCosto / totalMinutos else 0

        // Muestra el resumen total
        println("Consolidado Total:\n" +
                "Número total de llamadas: $totalLlamadas\n" +
                "Duración total de llamadas: $totalMinutos minutos\n" +
                "Costo total de llamadas: $totalCosto pesos\n" +
                "Costo promedio por minuto: $costoPromedioPorMinuto pesos/minuto\n")
    }

    // Función para reiniciar los datos de una cabina específica
    fun reiniciarCabina(cabinaId: Int) {
        // Comprueba que el ID de la cabina sea válido
        if (cabinaId <= cabinas.size && cabinaId > 0) {
            cabinas[cabinaId - 1].reiniciar() // Reinicia la cabina especificada
        } else {
            println("ID de cabina inválido") // Mensaje de error si el ID no es válido
        }
    }

    // Función para mostrar el menú y gestionar la interacción del usuario
    fun mostrarMenu() {
        var continuar = true // Variable para controlar el bucle del menú

        // Bucle que mantiene el menú activo hasta que el usuario decida salir
        while (continuar) {
            println("==== Menú Principal ====")
            println("1. Registrar una llamada")
            println("2. Mostrar información de una cabina")
            println("3. Mostrar consolidado total")
            println("4. Reiniciar una cabina")
            println("5. Agregar una nueva cabina")
            println("6. Salir")
            print("Seleccione una opción: ")

            // Lee la entrada del usuario y convierte a un número, o establece a null si no es un número
            val opcion = readLine()?.toIntOrNull()

            if (opcion == 1) {
                registrarLlamadaMenu()
            } else if (opcion == 2) {
                mostrarInfoCabinaMenu()
            } else if (opcion == 3) {
                mostrarConsolidadoTotal()
            } else if (opcion == 4) {
                reiniciarCabinaMenu()
            } else if (opcion == 5) {
                agregarCabina()
            } else if (opcion == 6) {
                println("Saliendo del programa.")
                continuar = false // Sale del bucle, terminando el programa
            } else {
                println("Opción no válida. Intente de nuevo.") // Mensaje de error si la opción no es válida
            }
        }
    }

    // Función auxiliar para gestionar la entrada del usuario al registrar una llamada
    private fun registrarLlamadaMenu() {
        print("Ingrese el ID de la cabina: ")
        val cabinaId = readLine()?.toIntOrNull() ?: return println("Entrada inválida.")
        print("Ingrese el tipo de llamada (LOCAL, LARGA_DISTANCIA, CELULAR): ")
        val tipo = readLine()?.uppercase() ?: return println("Entrada inválida.")
        print("Ingrese la duración de la llamada en minutos: ")
        val duracion = readLine()?.toIntOrNull() ?: return println("Entrada inválida.")
        registrarLlamada(cabinaId, tipo, duracion)
    }

    // Función auxiliar para gestionar la entrada del usuario al mostrar información de una cabina
    private fun mostrarInfoCabinaMenu() {
        print("Ingrese el ID de la cabina: ")
        val cabinaId = readLine()?.toIntOrNull() ?: return println("Entrada inválida.")
        mostrarInfoCabina(cabinaId)
    }

    // Función auxiliar para gestionar la entrada del usuario al reiniciar una cabina
    private fun reiniciarCabinaMenu() {
        print("Ingrese el ID de la cabina a reiniciar: ")
        val cabinaId = readLine()?.toIntOrNull() ?: return println("Entrada inválida.")
        reiniciarCabina(cabinaId)
    }
}

// Función principal que inicia el programa
fun main() {
    val controlador = ControlGastosTelefonicos() // Crea una instancia del controlador de gastos
    controlador.mostrarMenu() // Muestra el menú principal para interactuar con el usuario
}

