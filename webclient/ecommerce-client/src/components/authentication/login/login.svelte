<script lang="ts">
	import { openModal } from "svelte-modals";
	import LoginModal from "./login-modal.svelte";


    function handleOpen() {
        openModal(LoginModal)
    }
</script>

<button on:click={handleOpen}>Login</button>