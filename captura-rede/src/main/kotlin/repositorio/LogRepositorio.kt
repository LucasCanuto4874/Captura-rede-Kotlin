package repositorio

import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate

class LogRepositorio {
    lateinit var jdbcTemplate: JdbcTemplate

    fun configurar() {
        val datasource = BasicDataSource()
        datasource.driverClassName = "com.mysql.cj.jdbc.Driver"
        datasource.url = "jdbc:mysql://localhost:3306/novascan"
        datasource.username = "root"
        datasource.password = "0105"
        jdbcTemplate = JdbcTemplate(datasource)
    }

    fun capturaBytesEnviados(bytesEnviados: Int, fkComponente: Int, fkDispositivo: Int): Boolean{
        val qtdLinhasInseridas = jdbcTemplate.update(
            "INSERT INTO componente (valor, dataHora, descricao, fkComponente, fkDispositivo) VALUES (?, " +
                    "current_timestamp(), 'BytesEnviados', ?, ?)",
            bytesEnviados,
            fkComponente,
            fkDispositivo
        )
        return qtdLinhasInseridas > 0
    }

    fun capturaBytesRecebidos(bytesEnviados: Int, fkComponente: Int, fkDispositivo: Int): Boolean{
        val qtdLinhasInseridas = jdbcTemplate.update(
            "INSERT INTO componente (valor, dataHora, descricao, fkComponente, fkDispositivo) VALUES (?, " +
                    "current_timestamp(), 'BytesRecebidos', ?, ?)",
            bytesEnviados,
            fkComponente,
            fkDispositivo
        )
        return qtdLinhasInseridas > 0
    }
}