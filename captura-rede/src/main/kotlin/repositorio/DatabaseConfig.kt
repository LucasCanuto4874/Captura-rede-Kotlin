package repositorio

import io.github.cdimascio.dotenv.Dotenv
import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate


object DatabaseConfig {
    val jdbcTemplate: JdbcTemplate

    init {
        val dotenv = Dotenv.configure()
            .filename(".env")
            .directory("src/main/kotlin/repositorio/.env")
            .load()

        val datasource = BasicDataSource()
        datasource.driverClassName = dotenv["DATABASE_DRIVER"]
        datasource.url = dotenv["DATABASE_URL"]
        datasource.username = dotenv["DATABASE_USER"]
        datasource.password = dotenv["DATABASE_PASSWORD"]

        jdbcTemplate = JdbcTemplate(datasource)
    }
}