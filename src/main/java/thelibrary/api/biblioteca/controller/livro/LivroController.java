package thelibrary.api.biblioteca.controller.livro;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thelibrary.api.biblioteca.dto.livro.LivroAtualizacaoDto;
import thelibrary.api.biblioteca.dto.livro.LivroCadastroDto;
import thelibrary.api.biblioteca.dto.livro.LivroConsultaDto;
import thelibrary.api.biblioteca.entity.Livro;
import thelibrary.api.biblioteca.repository.livro.LivroRepository;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping
    public void cadastrar(@RequestBody LivroCadastroDto livro){

        livroRepository.save(new Livro(livro));
    }
    @GetMapping("/logico")
    public ResponseEntity<Page<LivroConsultaDto>> listarAtivo(Pageable paginacao){

        var response =livroRepository.findAllByAtivoTrue(paginacao).map(LivroConsultaDto::new);
        if (response.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/paginacao")
    public Page<LivroConsultaDto> listar(Pageable paginacao){
        return livroRepository.findAll(paginacao).map(LivroConsultaDto::new);
    }
    @GetMapping("/ordenado")
    public Page<LivroConsultaDto> buscar(@PageableDefault(sort = {"titulo"}) Pageable paginacao){
        return livroRepository.findAll(paginacao).map(LivroConsultaDto::new);
    }


    @GetMapping
    public List<LivroConsultaDto> listartudo(){

        return livroRepository.findAll().stream().map(LivroConsultaDto::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid LivroAtualizacaoDto dados){
        Livro livro = livroRepository.getReferenceById(dados.id());
        livro.atualizar(dados);
        livroRepository.save(livro);

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
