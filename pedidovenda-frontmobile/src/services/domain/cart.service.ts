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

  removeProduto(produto: ProdutoDTO): Cart {
    let cart = this.getCart();
    let position = cart.itens.findIndex((p) => p.produto.id == produto.id);
    if (position != -1) {
      cart.itens.splice(position, 1);
    }

    this.storageService.setCart(cart);
    return cart;
  }

  increseQuantity(produto: ProdutoDTO): Cart {
    let cart = this.getCart();
    let position = cart.itens.findIndex((p) => p.produto.id == produto.id);
    if (position != -1) {
      cart.itens[position].quantidade++;
    }

    this.storageService.setCart(cart);
    return cart;
  }

  decreaseQuantity(produto: ProdutoDTO): Cart {
    let cart = this.getCart();
    let position = cart.itens.findIndex((p) => p.produto.id == produto.id);
    if (position != -1) {
      cart.itens[position].quantidade--;
      if (cart.itens[position].quantidade < 1) {
        cart = this.removeProduto(produto);
      }
    }

    this.storageService.setCart(cart);
    return cart;
  }

  total(): number {
    let cart = this.getCart();
    let soma = 0;

    for (var i = 0; i < cart.itens.length; i++) {
      soma += cart.itens[i].produto.preco * cart.itens[i].quantidade;
    }

    return soma;
  }
}
