<script lang="ts">
	import { authenticationService } from "../../services/authentication/authentication-service";
	import Login from "./login/login.svelte";
	import Logout from "./logout.svelte";
	import Register from "./register/register.svelte";
    const authUserChangesObservable = authenticationService.authUserChanges
</script>

{#if $authUserChangesObservable != null}
    <Logout/>
{:else}
    <div>
        <Login/>
        <Register/>
    </div>
{/if}