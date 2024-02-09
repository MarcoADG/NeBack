package br.com.apineki.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apineki.DTO.AssociacaoSkillRequestDTO;
import br.com.apineki.DTO.UsuarioSkillDTO;
import br.com.apineki.entities.AssociacaoSkill;
import br.com.apineki.entities.Skills;
import br.com.apineki.entities.Usuario;
import br.com.apineki.repositories.AssociacaoSkillRepository;
import br.com.apineki.repositories.SkillsRepository;
import br.com.apineki.repositories.UsuarioRepository;


@Service
public class AssociacaoSkillService {

	@Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SkillsRepository skillsRepository;

    @Autowired
    private AssociacaoSkillRepository associacaoSkillRepository;
    
    public List<UsuarioSkillDTO> listarSkillsDoUsuario(Integer usuarioId) {
        List<UsuarioSkillDTO> usuarioSkills = new ArrayList<>();

        // Busca todas as associações de skill do usuário pelo ID
        List<AssociacaoSkill> associacoes = associacaoSkillRepository.findByUsuarioId(usuarioId);

        // Converte as associações de skill para UsuarioSkillDTO
        for (AssociacaoSkill associacao : associacoes) {
            UsuarioSkillDTO usuarioSkillDTO = new UsuarioSkillDTO();
            usuarioSkillDTO.setId(associacao.getId());
            usuarioSkillDTO.setSkillId(associacao.getSkills().getId());
            usuarioSkillDTO.setImagem(associacao.getSkills().getImagem());
            usuarioSkillDTO.setNome(associacao.getSkills().getNome());
            usuarioSkillDTO.setDescricao(associacao.getSkills().getDescricao());
            usuarioSkillDTO.setLevel(associacao.getLevel());
            
            usuarioSkills.add(usuarioSkillDTO);
        }

        return usuarioSkills;
    }


    public void associarSkillAoUsuario(AssociacaoSkillRequestDTO requestDTO) {
        // Buscar o usuário pelo ID
        Usuario usuario = usuarioRepository.findById(requestDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Buscar a skill pelo ID
        Skills skill = skillsRepository.findById(requestDTO.getSkillId())
                .orElseThrow(() -> new RuntimeException("Skill não encontrada"));

        // Criar uma nova associação de skill
        AssociacaoSkill associacaoSkill = new AssociacaoSkill();
        associacaoSkill.setUsuario(usuario);
        associacaoSkill.setSkills(skill);
        associacaoSkill.setLevel(requestDTO.getLevel());

        // Salvar a associação no banco de dados
        associacaoSkillRepository.save(associacaoSkill);
    }
    
    public void atualizarAssociacaoSkill(Integer associacaoSkillId, Integer novoLevel) {
        // Busca a associação de skill pelo ID
        AssociacaoSkill associacaoSkill = associacaoSkillRepository.findById(associacaoSkillId)
            .orElseThrow(() -> new RuntimeException("Associação de skill não encontrada"));

        // Atualiza o nível da associação de skill
        associacaoSkill.setLevel(novoLevel);

        // Salva a atualização no banco de dados
        associacaoSkillRepository.save(associacaoSkill);
    }
    
    public void excluirAssociacaoSkill(Integer associacaoSkillId) {
        // Busca a associação de skill pelo ID
        AssociacaoSkill associacaoSkill = associacaoSkillRepository.findById(associacaoSkillId)
            .orElseThrow(() -> new RuntimeException("Associação de skill não encontrada"));

        // Exclui a associação de skill do banco de dados
        associacaoSkillRepository.delete(associacaoSkill);
    }

    public List<AssociacaoSkill> listarTodasAssociacoesSkill() {
        // Retorna todas as associações de skill no banco de dados
        return associacaoSkillRepository.findAll();
    }
    
    public AssociacaoSkill buscarAssociacaoSkillPorId(Integer associacaoSkillId) {
        // Busca a associação de skill pelo ID
        return associacaoSkillRepository.findById(associacaoSkillId)
                .orElseThrow(() -> new RuntimeException("Associação de skill não encontrada"));
    }

}