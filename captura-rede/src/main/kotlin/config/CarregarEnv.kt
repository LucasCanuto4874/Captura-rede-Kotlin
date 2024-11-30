package config
import java.util.Properties


class CarregarEnv {
    fun carregarArquivoEnv() {
        val props = Properties()
        val inputStream = Thread.currentThread().contextClassLoader.getResourceAsStream(".env")
        if (inputStream != null) {
            props.load(inputStream)
            for ((key, value) in props) {
                println("$key = $value") // Opcional: Exibir para validação
                System.setProperty(key.toString(), value.toString())
            }
        } else {
            println("Arquivo .env não encontrado no JAR!")
        }
    }
}