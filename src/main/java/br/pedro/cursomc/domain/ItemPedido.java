package br.pedro.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.MapsId;

@Entity
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId // Id embutido em um tipo auxiliar

	private ItemPedidoPK id = new ItemPedidoPK(); // atributo composto
	
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	public ItemPedido() {
		
	}
	// associações feitas no ItemPedidoPK
	
	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) { // troquei o itemPedidoPK por "pedido" e "produto" pois "itemPedidoPK" é do um objeto a parte
		super();
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public Pedido getPedido() {
		return id.getPedido();
	}
	
	public Produto getProduto() {
		return id.getProduto();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id);
	}

	
	
	
		
}
