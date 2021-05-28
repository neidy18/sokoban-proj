package logic

import loader.Loader

class BaseRules{

    var energy: Int = Loader.energy
        private set
    var totalRows = Loader.totalRows
        private set
    var totalColumns = Loader.totalColumns
        private set
    private var grid = Loader.grid

    private var _direction = 'N'
    private var _currentPosition = getForkliftCurrentDirection(grid)
    var hasEnded = false
        private set

    var endMessage = ""
        private set

    fun moveForklift(r: Int, c: Int){
        _currentPosition = getForkliftCurrentDirection(grid)
        _direction = getDirection(r,c)
        if (!hasEnded) {
            if (isBox(r , c)) {
                if (move(_direction , 'C' , Pair(r , c)))
                    if (move(_direction , 'E' , _currentPosition))
                        energy--
            } else {
                if (move(_direction , 'E' , _currentPosition))
                    energy--
            }
            if (energy <= 0) {
                hasEnded = true
                endMessage = "Game Over! :("
            }
            if ((countTarget() <= 0) and (!hasEnded)) {
                hasEnded = true
                endMessage = "You WIN!!! :D"
            }
        }
    }

    fun reset() {
        Loader.load()
        energy = Loader.energy
        totalRows = Loader.totalRows
        totalColumns = Loader.totalColumns
        grid = Loader.grid
        hasEnded = false
    }

    private fun isWall(r: Int, c: Int) : Boolean =
            grid[r][c] == '#'

    private fun isBox(r: Int, c: Int) : Boolean =
            grid[r][c] == 'C'

    private fun countTarget() : Int {

        var count = 0
        grid.forEach {
            it.forEach {
                if(it == 'X')
                    count++
            }
        }
        return count
    }

    private fun move(direction: Char , obj: Char , curPos: Pair<Int, Int>) : Boolean {
        var x = 0
        var y = 0
        var move = false
        when(direction){
            'D' -> {x = curPos.second; y = curPos.first + 1}
            'U' -> {x = curPos.second; y = curPos.first - 1}
            'R' -> {x = curPos.second + 1; y = curPos.first}
            'L' -> {x = curPos.second - 1; y = curPos.first}
            else -> grid[curPos.first][curPos.second]
        }
        if (!isWall(y, x))
            {
                grid[y][x] = obj
                grid[curPos.first][curPos.second] = ' '
                move = true
            }

        return move
    }

    private fun getDirection(r: Int , c: Int) : Char {
        if (r-1 >= 0) if (grid[r-1][c] == 'E') {
            return 'D'
        }
        if (r+1 < totalRows) if (grid[r+1][c] == 'E') {
            return 'U'
        }
        if (c-1 >= 0) if (grid[r][c-1] == 'E') {
            return 'R'
        }
        if (c+1 < totalColumns) if (grid[r][c+1] == 'E') {
            return 'L'
        }
        return 'N'
    }

    private fun getForkliftCurrentDirection(grid: MutableList<CharArray>) : Pair<Int, Int> {
        var y = 0
        var x = 0
        grid.forEach {
            it.forEach {
                if(it == 'E')
                    {return Pair(y,x)}
                x++
            }
            x = 0
            y++
        }
        return Pair(0,0)
    }

    fun getImage(r: Int, c: Int) : String =
        when(grid[r][c]){
            'C' -> "images/Caixote.png"
            'E' -> "images/Empilhadora_${if (_direction == 'N') 'D' else _direction}.png"
            '#' -> "images/Parede.png"
            'X' -> "images/Alvo.png"
            else -> "images/Chao.png"
        }
}