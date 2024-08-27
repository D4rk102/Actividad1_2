// EJEMPLO DE UN DATA CLASS

data class Circulo(var radio: Double) {

    fun calcularArea(): Double {
        return Math.PI * radio * radio
    }

    fun calcularCircunferencia(): Double {
        return 2 * Math.PI * radio
    }
}

fun main() {
    val circulo = Circulo(5.0)

    // Calcular y mostrar el área
    println("Área del círculo: ${circulo.calcularArea()}")

    // Calcular y mostrar la circunferencia
    println("Circunferencia del círculo: ${circulo.calcularCircunferencia()}")

    // Usando el método `copy()` para crear un nuevo círculo con un radio diferente
    val circulo2 = circulo.copy(radio = 10.0)
    println("Área del nuevo círculo: ${circulo2.calcularArea()}")
}
