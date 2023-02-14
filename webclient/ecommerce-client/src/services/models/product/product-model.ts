import type { EntityMarker } from "../entity";

export interface Product extends EntityMarker {
    nome: string,
    descricao: string,
    categoria: string,
    marca: string,
    preco: number,
    peso: number
}