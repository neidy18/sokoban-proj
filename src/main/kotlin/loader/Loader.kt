package loader

import java.io.File
import java.util.*

class Loader(val file : String) {

    companion object {

        var grid: MutableList<CharArray> = mutableListOf()
            private set
        var energy = 0
            private set
        var totalRows = 0
            private set
        var totalColumns = 0
            private set

        init {
            load()
        }

        fun load(){
            var newgrid = mutableListOf<CharArray>()
            val scanner = Scanner(File("sokoban.sok"))
            energy = scanner.nextLine().toInt()
            totalRows = scanner.nextLine().toInt()
            totalColumns = scanner.nextLine().toInt()
            while (scanner.hasNext()) {
                newgrid.add(scanner.nextLine().toCharArray())
            }
            grid = newgrid
            scanner.close()
        }
    }
}