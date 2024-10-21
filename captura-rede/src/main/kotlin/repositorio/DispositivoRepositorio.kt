package repositorio

import dominio.Usuario
import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject

class DispositivoRepositorio {

    lateinit var jdbcTemplate: JdbcTemplate

    fun configurar() {
        val datasource = BasicDataSource()
        datasource.driverClassName = "com.mysql.cj.jdbc.Driver"
        datasource.url = "jdbc:mysql://44.211.223.18:3306/novaScan"
        datasource.username = "root"
        datasource.password = "urubu100"
        jdbcTemplate = JdbcTemplate(datasource)
    }

    fun buscarUltimoDispositivo(fkEmpresa: Int): Int{
        val ultimoDispositivo = jdbcTemplate.queryForObject(
            "SELECT max(id) FROM dispositivo WHERE fkEmpresa = ?",
            Int::class.java,
            fkEmpresa
        )

        return ultimoDispositivo
    }
}