import { EntityService } from "../entity-service";
import type { Product } from "./product-model";

class ProductService extends EntityService<Product> {
    constructor() {
        super('produtos')
    }
}

export const productServiceInstance = new ProductService()
