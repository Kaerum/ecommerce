<script lang="ts">
	import { authenticationService } from "../../services/authentication/authentication-service";


    function logout() {
        authenticationService.logout()
    }
</script>

<button on:click={logout}>Logout</button>