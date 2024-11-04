package repositorio

import dominio.Usuario
import io.github.cdimascio.dotenv.Dotenv
import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate


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