package dominio

class Log {
    var id: Int = 0
    var fkComponente: Int = 0
        private set
    var fkDispositivo: Int = 0
        private set
    var valor: Int = 0
        private set
    var dataHora: String = ""
        private set
    var descricao: String = ""
        private set
    var fkAlerta: Int = 0
        private set


    fun setfkComponente(novofkComponente: Int){
        fkComponente = novofkComponente
    }
    fun setfkDispositivo(novofkDispositivo: Int){
        fkDispositivo = novofkDispositivo
    }
    fun setValor(novoValor: Int){
        valor = novoValor
    }
    fun setDataHora(novoDataHora: String){
        dataHora = novoDataHora
    }
    fun setDescricao(novoDescricao: String){
        descricao = novoDescricao
    }
    fun setAlerta(novoAlerta: Int){
        fkAlerta = novoAlerta
    }

}