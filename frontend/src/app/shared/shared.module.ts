import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainHeaderComponent } from './main-header/main-header.component';
import { FooterComponent } from './footer/footer.component';

@NgModule({
  declarations: [MainHeaderComponent, FooterComponent],
  exports: [MainHeaderComponent, FooterComponent],
  imports: [CommonModule],
})
export class SharedModule {}
