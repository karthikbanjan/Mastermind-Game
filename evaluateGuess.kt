//https://github.com/karthikbanjan
package mastermind

data class Evaluation(var rightPosition: Int, var wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    val e = Evaluation(0,0)
    val guessMap = guess.groupingBy { it }.eachCount().toMutableMap()
    val secretMap = secret.groupingBy { it }.eachCount()
    guessMap.forEach {
        guessMap[it.key] = 0
    }
    for(i in guess.indices) {
        if (guess[i] == secret[i]) {
            e.rightPosition++
            guessMap[guess[i]] = guessMap[guess[i]]!! + 1
        }
    }
    for(i in guess.indices) {
        if((guess[i] in secret) && (guess[i] != secret[i]) && (guessMap[guess[i]]!! < secretMap[guess[i]]!!)) {
            e.wrongPosition++
            guessMap[guess[i]] = guessMap[guess[i]]!! + 1
        }
    }
    return e
}
