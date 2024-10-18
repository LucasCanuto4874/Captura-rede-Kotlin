package dominio

class Componente {
    var id: Int = 0
    var fkDispositivo: Int = 0
    private set
    var nome: String = ""
    private set
    var tipo: String =""
    private set

    fun setfkDispositivo(novofkDispositivo: Int): Componente{
        fkDispositivo = novofkDispositivo
        return this
    }
    fun setNome(novoNome: String): Componente{
        nome = novoNome
        return this
    }
    fun setTipo(novoTipo: String): Componente{
        tipo = novoTipo
        return this
    }
}