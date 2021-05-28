package application

import loader.Loader
import logic.BaseRules
import pt.iscte.guitoo.board.Board

class BoardGUI {

    var rules = BaseRules()

    // 50 corresponde ah largura (pixels)das imagens fornecidas
    val board: Board = Board("Sokoban", rules.totalRows, rules.totalColumns, 50)

    init {
        // dada a coordenada (linha, coluna) devolve nome de imagem a mostrar
        board.setIconProvider { r, c ->
            rules.getImage(r, c)
        }

        // quando a posicao (linha, coluna) eh primida, executa a acao
        board.addMouseListener { r, c ->
            if (rules.hasEnded){
                board.showMessage(rules.endMessage)
                rules.reset()
            }
            rules.moveForklift(r, c)
        }

        // adiciona uma etiqueta contendo o texto fornecido (atualiazado sempre que ha uma accao/clique)
        board.addLabel { " Energy: ${rules.energy} " }

//        // adiciona um botao, que executa a acao fornecida
//        board.addAction("Limpar") {
//            val p: String = board.promptText("Limpar? S/N")
//            if(p == "S")
//                grid.forEach { it.fill(false) }
//        }
    }
    fun open() {
        // abre a janela
        board.open()
    }
}