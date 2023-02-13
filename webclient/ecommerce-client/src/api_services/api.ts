class Api {
    constructor() {}

    public entity(entity: name) {
        return new EntityService(name)
    }
}


export const api: Api = new Api()