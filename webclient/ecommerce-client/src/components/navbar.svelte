<script lang="ts">

</script>

<div class="navbar-container">
    <div class="left-container">
        <span>Ecommerce</span>
    </div>
    <div class="middle-container">
        <input type='search'>
    </div>
    <div class="right-container">
        <button>Login</button>
        <button>Cadastro</button>
    </div>
</div>


<style lang="scss">
    .navbar-container {
        display: flex;
        height: 40px;
        justify-content: center;
        align-items: center;
        padding: var(--default-padding);

        .left-container {
            display: flex;
            flex: 0 0 25%;
            align-items: center;
        }

        .middle-container {
            display: flex;
            align-items: center;
            justify-content: center;
            flex: 0 0 50%;
            input {
                height: 20px;
                flex-basis: 100%;
            }
        }

        .right-container {
            display: flex;
            align-items: center;
            justify-content: center;
            flex: 0 0 25%;
            button {
                margin: var(--default-spacing)
            }
        }
    }
</style>