import { shareReplay, startWith } from 'rxjs'
import { persisted } from 'svelte-local-storage-store'
import type { Nullable } from '../../typescript/nullable'
import type { Result } from '../../typescript/result'
import { toObservable } from '../../typescript/toObservable'
import type { AuthenticatedUser } from './authenticated-user-model'
import type { Authority } from './authenticated-user-model'

class AuthenticationService {
    private authUserPersistence = persisted<Nullable<AuthenticatedUser>>('authUser', null)
    private authUserSubject = toObservable(this.authUserPersistence)
        .pipe(shareReplay(1))

    constructor() {}

    public async login(nome: string, senha: string): Promise<Result<void>> {
        const result = await fetch('http://localhost:8080/autenticacao/logar', {
            method: 'POST',
            body: JSON.stringify({
                nome,
                senha
            }),
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': 'http://localhost:8080',
            }
        })
        if (result.ok) {
            const response: { nome: string, autorizacoes: Authority[], token: string } = await result.json()
            this.authUserPersistence.set({ nome: response.nome, authorities: response.autorizacoes, token: response.token })
        } else {
            return { error: 'Falha ao tentar logar!' }
        }
    }

    public logout() {
        this.authUserPersistence.set(null)
    }

    public async register(nome: string, senha: string, autoridadeUsuario: string, isCnpj: boolean): Promise<Result<void>> {
        const result = await fetch('http://localhost:8080/autenticacao/registrar', {
            method: 'POST',
            body: JSON.stringify({
                nome,
                senha,
                autoridadeUsuario,
                isCnpj
            }),
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': 'http://localhost:8080',
            }
        })
        if (!result.ok) {
            return { error: 'Falha ao tentar logar!' }
        }
    }

    public get authUserChanges() {
        return this.authUserSubject.pipe(startWith())
    }
}

export const authenticationService = new AuthenticationService()