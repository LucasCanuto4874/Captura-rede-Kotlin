package dominio

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import Slack.AplicacaoSlack

class MensagemAlertas {
    private val slack = AplicacaoSlack()

    fun alertaBytesEnviadosPersonalizado(valorCapturado: Long) {
        val mensagem = slack.enviarMesagemslack("""
                                                        :warning: *Alerta Disparado de Bytes Enviados! (Alerta Personalizado)* :warning:
                                                        
                                                         🚨 Status: Alerta Disparado!
                                                         📊 Valor Capturado: $valorCapturado MB
                                                         ⏰ Hora: ${LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))}
                                                    """.trimIndent())
    }

    fun alertaBytesRecebidosPersonalizado(valorCapturado: Long){
        val mensagem = slack.enviarMesagemslack("""
                                                        :warning: *Alerta Disparado de Bytes Recebidos! (Alerta Personalizado)* :warning:
                                                        
                                                         🚨 Status: Alerta Disparado!
                                                         📊 Valor Capturado: $valorCapturado MB
                                                         ⏰ Hora: ${LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))}
                                                    """.trimIndent())
    }

    fun alertaBytesEnviadosPadraoSistema(valorCapturado: Long){
        val mensagem = slack.enviarMesagemslack("""
                                                        :warning: *Alerta Disparado de Bytes Enviados! (Alerta Padrão do Sistema)* :warning:
                                                        
                                                         🚨 Status: Alerta Disparado!
                                                         📊 Valor Capturado: $valorCapturado MB
                                                         ⏰ Hora: ${LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))}
                                                    """.trimIndent())
    }

    fun alertaBytesRecebidosPadraoSistema(valorCapturado: Long){
        val mensagem = slack.enviarMesagemslack("""
                                                        :warning: *Alerta Disparado de Bytes Recebidos! (Alerta Padrão do Sistema)* :warning:
                                                        
                                                         🚨 Status: Alerta Disparado!
                                                         📊 Valor Capturado: $valorCapturado MB
                                                         ⏰ Hora: ${LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))}
                                                    """.trimIndent())
    }


}