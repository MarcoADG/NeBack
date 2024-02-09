package br.com.apineki.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.apineki.entities.Usuario;
import br.com.apineki.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	BCryptPasswordEncoder encoder;

	public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepository.findAll();
    }
    
    public Usuario buscarPorId(Integer usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
	
    public Usuario cadastrarUsuario(Usuario usuario) {
        // Verifica se o usuário existe
        Usuario usuarioExistente = usuarioRepository.findByLogin(usuario.getLogin());
        if (usuarioExistente != null) {
            throw new RuntimeException("Usuário já existe");
        }

        // Salva o usuário na base de dados
        usuario.setLogin(usuario.getLogin());
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    //metodo que tinha antes de adicionar jwt
    /*public String login(String login, String senha) {
         Verifica se o usuário existe
        Usuario usuario = usuarioRepository.findByLogin(login);
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }
         Verifica se a senha fornecida corresponde à senha armazenada
        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new RuntimeException("Credenciais inválidas");
        }

         Se as credenciais estiverem corretas, você pode gerar um token JWT ou
         simplesmente retornar uma mensagem de sucesso
        return "Login bem-sucedido";
    }*/

	public Usuario atualizarUsuario(Integer usuarioId, String novoLogin, String novaSenha) {
		// Busca o usuário pelo ID fornecido
		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		// Atualiza os campos do usuário, se os novos valores não forem nulos
		if (novoLogin != null) {
			usuario.setLogin(novoLogin);
		}
		if (novaSenha != null) {
			usuario.setSenha(novaSenha);
		}

		// Salva a atualização no banco de dados e retorna o usuário atualizado
		return usuarioRepository.save(usuario);
	}

	public void deletarUsuario(Integer usuarioId) {
		// Busca o usuário pelo ID fornecido
		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		// Exclui o usuário do banco de dados
		usuarioRepository.delete(usuario);
	}

}