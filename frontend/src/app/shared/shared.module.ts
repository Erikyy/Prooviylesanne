import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainHeaderComponent } from './main-header/main-header.component';
import { FooterComponent } from './footer/footer.component';
import { FooterItemComponent } from './footer/footer-item/footer-item.component';
import { HeroComponent } from './hero/hero.component';
import { ButtonComponent } from './button/button.component';
import { InputComponent } from './input/input.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MiniBannerComponent } from './mini-banner/mini-banner.component';

@NgModule({
  declarations: [
    MainHeaderComponent,
    FooterComponent,
    FooterItemComponent,
    HeroComponent,
    ButtonComponent,
    InputComponent,
    MiniBannerComponent,
  ],
  exports: [
    MainHeaderComponent,
    FooterComponent,
    HeroComponent,
    ButtonComponent,
    InputComponent,
    MiniBannerComponent,
  ],
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
})
export class SharedModule {}
