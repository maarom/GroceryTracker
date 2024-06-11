import { Component, OnInit } from '@angular/core';
import { GroceryService } from './grocery.service';
import { Item } from './item';
import { NgForm } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { ConfirmationDialogService } from './confirmation-dialog.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  public items: Item[] = [];
  public filteredItems: Item[] = [];
  public editItem: Item | null = null;

  constructor(
    private groceryService: GroceryService,
    private confirmationDialogService: ConfirmationDialogService
  ) { }

  ngOnInit(): void {
    this.getItems();
  }

  public getItems(): void {
    this.groceryService.getItems().subscribe(
      (response: Item[]) => {
        this.items = response;
        this.filteredItems = response;
        console.log(this.items);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onSortChange(event: Event): void {
    const sortField = (event.target as HTMLSelectElement).value;
    this.sortItems(sortField);
  }

  private sortItems(field: string): void {
    if (this.items) {
      if (field === 'name-asc') {
        this.filteredItems.sort((a, b) => a.name.localeCompare(b.name));
      } else if (field === 'name-desc') {
        this.filteredItems.sort((a, b) => b.name.localeCompare(a.name));
      } else if (field === 'price-asc') {
        this.filteredItems.sort((a, b) => a.price - b.price);
      } else if (field === 'price-desc') {
        this.filteredItems.sort((a, b) => b.price - a.price);
      } else if (field === 'quantity-asc') {
        this.filteredItems.sort((a, b) => a.quantity - b.quantity);
      } else if (field === 'quantity-desc') {
        this.filteredItems.sort((a, b) => b.quantity - a.quantity);
      }
    }
  }

  public onSearch(event: Event): void {
    const searchTerm = (event.target as HTMLInputElement).value.toLowerCase();
    this.filterItems(searchTerm);
  }

  private filterItems(searchTerm: string): void {
    if (!searchTerm) {
      this.filteredItems = [...this.items];
      return;
    }

    this.filteredItems = this.items.filter(item =>
      item.name.toLowerCase().includes(searchTerm) || item.id.toString().startsWith(searchTerm)
    );
  }

  public onAddItem(addForm: NgForm): void {
    if (!addForm.valid || addForm.value.price < 0) {
      alert("Please fill in all required fields and ensure price is not below 0.");
      return;
    }

    document.getElementById('add-item-form')?.click();
    this.groceryService.addItem(addForm.value).subscribe(
      (response: Item) => {
        console.log(response);
        this.getItems();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onEditItem(item: Item): void {
    this.editItem = item;
  }

  public onUpdateItem(editForm: NgForm): void {
    if (!editForm.valid || editForm.value.price < 0) {
      alert("Please fill in all required fields and ensure price is not below 0.");
      return;
    }

    if (this.editItem) {
      this.groceryService.updateItem(this.editItem.id, editForm.value).subscribe(
        (response: Item) => {
          console.log(response);
          this.getItems();
          this.editItem = null;
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }

  public onDeleteItem(itemId: number): void {
    this.confirmationDialogService.confirm('Are you sure you want to delete this item?').subscribe(confirmed => {
      if (confirmed) {
        this.groceryService.deleteItem(itemId).subscribe(
          (response: void) => {
            this.getItems();
          },
          (error: HttpErrorResponse) => {
            alert(error.message);
          }
        );
      }
    });
  }

  public cancelEdit(): void {
    this.editItem = null;
  }
}
