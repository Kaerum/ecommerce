<script lang="ts">
	import { openModal } from "svelte-modals";
	import RegisterModal from "./register-modal.svelte";


    function handleOpen() {
        openModal(RegisterModal)
    }
</script>

<button on:click={handleOpen}>Registrar</button>