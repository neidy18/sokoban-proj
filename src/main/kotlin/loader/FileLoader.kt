package loader

import java.io.File

interface FileLoader {
    // devolve a extensão de ficheiro associada
    val extension: String
    // constrói o objeto de jogo com a informação do ficheiro
    fun load(f: File)
}
