import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-confirmation-dialog',
  templateUrl: './confirmation-dialog.component.html',
  styleUrls: ['./confirmation-dialog.component.css']
})
export class ConfirmationDialogComponent {
  @Input() message: string = 'Are you sure you want to delete this item?';
  @Output() confirmed = new EventEmitter<boolean>();

  cancel(): void {
    this.confirmed.emit(false);
  }

  confirm(): void {
    this.confirmed.emit(true);
  }
}
