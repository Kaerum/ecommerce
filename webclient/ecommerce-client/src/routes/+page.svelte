<script>
    import NavBar from '../components/navbar.svelte'
	import ProductList from '../components/products/product-list.svelte';
</script>

<div class="container">
    <NavBar></NavBar>
    <ProductList></ProductList>
</div>

<style lang="scss">
    .container {
        padding: var(--default-padding);
        display: flex;
        flex-direction: column;
        height: 100%;
    }
</style>