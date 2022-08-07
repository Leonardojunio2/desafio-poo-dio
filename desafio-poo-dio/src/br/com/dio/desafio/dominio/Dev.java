package br.com.dio.desafio.dominio;

import java.util.*;

public class Dev {
    private String nome;
    //Conteudo: Se não tivesse criado conteudo como herança para curso em mentoria, não poderia adicionar cursos e mentorias no mesmo parametro
    //Apesar de poder instanciar com LinkedHashSet<Conteudo>, é mais adequado com Set<Conteudo> para evitar acoplamento
    private Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
    private Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();

    public void inscreverBootcamp(Bootcamp bootcamp){
        this.conteudosInscritos.addAll(bootcamp.getConteudos());
        bootcamp.getDevsInscritos().add(this);
    }

    public void progredir() {
    	//Aqui esta pegando por ordem, (acho que) ideal seria indicar qual conteudo foi finalizado
    	//Pontos a pesquisar: Optional e stream
        Optional<Conteudo> conteudo = this.conteudosInscritos.stream().findFirst();
        
        if(conteudo.isPresent()) { //Verifica se possui valor
        	//Apesar de conteudo ser um Optional, quando captura com get, ele vem com a entidade certa
            this.conteudosConcluidos.add(conteudo.get());
            this.conteudosInscritos.remove(conteudo.get());
        } else {
        	//Seria interessante informar se ja foi finalizado o bootcamp
            System.err.println("Você não está matriculado em nenhum conteúdo!");
        }
    }

    public double calcularTotalXp() {
        Iterator<Conteudo> iterator = this.conteudosConcluidos.iterator();
        double soma = 0;
        
        while(iterator.hasNext()){
//            double next = iterator.next().calcularXp();
            soma += iterator.next().calcularXp();
        }
        
        return soma;

        /*return this.conteudosConcluidos
                .stream()
                .mapToDouble(Conteudo::calcularXp)
                .sum();*/
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Conteudo> getConteudosInscritos() {
        return conteudosInscritos;
    }

    public void setConteudosInscritos(Set<Conteudo> conteudosInscritos) {
        this.conteudosInscritos = conteudosInscritos;
    }

    public Set<Conteudo> getConteudosConcluidos() {
        return conteudosConcluidos;
    }

    public void setConteudosConcluidos(Set<Conteudo> conteudosConcluidos) {
        this.conteudosConcluidos = conteudosConcluidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dev dev = (Dev) o;
        return Objects.equals(nome, dev.nome) && Objects.equals(conteudosInscritos, dev.conteudosInscritos) && Objects.equals(conteudosConcluidos, dev.conteudosConcluidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, conteudosInscritos, conteudosConcluidos);
    }
}
