<script lang="ts">
	import WrappingList from "../wrapping-list.svelte";
	import type { Product } from "../../services/models/product/product-model";
	import ProductDisplay from "./product-display.svelte";
	import { productServiceInstance } from "../../services/models/product/product-service";
	import { permissionService } from "../../services/permissions/permission-service";
	import { AUTHORITIES } from "../../services/authentication/authenticated-user-model";
	import type { IdentifiedEntity } from "../../services/models/entity";
	import { openModal } from "svelte-modals";
	import ProductCreate from "./product-create/product-create.modal.svelte";
	import { cartService } from "../../services/cart/cart-service";
    
    const permissionsObservable = permissionService.permissionsChanges
    const cartStore = cartService.onCartChanges

    // Sort e filtragem
    let sortField = 'nome'
    let sortDirection: 'ASC' | 'DESC' = 'DESC'
    let page = 0
    let lastPage = 0
    let listSize = 50
    let items: IdentifiedEntity<Product>[] = []
    $: sort = { chave: sortField, direcao: sortDirection }
    $: productServiceInstance.list([], [sort], page, listSize)
        .then(list => {
            lastPage = list.totalPages
            items = list.content
        })

    async function deleteId(event: CustomEvent<number>) {
        await productServiceInstance.delete(event.detail)
        reloadList()
    }

    async function reloadList() {
        const result = await productServiceInstance.list([], [sort], page, listSize)
        lastPage = result.totalPages
        items = result.content
    }

    function openCreateProductModal() {
        openModal(ProductCreate, { callback: afterProductCreateClosed })
    }

    function afterProductCreateClosed(created: boolean) {
        if (created) {
            reloadList()
        }
    }
</script>

<div class="list-control default-box-shadow">
    <div class="options">
        <select bind:value={sortField} placeholder="Campo de ordenação">
            <option value="nome">Nome</option>
            <option value="preco" >Preco</option>
        </select>
        <select bind:value={sortDirection} placeholder="Ordenação">
            <option value="DESC">Decrescente</option>
            <option value="ASC">Crescente</option>
        </select>
    </div>
    <div class="actions">
        {#if $permissionsObservable.hasAuthority(AUTHORITIES.ADMIN)}
            <button on:click={openCreateProductModal}>Cadastrar produto</button>
        {/if}
    </div>
</div>
<WrappingList {items}>
    <span slot="item" let:item>
        <ProductDisplay on:delete={deleteId} product={item}/>
    </span>
    <div slot="empty">
        Nenhum produto foi encontrado!
    </div>
</WrappingList>
<div class="page-control">
    
</div>

<style lang="scss">
    .list-control {
        width: 100%;
        margin: var(--default-padding) 0;
        padding: var(--default-padding);
        display: flex;
        justify-content: space-between;
    }
</style>