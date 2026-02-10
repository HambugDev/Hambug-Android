package desktop.hambug.util

interface TestEnvironment {
    fun isFakeTest(): Boolean
}

class ProductionTestEnvironment : TestEnvironment {
    override fun isFakeTest(): Boolean = false
}
