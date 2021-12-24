package br.pedro.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.pedro.cursomc.domain.Categoria;
import br.pedro.cursomc.domain.Cidade;
import br.pedro.cursomc.domain.Cliente;
import br.pedro.cursomc.domain.Endereco;
import br.pedro.cursomc.domain.Estado;
import br.pedro.cursomc.domain.Produto;
import br.pedro.cursomc.domain.enums.TipoCliente;
import br.pedro.cursomc.repositories.CategoriaRepository;
import br.pedro.cursomc.repositories.CidadeRepository;
import br.pedro.cursomc.repositories.ClienteRepository;
import br.pedro.cursomc.repositories.EnderecoRepository;
import br.pedro.cursomc.repositories.EstadoRepository;
import br.pedro.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse Jeimer",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		Cidade c1 = new Cidade(null,"Uberlandia",est1);
		Cidade c2 = new Cidade(null,"São Paulo", est2);
		Cidade c3 = new Cidade(null,"Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3)); // Adicionar à lista de cidades da classe estado.
		
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3)); //Salvando no banco
		
		Cliente cli1 = new Cliente(null, "mario", "mario@gmail.com", "123.234.321-88", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("(31) 9 97334179", "(31) 9 312341241"));
		
		Endereco e1 = new Endereco(null, "Rua coqueiro", "231B", "", "Santa Efigenia", "312312-231",cli1 ,c1);
		Endereco e2 = new Endereco(null,"Avenida Matos","105","Sala 800","Centro","3125123",cli1,c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		
	}
}