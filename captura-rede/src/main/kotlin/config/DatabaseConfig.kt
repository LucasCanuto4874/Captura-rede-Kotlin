package config

import io.github.cdimascio.dotenv.Dotenv
import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate


object DatabaseConfig {

    private val dotenv = Dotenv.configure()
        .filename(".env")
        .load()

    val jdbcTemplate: JdbcTemplate

    init {
        val datasource = BasicDataSource()
        datasource.driverClassName = dotenv["DATABASE_DRIVER"]
        datasource.url = dotenv["DATABASE_URL"]
        datasource.username = dotenv["DATABASE_USER"]
        datasource.password = dotenv["DATABASE_PASSWORD"]

        jdbcTemplate = JdbcTemplate(datasource)
    }

    //retornando dotenv para ter acesso ao arquivo importando para a main
    fun getDotEnv(): Dotenv{
        return dotenv
    }
}