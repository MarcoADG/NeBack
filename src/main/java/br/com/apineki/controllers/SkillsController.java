package br.com.apineki.controllers;

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

import br.com.apineki.DTO.SkillsRequestDTO;
import br.com.apineki.entities.Skills;
import br.com.apineki.services.SkillsService;


@RestController
@RequestMapping("/skills")
public class SkillsController {

	@Autowired
    private SkillsService skillsService;

	@GetMapping("/{skillId}")
    public ResponseEntity<?> buscarSkillPorId(@PathVariable Integer skillId) {
        try {
            Skills skill = skillsService.buscarSkillPorId(skillId);
            return ResponseEntity.ok(skill);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listarTodasSkills() {
        try {
            return ResponseEntity.ok(skillsService.listarTodasSkills());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
	
    @PostMapping("/criar")
    public ResponseEntity<?> criarSkill(@RequestBody SkillsRequestDTO requestDTO) {
        try {
            skillsService.criarSkill(requestDTO.getImagem(), requestDTO.getNome(), requestDTO.getDescricao());
            return ResponseEntity.status(HttpStatus.CREATED).body("Skill criada com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{skillId}")
    public ResponseEntity<?> atualizarSkill(@PathVariable Integer skillId, @RequestBody SkillsRequestDTO requestDTO) {
        try {
            skillsService.atualizarSkill(skillId, requestDTO.getImagem(), requestDTO.getNome(), requestDTO.getDescricao());
            return ResponseEntity.ok("Skill atualizada com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<?> deletarSkill(@PathVariable Integer skillId) {
        try {
            skillsService.deletarSkill(skillId);
            return ResponseEntity.ok("Skill deletada com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
