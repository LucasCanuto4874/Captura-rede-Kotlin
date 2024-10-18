package dominio

class Usuario {
    var email: String = ""
        private set

    var senha: String = ""
        private set

    fun setEmail(novoEmail: String): Usuario{
        email = novoEmail
        return this
    }

    fun setSenha(novoSenha: String): Usuario{
        senha = novoSenha
        return this
    }
}