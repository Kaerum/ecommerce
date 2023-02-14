import { persisted } from 'svelte-local-storage-store'

interface CartProduct {
    id: number,
    quantity: number
}

interface Cart {
    products: CartProduct[]
}

class CartService {
    private cartPersistence = persisted<Cart>('cart', { products: [] })

    private getOrCreateCartProduct(cart: Cart, id: number): { product: CartProduct, index: number } {
        let index = cart.products.findIndex(p => p.id === id)
        let product: CartProduct
        if (index === -1) {
            product = { id, quantity: 0 }
            index = cart.products.length
            cart.products.push(product)
        } else {
            product = cart.products[index]
        }
        return { product, index }
    }

    public addItem(id: number, quantity: number) {
        this.cartPersistence.update((cart => {
            const { product, index } = this.getOrCreateCartProduct(cart, id)
            product.quantity += quantity
            return cart
        }))
    }

    public subtractItem(id: number, quantity: number) {
        this.cartPersistence.update((cart => {
            const { product, index } = this.getOrCreateCartProduct(cart, id)
            product.quantity -= quantity
            if (product.quantity <= 0) {
                delete cart.products[index]
            }
            return cart
        }))
    }

    public get onCartChanges() {
        return { subscribe: this.cartPersistence.subscribe }
    }
}

export const cartService = new CartService()