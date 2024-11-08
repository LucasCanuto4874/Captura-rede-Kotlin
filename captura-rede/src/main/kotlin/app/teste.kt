package app
import Slack.AplicacaoSlack

fun main() {
    val mensagem = "Olá Slack"

    val slack = AplicacaoSlack()

    slack.enviarMesagemslack(mensagem)
}