import { ReplaySubject, startWith } from 'rxjs'
import type { Nullable } from '../../typescript/nullable'
import type { Authorities, AuthenticatedUser, Authority } from '../authentication/authenticated-user-model'
import { authenticationService } from '../authentication/authentication-service'

class PermissionService {
    private permissionsSubject = new ReplaySubject<Permissions>(1)

    constructor() {
        authenticationService.authUserChanges.subscribe(this.updatePermissions.bind(this))
    }

    private updatePermissions(authUser: Nullable<AuthenticatedUser>) {
        this.permissionsSubject.next(new Permissions(authUser))
    }

    public get permissionsChanges() {
        return this.permissionsSubject.pipe(startWith(new Permissions({ authorities: [] })))
    }
}

class Permissions {
    private permissions = new Set<Authorities>()

    constructor(authUser: Nullable<{ authorities: Authority[] }>) {
        this.permissions = new Set()
        if (authUser != null) {
            this.permissions = new Set(authUser.authorities.map(a => a.authority))
        }
    }

    public hasAuthority(authority: Authorities) {
        return this.permissions.has(authority)
    }
}

export const permissionService = new PermissionService()