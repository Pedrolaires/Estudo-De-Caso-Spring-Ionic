package br.pedro.cursomc.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	Cancelado(3, "Cancelado");
	
	private int codigo;
	private String descricao;
	
	private EstadoPagamento(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) throws Exception{
		if(cod == null)
			return null;
		for(EstadoPagamento x : EstadoPagamento.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new Exception("Id inválido!");
		
	}
	
}
