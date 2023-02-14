<script lang="ts">
	import { closeModal } from 'svelte-modals';
    import { form, field } from 'svelte-forms';
    import { required } from 'svelte-forms/validators'
	import { authenticationService } from '../../../services/authentication/authentication-service';
	import { isError } from '../../../typescript/result';

	export let isOpen: boolean
    const name = field('name', '', [required()])
    const password = field('password', '', [required()])
    const userType = field('userType', 'USER', [required()])
    const isCnpj = field('isCnpj', false, [required()])
    const registerForm = form(name, password, userType, isCnpj)
    registerForm.validate()
    let disabled = false
    $: shouldDisable = disabled || !$registerForm.valid
    async function register() {
        disabled = true
        const result = await authenticationService.register($name.value, $password.value, $userType.value, $isCnpj.value)
        if (isError(result)) {
            disabled = false
            alert(result.error)
            return
        }
        alert("Sucesso!")
        closeModal()
    }
</script>

{#if isOpen}
	<div role="dialog" class="modal">
		<div class="contents">
			<form>
                <input bind:value={$name.value} type="text" placeholder="Nome">
                <input bind:value={$password.value} type="password" placeholder="Senha">
                <select bind:value={$userType.value} placeholder="Tipo de usuário">
                    <option value="USER">Usuário</option>
                    <option value="ADMIN">Administrador</option>
                </select>
                <div>
                    <label for="checkbox">É CNPJ?</label>
                    <input name="checkbox" id="checkbox" bind:value={$isCnpj.value} type="checkbox">
                </div>
            </form>
			<div class="actions">
                <button disabled={disabled} on:click={closeModal}>Cancelar</button>
				<button disabled={shouldDisable} on:click={register}>Cadastrar</button>
			</div>
		</div>
	</div>
{/if}

<style lang="scss">
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

        form {
            padding: var(--default-padding);
            display: flex;
            flex-direction: column;
            * {
                margin: var(--default-spacing);
            }
        }
	}

	.actions {
		margin-top: var(--default-spacing);
		display: flex;
		justify-content: space-around;
	}
</style>
