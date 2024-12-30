package thelibrary.api.biblioteca.controller.writer;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import thelibrary.api.biblioteca.dto.writer.WriterCreateRequestDto;
import thelibrary.api.biblioteca.dto.writer.WriterRequestDto;
import thelibrary.api.biblioteca.dto.writer.WriterResponseDto;
import thelibrary.api.biblioteca.dto.writer.WriterWithBooksDto;
import thelibrary.api.biblioteca.entity.Writer;
import thelibrary.api.biblioteca.service.writer.WriterService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/writers")
public class WriterController {

    @Autowired
    private WriterService service;

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody @Valid WriterCreateRequestDto request , UriComponentsBuilder uriBuilder
    ) {

        Writer savedWriter = service.save(request);

        var uri = uriBuilder
                .path("/api/v1/writers/{id}")
                .buildAndExpand(
                        savedWriter.getId())
                .toUri();


        return ResponseEntity.created(uri).body(savedWriter);
    }
    @GetMapping
    public ResponseEntity<List<Writer>> listar(){

        List<Writer> resposta=service.findAll();
        if(resposta.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("writersWithBooks/{id}")
    public ResponseEntity<WriterWithBooksDto> findWritersWithBooks(@PathVariable Integer id){
        WriterWithBooksDto writer = service.findWritersWithBooks(id);
        return ResponseEntity.ok(writer);
    }
    @GetMapping("/{id}")
    public ResponseEntity<WriterResponseDto> detalhar(@PathVariable Integer id){
        WriterResponseDto writer = service.findById(id);
        return ResponseEntity.ok(writer);
    }


    @GetMapping("/logico")
    public ResponseEntity<Page<WriterResponseDto>> consultaLogica(Pageable paginacao){
        Page<WriterResponseDto> resposta = service.findAllByActiveTrue(paginacao);
        return ResponseEntity.ok(resposta);
    }
    @GetMapping("/paginacao") // Se achar nescessário pode realizar mais consultas relativas a isto no site do Spring Data JPA procurando por Pageable.
    public ResponseEntity<Page<WriterResponseDto>> listar(Pageable paginacao){
        Page<WriterResponseDto> resposta = service.findAllPageable(paginacao);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/ordenado")
    public ResponseEntity<Page<WriterResponseDto>> buscar( @PageableDefault(sort = {"nome"}) Pageable pagina){
        Page<WriterResponseDto> resposta = service.findAllPageable(pagina);
        return ResponseEntity.ok(resposta);
    }
    @PutMapping
    @Transactional
    public ResponseEntity<Writer> atualizar(@RequestBody @Valid WriterRequestDto dados){
        Writer writer = service.getReferenceById(dados.id());
        if (writer == null){
            return ResponseEntity.notFound().build();
        }
        Writer responsedata = service.update(dados);
        return ResponseEntity.ok(responsedata);

    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Integer id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/logico/{id}")
    @Transactional
    public ResponseEntity<Writer> desativar(@PathVariable Integer id){
        Writer writer = service.getReferenceById(id);
        Writer response=service.desativar(writer.getId());
        return ResponseEntity.status(204).body(response);
    }
}
