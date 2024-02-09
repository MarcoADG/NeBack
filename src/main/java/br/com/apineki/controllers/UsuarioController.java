package br.com.apineki.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apineki.DTO.UsuarioRequestDTO;
import br.com.apineki.DTO.UsuarioResponseDTO;
import br.com.apineki.entities.Usuario;
import br.com.apineki.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
    private UsuarioService usuarioService;

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable Integer id) throws Exception {
		Usuario usuario = usuarioService.buscarPorId(id);
		if(usuario != null) {
			UsuarioResponseDTO usuarioDTO = convertToResponseDTO(usuario);
			return ResponseEntity.ok(usuarioDTO);
		}else {
			throw new Exception ("Usuario não encontrado por id:" + id);
		}
	    
	}

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodosUsuarios() throws Exception {
    	List<Usuario> usuario = usuarioService.buscarTodosUsuarios();
    	
    	List<UsuarioResponseDTO> usuarioDTO = usuario.stream().map(this::convertToResponseDTO)
    			.collect(Collectors.toList());
        if(!usuarioDTO.isEmpty()) {
        	return ResponseEntity.ok(usuarioDTO);
        } else {
        	throw new Exception();
        }
    }
	
    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        try {
            usuarioService.cadastrarUsuario(usuario);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable Integer usuarioId, @RequestBody UsuarioRequestDTO requestDTO) {
        try {
            usuarioService.atualizarUsuario(usuarioId, requestDTO.getLogin(), requestDTO.getSenha());
            return ResponseEntity.ok("Usuário atualizado com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Integer usuarioId) {
        try {
            usuarioService.deletarUsuario(usuarioId);
            return ResponseEntity.ok("Usuário deletado com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    private UsuarioResponseDTO convertToResponseDTO(Usuario usuario) {
    	return new UsuarioResponseDTO(usuario.getId(), usuario.getLogin(), usuario.getSenha());
    }

}
