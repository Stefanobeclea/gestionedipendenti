package it.prova.gestionedipendenti.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "progetto")
public class Progetto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cliente")
	private String cliente;
	@Column(name = "durataInMesi")
	private int durataInMesi;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "progetti")
	Set<Impiegato> impiegati = new HashSet<Impiegato>();

	public Progetto() {
		super();
	}

	public Progetto(String nome, String cliente, int durataInMesi) {
		super();
		this.nome = nome;
		this.cliente = cliente;
		this.durataInMesi = durataInMesi;
	}

	public Progetto(String nome, String cliente, int durataInMesi, Set<Impiegato> impiegati) {
		super();
		this.nome = nome;
		this.cliente = cliente;
		this.durataInMesi = durataInMesi;
		this.impiegati = impiegati;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public int getDurataInMesi() {
		return durataInMesi;
	}

	public void setDurataInMesi(int durataInMesi) {
		this.durataInMesi = durataInMesi;
	}

	public Set<Impiegato> getImpiegati() {
		return impiegati;
	}

	public void setImpiegati(Set<Impiegato> impiegati) {
		this.impiegati = impiegati;
	}

	@Override
	public String toString() {
		return "Progetto [id=" + id + ", nome=" + nome + ", cliente=" + cliente + ", durataInMesi=" + durataInMesi
				+ "]";
	}

}