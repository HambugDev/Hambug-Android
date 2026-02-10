package desktop.hambug.util

class FakeTestEnvironment : TestEnvironment {
    override fun isFakeTest(): Boolean = true
}
