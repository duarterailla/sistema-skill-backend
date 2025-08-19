package com.neki.sistemaskill.controller;
// Controller responsável pelos endpoints de skills do usuário

import com.neki.sistemaskill.model.UserSkill;
import com.neki.sistemaskill.model.User;
import com.neki.sistemaskill.service.UserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RequestMapping("/api/user-skills")
public class UserSkillController {
    // Injeta o serviço de UserSkill

    @GetMapping("/{userId}/pdf")
    public void exportUserSkillsPdf(@PathVariable Integer userId, HttpServletResponse response) throws IOException, DocumentException {
    // Gera e exporta o PDF das skills do usuário
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=skills_usuario_" + userId + ".pdf");

        List<UserSkill> userSkills = userSkillService.listUserSkills(userId);
    // Busca as skills do usuário
        User user = null;
    // Busca o usuário pelo id
        if (!userSkills.isEmpty()) {
            // Se houver skills, pega o usuário da primeira skill
            user = userSkills.get(0).getUser();
        }
        if (user == null) {
            // Se não encontrou, busca diretamente pelo id
            // Se ainda não encontrou, retorna 404
            user = userSkillService.getUserById(userId);
        }
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("Usuário não encontrado");
            return;
        }

        Document document = new Document();
    // Cria o documento PDF
        PdfWriter.getInstance(document, response.getOutputStream());
    // Prepara o writer para o PDF
        document.open();
    // Abre o documento para edição

        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    // Formata a data/hora
        String dataHora = java.time.LocalDateTime.now().format(formatter);
    // Pega a data/hora atual

        document.add(new Paragraph("Relatório de Skills do Usuário"));
    // Adiciona título ao PDF
        document.add(new Paragraph(" " ));
        document.add(new Paragraph("Nome: " + (user.getNomeCompleto() != null ? user.getNomeCompleto() : "-")));
    // Adiciona nome do usuário
        document.add(new Paragraph("E-mail: " + user.getEmail()));
    // Adiciona e-mail do usuário
        document.add(new Paragraph("Data/Hora do download: " + dataHora));
    // Adiciona data/hora do download
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(3);
    // Cria tabela para skills
        table.addCell("Skill");
    // Cabeçalho: Skill
        table.addCell("Nível");
    // Cabeçalho: Nível
        table.addCell("Descrição");
    // Cabeçalho: Descrição

        for (UserSkill us : userSkills) {
            // Adiciona cada skill do usuário na tabela
            table.addCell(us.getSkill().getNome());
            table.addCell(String.valueOf(us.getLevel()));
            table.addCell(us.getSkill().getDescricao());
        }

        document.add(table);
    // Adiciona tabela ao PDF
        document.close();
    // Finaliza o documento PDF
    }
    @Autowired
    private UserSkillService userSkillService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserSkill>> listUserSkills(@PathVariable Integer userId) {
    // Retorna lista de skills do usuário
        return ResponseEntity.ok(userSkillService.listUserSkills(userId));
    }

    @PostMapping
    public ResponseEntity<UserSkill> associateSkill(@RequestBody Map<String, Object> body) {
    // Associa uma skill ao usuário
    Object userIdObj = body.get("userId");
    Object skillIdObj = body.get("skillId");
    Integer userId = (userIdObj instanceof Integer) ? (Integer) userIdObj : Integer.parseInt(userIdObj.toString());
    Integer skillId = (skillIdObj instanceof Integer) ? (Integer) skillIdObj : Integer.parseInt(skillIdObj.toString());
    Object levelObj = body.get("level");
    String level = (levelObj != null) ? levelObj.toString() : null;
    Object descricaoObj = body.get("descricao");
    String descricao = (descricaoObj != null) ? descricaoObj.toString() : null;
    return ResponseEntity.ok(userSkillService.associateSkill(userId, skillId, level, descricao));
    }

    @PutMapping("/{userSkillId}")
    public ResponseEntity<UserSkill> updateUserSkill(@PathVariable Integer userSkillId, @RequestBody Map<String, Object> body) {
    // Atualiza o nível de uma skill do usuário
        Object levelObj = body.get("level");
        String level = (levelObj != null) ? levelObj.toString() : null;
        return ResponseEntity.ok(userSkillService.updateUserSkill(userSkillId, level));
    }

    @DeleteMapping("/{userSkillId}")
    public ResponseEntity<?> deleteUserSkill(@PathVariable Integer userSkillId) {
    // Remove uma skill do usuário
        userSkillService.deleteUserSkill(userSkillId);
        return ResponseEntity.ok(Map.of("message", "Associação removida com sucesso"));
    }
}
