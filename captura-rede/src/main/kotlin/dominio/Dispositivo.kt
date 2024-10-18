package dominio

class Dispositivo {
    var id: Int = 0

    var nome: String = ""
        private set
    var fkEmpresa: Int = 0
        private set

    fun setNome(novoNome: String){
        nome = novoNome
    }
    fun setfkEmpresa(novoFK: Int){
        fkEmpresa = novoFK
    }
}