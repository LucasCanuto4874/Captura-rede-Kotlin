package repositorio

import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate

class ComponenteRepositorio {
    lateinit var jdbcTemplate: JdbcTemplate

    fun configurar() {
        val datasource = BasicDataSource()
        datasource.driverClassName = "com.mysql.cj.jdbc.Driver"
        datasource.url = "jdbc:mysql://54.166.119.68:3306/novaScan"
        datasource.username = "root"
        datasource.password = "urubu100"
        jdbcTemplate = JdbcTemplate(datasource)
    }

    fun inserirComponente(fkDispositivo: Int, nome: String): Boolean{
        val qtdLinhasInseridas = jdbcTemplate.update(
            "INSERT INTO componentes (fkDispositivo, nome, tipo) VALUES (?, ?, 'Placa de Rede')",
            fkDispositivo,
            nome
        )

        return qtdLinhasInseridas > 0
    }

    fun buscarUltimoComponente(fkDispositivo: Int): Int{
        val idComponente = jdbcTemplate.queryForObject(
            "SELECT max(id) FROM componentes WHERE fkDispositivo = ?",
            Int::class.java,
            fkDispositivo,
        )
        return idComponente
    }
}