import { ProdutoDTO } from "./../../models/produto.dto";
import { Cart } from "./../../models/cart";
import { Injectable } from "@angular/core";
import { StorageService } from "./../storage.service";

@Injectable()
export class CartService {
  constructor(public storageService: StorageService) {}

  createOrClearCart(): Cart {
    let cart: Cart = { itens: [] };
    this.storageService.setCart(cart);

    return cart;
  }

  getCart(): Cart {
    let cart: Cart = this.storageService.getCart();
    if (cart == null) {
      cart = this.createOrClearCart();
    }

    return cart;
  }

  addProduto(produto: ProdutoDTO): Cart {
    let cart = this.getCart();
    let position = cart.itens.findIndex((p) => p.produto.id == produto.id);
    if (position == -1) {
      cart.itens.push({ quantidade: 1, produto });
    }

    this.storageService.setCart(cart);
    return cart;
  }
}
