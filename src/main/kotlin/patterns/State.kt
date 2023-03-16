import org.junit.jupiter.api.Test

sealed class AuthorizationState

object Unauthorized : AuthorizationState()

class Authorized(val userName: String) : AuthorizationState()

class AuthorizationPresenter {

    private var state: AuthorizationState = Unauthorized

    val isAuthorized: Boolean
        get() = when (state) {
            is Authorized -> true
            is Unauthorized -> false
        }

    val userName: String
        get() {
            return when (val state = this.state) { //val enables smart casting of state
                is Authorized -> state.userName
                is Unauthorized -> "Unknown"
            }
        }

    fun loginUser(userName: String) {
        state = Authorized(userName)
    }

    fun logoutUser() {
        state = Unauthorized
    }

    override fun toString() = "User '$userName' is logged in: $isAuthorized"
}

class StateTest {

    @Test
    fun State() {
        val authorizationPresenter = AuthorizationPresenter()

        authorizationPresenter.loginUser("admin")
        println(authorizationPresenter)
        assert(authorizationPresenter.isAuthorized == true)
        assert(authorizationPresenter.userName == "admin")

        authorizationPresenter.logoutUser()
        println(authorizationPresenter)
        assert(authorizationPresenter.isAuthorized == false)
        assert(authorizationPresenter.userName == "Unknown")
    }
}

