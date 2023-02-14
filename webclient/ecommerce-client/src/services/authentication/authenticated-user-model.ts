export type Authorities = 'USER' | 'ADMIN' | 'CNPJ'

export const AUTHORITIES: { [key: string]: Authorities } = {
    USER: 'USER',
    ADMIN: 'ADMIN',
    CNPJ: 'CNPJ'
}

export interface Authority {
    authority: Authorities
}

export interface AuthenticatedUser {
    nome: string,
    authorities: Authority[],
    token: string
}