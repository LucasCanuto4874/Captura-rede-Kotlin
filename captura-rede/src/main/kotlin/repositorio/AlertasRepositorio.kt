package repositorio

import config.DatabaseConfig
import dominio.Alerta
import org.springframework.jdbc.core.BeanPropertyRowMapper

class AlertasRepositorio {

    private val jdbcTemplate = DatabaseConfig.jdbcTemplate

    fun buscarAlertasUsuario(idUsuario: Int, idDispositivo: Int): List<Alerta>{

        val listaAlertas = jdbcTemplate.query("""
        SELECT d.nome as nomeMaquina, a.minIntervalo, a.maxIntervalo, c.tipo as tipoComponente, 
               tp.tipo as tipoAlerta, a.fkUsuario, a.fkDispositivo
        FROM alerta as a 
        JOIN componente as c ON a.fkComponente = c.id
        JOIN tipoAlerta as tp ON a.fkTipoAlerta = tp.id
        JOIN dispositivo as d ON a.fkDispositivo = d.id
        JOIN usuario as u ON a.fkUsuario = u.id
        WHERE a.fkUsuario = ? AND a.fkDispositivo = ?
    """, { rs, _ ->
            Alerta(
                nomeMaquina = rs.getString("nomeMaquina"),
                minIntervalo = rs.getDouble("minIntervalo"),
                maxIntervalo = rs.getDouble("maxIntervalo"),
                tipoComponente = rs.getString("tipoComponente"),
                tipoAlerta = rs.getString("tipoAlerta"),
                fkUsuario = rs.getInt("fkUsuario"),
                fkDispositivo = rs.getInt("fkDispositivo")
            )
        }, idUsuario, idDispositivo)

//        fazendo um BeanRowmapper Personalizado para ele achar as colunas da consulta do MySql com exatamente esses nomes

        return listaAlertas
    }
}