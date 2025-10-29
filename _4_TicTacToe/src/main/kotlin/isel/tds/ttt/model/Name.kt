package isel.tds.ttt.model

@JvmInline
value class Name(val value: String) {
    init { require( isValid(value) ) { "Invalid name $value" } }
    override fun toString() = value
    companion object {
        private fun isValid(value: String) =
            value.isNotBlank() && value.all { it.isLetterOrDigit() }
    }
}