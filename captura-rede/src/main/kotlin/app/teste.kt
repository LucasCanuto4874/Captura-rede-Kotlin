package app

import com.github.britooo.looca.api.core.Looca

fun main() {
    val looca = Looca()
    val placaRede = looca.rede.grupoDeInterfaces.interfaces
    println(placaRede)
}