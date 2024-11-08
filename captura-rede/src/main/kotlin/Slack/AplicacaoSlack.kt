package Slack

import com.slack.api.Slack
import com.slack.api.methods.SlackApiException
import com.slack.api.methods.request.chat.ChatPostMessageRequest
import config.DatabaseConfig
import java.lang.Exception

class AplicacaoSlack {

    fun enviarMesagemslack( mensagem: String){
        val slack = Slack.getInstance()
        val token = DatabaseConfig.getDotEnv()["API_SLACK"]
        try {
            val resposta = slack.methods(token).chatPostMessage{
                it.channel("#alertas-dashboard-novascan").text(mensagem)
            }
            if(resposta.isOk){
                println("Alerta Enviado com sucesso")
            }
            else{
                println("Erro: ${resposta.error}")
            }
        }
        catch (error: SlackApiException){
            error.printStackTrace()
        }
        catch(error: Exception){
            error.printStackTrace()
        }
    }
}