package repositorio

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
            "SELECT max(id) FROM componente WHERE fkDispositivo = ?",
            Int::class.java,
            fkDispositivo,
        )
        return idComponente
    }
}