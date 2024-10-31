package repositorio

import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate

class ComponenteRepositorio {
    lateinit var jdbcTemplate: JdbcTemplate

    fun configurar() {
        val datasource = BasicDataSource()
        datasource.driverClassName = "com.mysql.cj.jdbc.Driver"
        datasource.url = "jdbc:mysql://localhost/novascan"
        datasource.username = "root"
        datasource.password = "0105"
        jdbcTemplate = JdbcTemplate(datasource)
    }

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