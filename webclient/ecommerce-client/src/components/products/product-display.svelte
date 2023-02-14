<script lang="ts">
	import { createEventDispatcher } from "svelte";
	import { AUTHORITIES } from "../../services/authentication/authenticated-user-model";
	import { cartService } from "../../services/cart/cart-service";
	import type { IdentifiedEntity } from "../../services/models/entity";
	import type { Product } from "../../services/models/product/product-model";
	import { permissionService } from "../../services/permissions/permission-service";
    const permissionsObservable = permissionService.permissionsChanges

    const dispatch = createEventDispatcher<{ delete: number }>()

    export let product: IdentifiedEntity<Product>
</script>

<div class="container default-box-shadow">
    {#if $permissionsObservable.hasAuthority(AUTHORITIES.ADMIN)}
        <div class="admin">
            <button>Editar</button>
            <button on:click={() => dispatch('delete', product.id)}>X</button>
        </div>
    {/if}
    <img class="image" alt={product.descricao}>
    <div class="description">
        <span class="name">{product.nome}</span>
        <span class="price">R$ {product.preco}</span>
    </div>
    <div><button on:click={() => cartService.addItem(product.id, 1)}>Adicionar ao carrinho</button></div>
</div>

<style lang="scss">
    .container {
        display: flex;
        flex-direction: column;
        align-items: center;
        box-shadow: inset;
        background-color: #fafafa;
        padding: var(--default-padding);
        transform: translate3d(0, 0, 0);

        .admin {
            position: absolute;
            top: 0;
            right: 0;
        }

        .image {
            width: 100%;
            height: 120px;
        }

        .description {
            padding: calc(var(--default-padding) / 2);
            display: flex;
            flex-direction: column;
            width: 100%;
            height: 150px;
            .name {
                text-overflow: ellipsis;
                overflow: hidden;
                font-weight: 200;
                font-size: .90em;
            }
            .price {
                margin-top: calc(var(--default-spacing) * 2 );
                margin-left: var(--default-spacing);
                font-weight: bold;
                font-size: 1.25em;
                color: var(--color-red);
            }
        }
    }
</style>