package lotto.model

interface RandomNumbersGeneratorWrapper {
    fun generateRandomNumbersInRange(): List<Int>
}

class RandomNumbersGeneratorWrapperImpl : RandomNumbersGeneratorWrapper {
    override fun generateRandomNumbersInRange(): List<Int> {
        val listOfNumbers = (LOWER_RANGE..UPPER_RANGE).shuffled().take(REQUIRED_COUNT)
        return listOfNumbers
    }

    companion object {
        private const val LOWER_RANGE = 1
        private const val UPPER_RANGE = 45
        private const val REQUIRED_COUNT = 6
    }
}
