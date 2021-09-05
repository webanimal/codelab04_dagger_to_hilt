package com.example.android.dagger.user

import com.example.android.dagger.storage.FakeStorage
import com.example.android.dagger.storage.Storage
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UserManagerTest {
	
	private lateinit var storage: Storage
	private lateinit var userManager: UserManager
	
	@Before
	fun setup() {
		storage = FakeStorage()
		userManager = UserManager(storage, UserDataRepository())
	}
	
	@Test
	fun `Username returns what is in the storage`() {
		assertEquals("", userManager.userName)
		
		userManager.registerUser("username", "password")
		
		assertEquals("username", userManager.userName)
	}
	
	@Test
	fun `IsUserRegistered behaves as expected`() {
		assertFalse(userManager.isUserRegistered())
		
		userManager.registerUser("username", "password")
		
		assertTrue(userManager.isUserRegistered())
	}
	
	@Test
	fun `Register user adds username and password to the storage`() {
		assertFalse(userManager.isUserRegistered())
		assertFalse(userManager.isUserLoggedIn())
		
		userManager.registerUser("username", "password")
		
		assertTrue(userManager.isUserRegistered())
		assertTrue(userManager.isUserLoggedIn())
		assertEquals("username", storage.getString("registered_user"))
		assertEquals("password", storage.getString("usernamepassword"))
	}
	
	@Test
	fun `Login succeeds when username is registered and password is correct`() {
		userManager.registerUser("username", "password")
		userManager.logout()
		
		assertTrue(userManager.loginUser("username", "password"))
		assertTrue(userManager.isUserLoggedIn())
	}
	
	@Test
	fun `Login fails when username is not registered`() {
		userManager.registerUser("username", "password")
		userManager.logout()
		
		assertFalse(userManager.loginUser("username2", "password"))
		assertFalse(userManager.isUserLoggedIn())
	}
	
	@Test
	fun `Login fails when username is registered but password is incorrect`() {
		userManager.registerUser("username", "password")
		userManager.logout()
		
		assertFalse(userManager.loginUser("username", "password2"))
		assertFalse(userManager.isUserLoggedIn())
	}
	
	@Test
	fun `Unregister behaves as expected`() {
		userManager.registerUser("username", "password")
		assertTrue(userManager.isUserLoggedIn())
		
		userManager.unregister()
		assertFalse(userManager.isUserLoggedIn())
		assertFalse(userManager.isUserRegistered())
	}
}