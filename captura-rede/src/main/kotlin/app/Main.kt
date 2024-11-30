package app

import com.github.britooo.looca.api.core.Looca
import dominio.Usuario
import java.io.File
import config.DatabaseConfig
import config.CarregarEnv
import dominio.MensagemAlertas
import repositorio.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

open class Main {
    companion object {
        @JvmStatic fun main(args: Array<String>) {


                val repositorioUsuario = UsuarioRepositorio()
                val repositorioDispositivo = DispositivoRepositorio()
                val repositorioComponente = ComponenteRepositorio()
                val repositorioLog = LogRepositorio()
                val repositorioAlerta = AlertasRepositorio()
                val dominioMensagem = MensagemAlertas()
                val looca = Looca()
                val carregarEnv = CarregarEnv()

                print(carregarEnv.carregarArquivoEnv())

                while(true){

                    print("""
            Captura de Rede NovaScan!
            
            Digite uma opção:
            1 - Iniciar Sistema
            2 - Sair
            
        """.trimIndent())

                    val opcao = readLine()?.toIntOrNull()
                    when(opcao){

                        1 ->{
                            val login = Usuario()

                            println("Digite seu email")
                            val email = readLine()
                            if (email != null) {
                                login.setEmail(email)
                            }

                            println("Digite sua senha")
                            val senha = readLine()
                            if (senha != null) {
                                login.setSenha(senha)
                            }

                            if(senha != null && email != null){
                                val fkEmpresa = repositorioUsuario.existeUsuario(email, senha)
                                val idUsuario = repositorioUsuario.buscarIdUsuarioLogado(email, senha)

                                println("Buscando o ultimo ID do dispositivo")

                                //Buscando o caminho do .env
                                val caminhoEnv = File(".env")

                                val codigoMaquina = DatabaseConfig.getDotEnv()["CODIGO_MAQUINA"]

                                if(codigoMaquina == null){

                                    val ultimoIdMaquina = repositorioDispositivo.buscarUltimoDispositivo(fkEmpresa)

                                    //setando o ultimo id da maquina dentro do arquivo .env
                                    caminhoEnv.appendText("\nCODIGO_MAQUINA=$ultimoIdMaquina")

                                    println("Componente ainda não cadastrado!")
                                    println("Cadastrando componente na máquina")

                                    val placaRede = looca.rede.grupoDeInterfaces.interfaces
                                    var nome = ""
                                    for (i in placaRede){
                                        if (i.bytesRecebidos > 1 && i.bytesEnviados > 1){
                                            nome = i.nome
                                            println(nome)
                                        }
                                    }
                                    repositorioComponente.inserirComponente(ultimoIdMaquina,nome)
                                }

                                val idMaquina = repositorioDispositivo.buscarUltimoDispositivo(fkEmpresa)
                                val alertasUsuario = repositorioAlerta.buscarAlertasUsuario(idUsuario, idMaquina)

                                if(alertasUsuario.isNotEmpty()){

                                    for(alerta in alertasUsuario){
                                        println("""
                                            Alertas Encontrados:
                                            Nome da Máquina: ${alerta.nomeMaquina}
                                            Mínimo do Intervalo: ${alerta.minIntervalo}
                                            Máximo do Intervalo: ${alerta.maxIntervalo}
                                            Tipo do Componente: ${alerta.tipoComponente}
                                            Tipo do Alerta: ${alerta.tipoAlerta}
                                        """.trimIndent())
                                    }
                                }
                                else{
                                    println("Nenhum Alerta Cadastrado")
                                }

                                    val placaRede = looca.rede.grupoDeInterfaces.interfaces
                                    var nome = ""
                                    for (i in placaRede){
                                        if (i.bytesRecebidos > 1 && i.bytesEnviados > 1){
                                            nome = i.nome
                                        }
                                    }
                                    println("Componente ja cadastrado!")
                                    println("Começando a captura")
                                    println("Capturando dados da placa")

                                    val ultimoComponente = repositorioComponente.buscarUltimoComponente(idMaquina)

                                    while (true){

                                        val interfacePrincipal = placaRede.firstOrNull { it.nome.contains(nome) } ?: placaRede[0]
                                        val bytesEnviados = interfacePrincipal.bytesEnviados
                                        val bytesRecebidos = interfacePrincipal.bytesRecebidos
                                        val bytesEnvConvertidos = bytesEnviados / (1024 * 1024) // Conversão para MB
                                        val bytesReConvertidos = bytesRecebidos / (1024 * 1024) // Conversão para MB
                                        val alertaMinimoBytes = 1
                                        for (alerta in alertasUsuario){

                                            if(alerta.tipoComponente == "Placa de Rede" && alerta.tipoAlerta == "Bytes Enviados e Recebidos(MB)"){
                                                if(bytesEnvConvertidos < alerta.minIntervalo && bytesEnvConvertidos > alerta.maxIntervalo){
                                                    println("Alerta: Bytes Enviados disparou o alerta!!!!")
                                                    println("Alerta: Bytes Enviados capturado: $bytesEnvConvertidos")
                                                    val alerta = 1
                                                    repositorioLog.capturaBytesEnviados(bytesEnvConvertidos, ultimoComponente, idMaquina, alerta)

                                                    //Comente a função abaixo caso não tenha token do slack e queira testar a aplicação
//                                                    dominioMensagem.alertaBytesEnviadosPersonalizado(bytesEnvConvertidos)

                                                }
                                                else{
                                                    val alerta = 0
                                                    println("Bytes Enviados Capturado: $bytesEnvConvertidos MB")
                                                    repositorioLog.capturaBytesEnviados(bytesEnvConvertidos, ultimoComponente, idMaquina, alerta)
                                                }

                                                if(bytesReConvertidos < alerta.minIntervalo && bytesReConvertidos > alerta.maxIntervalo){
                                                    println("Alerta: Bytes Recebidos disparou o alerta!!!!")
                                                    println("Alerta: Bytes Recebidos capturado: $bytesReConvertidos")
                                                    val alerta = 1
                                                    repositorioLog.capturaBytesRecebidos(bytesReConvertidos, ultimoComponente, idMaquina, alerta)

                                                    //Comente a função abaixo caso não tenha token do slack e queira testar a aplicação
//                                                    dominioMensagem.alertaBytesRecebidosPersonalizado(bytesReConvertidos)
                                                }
                                                else{
                                                    val alerta = 0
                                                    println("Bytes Recebidos Capturado: $bytesReConvertidos MB")
                                                    repositorioLog.capturaBytesRecebidos(bytesReConvertidos, ultimoComponente, idMaquina, alerta)
                                                }
                                            }
                                            break
                                        }

                                        if(bytesEnvConvertidos <= alertaMinimoBytes){
                                            println("Alerta: Bytes Enviados disparou o alerta!!!!")
                                            println("Alerta: Bytes Enviados capturado: $bytesEnvConvertidos")
                                            var alerta = 1
                                            repositorioLog.capturaBytesEnviados(bytesEnvConvertidos, ultimoComponente, idMaquina, alerta)

                                            //Comente a função abaixo caso não tenha token do slack e queira testar a aplicação
//                                            dominioMensagem.alertaBytesEnviadosPadraoSistema(bytesEnvConvertidos)

                                        }
                                        else{
                                            val alerta = 0
                                            println("Bytes Enviados Capturado: $bytesEnvConvertidos MB")
                                            repositorioLog.capturaBytesEnviados(bytesEnvConvertidos, ultimoComponente, idMaquina, alerta)
                                        }


                                        if(bytesReConvertidos <= alertaMinimoBytes){
                                            println("Alerta: Bytes Recebidos disparou o alerta!!!!")
                                            println("Alerta: Bytes Recebidos capturado: $bytesReConvertidos")
                                            val alerta = 1
                                            repositorioLog.capturaBytesRecebidos(bytesReConvertidos, ultimoComponente, idMaquina, alerta)

                                            //Comente a função abaixo caso não tenha token do slack e queira testar a aplicação
//                                            dominioMensagem.alertaBytesRecebidosPersonalizado(bytesReConvertidos)
                                        }
                                        else{
                                            val alerta = 0
                                            println("Bytes Recebidos Capturado: $bytesReConvertidos MB")
                                            repositorioLog.capturaBytesRecebidos(bytesReConvertidos, ultimoComponente, idMaquina, alerta)
                                        }

                                        Thread.sleep(5000)

                                    }
                            }

                        }
                        2 ->{
                            break
                        }
                    }
                }

            }
    }
    }
