package thelibrary.api.biblioteca.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import thelibrary.api.biblioteca.dto.livro.DadosAtualizarLivro;
import thelibrary.api.biblioteca.dto.livro.DadosCadastrarLivro;
import thelibrary.api.biblioteca.dto.livro.DadosConsultaLivro;
import thelibrary.api.biblioteca.entity.Livro;
import thelibrary.api.biblioteca.repository.livro.LivroRepository;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastrarLivro livro){

        livroRepository.save(new Livro(livro));
    }
    @GetMapping("/logico")
    public Page<DadosConsultaLivro> listarAtivo(Pageable paginacao){
        return livroRepository.findAllByAtivoTrue(paginacao).map(DadosConsultaLivro::new);
    }
    @GetMapping("/paginacao")
    public Page<DadosConsultaLivro> listar(Pageable paginacao){
        return livroRepository.findAll(paginacao).map(DadosConsultaLivro::new);
    }
    @GetMapping("/ordenado")
    public Page<DadosConsultaLivro> buscar(@PageableDefault(sort = {"titulo"}) Pageable paginacao){
        return livroRepository.findAll(paginacao).map(DadosConsultaLivro::new);
    }


    @GetMapping
    public List<DadosConsultaLivro> listartudo(){

        return livroRepository.findAll().stream().map(DadosConsultaLivro::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarLivro dados){
        Livro livro = livroRepository.getReferenceById(dados.id());
        livro.atualizar(dados);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id){
        livroRepository.deleteById(id);
    }
    @DeleteMapping("/logico/{id}")
    @Transactional
    public void desativar(@PathVariable Long id){
        Livro livro = livroRepository.getReferenceById(id);
        livro.desativar();
    }

}
