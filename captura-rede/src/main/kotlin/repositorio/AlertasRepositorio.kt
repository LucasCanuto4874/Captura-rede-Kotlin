package repositorio

import config.DatabaseConfig
import org.springframework.jdbc.core.BeanPropertyRowMapper

class AlertasRepositorio {

    private val jdbcTemplate = DatabaseConfig.jdbcTemplate

    fun buscarAlertasUsuario(idUsuario: Int, idDispositivo: Int): List<Any>{

        val listaAlertas = jdbcTemplate.query("""
           SELECT * FROM alertaUsuario WHERE fkUsuario = ? AND fkDispositivo = ?; 
        """,BeanPropertyRowMapper(Any::class.java),
            idUsuario,
            idDispositivo)

        return listaAlertas
    }
}