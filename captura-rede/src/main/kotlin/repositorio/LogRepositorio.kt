package repositorio

import io.github.cdimascio.dotenv.Dotenv
import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate

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