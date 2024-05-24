import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Item } from './item';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GroceryService {
  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  // Get list of items
  public  getItems(): Observable<Item[]>{
    return this.http.
    get<Item []>(`${this.apiServerUrl}/grocery/items`);
  }

  // Adding new item
  public addItem(item: Item): Observable<Item> {
    return this.http.post<Item>(`${this.apiServerUrl}/grocery/items`, item);
  }

  // Update item
  // public updateItem(item: Item): Observable<Item>{
  //   return this.http.put<Item>(`${this.apiServerUrl}/grocery/items`, item);
  // }
  public updateItem(itemId: number, item: Item): Observable<Item> {
    return this.http.put<Item>(`${this.apiServerUrl}/grocery/items/${itemId}`, item);
  }


  // Delete items
  public deleteItem(itemId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/grocery/items/${itemId}`);
  }
  
}
