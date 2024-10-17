package dominio

class Componentes {
    var id: Int = 0
    var fkDispositivo: Int = 0
    private set
    var nome: String = ""
    private set
    var tipo: String =""
    private set

    fun setfkDispositivo(novofkDispositivo: Int){
        fkDispositivo = novofkDispositivo
    }
    fun setNome(novoNome: String){
        nome = novoNome
    }
    fun setTipo(novoTipo: String){
        tipo = novoTipo
    }
}