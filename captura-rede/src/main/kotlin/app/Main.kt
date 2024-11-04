package app

import com.github.britooo.looca.api.core.Looca
import dominio.Usuario
import repositorio.ComponenteRepositorio
import repositorio.DispositivoRepositorio
import repositorio.LogRepositorio
import repositorio.UsuarioRepositorio
import java.io.File
import config.DatabaseConfig

open class Main {
    companion object {
        @JvmStatic fun main(args: Array<String>) {

                val repositorioUsuario = UsuarioRepositorio()
                val repositorioDispositivo = DispositivoRepositorio()
                val repositorioComponente = ComponenteRepositorio()
                val repositorioLog = LogRepositorio()
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



                            //Buscando o caminho do .env
                            val caminhoEnv = File("src/main/kotlin/.env")

                            //setando o ultimo id da maquina dentro do arquivo .env
                            caminhoEnv.appendText("\nCODIGO_MAQUINA=$ultimoIdMaquina")

                            val codigoMaquina = DatabaseConfig.getDotEnv()["CODIGO_MAQUINA"]

                            if(codigoMaquina == null){

                                print("Componente ainda não cadastrado!")
                                println("Cadastrando componente na máquina: ${codigoMaquina}")

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
                            else{

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

                                val ultimoComponente = repositorioComponente.buscarUltimoComponente(ultimoIdMaquina)

                                while (true){
                                    val interfacePrincipal = placaRede.firstOrNull { it.nome.contains(nome) } ?: placaRede[0]
                                    val bytesEnviados = interfacePrincipal.bytesEnviados
                                    val bytesRecebidos = interfacePrincipal.bytesRecebidos
                                    val bytesEnvConvertidos = bytesEnviados / (1024 * 1024) // Conversão para MB
                                    val bytesReConvertidos = bytesRecebidos / (1024 * 1024) // Conversão para
                                    repositorioLog.capturaBytesEnviados(bytesEnvConvertidos, ultimoComponente, ultimoIdMaquina)
                                    repositorioLog.capturaBytesRecebidos(bytesReConvertidos, ultimoComponente, ultimoIdMaquina)
                                    println("Exibindo Bytes Recebidos: ${bytesEnvConvertidos}MB")
                                    println("Exibindo Bytes Enviados: ${bytesReConvertidos}MB")
                                    Thread.sleep(1000)

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
