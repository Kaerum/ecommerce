import type { Nullable } from "../typescript/nullable"
import type { AuthenticatedUser } from "./authentication/authenticated-user-model"
import { authenticationService } from "./authentication/authentication-service"

let authUser: Nullable<AuthenticatedUser> = null
authenticationService.authUserChanges.subscribe(user => {
    authUser = user
})

export const apiFetch = function (input: RequestInfo, body?: Record<string, any> | string, init?: RequestInit) {
    const baseURL = 'http://localhost:8080/'
    const baseRequest: { body?: string, headers: Record<string, string> } = {
        headers: {
            'Access-Control-Allow-Origin': 'http://localhost:8080'
        }
    }
    if (authUser != null) {
        baseRequest.headers['Authorization'] = `Bearer ${authUser.token}` 
    }
    let content: string | undefined = undefined
    if (body) {
        if (typeof body !== 'string') {
            baseRequest.headers['Content-Type'] = 'application/json'
            content = JSON.stringify(body)
        } else {
            content = body
        }
    }
    baseRequest.body = content
    return fetch(`${baseURL}${input}`, Object.assign({}, baseRequest,init))
}