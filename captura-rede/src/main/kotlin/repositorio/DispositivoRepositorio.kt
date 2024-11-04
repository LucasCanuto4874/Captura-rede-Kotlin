package repositorio

import dominio.Usuario
import io.github.cdimascio.dotenv.Dotenv
import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject

class DispositivoRepositorio {

    private val jdbcTemplate = DatabaseConfig.jdbcTemplate

    fun buscarUltimoDispositivo(fkEmpresa: Int): Int{
        val ultimoDispositivo = jdbcTemplate.queryForObject(
            "SELECT max(id) FROM dispositivo WHERE fkEmpresa = ?",
            Int::class.java,
            fkEmpresa
        )

        return ultimoDispositivo
    }
}