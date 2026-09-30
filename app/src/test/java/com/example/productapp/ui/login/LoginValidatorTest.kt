package com.example.productapp.ui.login

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class LoginValidatorTest {

    @Test
    fun `blank email returns required error`() {
        assertEquals("Email is required.", LoginValidator.validateEmail(""))
    }

    @Test
    fun `invalid email returns format error`() {
        assertEquals(
            "Enter a valid email address.",
            LoginValidator.validateEmail("not-an-email")
        )
    }

    @Test
    fun `valid email passes`() {
        assertNull(LoginValidator.validateEmail("test@example.com"))
    }

    @Test
    fun `blank password returns required error`() {
        assertEquals("Password is required.", LoginValidator.validatePassword(""))
    }

    @Test
    fun `short password returns length error`() {
        assertEquals(
            "Password must be at least 6 characters.",
            LoginValidator.validatePassword("123")
        )
    }

    @Test
    fun `correct mock credentials match`() {
        assertTrue(LoginValidator.credentialsMatch("test@example.com", "password123"))
    }

    @Test
    fun `wrong credentials do not match`() {
        assertFalse(LoginValidator.credentialsMatch("test@example.com", "wrong"))
    }
}
