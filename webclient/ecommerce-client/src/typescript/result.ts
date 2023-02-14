type Success<T> = T extends void ? undefined : {
    value: T
}

interface Error {
    error: string
}

export type Result<T> = Success<T> | Error

export function isError<T>(result: Result<T>): result is Error {
    return typeof result === 'object' && 'error' in result
}
