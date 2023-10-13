import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.visprog_labweek5.UIState.Game
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class Soal1ViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(Game())
    val uiState: StateFlow<Game> = _uiState.asStateFlow()

    private val random = Random(System.currentTimeMillis())

    init {
        random()
    }

    fun random() {
        val randomValue = (1..10).random()
        _uiState.update { current -> current.copy(random = randomValue) }
    }

    fun reduceChance() {
        val chance = _uiState.value.chance + 1
        _uiState.update { current -> current.copy(chance = chance) }
    }

    fun scoreIncrease() {
        val score = _uiState.value.score + 1
        _uiState.update { current -> current.copy(score = score) }
    }

    fun answer(value: Int) {
        _uiState.update { current -> current.copy(answer = value) }
    }

    fun gameOver() {
        _uiState.update { current -> current.copy(gameOver = true) }
    }

    fun gameReset(){
        _uiState.update { current -> current.copy(chance = 0) }
        _uiState.update { current -> current.copy(score = 0) }
        _uiState.update { current -> current.copy(gameOver = false) }
        random()
    }

    fun play() {
        if (_uiState.value.random == _uiState.value.answer) {
            scoreIncrease()
            random()
            if (_uiState.value.chance == 3 || _uiState.value.score == 3) {
                gameOver()
            }
        } else {
            reduceChance()
            if (_uiState.value.chance == 3 || _uiState.value.score == 3) {
                gameOver()
            }
        }
    }
}
