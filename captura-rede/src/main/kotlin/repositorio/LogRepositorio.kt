package repositorio

import config.DatabaseConfig

class LogRepositorio {

    private val jdbcTemplate = DatabaseConfig.jdbcTemplate

    fun capturaBytesEnviados(bytesEnviados: Long, fkComponente: Int, fkDispositivo: Int, alerta: Int): Boolean{
        val tempo = java.time.LocalDateTime.now()
        val qtdLinhasInseridas = jdbcTemplate.update(
            "INSERT INTO log (valor, unidadeDeMedida, dataHora, descricao, eAlerta, fkComponente, fkDispositivo) VALUES (?, 'MB', ?" +
                    ", 'BytesEnviados', ?, ?, ?)",
            bytesEnviados,
            tempo,
            alerta,
            fkComponente,
            fkDispositivo
        )
        return qtdLinhasInseridas > 0
    }

    fun capturaBytesRecebidos(bytesRecebidos: Long, fkComponente: Int, fkDispositivo: Int, alerta: Int): Boolean{
        val tempo = java.time.LocalDateTime.now()
        val qtdLinhasInseridas = jdbcTemplate.update(
            "INSERT INTO log (valor, unidadeDeMedida, dataHora, descricao, eAlerta, fkComponente, fkDispositivo) VALUES (?, 'MB', ?," +
                    "'BytesRecebidos', ?, ?, ?)",
            bytesRecebidos,
            tempo,
            alerta,
            fkComponente,
            fkDispositivo
        )
        return qtdLinhasInseridas > 0
    }
}