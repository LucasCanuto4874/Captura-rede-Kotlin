package repositorio

import dominio.Usuario
import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate

class Dispositivo {

    lateinit var jdbcTemplate: JdbcTemplate

    fun configurar() {
        val datasource = BasicDataSource()
        datasource.driverClassName = "com.mysql.cj.jdbc.Driver"
        datasource.url = "jdbc:mysql://localhost:3306/novaScan"
        datasource.username = "root"
        datasource.password = "CivicSi2007"
        jdbcTemplate = JdbcTemplate(datasource)
    }

    fun buscarUltimoDispositivo(fkEmpresa: Usuario): Dispositivo{
        val ultimoDispositivo = jdbcTemplate.queryForObject(
            "SELECT "
        )
    }
}