<script lang="ts">
	import { closeModal } from 'svelte-modals';
    import { form, field } from 'svelte-forms';
    import { min, required } from 'svelte-forms/validators'
	import { isError } from '../../../typescript/result';
	import { productServiceInstance } from '../../../services/models/product/product-service';
	import type { Product } from '../../../services/models/product/product-model';

	export let isOpen: boolean
    export let callback: (saved: boolean) => void = () => {}

    const name = field('name', '', [required()])
    const descricao = field('descricao', '', [required()])
    const categoria = field('categoria', '', [required()])
    const marca = field('marca', '', [required()])
    const preco = field('preco', 0, [required(), min(0)])
    const peso = field('peso', 0, [required(), min(0)])

    const createForm = form(name, descricao, categoria, marca, preco, peso)
    createForm.validate()
    let disabled = false
    $: shouldDisable = disabled || !$createForm.valid
    async function cadastrar() {
        disabled = true
        const result = await productServiceInstance.save({
            nome: $name.value,
            descricao: $descricao.value,
            categoria: $categoria.value,
            marca: $marca.value,
            preco: $preco.value,
            peso: $peso.value
        })
        if (isError(result)) {
            disabled = false
            alert(result.error)
            callback(false)
            return
        }
        callback(true)
        closeModal()
    }
</script>

{#if isOpen}
	<div role="dialog" class="modal">
		<div class="contents">
			<form>
                <input bind:value={$name.value} type="text" placeholder="Nome">
                <input bind:value={$descricao.value} type="text" placeholder="Descrição">
                <input bind:value={$categoria.value} type="text" placeholder="Categoria">
                <input bind:value={$marca.value} type="text" placeholder="Marca">
                <input bind:value={$preco.value} type="number" placeholder="Preço">
                <input bind:value={$peso.value} type="number" placeholder="Peso">
            </form>
			<div class="actions">
                <button disabled={disabled} on:click={closeModal}>Cancelar</button>
				<button disabled={shouldDisable} on:click={cadastrar}>Cadastrar produto</button>
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
