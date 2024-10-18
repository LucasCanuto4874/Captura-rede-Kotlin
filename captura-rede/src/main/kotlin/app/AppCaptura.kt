package app

import com.github.britooo.looca.api.core.Looca
import dominio.Usuario
import repositorio.ComponenteRepositorio
import repositorio.DispositivoRepositorio
import repositorio.LogRepositorio
import repositorio.UsuarioRepositorio
import kotlin.concurrent.thread


fun main() {

    val repositorioUsuario = UsuarioRepositorio()
    repositorioUsuario.configurar()
    val repositorioDispositivo = DispositivoRepositorio()
    repositorioDispositivo.configurar()
    val repositorioComponente = ComponenteRepositorio()
    repositorioComponente.configurar()
    val repositorioLog = LogRepositorio()
    repositorioLog.configurar()
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

                val placaRede = looca.rede.grupoDeInterfaces.interfaces
                var nome = ""
                print("Cadastrando componente")
                for (i in placaRede){
                    if (i.bytesRecebidos > 1 && i.bytesEnviados > 1){
                        nome = i.nome
                        println(nome)
                    }
                }


                repositorioComponente.inserirComponente(ultimoIdMaquina,nome)


                print("Capturando dados da placa")
                val ultimoComponente = repositorioComponente.buscarUltimoComponente(ultimoIdMaquina)

                print("Capturando dados")

                while (true){

                    val interfacePrincipal = placaRede.firstOrNull { it.nome.contains("wlan1") } ?: placaRede[0]
                    val bytesEnviados = interfacePrincipal.bytesEnviados
                    val bytesRecebidos = interfacePrincipal.bytesRecebidos
                    val BytesEnvConvertidos = bytesEnviados / (1024 * 1024) // Conversão para MB
                    val BytesReConvertidos = bytesRecebidos / (1024 * 1024) // Conversão para
                    repositorioLog.capturaBytesEnviados(BytesEnvConvertidos, ultimoComponente, ultimoIdMaquina)
                    repositorioLog.capturaBytesRecebidos(BytesReConvertidos, ultimoComponente, ultimoIdMaquina)
                    println("Exibindo Bytes Recebidos: ${BytesReConvertidos}MB")
                    println("Exibindo Bytes Enviados: ${BytesEnvConvertidos}MB")
                    Thread.sleep(5000)

            }
            }
            2 ->{
                break
            }
        }
    }
}