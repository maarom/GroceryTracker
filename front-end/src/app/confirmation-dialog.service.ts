import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfirmationDialogService {
  private confirmSubject = new Subject<boolean>();

  constructor() { }

  confirm(message: string): Observable<boolean> {
    return new Observable<boolean>(observer => {
      const confirmation = window.confirm(message);
      observer.next(confirmation);
      observer.complete();
    });
  }
}
