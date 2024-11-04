package repositorio

import config.DatabaseConfig

class LogRepositorio {

    private val jdbcTemplate = DatabaseConfig.jdbcTemplate

    fun capturaBytesEnviados(bytesEnviados: Long, fkComponente: Int, fkDispositivo: Int): Boolean{
        val tempo = java.time.LocalDateTime.now()
        val qtdLinhasInseridas = jdbcTemplate.update(
            "INSERT INTO log (valor, unidadeDeMedida, dataHora, descricao, eAlerta, fkComponente, fkDispositivo) VALUES (?, 'MB', ?" +
                    ", 'BytesEnviados', 0, ?, ?)",
            bytesEnviados,
            tempo,
            fkComponente,
            fkDispositivo
        )
        return qtdLinhasInseridas > 0
    }

    fun capturaBytesRecebidos(bytesRecebidos: Long, fkComponente: Int, fkDispositivo: Int): Boolean{
        val tempo = java.time.LocalDateTime.now()
        val qtdLinhasInseridas = jdbcTemplate.update(
            "INSERT INTO log (valor, unidadeDeMedida, dataHora, descricao, eAlerta, fkComponente, fkDispositivo) VALUES (?, 'MB', ?," +
                    "'BytesRecebidos', 0, ?, ?)",
            bytesRecebidos,
            tempo,
            fkComponente,
            fkDispositivo
        )
        return qtdLinhasInseridas > 0
    }
}