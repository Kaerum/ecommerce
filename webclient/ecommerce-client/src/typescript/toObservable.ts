import type { Writable, Readable, Unsubscriber } from 'svelte/store';
import { Observable } from 'rxjs';

export function toObservable<T>(svelteStore: Writable<T> | Readable<T>): Observable<T> {
  let unsub: Unsubscriber

  const obs = new Observable<T>((subscriber) => {
    unsub = svelteStore.subscribe((val) => {
      subscriber.next(val);
    })

    return () => {
      unsub();
    }
  })

  return obs;
}