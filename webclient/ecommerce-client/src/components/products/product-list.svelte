<script lang="ts">
	import WrappingList from "../wrapping-list.svelte";
	import type { Product } from "./product";
	import ProductDisplay from "./product-display.svelte";
    function createProduct() { return { categoria: '', nome: 'Macaxeiraasdfdfasasdfasdfdf linuxasdfasdasdfasdfasdff windowsasdfasdfasdfasdfasfasdf pcasdfasdassdfasdhjfsadjkhfgasdjfhasdgfkjasdhfgkasjdhfgasdkjhfgasdkjfhasjkdfhadsjkfhasdlkfjahsdlfkjasdhflakjsdfhlk 2005', peso: 1, preco: 1000, descricao: '', marca: '' } }
    const items: Product[] = new Array(100).fill(createProduct())
</script>

<WrappingList {items}>
    <span slot="item" let:item>
        <ProductDisplay product={item}/>
    </span>
    <div slot="empty">
        Nenhum produto foi encontrado!
    </div>
</WrappingList>
<div class="page-control">
    
</div>

<style lang="scss">
</style>