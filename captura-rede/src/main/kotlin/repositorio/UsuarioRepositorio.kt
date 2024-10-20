package repositorio

import dominio.Usuario
import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate


class UsuarioRepositorio {

    lateinit var jdbcTemplate: JdbcTemplate

    fun configurar() {
            val datasource = BasicDataSource()
            datasource.driverClassName = "com.mysql.cj.jdbc.Driver"
            datasource.url = "jdbc:mysql://44.211.223.18:3306/novaScan"
            datasource.username = "root"
            datasource.password = "urubu100"
            jdbcTemplate = JdbcTemplate(datasource)
    }

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