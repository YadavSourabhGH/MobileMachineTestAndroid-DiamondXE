package com.example.productapp.ui.login

/** Mock credentials as per the machine-test spec. */
object MockAuth {
    const val VALID_EMAIL = "test@example.com"
    const val VALID_PASSWORD = "password123"
}

data class LoginFormState(
    val emailError: String? = null,
    val passwordError: String? = null,
    val authError: String? = null
)

/** Pure-Kotlin validation helpers — no Android dependencies, easy to unit test. */
object LoginValidator {

    // Simple, practical email pattern (local-part@domain.tld).
    private val EmailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

    fun validateEmail(email: String): String? {
        if (email.isBlank()) return "Email is required."
        if (!EmailRegex.matches(email.trim())) {
            return "Enter a valid email address."
        }
        return null
    }

    fun validatePassword(password: String): String? {
        if (password.isBlank()) return "Password is required."
        if (password.length < 6) return "Password must be at least 6 characters."
        return null
    }

    fun credentialsMatch(email: String, password: String): Boolean {
        return email.trim().equals(MockAuth.VALID_EMAIL, ignoreCase = true) &&
            password == MockAuth.VALID_PASSWORD
    }
}
