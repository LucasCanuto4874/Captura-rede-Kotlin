package dominio

class Usuario {
    var id: Int = 0

    var nome: String = ""
        private set

    var email: String = ""
        private set

    var senha: String = ""


    fun setNome(novoNome: String){
        nome = novoNome
    }

    fun setEmail(novoEmail: String){
        email = novoEmail
    }

    fun setSenha(novoEmail: String){
        email = novoEmail
    }


    fun getId():Int{
        return id
    }

    fun getNome(): String{
        return nome
    }

    fun getEmail(): String{
        return email
    }

    fun getSenha(): String{
        return senha
    }
}