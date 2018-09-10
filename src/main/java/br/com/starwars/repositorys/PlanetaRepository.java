package br.com.starwars.repositorys;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import br.com.codecs.PlanetaCodec;
import br.com.starwars.models.Planeta;

@Repository
public class PlanetaRepository {
	private MongoClient cliente;
	private MongoDatabase bancoDeDados;
	private void criarConexao() {
		Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
		PlanetaCodec planetaCodec = new PlanetaCodec(codec);
		
		
		CodecRegistry registro = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), 
				CodecRegistries.fromCodecs(planetaCodec));
		MongoClientOptions opcoes =MongoClientOptions.builder().codecRegistry(registro).build();
		
		this.cliente = new MongoClient("localhost:27017", opcoes);
		this.bancoDeDados = cliente.getDatabase("test");
		
	}
	
	public void salvar(Planeta planeta) {
		
		criarConexao();
		
	    MongoCollection<Planeta>planetas = this.bancoDeDados.getCollection("planetas", Planeta.class);
	    planetas.insertOne(planeta);
	   cliente.close();
	}	
	public List<Planeta> obterTodosPlanetas(){
		criarConexao();
	    MongoCollection<Planeta>planetas = this.bancoDeDados.getCollection("planetas", Planeta.class);
	    
	    MongoCursor<Planeta> resultado = planetas.find().iterator();
	    
	    List<Planeta> planetaEncontrados = new ArrayList<Planeta>();
	    
	    while(resultado.hasNext()){
	    	Planeta planeta = resultado.next();
	    	planetaEncontrados.add(planeta);
	    }
	    cliente.close();
		
		return planetaEncontrados;
	}

	public Planeta obterPlanetaPor(String id) {
		criarConexao();
		MongoCollection<Planeta> planetas = this.bancoDeDados.getCollection("planetas", Planeta.class);
		Planeta planeta = planetas.find(Filters.eq("_id", new ObjectId(id))).first();
		return planeta;
	}

	public List<Planeta> pesquisarPor(String nome) {
		criarConexao();
		MongoCollection<Planeta> planetaColletion = this.bancoDeDados.getCollection("planetas", Planeta.class);
		MongoCursor<Planeta> resultados = planetaColletion.find(Filters.eq("nome", nome), Planeta.class).iterator();
		List<Planeta> planetas = new ArrayList<Planeta>();
		while(resultados.hasNext()){
			planetas.add(resultados.next());
		}
		this.cliente.close();
		
		return planetas;
	}

}
