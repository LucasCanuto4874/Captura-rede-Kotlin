package app

import com.github.britooo.looca.api.core.Looca

fun main() {
    val looca = Looca()
    val placaRede = looca.rede.grupoDeInterfaces.interfaces
    println(placaRede)

    for (i in placaRede){
        if (i.bytesRecebidos > 1 && i.bytesEnviados > 1){
            var nome = i.nome
            println(nome)
        }

    }

}