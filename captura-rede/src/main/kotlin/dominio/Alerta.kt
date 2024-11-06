package dominio

data class Alerta(
    val nomeMaquina: String = "",
    val minIntervalo: Double = 0.0,
    val maxIntervalo: Double = 0.0,
    val tipoComponente: String = "",
    val tipoAlerta: String = "",
    val fkUsuario: Int = 0,
    val fkDispositivo: Int = 0
) {

}