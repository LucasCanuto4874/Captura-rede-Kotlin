package repositorio

import config.DatabaseConfig

class ComponenteRepositorio {

    private val jdbcTemplate = DatabaseConfig.jdbcTemplate

    fun inserirComponente(fkDispositivo: Int, nome: String): Boolean{
        val qtdLinhasInseridas = jdbcTemplate.update(
            "INSERT INTO componente (fkDispositivo, nome, tipo) VALUES (?, ?, 'Placa de Rede')",
            fkDispositivo,
            nome
        )

        return qtdLinhasInseridas > 0
    }

    fun buscarUltimoComponente(fkDispositivo: Int): Int{
        val idComponente = jdbcTemplate.queryForObject(
            "SELECT id FROM ultimoComponente WHERE tipo = 'Placa de Rede' AND fkDispositivo = ?",
            Int::class.java,
            fkDispositivo,
        )
        return idComponente
    }
}