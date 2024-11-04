package repositorio

import config.DatabaseConfig


class UsuarioRepositorio {

    private val jdbcTemplate = DatabaseConfig.jdbcTemplate

    fun existeUsuario(email: String, senha: String): Int{
        val fkEmpresa = jdbcTemplate.queryForObject(
            "SELECT fkEmpresa FROM usuario WHERE email = ? AND senha = ?",
            Int::class.java,
            email,
            senha,
        )
        return fkEmpresa
    }
}