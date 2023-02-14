import type { Result } from "../../typescript/result"
import { apiFetch } from "../fetch"
import type { IdentifiedEntity } from "./entity"
import type { Product } from "./product/product-model"

interface Sort {
    chave: string,
    direcao: 'ASC' | 'DESC'
}

interface Filter {

}

interface ListResponseSort {
    empty: boolean,
    sorted: boolean,
    unsorted: boolean
}

interface ListResponse<T> {
    content: T[],
    pageable: {
        sort: ListResponseSort,
        offset: number,
        pageSize: number,
        pageNumber: number,
        unpaged: boolean,
        page: boolean
    }
    last: boolean,
    totalElements: number,
    totalPages: number,
    size: number,
    number: number,
    sort: ListResponseSort,
    first: boolean,
    numberOfElements: number,
    empty: boolean 
}

export class EntityService<T> {
    constructor(private entity: string) {}

    public async list(filter: Filter[], sort: Sort[], page: number, listSize: number): Promise<ListResponse<IdentifiedEntity<T>>> {
        try {
            const fetched = await apiFetch(`${this.entity}/buscar`, {
                filtros: filter,
                sorts: sort,
                pagina: page,
                tamanho: listSize
            }, {
                method: 'POST'
            })
            return await fetched.json() as unknown as ListResponse<IdentifiedEntity<T>>
        } catch (error) {
            return {
                content: [],
                pageable: {
                    sort: {
                        empty: true,
                        sorted: false,
                        unsorted: true
                    },
                    offset: 0,
                    pageSize: listSize,
                    pageNumber: page,
                    unpaged: false,
                    page: true
                },
                last: true,
                totalElements: 0,
                totalPages: 1,
                size: 0,
                number: listSize,
                sort: {
                    empty: true,
                    sorted: false,
                    unsorted: true
                },
                first: true,
                numberOfElements: 0,
                empty: true
            }
        }
    }

    public async delete(id: number) {
        try {
            await apiFetch(`${this.entity}/${id}`, undefined, { method: 'DELETE' })
        } catch (error) {
            console.error(error)
        }
    }

    public async save(produto: Product): Promise<Result<void>> {
        try {
            await apiFetch(this.entity, produto, { method: 'POST' })
        } catch (error) {
            console.error(error)
            return { error: 'Falha ao tentar salvar produto!' }
        }
    }
}