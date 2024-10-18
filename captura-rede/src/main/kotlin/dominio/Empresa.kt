package dominio

class Empresa {
    var id: Int = 0

    var razaoSocial: String = ""
        private set
    var cnpj: Int= 0
        private set


    fun setRazaoSocial(novoRS: String): Empresa{
        razaoSocial = novoRS
        return this
    }

    fun setCNPJ(novoCNPJ: Int): Empresa{
        cnpj = novoCNPJ
        return this
    }
}