<script lang="ts">
	import type { Product } from "./product";


    export let product: Product
</script>

<div class="container default-box-shadow">
    <div class="admin">
        <button>Editar</button>
        <button>X</button>
    </div>
    <img class="image" alt={product.descricao}>
    <div class="description">
        <span class="name">{product.nome}</span>
        <span class="price">R$ {product.preco}</span>
    </div>
    <div><button>Comprar</button></div>
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