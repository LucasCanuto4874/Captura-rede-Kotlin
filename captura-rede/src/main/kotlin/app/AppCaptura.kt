package app

import com.github.britooo.looca.api.core.Looca
import dominio.Usuario
import repositorio.DispositivoRepositorio
import repositorio.UsuarioRepositorio


fun main() {

    val repositorioUsuario = UsuarioRepositorio()
    repositorioUsuario.configurar()
    val repositorioDispositivo = DispositivoRepositorio()
    repositorioDispositivo.configurar()
    val looca = Looca()

    while(true){


        print("""
            Captura de Rede NovaScan!
            
            Digite uma opção:
            1 - Iniciar Sistema
            2 - Sair
            
        """.trimIndent())

        val opcao = readln().toInt()

        when(opcao){

            1 ->{
                val login = Usuario()

                println("Digite seu email")
                val email = readln()
                login.setEmail(email)

                println("Digite sua senha")
                val senha = readln()
                login.setSenha(senha)

                val fkEmpresa = repositorioUsuario.existeUsuario(email, senha)

                println("Buscando o ultimo ID do dispositivo")

                val ultimoIdMaquina = repositorioDispositivo.buscarUltimoDispositivo(fkEmpresa)

                println("Cadastro de componente")

                val placaRede = looca.rede.grupoDeInterfaces
            }
            2 ->{
                break
            }
        }
    }
}