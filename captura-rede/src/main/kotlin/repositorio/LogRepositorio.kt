package repositorio

import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate

class LogRepositorio {
    lateinit var jdbcTemplate: JdbcTemplate

    fun configurar() {
        val datasource = BasicDataSource()
        datasource.driverClassName = "com.mysql.cj.jdbc.Driver"
        datasource.url = "jdbc:mysql://localhost/novascan"
        datasource.username = "root"
        datasource.password = "0105"
        jdbcTemplate = JdbcTemplate(datasource)
    }

    fun capturaBytesEnviados(bytesEnviados: Long, fkComponente: Int, fkDispositivo: Int): Boolean{
        val tempo = java.time.LocalDateTime.now()
        val qtdLinhasInseridas = jdbcTemplate.update(
            "INSERT INTO log (valor, unidadeDeMedida, dataHora, descricao, fkComponente, fkDispositivo) VALUES (?, 'MB', ?" +
                    ", 'BytesEnviados', ?, ?)",
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
            "INSERT INTO log (valor, unidadeDeMedida, dataHora, descricao, fkComponente, fkDispositivo) VALUES (?, 'MB' ?," +
                    "'BytesRecebidos', ?, ?)",
            bytesRecebidos,
            tempo,
            fkComponente,
            fkDispositivo
        )
        return qtdLinhasInseridas > 0
    }
}