<script lang="ts">
	import { closeModal } from 'svelte-modals';
    import { form, field } from 'svelte-forms';
    import { required } from 'svelte-forms/validators'
	import { authenticationService } from '../../../services/authentication/authentication-service';
	import { isError } from '../../../typescript/result';

	export let isOpen: boolean
    const name = field('name', '', [required()])
    const password = field('password', '', [required()])
    const loginForm = form(name, password)
    loginForm.validate()
    let disabled = false
    $: shouldDisable = disabled || !$loginForm.valid
    async function login() {
        disabled = true
        const result = await authenticationService.login($name.value, $password.value)
        if (isError(result)) {
            disabled = false
            alert(result.error)
            return
        }
        closeModal()
    }
</script>

{#if isOpen}
	<div role="dialog" class="modal">
		<div class="contents">
			<form>
                <input bind:value={$name.value} type="text" placeholder="Nome">
                <input bind:value={$password.value} type="password" placeholder="Senha">
            </form>
			<div class="actions">
                <button disabled={disabled} on:click={closeModal}>Cancelar</button>
				<button disabled={shouldDisable} on:click={login}>Login</button>
			</div>
		</div>
	</div>
{/if}

<style>
	.modal {
		position: fixed;
		top: 0;
		bottom: 0;
		right: 0;
		left: 0;
		display: flex;
		justify-content: center;
		align-items: center;

		/* allow click-through to backdrop */
		pointer-events: none;
	}

	.contents {
		min-width: 240px;
		border-radius: 6px;
		padding: 16px;
		background: white;
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		pointer-events: auto;
	}

	.actions {
		margin-top: 32px;
		display: flex;
		justify-content: space-around;
	}
</style>
