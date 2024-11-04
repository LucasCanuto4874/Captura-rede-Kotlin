package repositorio

import config.DatabaseConfig

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