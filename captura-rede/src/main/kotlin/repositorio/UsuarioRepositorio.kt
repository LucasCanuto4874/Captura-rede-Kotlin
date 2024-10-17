package repositorio

import dominio.Usuario
import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject


class UsuarioRepositorio {

    lateinit var jdbcTemplate: JdbcTemplate

    fun configurar() {
            val datasource = BasicDataSource()
            datasource.driverClassName = "com.mysql.cj.jdbc.Driver"
            datasource.url = "jdbc:mysql://localhost:3306/novaScan"
            datasource.username = "root"
            datasource.password = "CivicSi2007"
            jdbcTemplate = JdbcTemplate(datasource)
    }

    fun existeUsuario(usuario: Usuario): Usuario{
        val fkEmpresa = jdbcTemplate.queryForObject(
            "SELECT fkEmpresa FROM usuario WHERE email = ? AND senha = ?",
            Usuario::class.java,
            usuario.email,
            usuario.senha
        )

        return fkEmpresa
    }
}