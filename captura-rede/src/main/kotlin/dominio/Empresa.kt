package dominio

class Empresa {
    var id: Int = 0
    var razaoSocial: String = ""
        private set
    var cnpj: Int=0
        private set


    fun setRazaoSocial(novoRS: String){
        razaoSocial = novoRS
    }
    fun setCNPJ(novoCNPJ: Int){
        cnpj = novoCNPJ
    }

    fun getId(): Int{
        return id
    }
    fun getRazaoSocial(): String{
        return razaoSocial
    }
    fun getCNPJ(): Int{
        return cnpj
    }
}