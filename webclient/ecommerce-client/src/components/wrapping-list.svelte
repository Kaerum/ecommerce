<script lang="ts">
    type Item = $$Generic
    interface $$Slots {
        item: { item: Item }
        empty: {}
    }
    export let items: Item[]
</script>

<div class="component-list">
    {#if items && items.length > 0}
        <div class="table">
            {#each items as item}
                <slot name="item" {item}></slot>
            {/each}
        </div>
    {:else}
        <div class="list-container">
            <slot name="empty"></slot>
        </div>
    {/if}
</div>



<style lang="scss">
    .component-list {
        width: 100%;
        height: 100%;
    }

    .table {
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(250px, auto));
        gap: var(--default-gap);
    }

    .list-container {
        display: flex;
        height: 100%;
        justify-content: center;
        align-items: center;
    }
</style>