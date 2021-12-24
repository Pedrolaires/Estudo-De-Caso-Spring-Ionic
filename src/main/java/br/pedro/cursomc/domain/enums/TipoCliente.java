package br.pedro.cursomc.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1,"Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Juridica");
	
	private int codigo;
	private String descricao;
	
	private TipoCliente(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) throws Exception{
		if(cod == null)
			return null;
		for(TipoCliente x : TipoCliente.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new Exception("Id inválido!");
		
	}
}
