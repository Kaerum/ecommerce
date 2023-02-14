const EntityMarkerBrand = Symbol()

export interface EntityMarker {
    [EntityMarkerBrand]?: any
}

export type IdentifiedEntity<T> = T extends EntityMarker ? T & { id: number } : T