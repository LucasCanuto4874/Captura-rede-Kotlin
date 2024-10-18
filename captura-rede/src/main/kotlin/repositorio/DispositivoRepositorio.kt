package repositorio

import dominio.Dispositivo
import dominio.Usuario
import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject

class DispositivoRepositorio {

    lateinit var jdbcTemplate: JdbcTemplate

    fun configurar() {
        val datasource = BasicDataSource()
        datasource.driverClassName = "com.mysql.cj.jdbc.Driver"
        datasource.url = "jdbc:mysql://localhost:3306/novascan"
        datasource.username = "root"
        datasource.password = "CivicSi2007"
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